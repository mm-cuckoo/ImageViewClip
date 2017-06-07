package com.cfox.ivcliplib;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * <br/> ****************************************************
 * <br/> Project Name : ImageViewClip
 * <br/> Create Date : 2017/6/6
 * <br/> Author : CFOX
 * <br/> Github : https://github.com/CNCFOX
 * <br/> Blog : http://www.cfox.site
 * <br/> Msg :
 * <br/> Remark :
 * <br/> ****************************************************
 */
public class CImageView extends ImageView {

    private int mWidth;
    private int mHeight;

    private boolean isCrop = false;


    public CImageView(Context context) {
        super(context);
    }

    public CImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isCrop) {
            synchronized (CImageView.class) {
                if (!isCrop) {
                    isCrop = true;
                    crop();
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        CImageUtils.instance(getContext()).recycle();
    }

    public void crop() {
        if (mWidth > 0 && mHeight > 0) {
            CImageUtils.instance(getContext()).crop(this,mWidth,mHeight);
        }
    }
}
