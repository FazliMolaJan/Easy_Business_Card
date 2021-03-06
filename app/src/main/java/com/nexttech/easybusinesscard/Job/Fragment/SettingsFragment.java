package com.nexttech.easybusinesscard.Job.Fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.R;


public class SettingsFragment extends Fragment {


    Context context;
    CardView cvprofile,cvsecurity,cvprivacy,cvnotification,cvappimage;
    TextView tvprofile,tvsecurity,tvprivacy,tvnotification,tvappimage;
    View vi;

    View DialogueView;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;



    public SettingsFragment(Context context) {
       this.context= context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        vi = inflater.inflate(R.layout.fragment_settings, container, false);
        // Inflate the layout for this fragment


        cvprofile = vi.findViewById(R.id.cvprofile);
        cvsecurity = vi.findViewById(R.id.cvsecurity);
        cvprivacy = vi.findViewById(R.id.cvprivacy);
        cvnotification = vi.findViewById(R.id.cvnotification);
        cvappimage = vi.findViewById(R.id.cvappimage);



        tvprofile = vi.findViewById(R.id.tvprofile);
        tvsecurity = vi.findViewById(R.id.tvsecurity);
        tvprivacy = vi.findViewById(R.id.tvprivacy);
        tvnotification = vi.findViewById(R.id.tvnotification);
        tvappimage= vi.findViewById(R.id.tvappimage);
        builder = new AlertDialog.Builder(context);



        tvappimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogueView = getLayoutInflater().inflate(R.layout.dailoige_image_quality, null,false);


                builder.setView(null);
                builder.setView(DialogueView);
                alertDialog=builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.show();

            }
        });

        cvappimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogueView = getLayoutInflater().inflate(R.layout.dailoige_image_quality, null,false);


                builder.setView(null);
                builder.setView(DialogueView);
                alertDialog=builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.show();

            }
        });
        cvnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogueView = getLayoutInflater().inflate(R.layout.notification, null,false);


                builder.setView(null);
                builder.setView(DialogueView);
                alertDialog=builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.show();

            }
        });
        tvnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogueView = getLayoutInflater().inflate(R.layout.notification, null,false);


                builder.setView(null);
                builder.setView(DialogueView);
                alertDialog=builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.show();
            }
        });

        cvprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container,new Profile_settings()).commit();
            }
        });

        tvprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container,new Profile_settings()).commit();
            }
        });


        cvsecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container,new SecurityFragment()).commit();
            }
        });

        tvsecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container,new SecurityFragment()).commit();
            }
        });

        cvprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogueView = getLayoutInflater().inflate(R.layout.dailoige_privacy_who_can_see_my_bussniess_card, null,false);


                builder.setView(null);
                builder.setView(DialogueView);
                alertDialog=builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.show();
            }
        });

        tvprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogueView = getLayoutInflater().inflate(R.layout.dailoige_privacy_who_can_see_my_bussniess_card, null,false);


                builder.setView(null);
                builder.setView(DialogueView);
                alertDialog=builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.show();
            }
        });

        return vi;
    }

}
