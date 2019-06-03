package top.luhailiang.mylibrary.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import top.luhailiang.mylibrary.R;
import top.luhailiang.mylibrary.model.LendReturnRecord;
import top.luhailiang.mylibrary.utils.MyApplication;

public class LendReturnRecordsAdapter extends RecyclerView.Adapter<LendReturnRecordsAdapter.ViewHolder> {

    private List<LendReturnRecord> mLendReturnRecordList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView mBookName;
        TextView mLendDate;
        TextView mShouldReturnDate;
        TextView mReturnDate;
        TextView mExtendedDays;


        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            mBookName = view.findViewById(R.id.book_name);
            mLendDate = view.findViewById(R.id.lend_date);
            mShouldReturnDate = view.findViewById(R.id.should_return_date);
            mReturnDate = view.findViewById(R.id.return_date);
            mExtendedDays = view.findViewById(R.id.extended_days);
        }
    }

    public LendReturnRecordsAdapter(List<LendReturnRecord> lendReturnRecordList) {
        mLendReturnRecordList = lendReturnRecordList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (MyApplication.mContext == null) {
            MyApplication.mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(MyApplication.mContext).inflate(R.layout.lend_return_records_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        LendReturnRecord lendReturnRecord = mLendReturnRecordList.get(i);
        viewHolder.mBookName.setText(lendReturnRecord.bookName);
        viewHolder.mLendDate.setText(lendReturnRecord.lendDate);
        viewHolder.mShouldReturnDate.setText(lendReturnRecord.shouldReturnDate);
        viewHolder.mReturnDate.setText(lendReturnRecord.returnDate);
        viewHolder.mExtendedDays.setText(lendReturnRecord.extendedDays);
    }

    @Override
    public int getItemCount() {
        return mLendReturnRecordList.size();
    }

}
