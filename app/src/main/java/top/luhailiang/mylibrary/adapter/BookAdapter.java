package top.luhailiang.mylibrary.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import top.luhailiang.mylibrary.R;
import top.luhailiang.mylibrary.activity.BookInfoActivity;
import top.luhailiang.mylibrary.model.BookInfoDTO;
import top.luhailiang.mylibrary.utils.MyApplication;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {


    //private Context mContext;
    private List<BookInfoDTO> mBookInfoDTOS;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView bookImage;
        TextView bookName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            bookImage = view.findViewById(R.id.book_image);
            bookName = view.findViewById(R.id.book_name);
        }
    }

    public BookAdapter(List<BookInfoDTO> bookInfoDTOS) {
        mBookInfoDTOS = bookInfoDTOS;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        if (MyApplication.mContext == null) {
            MyApplication.mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(MyApplication.mContext).inflate(R.layout.book_item, viewGroup, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                BookInfoDTO bookInfoDTO = mBookInfoDTOS.get(position);

                //使用intent 将cardview中的bookId bookName imageId 传到BookInfoActivity中
                Intent intent = new Intent(MyApplication.mContext, BookInfoActivity.class);
                intent.putExtra(BookInfoActivity.BOOK_ID, bookInfoDTO.getBookId());
                intent.putExtra(BookInfoActivity.BOOK_NAME, bookInfoDTO.getBookName());
                intent.putExtra(BookInfoActivity.BOOK_IMAGE_ID, bookInfoDTO.getImageId());
                MyApplication.mContext.startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder viewHolder, int i) {
        BookInfoDTO bookInfoDTO = mBookInfoDTOS.get(i);
        viewHolder.bookName.setText(bookInfoDTO.getBookName());
        Glide.with(MyApplication.mContext).load(bookInfoDTO.getImageId()).into(viewHolder.bookImage);
    }

    @Override
    public int getItemCount() {
        return mBookInfoDTOS.size();
    }
}
