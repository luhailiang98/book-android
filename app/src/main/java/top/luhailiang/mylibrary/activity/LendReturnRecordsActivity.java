package top.luhailiang.mylibrary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.util.ArrayList;

import top.luhailiang.mylibrary.R;
import top.luhailiang.mylibrary.adapter.LendReturnRecordsAdapter;
import top.luhailiang.mylibrary.api.GetApi;
import top.luhailiang.mylibrary.model.LendReturnRecord;
import top.luhailiang.mylibrary.model.response.LendReturnRecordResponse;
import top.luhailiang.mylibrary.utils.CONTENST;
import top.luhailiang.mylibrary.utils.MyApplication;
import top.luhailiang.mylibrary.utils.SharedPreferencesUtil;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiListener;
import top.luhailiang.mylibrary.utils.okhttp3.api.ApiUtil;

public class LendReturnRecordsActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private SwipeRefreshLayout mSwipeRefresh;

    private ArrayList<LendReturnRecord> mLendReturnRecordList = new ArrayList<>();

    private LendReturnRecordsAdapter mLendReturnRecordsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lendreturnrecords);
        mRecycleView = findViewById(R.id.recycle_view);
        mSwipeRefresh = findViewById(R.id.swipe_refresh);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("MYLIBRARY-借阅记录");
        }

        processGetLendReturnRecordsApi();

        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLendReturnRecords();
            }
        });
    }

    private void refreshLendReturnRecords() {
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
                        mLendReturnRecordList.clear();
                        processGetLendReturnRecordsApi();
                        mLendReturnRecordsAdapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void processGetLendReturnRecordsApi() {
        long userId = (long) SharedPreferencesUtil.getData("userId", 1L);
        new GetApi().get(CONTENST.LEND_RETURN_RECORD + "/" + userId, new ApiListener() {
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
                                GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.mContext, 1);
                                mRecycleView.setLayoutManager(layoutManager);
                                mLendReturnRecordsAdapter = new LendReturnRecordsAdapter(mLendReturnRecordList);
                                mRecycleView.setAdapter(mLendReturnRecordsAdapter);
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
            LendReturnRecordResponse lendReturnRecordResponse = gson.fromJson(response, LendReturnRecordResponse.class);
            mLendReturnRecordList = lendReturnRecordResponse.data;
            for (LendReturnRecord lendReturnRecord : mLendReturnRecordList) {
                Log.i("TAG", "lendReturnRecord: " + lendReturnRecord.bookName);
                Log.i("TAG", "lendReturnRecord: " + lendReturnRecord.lendDate);
                Log.i("TAG", "lendReturnRecord: " + lendReturnRecord.shouldReturnDate);
                Log.i("TAG", "lendReturnRecord: " + lendReturnRecord.returnDate);
                Log.i("TAG", "lendReturnRecord: " + lendReturnRecord.extendedDays);
            }


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
