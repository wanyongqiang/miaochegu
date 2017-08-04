package com.miaochegu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;
import com.miaochegu.GettingStartedApp;
import com.miaochegu.R;
import com.miaochegu.adapter.YTGWaiteAssessAdapter;
import com.miaochegu.util.ListItemClickHelp;
import com.miaochegu.util.StatusbarUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by toshiba on 2017/7/11.
 */

public class WaiteAssessActivity extends Activity implements YTGWaiteAssessAdapter.OnItemClickListener, ListItemClickHelp {

    Context context;
    TextView tvTitle;
    private ImageView iv_back;
    private RecyclerView rlAssess;
    private YTGWaiteAssessAdapter mYTGWaiteAssessAdapter;
    private List<AVObject> data = new ArrayList<>();
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_waite_assess);
        context = this;
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rlAssess = (RecyclerView) findViewById(R.id.rl_assess);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if ("DPG".equals(type)) {
            tvTitle.setText("待评审");
            GettingStartedApp.getInstance().setTempStr("DPG");
        } else if ("WTG".equals(type)) {
            tvTitle.setText("未通过");
            GettingStartedApp.getInstance().setTempStr("WTG");
        } else if ("YTG".equals(type)) {
            tvTitle.setText("已通过");
            GettingStartedApp.getInstance().setTempStr("YTG");
        } else if ("WWC".equals(type)) {
            tvTitle.setText("未完成");
            GettingStartedApp.getInstance().setTempStr("WWC");
        } else if ("PSZ".equals(type)) {
            tvTitle.setText("评审中");
            GettingStartedApp.getInstance().setTempStr("PSZ");
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaiteAssessActivity.this.finish();
            }
        });

        setRecyclerView();
    }

    private void setRecyclerView() {
        //设置布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rlAssess.setLayoutManager(layoutManager);
        //设置动画
        rlAssess.setItemAnimator(new DefaultItemAnimator());
        mYTGWaiteAssessAdapter = new YTGWaiteAssessAdapter(context, data, this);
        mYTGWaiteAssessAdapter.setmOnItemeClickListener(this);
        rlAssess.setAdapter(mYTGWaiteAssessAdapter);
    }

    @Override
    public void onItemeClick(View view, int position) {
    }

    @Override
    public void onClick(View item, int position, int which, String id) {
        switch (which) {
            case R.id.tv_chakan:
                showDialog();
                break;
        }
    }

    /**
     * 网络断开弹窗
     */
    private void showDialog() {
        final Dialog bottomDialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_submit_infos, null);
        bottomDialog.setContentView(contentView);
        Button tvNo = (Button) contentView.findViewById(R.id.alert_exit_no);
        Button tvYes = (Button) contentView.findViewById(R.id.alert_exit_yes);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.setCanceledOnTouchOutside(false);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WaiteAssessActivity.this, LookBelogActivity.class));
                bottomDialog.dismiss();
            }
        });
    }
}
