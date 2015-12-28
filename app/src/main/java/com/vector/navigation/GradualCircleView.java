package com.vector.navigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 可修改透明度的圆点
 *
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/8/13.
 */
public class GradualCircleView extends View {

    private int mDefaultColor = 0xffbfbfbf;
    private int mColor;
    private int mAlpha;
    private int mRadius;
    private Paint mPaint;
    private Paint mDefaultPaint;

    public GradualCircleView(Context context) {
        this(context,null);
    }

    public GradualCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradualCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRadius = context.getResources().getDimensionPixelOffset(R.dimen.navigation_radius);
        if(attrs!=null){
            mColor = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android","color",mDefaultColor);
            mAlpha = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android","alpha",0);
        }else{
            mColor = mDefaultColor;
        }
        init();
    }

    private void init(){
        mPaint = new Paint();
        mDefaultPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setAlpha(mAlpha);
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setColor(mDefaultColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mRadius*2,mRadius*2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mRadius,mRadius,mRadius,mDefaultPaint);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
        mPaint.setColor(mColor);
        invalidate();
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
        requestLayout();
    }

    /**
     *
     * @param alpha  range [0..255]
     */
    public void setAlpha(int alpha){
        mPaint.setAlpha(alpha);
        invalidate();
    }

}
