package com.miaochegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.miaochegu.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.miaochegu.R.id.tv_cartype;

/**
 * Created by roztop on 2017/8/7.
 */

public class CarListDetailActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_because)
    TextView tvBecause;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(tv_cartype)
    TextView tvCartype;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_vin)
    TextView tvVin;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_km_and_price)
    TextView tvKmAndPrice;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_update)
    TextView tvUpdate;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private String carid = "";
    private String taskid = "";
    private String auditid;

    private static Handler handler = new Handler();
    private String strNew;
    private String usenature;
    private String cprice;
    private String ckm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carlist_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        carid = intent.getStringExtra("CARID");
        taskid = intent.getStringExtra("TASKID");
        auditid = intent.getStringExtra("AUDITID");
        int type = intent.getIntExtra("TYPE", 0);
        if (type == 111) {
            llBottom.setVisibility(View.VISIBLE);
        } else {
            llBottom.setVisibility(View.GONE);
        }
        initView(carid);
    }

    private void initView(final String carid) {
        tvNumber.setText(taskid);
        AVQuery<AVObject> avQuery = new AVQuery<>("Car");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(final List<AVObject> list, AVException e) {
                if (e == null && list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (carid.equals(list.get(i).get("carid").toString())) {
                            final int pos = i;
                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    tvCartype.setText(list.get(pos).get("cmodels").toString());
                                    tvCity.setText(list.get(pos).get("caddress").toString());
                                    tvVin.setText(list.get(pos).get("vin").toString());
                                    String strOld = list.get(pos).get("cyear").toString();
                                    strNew = getDateString(strOld);
                                    usenature = list.get(pos).get("usenature").toString();
                                    tvYear.setText(strNew + "  " + usenature);
                                    ckm = list.get(pos).get("ckm").toString();
                                    cprice = list.get(pos).get("cprice").toString();
                                    tvKmAndPrice.setText(ckm + "万公里  " + cprice + "万元成交");
                                    if (list.get(pos).get("sceneinfo") != null) {
                                        tvDesc.setText(list.get(pos).get("sceneinfo").toString());
                                    } else {
                                        tvDesc.setText("无");
                                    }
                                }
                            });
                            AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
                            avQuery.orderByDescending("createdAt");
                            avQuery.findInBackground(new FindCallback<AVObject>() {
                                @Override
                                public void done(final List<AVObject> list, AVException e) {
                                    if (e == null && list != null) {
                                        for (int j = 0; j < list.size(); j++) {
                                            if (auditid.equals(list.get(j).get("sid").toString()) && (int) list.get(j).get("atype") == 0) {
                                                final int p = j;
                                                handler.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if (list.get(pos).get("sceneinfo") != null) {
                                                            tvBecause.setText(list.get(p).get("aidea").toString());
                                                        } else {
                                                            tvBecause.setText("无");
                                                        }
                                                        if (list.get(p).get("atime") != null) {
                                                            String string = list.get(p).get("atime").toString();
                                                            tvTime.setText(string != null ? getDateString(string) : "");
                                                        } else {
                                                            tvTime.setText("");
                                                        }
                                                        int aType = (Integer) list.get(p).get("atype");
                                                        if (aType == 0) {
                                                            tvType.setText("未完成");
                                                        } else if (aType == 1) {
                                                            tvType.setText("待评审");
                                                        } else if (aType == 2) {
                                                            tvType.setText("审核中");
                                                        } else if (aType == 3) {
                                                            tvType.setText("已通过");
                                                        } else if (aType == 4) {
                                                            tvType.setText("未通过");
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    } else {
                                        e.printStackTrace();
                                    }
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

    @OnClick({R.id.iv_back, R.id.tv_cancel, R.id.tv_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                this.finish();
                break;
            case R.id.tv_cancel://取消
                this.finish();
                break;
            case R.id.tv_update://修改
                String value = tvCity.getText().toString();
                String value1 = tvVin.getText().toString();
                String value2 = tvCartype.getText().toString();
                startActivity(new Intent(this, EditorTaskActivity.class).putExtra("A", value).putExtra("B", value1)
                        .putExtra("C", value2).putExtra("D", strNew).putExtra("E", ckm).putExtra("F", cprice).putExtra("G", usenature)
                        .putExtra("H", tvDesc.getText().toString()));
                break;
        }
    }

}
