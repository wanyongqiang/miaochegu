package com.miaochegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miaochegu.R;


/**
 * Created by toshiba on 2017/7/11.
 */

public class LookResultActivity extends Activity {

    TextView tv_chakan, tv_complete;
    LinearLayout ll_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_suc);

        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_chakan = (TextView) findViewById(R.id.tv_chakan);
        tv_complete = (TextView) findViewById(R.id.tv_complete);

        tv_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LookResultActivity.this, CarInfoActivity.class).putExtra("type", "mine"));
                finish();
            }
        });
        tv_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LookResultActivity.this, CarInfoActivity.class).putExtra("type", "gj"));
                finish();
            }
        });
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
