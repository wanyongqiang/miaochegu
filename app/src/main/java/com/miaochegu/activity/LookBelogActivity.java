package com.miaochegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.miaochegu.R;
import com.miaochegu.util.StatusbarUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by toshiba on 2017/7/11.
 */

public class LookBelogActivity extends Activity {

    ImageView iv_back;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_fs)
    TextView tvFs;
    @BindView(R.id.tv_vincode)
    TextView tvVincode;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_km)
    TextView tvKm;
    @BindView(R.id.tv_b)
    TextView tvB;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_a)
    TextView tvA;
    private String cid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_look_belog);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        cid = intent.getStringExtra("CID");

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initview();
        initData();
    }

    private void initview() {
        AVQuery<AVObject> avQuery = new AVQuery<>("Car");
        avQuery.whereEqualTo("carid", cid);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list != null && list.size() > 0) {
                    tvContent.setText("[" + list.get(0).get("caddress").toString() + "]" + list.get(0).get("cmodels"));
                    tvTime.setText("");
                    tvPrice.setText("￥" + list.get(0).get("cprice").toString() + "万元");
                    tvVincode.setText(list.get(0).get("vin").toString());
                    tvDate.setText(list.get(0).get("cyear") + "");
                    tvAddress.setText(list.get(0).get("caddress") + "");
                    tvKm.setText(list.get(0).get("ckm") + "");
                    tvPrice.setText(list.get(0).get("clinchprice") + "");
                }
            }
        });
    }

    private void initData() {

    }
}
