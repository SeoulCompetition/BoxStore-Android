package com.b05studio.boxstore.view.fragment;

/**
 * Created by seungwoo on 2017-09-25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.Notification;
import com.b05studio.boxstore.util.GetTimeAgo;
import com.b05studio.boxstore.util.SimpleDividerItemDecoration;
import com.b05studio.boxstore.view.activity.ChatActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.facebook.FacebookSdk.getApplicationContext;

public class NotificationFragment extends Fragment {

    @BindView(R.id.notificationRecyclerview)
    RecyclerView notifyRecyclerview;

    private DatabaseReference mRootRef;
    private NotifyAdapter notiftyAdapter;

    private List<Notification> mNotifyList = new ArrayList<>();


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
        mRootRef = FirebaseDatabase.getInstance().getReference();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager notifyLayoutManager = new LinearLayoutManager(getContext());
        notifyRecyclerview.setLayoutManager(notifyLayoutManager);
        notifyRecyclerview.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        notiftyAdapter = new NotifyAdapter(mNotifyList);
        notifyRecyclerview.setAdapter(notiftyAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference massageRef = mRootRef.child("notifications").child(BoxStoreApplication.getCurrentUser().getuId());

        Query messageQuery = massageRef.limitToLast(10);
        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {

                if(dataSnapshot.exists()){
                    Notification newMessage = dataSnapshot.getValue(Notification.class);
                    notiftyAdapter.refillAdapter(newMessage);
                    notifyRecyclerview.scrollToPosition(notiftyAdapter.getItemCount()-1);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {



        public NotifyAdapter() {

        }
        public NotifyAdapter(List<Notification> nNotifyList)
        {
           mNotifyList = nNotifyList;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.cardNotifyView)
            ConstraintLayout cardView;
            @BindView(R.id.cardNotifyTextView)
            TextView cardNotifyText;
            @BindView(R.id.cardNotifyTimeTextView)
            TextView cardNotifyTimeText;
            @BindView(R.id.cardNotifyProfile)
            CircleImageView cardNoifyImage;

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
        public void onBindViewHolder(final NotifyAdapter.ViewHolder holder, int position) {

            final Notification c = mNotifyList.get(position);

            holder.cardNoifyImage.setImageResource(R.drawable.notify_profile);
            holder.cardNotifyText.setText(c.getName()+ " 님이 " + c.getStuff_name() + "상품에 대한 문의를 남기셨습니다.");


            GetTimeAgo getTimeAgo = new GetTimeAgo();

            //long lastTIme = Long.parseLong(online);

           // String lastSeenTime = getTimeAgo.getTimeAgo(lastTIme,getApplicationContext());

            //mLastSeenView.setText(lastSeenTime);
            //holder.cardNotifyTimeText.setText("ourtimeisrunning out");

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TAG ","Click");
                    Intent intent =new Intent(getContext(),ChatActivity.class);
                    intent.putExtra("stuff_id",c.getStuff_id());
                    intent.putExtra("BuyerUID",c.getFrom());
                    intent.putExtra("BuyerName",c.getName());
                    intent.putExtra("stuff_name",c.getStuff_name());
                    intent.putExtra("stuff_price",c.getStuff_price());
                    intent.putExtra("stuff_image",c.getStuff_image());
                    intent.putExtra("SellerUID",c.getSellerUID());
                    intent.putExtra("SellerName",c.getSellerName());
                    intent.putExtra("Type","Notification");
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mNotifyList.size();
        }

        public void refillAdapter(Notification newFireChatMessage){

        /*add new message chat to list*/

        mNotifyList.add(newFireChatMessage);

        /*refresh view*/
            notifyItemInserted(getItemCount()-1);
        }
        public void cleanUp() {
            mNotifyList.clear();
        }
    }


}