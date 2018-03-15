package com.example.countdowndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mCountDownNumLayout;
    private TextView mCountDownNumView; //数字倒计时
    private FrameLayout mCountDownPicLayout;
    private ImageView mCountDownPicView; //图片倒计时
    private Button mCountDownNumBtn; //开始数字倒计时按钮
    private Button mCountDownPicBtn; //开始图片倒计时按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCountDownNumLayout = findViewById(R.id.fl_count_timer_num);
        mCountDownNumView = findViewById(R.id.tv_count_timer);
        mCountDownPicLayout = findViewById(R.id.fl_count_timer_pic);
        mCountDownPicView = findViewById(R.id.iv_count_timer);
        mCountDownNumBtn = findViewById(R.id.btn_count_down_num);
        mCountDownNumBtn.setOnClickListener(this);
        mCountDownPicBtn = findViewById(R.id.btn_count_down_pic);
        mCountDownPicBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCountDownNumBtn) {
            RecordCountTimer countTimer = new RecordCountTimer(mCountDownNumView);
            countTimer.setOnCountTimerListener(new RecordCountTimer.OnCountTimerListener() {
                @Override
                public void onStart() {
                    //倒计时开始
                    mCountDownNumBtn.setVisibility(View.GONE);
                    mCountDownPicBtn.setVisibility(View.GONE);
                    mCountDownNumLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFinish() {
                    //倒计时结束
                    mCountDownNumLayout.setVisibility(View.GONE);
                    mCountDownNumBtn.setVisibility(View.VISIBLE);
                    mCountDownPicBtn.setVisibility(View.VISIBLE);
                }
            });
            countTimer.start();
        } else if (v == mCountDownPicBtn) {
            RecordCountTimer countTimer = new RecordCountTimer(mCountDownPicView);
            countTimer.setOnCountTimerListener(new RecordCountTimer.OnCountTimerListener() {
                @Override
                public void onStart() {
                    //倒计时开始
                    mCountDownPicBtn.setVisibility(View.GONE);
                    mCountDownNumBtn.setVisibility(View.GONE);
                    mCountDownPicLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFinish() {
                    //倒计时结束
                    mCountDownPicLayout.setVisibility(View.GONE);
                    mCountDownPicBtn.setVisibility(View.VISIBLE);
                    mCountDownNumBtn.setVisibility(View.VISIBLE);
                }
            });
            countTimer.start();
        }
    }
}
