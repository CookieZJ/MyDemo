package com.feicuiedu.gitdroid.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.splash.adapter.SplashPagerAdapter;
import com.feicuiedu.gitdroid.splash.pager.Pager2;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2016/7/26.
 */
public class SplashPagerFragment extends Fragment {
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    SplashPagerAdapter spa;
    @Bind(R.id.ivPhoneBlank)
    ImageView ivPhoneBlank;
    @Bind(R.id.ivPhoneFont)
    ImageView ivPhoneFont;
    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;
    FrameLayout splashcontent;
    private int colorGreen;
    private int colorYellow;
    private int colorRed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_splash_pager, null);
        ButterKnife.bind(this, view);
        splashcontent = (FrameLayout) view.findViewById(R.id.splashcontent);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spa = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(spa);
        indicator.setViewPager(viewPager);
        // 添加ViewPager监听(为了动画)
        viewPager.addOnPageChangeListener(pageColorListener);
        viewPager.addOnPageChangeListener(phoneViewListener);
        colorGreen = getResources().getColor(R.color.colorGreen);
        colorYellow = getResources().getColor(R.color.colorYellow);
        colorRed = getResources().getColor(R.color.colorRed);
    }

    // 主要为了做背景颜色渐变处理
    private ViewPager.OnPageChangeListener pageColorListener = new ViewPager.OnPageChangeListener() {
        private ArgbEvaluator evaluator = new ArgbEvaluator();

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 0) {
                int color = (int) evaluator.evaluate(positionOffset, colorGreen, colorRed);
                splashcontent.setBackgroundColor(color);
                return;
            }
            if (position == 1) {
                int color = (int) evaluator.evaluate(positionOffset, colorRed, colorYellow);
                splashcontent.setBackgroundColor(color);
                return;
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // 主要为了做"手机"的动画效果处理(平移、缩放、透明度变化)
    private ViewPager.OnPageChangeListener phoneViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 0) {
                float scale = 0.3f + 0.7f * positionOffset;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                int location = (int) ((positionOffset-1)*260);
                layoutPhone.setTranslationX(location);
                ivPhoneFont.setAlpha(positionOffset);
                return;
            }
            if(position == 1){
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
             if(position == 2){
                Pager2 pager2 = (Pager2) spa.getView(position);
                 pager2.showAnim();
             }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
