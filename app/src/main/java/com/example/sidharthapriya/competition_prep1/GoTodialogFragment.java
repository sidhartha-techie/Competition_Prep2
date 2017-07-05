package com.example.sidharthapriya.competition_prep1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static android.R.id.edit;
import static com.example.sidharthapriya.competition_prep1.R.id.questionbank_gotoedittext;

/**
 * Created by Sidhartha Priya on 6/20/2017.
 */

public class GoTodialogFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Test", "hello");


    }
    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gotodialog, parent, false);
        //EditText questionbank_gotoedittext = (EditText) view.findViewById(questionbank_gotoedittext);
        //SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
       // notatki.setText(settings.getString("value", ""));
        return view;
    }
    private OnGetFromUserClickListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnGetFromUserClickListener ) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnGetFromUserClickListener");
        }
    }

    public void getFromUser(View v) {
        if (mListener != null) {
           // mListener.getFromUser(getString(questionbank_gotoedittext));
        }
    }

    public interface OnGetFromUserClickListener {
        void getFromUser(String message);
    }
}