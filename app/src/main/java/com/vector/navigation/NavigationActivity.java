package com.vector.navigation;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 *
 * 导航界面，第一次打开APP的时候会出现
 *
 * Author: vector.huang
 * Email: 642378415@qq.com
 * Date: 2015/12/28
 * Description:<p>{TODO: 用一句话描述}
 */
public class NavigationActivity extends Activity implements ViewPager.OnPageChangeListener{


    private ArrayList<View> mViews;
    private ViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 移除标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 满屏 移除通知栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.navigation_activity);
        mViewHolder = new ViewHolder();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 3) return;
        int alpha = (int) Math.floor(positionOffset * 255);
        switch (position){
            case 0:
                mViewHolder.mCircle2.setAlpha(alpha);
                mViewHolder.mCircle1.setAlpha(255 - alpha);
                break;
            case 1:
                mViewHolder.mCircle3.setAlpha(alpha);
                mViewHolder.mCircle2.setAlpha(255 - alpha);
                break;
            case 2:
                mViewHolder.mCircle4.setAlpha(alpha);
                mViewHolder.mCircle3.setAlpha(255 - alpha);
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 3) {
            Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.window_bg_down_enter);
            mViewHolder.mStartBtn.startAnimation(alphaAnimation);
            mViewHolder.mStartBtn.setVisibility(View.VISIBLE);
        } else {
            if (mViewHolder.mStartBtn.getVisibility() == View.VISIBLE) {
                Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.window_bg_down_exit);
                mViewHolder.mStartBtn.startAnimation(alphaAnimation);
                mViewHolder.mStartBtn.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ViewHolder{
        ViewPager mViewPager;
        GradualCircleView mCircle1;
        GradualCircleView mCircle2;
        GradualCircleView mCircle3;
        GradualCircleView mCircle4;
        Button mStartBtn;

        private CustomPagerAdapter mAdapter;

        ViewHolder(){
            mViewPager = (ViewPager) findViewById(R.id.view_pager);
            mCircle1 = (GradualCircleView) findViewById(R.id.circle_1);
            mCircle2 = (GradualCircleView) findViewById(R.id.circle_2);
            mCircle3 = (GradualCircleView) findViewById(R.id.circle_3);
            mCircle4 = (GradualCircleView) findViewById(R.id.circle_4);
            mStartBtn = (Button) findViewById(R.id.btn);

            mViews = new ArrayList<>(4);
            int[] imgs = new int[]{R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3, R.drawable.ic_3};
            for (int i = 0; i < 4; i++) {
                ImageView imageView = new ImageView(NavigationActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(imgs[i]);
                mViews.add(imageView);
            }
            mAdapter = new CustomPagerAdapter();
            mViewPager.setAdapter(mAdapter);
            mViewPager.addOnPageChangeListener(NavigationActivity.this);
        }

    }

    private class CustomPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViews == null ? 0 : mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

    }
}
