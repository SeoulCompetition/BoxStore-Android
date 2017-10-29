package com.b05studio.boxstore.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.b05studio.boxstore.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailStuffPagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailStuffPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailStuffPagerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "imageUrl";

    // TODO: Rename and change types of parameters
    private String mParam1;

    @BindView(R.id.detailStuffViewPagerItemImageView)
    ImageView stuffImageView;

    public DetailStuffPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DetailStuffPagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailStuffPagerFragment newInstance(String param1) {
        DetailStuffPagerFragment fragment = new DetailStuffPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_detail_stuff_view_pager, container, false);
        ButterKnife.bind(this, rootView);

        Picasso.with(getContext()).load(mParam1).into(stuffImageView);
        return rootView;

        // TODO: 2017-10-30 mParam1 가지고 값조정하기 호우호우
    }



}
