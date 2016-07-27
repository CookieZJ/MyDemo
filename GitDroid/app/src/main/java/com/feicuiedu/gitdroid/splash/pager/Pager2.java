package com.feicuiedu.gitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.feicuiedu.gitdroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：yuanchao on 2016/7/26 0026 10:53
 * 邮箱：yuanchao@feicuiedu.com
 */
public class Pager2 extends FrameLayout {


    @Bind(R.id.ivBubble1)
   ImageView ivBubble1;
    @Bind(R.id.ivBubble2)
    ImageView ivBubble2;
    @Bind(R.id.ivBubble3)
    ImageView ivBubble3;

    public Pager2(Context context) {
        this(context, null);
    }

    public Pager2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pager2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2, this, true);
        ButterKnife.bind(this, view);
    }

     public void showAnim(){
         ivBubble1.setVisibility(GONE);
         ivBubble2.setVisibility(GONE);
         ivBubble3.setVisibility(GONE);
         postDelayed(new Runnable() {
             @Override
             public void run() {
                 ivBubble1.setVisibility(VISIBLE);
                 YoYo.with(Techniques.FadeInRight).duration(800).playOn(ivBubble1);

             }
         }, 50);
         postDelayed(new Runnable() {
             @Override
             public void run() {
                 ivBubble2.setVisibility(VISIBLE);
                 YoYo.with(Techniques.FadeInRight).duration(800).playOn(ivBubble2);

             }
         },1050);
         postDelayed(new Runnable() {
             @Override
             public void run() {
                 ivBubble3.setVisibility(VISIBLE);
                 YoYo.with(Techniques.FadeInRight).duration(800).playOn(ivBubble3);
             }
         },1550);
     }
}
