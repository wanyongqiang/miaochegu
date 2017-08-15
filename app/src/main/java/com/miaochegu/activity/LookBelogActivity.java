package com.miaochegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.miaochegu.R;
import com.miaochegu.util.StatusbarUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    private static Handler handler = new Handler();
    private String tid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_belog);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        cid = intent.getStringExtra("CID");
        tid = intent.getStringExtra("TID");

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initview();
    }

    private void initview() {
        tvNumber.setText("评估单号：" + tid);
        AVQuery<AVObject> avQuery = new AVQuery<>("Car");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(final List<AVObject> list, AVException e) {
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (cid.equals(list.get(i).get("carid").toString())) {
                            final int p = i;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tvContent.setText("[" + list.get(p).get("caddress").toString() + "]" + list.get(p).get("cmodels"));
                                    if (list.get(p).get("createdAt") != null) {
                                        String string = list.get(p).get("createdAt").toString();
                                        tvTime.setText(string != null ? getDateString(string) : "");
                                    } else {
                                        tvTime.setText("");
                                    }
                                    tvFs.setText("￥" + list.get(p).get("cprice").toString() + "万元");
                                    tvVincode.setText(list.get(p).get("vin").toString());
                                    if (list.get(p).get("cyear") != null) {
                                        tvDate.setText(getDateString(list.get(p).get("cyear").toString()));
                                    } else {
                                        tvDate.setText("");
                                    }
                                    tvAddress.setText(list.get(p).get("caddress") + "");
                                    tvKm.setText(list.get(p).get("ckm") + "");
                                    tvPrice.setText(list.get(p).get("clinchprice") + "");
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    private String getDateString(String strOld) {
        Date date = new Date();
        date.parse(strOld);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
