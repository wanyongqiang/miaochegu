package com.miaochegu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miaochegu.R;
import com.miaochegu.adapter.WaiteAssessAdapter;
import com.miaochegu.util.ListItemClickHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*****************************************
 * 可编辑任务
 *
 * @author wyq
 *         created at  2017/8/14 15:14
 ****************************************/

public class WaiteAssessActivity extends Activity implements WaiteAssessAdapter.OnItemClickListener, ListItemClickHelp, XRecyclerView.LoadingListener {

    Context context;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_assess)
    XRecyclerView rlAssess;
    @BindView(R.id.mProgess)
    ProgressBar mProgess;
    private WaiteAssessAdapter mWaiteAssessAdapter;
    private List<AVObject> data = new ArrayList<>();
    private String type;
    List<AVObject> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waite_assess);
        ButterKnife.bind(this);
        context = this;

        setRecyclerView();
    }

    private void getData() {
        mList.clear();
        AVQuery<AVObject> avQuery = new AVQuery<>("Task");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mWaiteAssessAdapter.upRes(list);
                    mProgess.setVisibility(View.GONE);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setRecyclerView() {
        //设置布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rlAssess.setLayoutManager(layoutManager);
        //设置动画
        rlAssess.setItemAnimator(new DefaultItemAnimator());
        rlAssess.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rlAssess.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rlAssess.setArrowImageView(R.drawable.iconfont_downgrey);
        rlAssess.setLoadingMoreEnabled(true);
        rlAssess.setLoadingListener(this);

        mWaiteAssessAdapter = new WaiteAssessAdapter(context, null, this);
        mWaiteAssessAdapter.setmOnItemeClickListener(this);
        rlAssess.setAdapter(mWaiteAssessAdapter);

        getData();
    }


    @Override
    public void onItemeClick(View view, int position, String cID, String tID, String sID) {
        startActivity(new Intent(this, CarListDetailActivity.class)
                .putExtra("CARID", cID).putExtra("TASKID", tID).putExtra("AUDITID", sID).putExtra("TYPE", 111));
    }

    @Override
    public void onClick(View item, int position, int which, String id, String tID) {
        switch (which) {
            case R.id.tv_chakan:
                break;
        }
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                this.finish();
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
                bottomDialog.dismiss();
            }
        });
    }

    @Override
    public void onRefresh() {
        rlAssess.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        rlAssess.loadMoreComplete();
    }
}
