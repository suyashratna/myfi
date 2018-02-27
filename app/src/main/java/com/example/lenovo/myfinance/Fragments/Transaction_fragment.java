package com.example.lenovo.myfinance.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.myfinance.Adapter.TransactionListAdapter;
import com.example.lenovo.myfinance.Bottomsheet_dialog;
import com.example.lenovo.myfinance.Model.Transaction;
import com.example.lenovo.myfinance.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Transaction_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Animation uptodown;
    Animation rotate;

    @BindView(R.id.addtransaction_button)
    Button maddtransaction_button ;

    @BindView(R.id.trans_image)ImageView transactions;

    TransactionListAdapter mTransactionListAdapter;
    List<Transaction> transactionList;


    @BindView(R.id.trans_recycler)RecyclerView mTransactionRecycler;

//    @BindView(R.id.bottomsheet_layout)
//    LinearLayout layout;

//    BottomSheetBehavior bab;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Transaction_fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        ButterKnife.bind(this,view);

        rotate= AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
        transactions.setAnimation(rotate);
//
        mTransactionListAdapter = new TransactionListAdapter(transactionList);
        mTransactionRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTransactionRecycler.setAdapter(mTransactionListAdapter);

        maddtransaction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                View parentView = getActivity().getLayoutInflater().inflate(R.layout.bottomsheet_transaction,null);
                bottomSheetDialog.setContentView(parentView);
                bottomSheetDialog.show();
            }
        });
//        Bundle bundle = getArguments();
//        transactionList.add(new Transaction
//                (String.valueOf(bundle.getString("category")),
//                String.valueOf(bundle.getString("account")),
//                Integer.valueOf(bundle.getString("amount"))));




        return view;

    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof Transaction_fragment.OnFragmentInteractionListener) {
//            mListener = (Transaction_fragment.OnFragmentInteractionListener) context;
//        } else {
//            Toast.makeText(context, "Transaction", Toast.LENGTH_SHORT).show();
//        }
//    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
