package com.miaochegu.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miaochegu.R;
import com.miaochegu.activity.LookBelogActivity;
import com.miaochegu.adapter.YTGWaiteAssessAdapter;
import com.miaochegu.util.ListItemClickHelp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roztop on 2017/7/29.
 */

public class YTGProcessFragment extends BaseFragment implements XRecyclerView.LoadingListener, YTGWaiteAssessAdapter.OnItemClickListener, ListItemClickHelp {

    Context context;
    @BindView(R.id.rl_assess)
    XRecyclerView rlAssess;
    private YTGWaiteAssessAdapter mYTGWaiteAssessAdapter;
    List<AVObject> mList = new ArrayList<>();

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_waite_assess, null);
        context = getActivity();
        ButterKnife.bind(this, view);

        //注册EventBus
        EventBus.getDefault().register(this);
        setRecyclerView();
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String event) {

        Log.d("harvic", event);
        getData(event);
    }

    private void getData(String str) {
        mList.clear();
        AVQuery<AVObject> avQuery = new AVQuery<>("Task");
        avQuery.orderByDescending("createdAt");
        avQuery.include("cid");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mYTGWaiteAssessAdapter.upRes(list);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public static YTGProcessFragment getMembers(String str) {
        YTGProcessFragment fragment = new YTGProcessFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TEST", str);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void setRecyclerView() {
        //设置布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rlAssess.setLayoutManager(layoutManager);
        //设置动画
        rlAssess.setItemAnimator(new DefaultItemAnimator());
        rlAssess.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rlAssess.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rlAssess.setArrowImageView(R.drawable.iconfont_downgrey);
        rlAssess.setLoadingMoreEnabled(true);
        rlAssess.setLoadingListener(this);

        mYTGWaiteAssessAdapter = new YTGWaiteAssessAdapter(context, null, this);
        mYTGWaiteAssessAdapter.setmOnItemeClickListener(this);
        rlAssess.setAdapter(mYTGWaiteAssessAdapter);
    }


    /**
     * 网络断开弹窗
     */
    private void showDialog(final String cid) {
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
                startActivity(new Intent(getActivity(), LookBelogActivity.class).putExtra("CID", cid));
                bottomDialog.dismiss();
            }
        });
    }

    @Override
    public void initData(String str) {
        getData(str);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    public void onItemeClick(View view, int position) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onClick(View item, int position, int which, String id) {
        switch (which) {
            case R.id.tv_chakan:
                showDialog(id);
                break;
        }
    }
}
