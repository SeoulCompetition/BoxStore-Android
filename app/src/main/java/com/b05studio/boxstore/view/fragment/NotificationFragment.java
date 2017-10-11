package com.b05studio.boxstore.view.fragment;

/**
 * Created by seungwoo on 2017-09-25.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.util.SimpleDividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationFragment extends Fragment {

    @BindView(R.id.notificationRecyclerview)
    RecyclerView notifyRecyclerview;

    private RecyclerView.Adapter notiftyAdapter;


    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager notifyLayoutManager = new LinearLayoutManager(getContext());
        notifyRecyclerview.setLayoutManager(notifyLayoutManager);
        notifyRecyclerview.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        notiftyAdapter = new NotifyAdapter();
        notifyRecyclerview.setAdapter(notiftyAdapter);

    }

    public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {

//        private ArrayList<Pattern> patterns;
//        private Context context;
//        private ArrayList<Pattern> copyPatterns = new ArrayList<>();
//
//        private PatternAdapter(ArrayList<Pattern> patterns, Context context) {
//            this.patterns = patterns;
//            this.context = context;
//            copyPatterns.addAll(patterns);
//        }

        // TODO: 2017-10-11 recycler view 
        public NotifyAdapter() {

        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }

        @Override
        public NotifyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notificaion, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(NotifyAdapter.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


}