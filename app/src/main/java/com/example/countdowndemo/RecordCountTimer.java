package com.example.countdowndemo;

import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Description：
 * Created by kang on 2018/1/24.
 */

public class RecordCountTimer extends CountDownTimer {
    private static final int TIME_COUNT = 3100; //倒计时总时间为3.1s，时间防止从2s开始显示（以倒计时3s为例子）
    private static final int TIME_INTERVAL = 1000; //倒计时间隔为1s
    private View view; //要设置动画的view

    private boolean mIsAnimStart;
    private AlphaAnimation alphaAnimation; //渐变动画
    private ScaleAnimation scaleAnimation; //缩放动画

    private OnCountTimerListener mOnCountTimerListener;

    public void setOnCountTimerListener(OnCountTimerListener onFinishListener) {
        this.mOnCountTimerListener = onFinishListener;
    }

    //倒计时开始和结束的监听
    public interface OnCountTimerListener {
        void onStart();

        void onFinish();
    }

    /**
     * @param view 要执行动画的view
     */
    public RecordCountTimer(View view) {
        this(TIME_COUNT, TIME_INTERVAL, view);
    }

    /**
     * 倒计时的view  可以是TextView、Button或者ImageView
     *
     * @param millisInFuture    表示从计时开始到结束的毫秒数
     * @param countDownInterval 表示onTick(long)回调的间隔时间
     * @param view              要执行动画的view
     */
    public RecordCountTimer(long millisInFuture, long countDownInterval, View view) {
        super(millisInFuture, countDownInterval);
        this.view = view;

        // 设置透明度渐变动画
        alphaAnimation = new AlphaAnimation(0, 1);
        // 设置动画持续时间
        alphaAnimation.setDuration(500);

        // 设置缩放渐变动画
        scaleAnimation = new ScaleAnimation(0.1f, 1f, 0.1f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500); //设置动画时长
        scaleAnimation.setRepeatCount(1); //设置重复次数
        scaleAnimation.setRepeatMode(Animation.REVERSE); //设置重复模式为倒转，即先从小变大再变小
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mOnCountTimerListener != null) {
                    mOnCountTimerListener.onFinish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void onTick(long millisUntilFinished) {
        if (!mIsAnimStart) {
            mIsAnimStart = true;
            if (mOnCountTimerListener != null) {
                mOnCountTimerListener.onStart();
            }
        }

        //每隔一秒修改一次UI
        if (view instanceof ImageView) { // 如果是图片，在此判断，每隔一秒，改变一次图片
            if (millisUntilFinished / 1000 == 1) {
                ((ImageView) view).setImageResource(R.drawable.img_show_count_down_1);
            } else if (millisUntilFinished / 1000 == 2) {
                ((ImageView) view).setImageResource(R.drawable.img_show_count_down_2);
            } else if (millisUntilFinished / 1000 == 3) {
                ((ImageView) view).setImageResource(R.drawable.img_show_count_down_3);
            }
        } else if (view instanceof TextView) {
            ((TextView) view).setText(String.valueOf(millisUntilFinished / 1000));
        }

        view.setVisibility(View.VISIBLE);
        view.setEnabled(false);

        view.startAnimation(alphaAnimation);
        view.startAnimation(scaleAnimation);
    }

    @Override
    public void onFinish() {

    }
}
