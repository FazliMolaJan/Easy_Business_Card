package com.nexttech.easybusinesscard;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class IconFragment extends Fragment {
    Button save;
    EditText size;
    Context context;
    private ImageView  icon_1,icon_2,icon_3,icon_4,icon_5,icon_6,icon_7,icon_8;

    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    View dialogueView;

    public IconFragment(Context context){
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_icon, null);
        size=view.findViewById(R.id.iconsize);
        save=view.findViewById(R.id.iconsave);

        builder = new AlertDialog.Builder(context);

        iconDialoguebox();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iconsize = size.getText().toString();


                if(TextUtils.isEmpty(iconsize)){
                    size.setError("Field empty!");
                    size.requestFocus();
                }else {

                }
            }
        });


        return view;
    }


    public void iconDialoguebox(){

        dialogueView=getLayoutInflater().inflate(R.layout.select_icon,null);
        icon_1 = dialogueView.findViewById(R.id.icon1);
        icon_2= dialogueView.findViewById(R.id.icon2);
        icon_3= dialogueView.findViewById(R.id.icon3);
        icon_4= dialogueView.findViewById(R.id.icon4);
        icon_5= dialogueView.findViewById(R.id.icon5);
        icon_6= dialogueView.findViewById(R.id.icon6);
        icon_7= dialogueView.findViewById(R.id.icon7);
        icon_8= dialogueView.findViewById(R.id.icon8);

        icon_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_1);
            }
        });
        icon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_2);

            }
        });
        icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_3);

            }
        });
        icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_4);

            }
        });
        icon_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_5);
            }
        });
        icon_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_6);

            }
        });
        icon_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_7);

            }
        });
        icon_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_8);

            }
        });

        builder.setView(null);
        builder.setView(dialogueView);
        alertDialog=builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialogDismiss();
        alertDialog.show();
    }
    private void setImage(ImageView image) {
        ImageView imageView=new ImageView(context);
        imageView.setTag("draggable imageview");
        imageView.setImageDrawable(image.getDrawable());
        imageView.setOnLongClickListener(new LongPresslistener(context));
        if(Create_card.absoluteLayoutFront.getVisibility()==View.VISIBLE){
            Create_card.absoluteLayoutFront.addView(imageView);


        }
        else {
            Create_card.absoluteLayoutBack.addView(imageView);
        }

        alertDialogDismiss();
    }

    private void alertDialogDismiss(){
        if (alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }
}