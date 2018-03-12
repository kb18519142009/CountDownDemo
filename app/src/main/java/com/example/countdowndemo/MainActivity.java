package com.example.countdowndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fl_count_timer_num;
    private TextView mCountTimerNumView; //数字倒计时
    private FrameLayout fl_count_timer_pic;
    private ImageView mCountTimerPicView; //图片倒计时
    private Button btn_count_down_num; //开始数字倒计时按钮
    private Button btn_count_down_pic; //开始图片倒计时按钮
    private RecordCountTimer mCountTimer; //倒计时

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_count_timer_num = findViewById(R.id.fl_count_timer_num);
        mCountTimerNumView = findViewById(R.id.tv_count_timer);
        fl_count_timer_pic = findViewById(R.id.fl_count_timer_pic);
        mCountTimerPicView = findViewById(R.id.iv_count_timer);
        btn_count_down_num = findViewById(R.id.btn_count_down_num);
        btn_count_down_num.setOnClickListener(this);
        btn_count_down_pic = findViewById(R.id.btn_count_down_pic);
        btn_count_down_pic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_count_down_num) {
            mCountTimer = new RecordCountTimer(mCountTimerNumView);
            mCountTimer.setOnCountTimerListener(new RecordCountTimer.OnCountTimerListener() {
                @Override
                public void onStart() {
                    //倒计时开始
                    btn_count_down_num.setVisibility(View.GONE);
                    btn_count_down_pic.setVisibility(View.GONE);
                    fl_count_timer_num.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFinish() {
                    //倒计时结束
                    fl_count_timer_num.setVisibility(View.GONE);
                    btn_count_down_num.setVisibility(View.VISIBLE);
                    btn_count_down_pic.setVisibility(View.VISIBLE);
                }
            });
            mCountTimer.start();
        } else if (v == btn_count_down_pic) {
            mCountTimer = new RecordCountTimer(mCountTimerPicView);
            mCountTimer.setOnCountTimerListener(new RecordCountTimer.OnCountTimerListener() {
                @Override
                public void onStart() {
                    //倒计时开始
                    btn_count_down_pic.setVisibility(View.GONE);
                    btn_count_down_num.setVisibility(View.GONE);
                    fl_count_timer_pic.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFinish() {
                    //倒计时结束
                    fl_count_timer_pic.setVisibility(View.GONE);
                    btn_count_down_pic.setVisibility(View.VISIBLE);
                    btn_count_down_num.setVisibility(View.VISIBLE);
                }
            });
            mCountTimer.start();
        }
    }
}
