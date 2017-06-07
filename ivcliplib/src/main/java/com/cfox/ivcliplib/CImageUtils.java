package com.cfox.ivcliplib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <br/> ****************************************************
 * <br/> Project Name : ImageViewClip
 * <br/> Create Date : 2017/6/7
 * <br/> Author : CFOX
 * <br/> Github : https://github.com/CNCFOX
 * <br/> Blog : http://www.cfox.site
 * <br/> Msg :
 * <br/> Remark :
 * <br/> ****************************************************
 */
public class CImageUtils {
    static {
        try {
            System.loadLibrary("ivclip");
        } catch (UnsatisfiedLinkError ule) {
            ule.printStackTrace();
        }
    }

    //RGB_565 || ARGB_8888
    private native Bitmap crop(String config, Bitmap src, int width, int height);

    private static CImageUtils sImageUtils = null;
    private static String mConfigData = null;
    private Bitmap mBitmap = null;

    public CImageUtils(Context context) {
        mConfigData = getConfig(context);
    }


    public static CImageUtils instance (Context context) {
        if (sImageUtils == null) {
            synchronized (CImageView.class) {
                if (sImageUtils == null) {
                    sImageUtils = new CImageUtils(context);
                }
            }
        }
        return sImageUtils;
    }

    public void crop(ImageView imageView , int width, int height) {
        mBitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        mBitmap = crop(mConfigData,mBitmap,width,height);
        imageView.setImageBitmap(mBitmap);
    }

    public Bitmap cropToBitmap(ImageView imageView, int width, int height){
        mBitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        return cropToBitmap(mBitmap,width,height);
    }

    public Bitmap cropToBitmap(Bitmap src, int width, int height){
        return crop(mConfigData,src,width,height);
    }


    public void recycle() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
        mConfigData = null;
        sImageUtils = null;
    }

    private String getConfig(Context context) {

        int res = context.getResources().getIdentifier("haarcascade_frontalface_alt", "raw", context.getPackageName());

        String configPath = "/data/data/" + context.getPackageName() + "/" + res;
        File configFile = new File(configPath);

        if (!configFile.exists()){

            InputStream in = null;
            OutputStream out = null;

            try {
                in = context.getResources().openRawResource(res);
                out = new FileOutputStream(configPath);

                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {

                    if (in != null) {
                        in.close();
                        in = null;
                    }

                    if (out != null) {
                        out.flush();
                        out.close();
                        out = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return configPath;
    }
}
