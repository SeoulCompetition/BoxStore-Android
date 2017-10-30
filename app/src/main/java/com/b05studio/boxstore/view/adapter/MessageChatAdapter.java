package com.b05studio.boxstore.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.ChatMessage;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Marcel on 11/7/2015.
 */
public class MessageChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ChatMessage> mChatList;
    private DatabaseReference mUserDatabase;
    public static final int SENDER = 0;
    public static final int RECIPIENT = 1;
    public static final int SENDER_BOX = 2;
    public static final int RECIPIENT_BOX = 3;

    public MessageChatAdapter(List<ChatMessage> listOfFireChats) {
        mChatList = listOfFireChats;
    }

    @Override
    public int getItemViewType(int position) {
        if(mChatList.get(position).getRecipientOrSenderStatus()==SENDER){
            return SENDER;
        }else {
            return RECIPIENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case SENDER:
                View viewSender = inflater.inflate(R.layout.layout_sender_message, viewGroup, false);
                viewHolder= new ViewHolderSender(viewSender);
                break;
            case RECIPIENT:
                View viewRecipient = inflater.inflate(R.layout.layout_recipient_message, viewGroup, false);
                viewHolder=new ViewHolderRecipient(viewRecipient);
                break;

            case SENDER_BOX:
                View viewSenderBox = inflater.inflate(R.layout.layout_sender_req,viewGroup,false);
                viewHolder = new ViewHolderSenderBox(viewSenderBox);
                break;
            case RECIPIENT_BOX:
                View viewRecipientBox = inflater.inflate(R.layout.layout_recipent_req,viewGroup,false);
                viewHolder = new ViewHolderRecipient(viewRecipientBox);
                break;
            default:
                View viewSenderDefault = inflater.inflate(R.layout.layout_sender_message, viewGroup, false);
                viewHolder= new ViewHolderSender(viewSenderDefault);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ChatMessage c = mChatList.get(position);

        String message_type = c.getType();

        switch (viewHolder.getItemViewType()){
            case SENDER:
                if(message_type.equals("image") || message_type.equals("text")){
                    ViewHolderSender viewHolderSender=(ViewHolderSender)viewHolder;
                    configureSenderView(viewHolderSender,position,message_type);
                }else{
                    ViewHolderSenderBox viewHolderSellerBox = (ViewHolderSenderBox)viewHolder;
                    configureSellerBoxView(viewHolderSellerBox,position,message_type);
                }
                break;
            case RECIPIENT:
                if(message_type.equals("image") || message_type.equals("text")) {
                    ViewHolderRecipient viewHolderRecipient=(ViewHolderRecipient)viewHolder;
                    configureRecipientView(viewHolderRecipient,position,message_type);
                }else{
                    ViewHolderRecipientBox viewHolderRecipientBox = (ViewHolderRecipientBox)viewHolder;
                    configureBuyerBoxView(viewHolderRecipientBox,position,message_type);
                }
                break;
        }



    }

    private void configureBuyerBoxView(ViewHolderRecipientBox viewHolderRecipientBox, int position, String message_type) {

        ChatMessage Message= mChatList.get(position);

        viewHolderRecipientBox.mSellerNameAndProductName.setText(Message.getMessage()+"'"+"아이템이름"+"'"+"에 대한 거래 승인을 요청하셨습니다.");

        viewHolderRecipientBox.getClickView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //todo : 거래 진행 화면으로 연결 근데 셀러가 클릭하면 셀러 프래그먼트가 떠야되고 바이어가 클릭하면 바이어 프래그먼트가 떠야됨
                Toast.makeText(getApplicationContext(), "Box View Click!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void configureSellerBoxView(ViewHolderSenderBox viewHolderSellerBox, int position, String message_type) {

    }

    private void configureSenderView(ViewHolderSender viewHolderSender, int position,String message_type) {
        ChatMessage senderFireMessage= mChatList.get(position);

        if(message_type.equals("text")){
            viewHolderSender.mSenderMessageImageView.setVisibility(View.GONE);
            viewHolderSender.mSenderMessageTextView.setVisibility(View.VISIBLE);
            viewHolderSender.getSenderMessageTextView().setText(senderFireMessage.getMessage());
        }
        if(message_type.equals("image")){
            viewHolderSender.getSenderMessageTextView().setVisibility(View.GONE);
            viewHolderSender.mSenderMessageImageView.setVisibility(View.VISIBLE);
            Picasso.with(viewHolderSender.mSenderMessageImageView.getContext()).load(senderFireMessage.getMessage())
                   .into(viewHolderSender.mSenderMessageImageView);
        }
    }

    private void configureRecipientView(ViewHolderRecipient viewHolderRecipient, int position,String message_type) {
        ChatMessage recipientFireMessage = mChatList.get(position);


        if(message_type.equals("text")){
            viewHolderRecipient.getRecipientMessageTextView().setText(recipientFireMessage.getMessage());
        }
        else{
            viewHolderRecipient.getRecipientMessageTextView().setVisibility(View.INVISIBLE);
            viewHolderRecipient.mRecipientMessageImageView.setVisibility(View.VISIBLE);
            Picasso.with(viewHolderRecipient.getmRecipientMessageImageView().getContext()).load(recipientFireMessage.getMessage())
                    .into(viewHolderRecipient.mRecipientMessageImageView);
        }
    }



    @Override
    public int getItemCount() {
        return mChatList.size();
    }


    public void refillAdapter(ChatMessage newFireChatMessage){

        /*add new message chat to list*/
        mChatList.add(newFireChatMessage);

        /*refresh view*/
        notifyItemInserted(getItemCount()-1);
    }


    public void cleanUp() {
        mChatList.clear();
    }


    /*==============ViewHolder===========*/

    /*ViewHolder for Sender*/

    public class ViewHolderSender extends RecyclerView.ViewHolder {

        private TextView mSenderMessageTextView;
        private ImageView mSenderMessageImageView;

        public ViewHolderSender(View itemView) {
            super(itemView);
            mSenderMessageTextView = (TextView) itemView.findViewById(R.id.text_view_sender_message);
            mSenderMessageImageView = (ImageView) itemView.findViewById(R.id.image_view_sender_message);
        }

        public TextView getSenderMessageTextView() {
            return mSenderMessageTextView;
        }

        public ImageView getmSenderMessageImageView() {
            return mSenderMessageImageView;

        }
    }


        /*ViewHolder for Recipient*/
        public class ViewHolderRecipient extends RecyclerView.ViewHolder {

            private TextView mRecipientMessageTextView;
            private ImageView mRecipientMessageImageView;

            public ViewHolderRecipient(View itemView) {
                super(itemView);
                mRecipientMessageTextView = (TextView) itemView.findViewById(R.id.text_view_recipient_message);
                mRecipientMessageImageView = (ImageView) itemView.findViewById(R.id.image_view_recipient_message);
            }

            public TextView getRecipientMessageTextView() {
                return mRecipientMessageTextView;
            }
            public ImageView getmRecipientMessageImageView(){
                return mRecipientMessageImageView;
            }

        }
        /*ViewHolder for SellerBox */
        public class ViewHolderSenderBox extends RecyclerView.ViewHolder {

            private View clickView;
            private TextView mSellerNameAndProductName;


            public ViewHolderSenderBox(View itemView) {
                super(itemView);
                clickView = (View) itemView.findViewById(R.id.sellClickview);
                mSellerNameAndProductName  = (TextView)itemView.findViewById(R.id.sellerBoxTextView);
            }

            public TextView getmSellerNameAndProductName() {return mSellerNameAndProductName;}
            public View getClickView() {return clickView;}
        }
    /*ViewHolder for BuyerBox */
    public class ViewHolderRecipientBox extends RecyclerView.ViewHolder {

        private View clickView;
        private TextView mSellerNameAndProductName;


        public ViewHolderRecipientBox(View itemView) {
            super(itemView);
            clickView = (View) itemView.findViewById(R.id.buyerClickview);
            mSellerNameAndProductName  = (TextView)itemView.findViewById(R.id.buyerBoxTextView);
        }

        public TextView getmSellerNameAndProductName() {return mSellerNameAndProductName;}
        public View getClickView() {return clickView;}
    }
}