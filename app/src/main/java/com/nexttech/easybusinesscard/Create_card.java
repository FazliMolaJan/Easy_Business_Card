package com.nexttech.easybusinesscard;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

public class Create_card extends AppCompatActivity{

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }else {
            viewPager.setCurrentItem(0);
        }
    }
    private Bitmap bitmapFront, bitmapBack;
    TextView importtemp,share,export,browse;

    View v1,v2,v3,v5;
    View focusview, dialogueView;
    LinearLayout mainlayout;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    RadioButton radioPicture, radioPdf;

    public static ImageView mainTempFront, mainTempBack, ivLockCard;

    public static ImageView deltebuttonfront,deltebuttonback;

   public static AbsoluteLayout absoluteLayoutFront;
   public static AbsoluteLayout absoluteLayoutBack;

   public static CustomViewPager viewPager;
    public static ViewpagerAdapter mAdapter;
    float fontxandfontvalue = 0;
    float fontyandfontvalue = 0;


    public static float xvalue;
    public static float yvalue;

    boolean b=false;
    public static boolean isLayoutVisible(){
        if(absoluteLayoutFront.getVisibility() == View.VISIBLE){
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        importtemp=findViewById(R.id.importtemp);
        share=findViewById(R.id.share);
        export=findViewById(R.id.export);
        browse=findViewById(R.id.browse);
        v1=findViewById(R.id.view1);
        v2=findViewById(R.id.view2);
        v3=findViewById(R.id.view3);
        v5=findViewById(R.id.view5);
        absoluteLayoutFront=findViewById(R.id.absolute_layout_front);
        absoluteLayoutBack=findViewById(R.id.absolute_layout_back);
        mainTempFront = findViewById(R.id.main_temp_front);
        mainTempBack= findViewById(R.id.main_temp_back);
        viewPager=findViewById(R.id.viewpager);
        deltebuttonfront=findViewById(R.id.deleteiconfront);
        deltebuttonback=findViewById(R.id.deleteiconback);

        mainlayout=findViewById(R.id.mainlayout);
        mainTempFront.setImageDrawable(getResources().getDrawable(R.drawable.cardone));
        mainTempBack.setImageDrawable(getResources().getDrawable(R.drawable.rear));

        ViewTreeObserver vto = absoluteLayoutFront.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fontxandfontvalue = absoluteLayoutFront.getX()+absoluteLayoutFront.getWidth();
                fontyandfontvalue = absoluteLayoutFront.getY()+absoluteLayoutFront.getHeight();

                ViewTreeObserver obs = absoluteLayoutFront.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);


            }

        });

        absoluteLayoutBack.setVisibility(View.GONE);
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(new ToolbarFragment(this));
        fragments.add(new TextFragment(this));
        fragments.add(new IconFragment(this));
        fragments.add(new ImageFragment(this));
        fragments.add(new Qr_code(this));
        mAdapter=new ViewpagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(mAdapter);
        absoluteLayoutFront.setOnDragListener(new LongPresslistener(this));
        absoluteLayoutBack.setOnDragListener(new LongPresslistener(this));
        builder = new AlertDialog.Builder(Create_card.this);
        importtemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    focusview.setBackgroundColor(Color.WHITE);
                    v1.setBackgroundColor(Color.BLACK);
                    focusview=v1;
                    b=true;
                }else {
                    v1.setBackgroundColor(Color.BLACK);
                    focusview=v1;
                    b=true;
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    focusview.setBackgroundColor(Color.WHITE);
                    v2.setBackgroundColor(Color.BLACK);
                    focusview=v2;
                    b=true;
                }else {
                    v2.setBackgroundColor(Color.BLACK);
                    focusview=v2;
                    b=true;
                }
                showShareDialougeBox();
            }
        });
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    focusview.setBackgroundColor(Color.WHITE);
                    v5.setBackgroundColor(Color.BLACK);
                    focusview=v5;
                    b=true;
                }else {
                    v5.setBackgroundColor(Color.BLACK);
                    focusview=v5;
                    b=true;
                }
                ShowDialogebox();

            }
        });
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    focusview.setBackgroundColor(Color.WHITE);
                    v3.setBackgroundColor(Color.BLACK);
                    focusview=v3;
                    b=true;
                }else {
                    v3.setBackgroundColor(Color.BLACK);
                    focusview=v3;
                    b=true;
                }

                TextView tvFront, tvBack, tvBoth, tvCancel;
                dialogueView = getLayoutInflater().inflate(R.layout.save_image_dialog, null);
                tvFront = dialogueView.findViewById(R.id.tv_front);
                tvBack = dialogueView.findViewById(R.id.tv_back);
                tvBoth = dialogueView.findViewById(R.id.tv_both);
                tvCancel = dialogueView.findViewById(R.id.tv_cancel);
                radioPicture = dialogueView.findViewById(R.id.radio_picture);
                radioPdf = dialogueView.findViewById(R.id.radio_pdf);
                builder.setView(null);
                builder.setView(dialogueView);
                alertDialog=builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialogDismiss();
                alertDialog.show();

                if (absoluteLayoutFront.getVisibility()==View.VISIBLE){
                    absoluteLayoutFront.setVisibility(View.GONE);
                    absoluteLayoutBack.setVisibility(View.VISIBLE);
                } else{
                    absoluteLayoutFront.setVisibility(View.VISIBLE);
                    absoluteLayoutBack.setVisibility(View.GONE);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bitmapFront = loadBitmapFromView(absoluteLayoutFront);
                        bitmapBack = loadBitmapFromView(absoluteLayoutBack);
                    }
                }, 1000);



                tvFront.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isStoragePermissionGranted()){
                            if (radioPicture.isSelected()){
                                SaveImage(bitmapFront, "Front");
                            } else {
                                savePDF(bitmapFront, "Front");
                            }
                        }
                    }
                });

                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isStoragePermissionGranted()){
                            if (radioPicture.isSelected()){
                                SaveImage(bitmapBack, "Back");
                            } else {
                                savePDF(bitmapBack, "Back");
                            }

                        }
                    }
                });

                tvBoth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isStoragePermissionGranted()){
                            if (radioPicture.isSelected()){
                                SaveImage(bitmapFront, "Front");
                                SaveImage(bitmapBack, "Back");
                            } else {
                                savePDF(bitmapFront, "Front");
                                savePDF(bitmapBack, "Back");
                            }

                        }
                    }
                });

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogDismiss();
                    }
                });


            }
        });
    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.measure(0, 0);
        v.getMeasuredWidth();
        v.getMeasuredHeight();
        v.draw(c);
        return b;
    }
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission","Permission is granted");
                return true;
            } else {

                Log.v("Permission","Permission is revoked");
                ActivityCompat.requestPermissions(Create_card.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Permission","Permission is granted");
            return true;
        }
    }

    private void SaveImage(Bitmap finalBitmap, String name) {

        String root = Environment.getExternalStorageDirectory().toString();

        String saveDirectoryName = "Business Cards/Picture";

        File myDir = new File(root + "/"+saveDirectoryName);
        myDir.mkdirs();
        String fname = System.currentTimeMillis()+" "+ name +".png";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(this, "Save to "+saveDirectoryName, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void savePDF(Bitmap bitmap, String name){
        String root = Environment.getExternalStorageDirectory().toString();

        String saveDirectoryName = "Business Cards/PDF";

        File myDir = new File(root + "/"+saveDirectoryName);
        myDir.mkdirs();
        String fname = System.currentTimeMillis()+" "+ name +".pdf";
        File file = new File (myDir, fname);

        try
        {
            Document document = new Document(new Rectangle(bitmap.getWidth(), bitmap.getHeight()),0, 0, 0, 0);

            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            addImage(document,byteArray);
            document.close();

            Toast.makeText(this, "Save to "+saveDirectoryName, Toast.LENGTH_SHORT).show();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void addImage(Document document,byte[] byteArray)
    {
        Image image = null;
        try
        {
            image = Image.getInstance(byteArray);
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
            image.scalePercent(scaler);
            image.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
        }
        catch (BadElementException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try
        {
            document.add(image);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void ShowDialogebox()
    {
        ImageView temp1, temp1rear, temp2,temp2rear, temp3, temp3rear;

        dialogueView = getLayoutInflater().inflate(R.layout.brows_template, null);
        temp1 = dialogueView.findViewById(R.id.imgtemp1);
        temp1rear = dialogueView.findViewById(R.id.imgtemp1rear);
        temp2 = dialogueView.findViewById(R.id.imgtemp2);
        temp2rear = dialogueView.findViewById(R.id.imgtemp2rear);
        temp3 = dialogueView.findViewById(R.id.imgtemp3);
        temp3rear = dialogueView.findViewById(R.id.imgtemp3rear);

        builder.setView(null);
        builder.setView(dialogueView);
        alertDialog=builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialogDismiss();
        alertDialog.show();

        temp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundImage(R.drawable.cardone, R.drawable.rear);
            }
        });
        temp1rear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundImage(R.drawable.cardone, R.drawable.rear);
            }
        });
        temp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundImage(R.drawable.temp2, R.drawable.temp2rear);
            }
        });
        temp2rear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundImage(R.drawable.temp2, R.drawable.temp2rear);

            }
        });
        temp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundImage(R.drawable.temp3, R.drawable.temp3rear);
            }
        });
        temp3rear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundImage(R.drawable.temp3, R.drawable.temp3rear);
            }
        });

    }
    private void setBackgroundImage(int imageResourceFront, int imageResourceBack){
        mainTempFront.setImageDrawable(getResources().getDrawable(imageResourceFront));
        mainTempBack.setImageDrawable(getResources().getDrawable(imageResourceBack));
        alertDialogDismiss();
    }
    private void alertDialogDismiss(){
        if (alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }
    void showShareDialougeBox(){

        BitmapDrawable drawable = (BitmapDrawable) mainTempFront.getDrawable();
        Bitmap bitmap1 = drawable.getBitmap();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, getImageUri(this,bitmap1));
        startActivity(Intent.createChooser(share,"Share via"));
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
         return Uri.parse(path);
    }



    public static void setCurrentFragmentwithData(int position,String tag){
        tageeee = tag;
        isDataAvailable = true;
        viewPager.setCurrentItem(position);
        mAdapter.notifyDataSetChanged();
    }
    public static boolean isDataAvailable = false;
    public static String tageeee;

}
