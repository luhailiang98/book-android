package top.luhailiang.mylibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import top.luhailiang.mylibrary.R;
import top.luhailiang.mylibrary.api.GetApi;
import top.luhailiang.mylibrary.model.BookInfo;
import top.luhailiang.mylibrary.model.BookInfoDTO;
import top.luhailiang.mylibrary.model.response.BookInfoResponse;
import top.luhailiang.mylibrary.utils.CONTENST;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiListener;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiUtil;

public class BookInfoActivity extends AppCompatActivity {

    public static final String BOOK_ID = "book_id";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_IMAGE_ID = "book_image_id";

    private WebView mWvBookIntroduction;
    private TextView mTvBookIsbn;
    private TextView mTvBookName;
    private TextView mTvBookAuthor;
    private TextView mTvBookPublish;
    private TextView mTvBookPrice;
    private TextView mTvBookShelf;
    private TextView mTvBookTypes;
    private TextView mTvBookStates;

    private BookInfoDTO mBookInfoDTO;
    private ArrayList<BookInfo> mBookInfos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinfo);
        mWvBookIntroduction = findViewById(R.id.wv_book_introduction);
        mTvBookName = findViewById(R.id.tv_book_name);
        mTvBookIsbn = findViewById(R.id.tv_book_isbn);
        mTvBookAuthor = findViewById(R.id.tv_book_author);
        mTvBookPublish = findViewById(R.id.tv_book_publish);
        mTvBookPrice = findViewById(R.id.tv_book_price);
        mTvBookShelf = findViewById(R.id.tv_book_shelf);
        mTvBookTypes = findViewById(R.id.tv_book_types);
        mTvBookStates = findViewById(R.id.tv_book_states);
        //接收intent传过来的参数
        Intent intent = getIntent();
        int bookId = intent.getIntExtra(BOOK_ID, 0);
        String bookName = intent.getStringExtra(BOOK_NAME);
        int bookImageId = intent.getIntExtra(BOOK_IMAGE_ID, 0);
        mBookInfoDTO = new BookInfoDTO(bookId, bookName, bookImageId);
        //测试数据传输
        //Toast.makeText(MyApplication.mContext, mBookInfoDTO.getBookId() + mBookInfoDTO.getBookName() + mBookInfoDTO.getImageId(), Toast.LENGTH_SHORT).show();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mBookInfoDTO.getBookName());
        }


        processGetBookByIdApi();


    }

    private void processGetBookByIdApi() {

        new GetApi().get(CONTENST.BOOK_LIST + "/" + mBookInfoDTO.getBookId(), new ApiListener() {
            @Override
            public void success(ApiUtil api) {
                GetApi api1 = (GetApi) api;
                String response = api1.mResponse;
                parseResponse(response);

                new Thread() {
                    public void run() {
                        //这儿是耗时操作，完成之后更新UI；
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (BookInfo bookInfo : mBookInfos) {

                                    //测试数据是否请求成功
                                    //Toast.makeText(MyApplication.mContext, bookInfo.bookAuthor, Toast.LENGTH_SHORT).show();

                                    Log.i("TAG", "BookInfo: " + bookInfo.bookId);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookIsbn);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookName);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookAuthor);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookPublish);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookPrice);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookShelf);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookCreateTime);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookLastModifyTime);
                                    Log.i("TAG", "BookInfo: " + bookInfo.types);
                                    Log.i("TAG", "BookInfo: " + bookInfo.states);
                                    Log.i("TAG", "BookInfo: " + bookInfo.bookIntroduction);
                                    //TODO 设计界面+将数据显示到界面上

                                    mTvBookName.setText(bookInfo.bookName);
                                    mTvBookIsbn.setText(bookInfo.bookIsbn);
                                    mTvBookAuthor.setText(bookInfo.bookAuthor);
                                    mTvBookPublish.setText(bookInfo.bookPublish);
                                    mTvBookPrice.setText(bookInfo.bookPrice + "元");
                                    mTvBookShelf.setText(bookInfo.bookShelf + "号");
                                    mTvBookTypes.setText(bookInfo.types);
                                    mTvBookStates.setText(bookInfo.states);
                                    //使用webview显示富文本
                                    mWvBookIntroduction.loadData(bookInfo.bookIntroduction, "text/html; charset=UTF-8", null);
                                }
                            }
                        });
                    }
                }.start();

            }

            @Override
            public void failure(ApiUtil api) {

            }
        });
    }

    private void parseResponse(String response) {
        try {
            Gson gson = new Gson();
            BookInfoResponse bookResponse = gson.fromJson(response, BookInfoResponse.class);
            mBookInfos = bookResponse.data;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
