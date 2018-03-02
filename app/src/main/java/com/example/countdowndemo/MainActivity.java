package com.example.countdowndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private TextView mCountTimerView; // 数字倒计时
        private ImageView mCountTimerView; // 图片倒计时
    private Button mBtnCountDown; //开始按钮

    private RecordCountTimer mCountTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mCountTimerView = findViewById(R.id.tv_count_timer);
        mCountTimerView = findViewById(R.id.iv_count_timer);
        mBtnCountDown = findViewById(R.id.btn_count_down);
        mBtnCountDown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnCountDown) {
            mCountTimer = new RecordCountTimer(mCountTimerView);
            mCountTimer.setOnCountTimerListener(new RecordCountTimer.OnCountTimerListener() {
                @Override
                public void onStart() {
                    //倒计时开始
                    mBtnCountDown.setVisibility(View.GONE);
                }

                @Override
                public void onFinish() {
                    //倒计时结束
                    mCountTimerView.setVisibility(View.GONE);
                    mBtnCountDown.setVisibility(View.VISIBLE);
                }
            });
            mCountTimer.start();
        }
    }
}
