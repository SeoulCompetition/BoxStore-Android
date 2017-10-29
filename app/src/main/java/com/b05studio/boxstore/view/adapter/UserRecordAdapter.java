package com.b05studio.boxstore.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.b05studio.boxstore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-29.
 */

public class UserRecordAdapter extends RecyclerView.Adapter<UserRecordAdapter.ViewHolder> {

    Context context;
    List<String> recordList;

    public UserRecordAdapter(Context context,List<String> recordList){
        this.context = context;
        this.recordList = recordList;
    }

    @Override
    public UserRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.record_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserRecordAdapter.ViewHolder holder, int position) {
        String recordName = recordList.get(position);
        holder.settingText.setText(recordName);

        switch (recordName){
            case "문의 내역":
                holder.settingImage.setImageResource(R.drawable.ic_record_question);
                break;
            case "판매 내역":
                holder.settingImage.setImageResource(R.drawable.ic_record_sell);
                break;
            case "구매 내역":
                holder.settingImage.setImageResource(R.drawable.ic_record_buy);
                break;
            case "등록된 상품":
                holder.settingImage.setImageResource(R.drawable.ic_record_upload);
                break;
            case "담은 상품":
                holder.settingImage.setImageResource(R.drawable.ic_record_basket);
                break;
            case "최근 본 상품":
                holder.settingImage.setImageResource(R.drawable.ic_record_lastseen);
                break;
        }
        holder.settingRightArrow.setImageResource(R.drawable.ic_recycler_right);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "죄송합니다 서비스 준비중입니다.",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.settingsImage)
        ImageView settingImage;
        @BindView(R.id.settingsText)
        TextView settingText;
        @BindView(R.id.settingsRightArrow)
        ImageView settingRightArrow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
