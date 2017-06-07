package com.cfox.imageviewclip;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.cfox.ivcliplib.CImageUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView mBaseView;
    private ImageView mImgA_A;
    private ImageView mImgB_C;
    private ImageView mImgC_B;
    private ImageView mImgD_D_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBaseView = (ImageView) findViewById(R.id.img_base1);
        mImgA_A = (ImageView) findViewById(R.id.img_a_a);
        mImgB_C = (ImageView) findViewById(R.id.img_b_c);
        mImgC_B = (ImageView) findViewById(R.id.img_c_b);
        mImgD_D_s = (ImageView) findViewById(R.id.img_d_d_s);

        mBaseView.setImageResource(R.mipmap.imgs);

        Bitmap baseBitmap = ((BitmapDrawable)mBaseView.getDrawable()).getBitmap();

        Bitmap clipBitmap = CImageUtils.instance(this).cropToBitmap(baseBitmap,320,320);
        mImgA_A.setImageBitmap(clipBitmap);

        clipBitmap = CImageUtils.instance(this).cropToBitmap(baseBitmap,320,440);
        mImgB_C.setImageBitmap(clipBitmap);

        clipBitmap = CImageUtils.instance(this).cropToBitmap(baseBitmap,440,320);
        mImgC_B.setImageBitmap(clipBitmap);

        clipBitmap = CImageUtils.instance(this).cropToBitmap(mBaseView,400,400);
        mImgD_D_s.setImageBitmap(clipBitmap);
    }
}
