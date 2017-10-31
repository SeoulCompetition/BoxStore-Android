package com.b05studio.boxstore.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.ChatMessage;
import com.b05studio.boxstore.util.ActivityResultEvent;
import com.b05studio.boxstore.view.adapter.MessageChatAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


/**
 * Created by seungwoo on 2017-10-28.
 */

public class ChatFragment extends Fragment {

    private static final String ARG_PIRCE = "PRICE";
    private static final String ARG_STATION = "STATION";

    private static final String TAG = ChatFragment.class.getSimpleName();

    @BindView(R.id.chat_recyclerview)
    RecyclerView chatRecyclerView;
    @BindView(R.id.sendChatBtn)
    EditText mUserMessageChatText;

    private String paramPrice;
    private String paramStation;

    private String mRecipientId;
    private String mCurrentUserId;

    private String stuffId;

    private MessageChatAdapter messageChatAdapter;
    private DatabaseReference messageChatDatabase;
    private ChildEventListener messageChatListener;


    private DatabaseReference mRootRef;

    //Storage Firebase
    private StorageReference mImageStorage;


    private static final int GALLERY_PICKED =1;

    //빈생성자
    public ChatFragment(){

    }

    public static ChatFragment newInstance(){
        ChatFragment chatFragment = new ChatFragment();
        return chatFragment;
    }
    public static ChatFragment newInstance(String paramPrice, String paramStation,String paramStuffId){
        ChatFragment chatFragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("BuyerUID",paramPrice);
        args.putString("SellerUID",paramStation);
        args.putString("stuff_id",paramStuffId);
        chatFragment.setArguments(args);
        return chatFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCurrentUserId = BoxStoreApplication.getCurrentUser().getuId();
            if(mCurrentUserId.equals(getArguments().getString("BuyerUID")))
            {
                //만약 현재 유저와 구매자의 uid가 같다면
                mRecipientId = getArguments().getString("SellerUID");
            } else {
                mRecipientId = getArguments().getString("BuyerUID");
            }
                //구매자가 접근할때 bundle을 이용해서 값을 받고 후에 저장
                stuffId = getArguments().getString("stuff_id");


        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        initReyclerView();

        mRootRef = FirebaseDatabase.getInstance().getReference();
        //mRootRef.keepSynced(true);
        mImageStorage = FirebaseStorage.getInstance().getReference();

        mUserMessageChatText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //Enter키눌렀을떄 처리
                    return true;
                }
                return false;
            }
        });



        setDatabaseInstance();
        setUsersId();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        DatabaseReference massageRef = mRootRef.child("messages").child(mCurrentUserId).child(mRecipientId).child(stuffId);

        Query messageQuery = massageRef.limitToLast(20);
        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {

                if(dataSnapshot.exists()){
                    ChatMessage newMessage = dataSnapshot.getValue(ChatMessage.class);
                    if(newMessage.getSender().equals(mCurrentUserId)){
                        if(newMessage.getType().equals("box")){
                            newMessage.setRecipientOrSenderStatus(MessageChatAdapter.SENDER_BOX);
                        }else{
                            newMessage.setRecipientOrSenderStatus(MessageChatAdapter.SENDER);
                        }
                    }else{
                        if(newMessage.getType().equals("box"))
                        {
                            newMessage.setRecipientOrSenderStatus(MessageChatAdapter.RECIPIENT_BOX);
                        }else {
                            newMessage.setRecipientOrSenderStatus(MessageChatAdapter.RECIPIENT);
                        }
                    }
                    messageChatAdapter.refillAdapter(newMessage);
                    chatRecyclerView.scrollToPosition(messageChatAdapter.getItemCount()-1);
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

    private void setUsersId() {
    }

    private void setDatabaseInstance() {
        //todo : 클릭시 Intent 내정보 상대정보 인텐트 전송
        //String chatRef = getIntent().getStringExtra("내 유저 아이디 / 상대편 유저 아이디");
        //messageChatDatabase = FirebaseDatabase.getInstance().getReference().child("messages").child(chatRef);
    }

    private void initReyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        chatRecyclerView.setLayoutManager(linearLayoutManager);
        chatRecyclerView.setHasFixedSize(true);
        messageChatAdapter = new MessageChatAdapter(getContext(),new ArrayList<ChatMessage>());
        chatRecyclerView.setAdapter(messageChatAdapter);
    }
    @OnClick(R.id.sednImageBtn)
    public void OnClickImageBtn(){
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(galleryIntent,"SELECT IMAGE"),GALLERY_PICKED);
    }
    @SuppressWarnings("unused")
    @Subscribe
    public void onActivityResultEvent(@NonNull ActivityResultEvent event) {
        onActivityResult(event.getRequestCode(), event.getResultCode(), event.getData());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == GALLERY_PICKED) {
            Uri imageUri = data.getData();

            final String current_user_ref = "messages/"+ mCurrentUserId + "/"+ mRecipientId  +"/"+ stuffId;
            final String chat_user_ref = "messages/"+ mRecipientId + "/" + mCurrentUserId  + "/"+ stuffId;

            DatabaseReference user_message_push = mRootRef.child("messages")
                    .child(mCurrentUserId).child(mRecipientId).child(stuffId).push();

            final String push_id = user_message_push.getKey();

            StorageReference filepath = mImageStorage.child("message_images").child(push_id + ".jpg");

            filepath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        String download_url = task.getResult().getDownloadUrl().toString();
                        //
                        //ChatMessage newMessage = new ChatMessage(download_url,mCurrentUserId,mRecipientId,"image");
                        //messageChatDatabase.push().setValue(newMessage);

                        Map messageMap = new HashMap();

                        messageMap.put("message",download_url);
                        messageMap.put("seen",false);
                        messageMap.put("type","image");
                        messageMap.put("time", ServerValue.TIMESTAMP);
                        messageMap.put("sender",mCurrentUserId);
                        messageMap.put("BuyerUID",getArguments().getString("BuyerUID"));
                        messageMap.put("SellerUID",getArguments().getString("SellerUID"));
                        messageMap.put("stuff_id",stuffId);

                        Map messageUserMap = new HashMap();

                        messageUserMap.put(current_user_ref + "/" + push_id,messageMap);
                        messageUserMap.put(chat_user_ref + "/" + push_id,messageMap);

                        mUserMessageChatText.setText("");

                        mRootRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if(databaseError != null) {
                                    Log.d("CHAT_LOG", databaseError.getMessage().toString());
                                }
                            }
                        });

                    }
                }
            });
        }
    }
    @OnClick(R.id.sendChatBtn)
    public void OnClickChatBtn(){
        String message = mUserMessageChatText.getText().toString();

        if(!TextUtils.isEmpty(message)){
            final String current_user_ref = "messages/"+ mCurrentUserId + "/"+ mRecipientId  +"/"+ stuffId;
            final String chat_user_ref = "messages/"+ mRecipientId + "/" + mCurrentUserId  + "/" + stuffId;


            DatabaseReference user_message_push = mRootRef.child("messages")
                    .child(mCurrentUserId).child(mRecipientId).child(stuffId).push();

            String push_id = user_message_push.getKey();

            Map messageMap = new HashMap();
            messageMap.put("message",message);
            messageMap.put("seen",false);
            messageMap.put("type","text");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("sender",mCurrentUserId);
            messageMap.put("BuyerUID",getArguments().getString("BuyerUID"));
            messageMap.put("SellerUID",getArguments().getString("SellerUID"));
            messageMap.put("stuff_id",stuffId);

            Map messageUserMap = new HashMap();
            messageUserMap.put(current_user_ref + "/" + push_id,messageMap);
            messageUserMap.put(chat_user_ref + "/" + push_id,messageMap);

            mUserMessageChatText.setText("");

            mRootRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if(databaseError != null) {
                        Log.d("CHAT_LOG", databaseError.getMessage().toString());
                    }
                }
            });
        }
    }

}
