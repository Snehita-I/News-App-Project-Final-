package com.example.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class fragment3 extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment3() {

    }

    // TODO: Rename and change types and number of parameters
    public static fragment3 newInstance(String param1, String param2) {
        fragment3 fragment = new fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.newsmain_profilepage, container, false);
    }

    ImageView pimage;
    TextView ptext;
    ImageButton imgb,imgb2;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pimage = (ImageView) view.findViewById(R.id.profile_image);
        ptext = (TextView) view.findViewById(R.id.profile_name);
        imgb = (ImageButton) view.findViewById(R.id.imageButton);
        imgb2 = (ImageButton)view.findViewById(R.id.imgB2);
        imgb.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
                                        LayoutInflater inflater = getLayoutInflater();
                                        final View dialogView = inflater.inflate(R.layout.addnameandimage, null);


                                        final Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
                                        Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);
                                        final EditText urname = (EditText) dialogView.findViewById(R.id.edt_comment);

                                        button2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                dialogBuilder.dismiss();
                                            }
                                        });
                                        button1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                String text = urname.getText().toString();

                                                ptext.setText(text);

                                                dialogBuilder.dismiss();
                                            }
                                        });

                                        dialogBuilder.setView(dialogView);
                                        dialogBuilder.show();
                                    }
                                });
        imgb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog dialogBuilder = new AlertDialog.Builder(getContext()).create();
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.addnameandimage, null);


                final Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
                Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);
                final EditText yourImageUrl = (EditText) dialogView.findViewById(R.id.edt_comment);

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String text = yourImageUrl.getText().toString();
                        Picasso.with(getContext()).load(text).into(pimage);
                        dialogBuilder.dismiss();
                    }
                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();
            }
        });


    }

}



