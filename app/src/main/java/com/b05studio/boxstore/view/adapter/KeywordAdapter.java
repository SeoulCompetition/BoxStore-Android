package com.b05studio.boxstore.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-23.
 */

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.ViewHolder> {

    private Context context;
    private List<String> mkeywordList;

    public KeywordAdapter(Context context,List<String> keywordList){
        this.context = context;
        this.mkeywordList = keywordList;
    }

    @Override
    public KeywordAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.keyword_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KeywordAdapter.ViewHolder holder, final int position) {

        holder.keywordImage.setImageResource(R.drawable.ic_actionbar_shop);
        holder.keywordText.setText(mkeywordList.get(position));
        holder.keywordCancleBtn.setImageResource(R.drawable.ic_image_add);
        holder.keywordCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });

    }

    public void addItem(int position,String infoData) {

        mkeywordList.add(position, infoData);
        notifyItemInserted(position);
    }
    private void delete(int position) {
        try{

            mkeywordList.remove(position);
            notifyItemRemoved(position);

        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        if(mkeywordList == null){
            return 0;
        }else{
            return mkeywordList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.keyword_icon)
        ImageView keywordImage;
        @BindView(R.id.keyword_text)
        TextView keywordText;
        @BindView(R.id.keyword_cancel_btn)
        ImageButton keywordCancleBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
