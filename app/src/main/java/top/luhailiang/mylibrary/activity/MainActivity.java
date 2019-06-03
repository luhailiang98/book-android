package top.luhailiang.mylibrary.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import top.luhailiang.mylibrary.R;
import top.luhailiang.mylibrary.adapter.BookAdapter;
import top.luhailiang.mylibrary.api.GetApi;
import top.luhailiang.mylibrary.model.BookInfo;
import top.luhailiang.mylibrary.model.BookInfoDTO;
import top.luhailiang.mylibrary.model.response.BookInfoResponse;
import top.luhailiang.mylibrary.utils.CONTENST;
import top.luhailiang.mylibrary.utils.MyApplication;
import top.luhailiang.mylibrary.utils.SharedPreferencesUtil;
import top.luhailiang.mylibrary.utils.ToastUtil;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiListener;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiUtil;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;

    private TextView mUserId;
    private TextView mUserTrueName;
    private TextView mUserEmail;
    private TextView mUserPhone;

    private ArrayList<BookInfo> mBookList = new ArrayList<>();
    private ArrayList<BookInfoDTO> mBookInfoDTOS = new ArrayList<>();
    private BookAdapter mBookAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecycleView = findViewById(R.id.recycle_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavView = findViewById(R.id.nav_view);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setTitle("MYLIBRARY-所有图书");
        }


        processGetAllBookApi();

        long userId = (long) SharedPreferencesUtil.getData("userId", 1L);
        String userTrueName = (String) SharedPreferencesUtil.getData("userTrueName", "");
        String userEmail = (String) SharedPreferencesUtil.getData("userEmail", "");
        String userPhone = (String) SharedPreferencesUtil.getData("userPhone", "");
        Log.i("TAG", "userId: " + userId);
        Log.i("TAG", "userTrueName: " + userTrueName);
        Log.i("TAG", "userEmail: " + userEmail);
        Log.i("TAG", "userPhone: " + userPhone);

        if (mNavView.getHeaderCount() > 0) {
            View header = mNavView.getHeaderView(0);
            mUserId = header.findViewById(R.id.userId);
            mUserTrueName = header.findViewById(R.id.userTrueName);
            mUserEmail = header.findViewById(R.id.userEmail);
            mUserPhone = header.findViewById(R.id.userPhone);
            mUserId.setText("借书证编号" + userId);
            mUserTrueName.setText(userTrueName + "");
            mUserEmail.setText(userEmail + "");
            mUserPhone.setText(userPhone + "");
        }

        //mNavView.setCheckedItem(R.id.nav_books);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //TODO OTHER THINGS
                //mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.nav_books:
                        Intent intent1 = new Intent(MyApplication.mContext, MainActivity.class);
                        startActivity(intent1);
//                        Toast.makeText(MainActivity.this, "所有图书", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_lendReturnRecord:
                        Intent intent2 = new Intent(MyApplication.mContext, LendReturnRecordsActivity.class);
                        startActivity(intent2);
                        //ToastUtil.showShortToast(MyApplication.mContext, "借阅记录");
                        break;
                    case R.id.nav_about:
                        Intent intent3 = new Intent(MyApplication.mContext, AboutActivity.class);
                        startActivity(intent3);
                        //ToastUtil.showShortToast(MyApplication.mContext, "关于");
                        break;

                    //退出登录
                    case R.id.nav_exit:
                        Intent intent4 = new Intent(MyApplication.mContext, LoginActivity.class);
                        startActivity(intent4);
                        break;
                    default:
                }
                return true;
            }
        });


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshBooks();
            }
        });

    }

    private void refreshBooks() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBookInfoDTOS.clear();
                        processGetAllBookApi();
                        mBookAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void processGetAllBookApi() {
        new GetApi().get(CONTENST.BOOK_LIST, new ApiListener() {
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
                                GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.mContext, 3);
                                mRecycleView.setLayoutManager(layoutManager);
                                mBookAdapter = new BookAdapter(mBookInfoDTOS);
                                mRecycleView.setAdapter(mBookAdapter);
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
            mBookList = bookResponse.data;
            for (BookInfo bookInfo : mBookList) {
                BookInfoDTO bookInfoDTO = new BookInfoDTO();
                bookInfoDTO.setBookId(bookInfo.bookId);
                bookInfoDTO.setBookName(bookInfo.bookName);
                bookInfoDTO.setImageId(R.drawable.logo);
                mBookInfoDTOS.add(bookInfoDTO);
            }

//            for (BookInfoDTO bookInfoDTO : mBookInfoDTOS) {
//                Log.i("TAG", "userResponse: " + bookInfoDTO.getBookName());
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}
