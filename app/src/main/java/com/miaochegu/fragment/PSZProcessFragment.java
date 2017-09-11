package com.miaochegu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miaochegu.R;
import com.miaochegu.activity.CarListDetailActivity;
import com.miaochegu.adapter.PSZWaiteAssessAdapter;
import com.miaochegu.model.CarInfoModel;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roztop on 2017/7/29.
 */

public class PSZProcessFragment extends BaseFragment implements XRecyclerView.LoadingListener, PSZWaiteAssessAdapter.OnItemClickListener {

    Context context;
    @BindView(R.id.rl_assess)
    XRecyclerView rlAssess;
    @BindView(R.id.mProgess)
    ProgressBar mProgess;
    private PSZWaiteAssessAdapter mPSZWaiteAssessAdapter;
    int pos = 0;
    List<CarInfoModel> mList = new ArrayList<>();

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_waite_assess_two, null);
        context = getActivity();
        ButterKnife.bind(this, view);
        mProgess.setVisibility(View.VISIBLE);
        setRecyclerView();
        return view;
    }

    private long pretimr = 0;

    private synchronized void getData(String str) {
        if (System.currentTimeMillis() - pretimr >= 3000) {
            Log.e("aaa", "hello");
            AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
            avQuery.whereEqualTo("atype", 2);
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            mProgess.setVisibility(View.GONE);
                        }
                    });
                    mList.clear();
                    pos = 0;
                    setData(list);
                }
            });

        }
    }

    public static PSZProcessFragment getMembers(String str) {
        PSZProcessFragment fragment = new PSZProcessFragment();
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

        mPSZWaiteAssessAdapter = new PSZWaiteAssessAdapter(context, null);
        mPSZWaiteAssessAdapter.setmOnItemeClickListener(this);
        rlAssess.setAdapter(mPSZWaiteAssessAdapter);
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
    }

    @Override
    public void onItemeClick(View view, int position, String cID, String tID,String sID) {
        startActivity(new Intent(getActivity(), CarListDetailActivity.class)
                .putExtra("CARID", cID).putExtra("TASKID", tID).putExtra("AUDITID", sID).putExtra("TYPE", 0));
    }

    @Override
    public void onRefresh() {
        rlAssess.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        rlAssess.loadMoreComplete();
    }

    public synchronized void setData(final List<AVObject> data) {
        final CarInfoModel carInfoModel = new CarInfoModel();
        if (data != null && data.size() > 0 && data.size() > pos) {
            final int sid = (int) data.get(pos).get("sid");
            AVQuery<AVObject> avQuery = new AVQuery<>("Task");
            avQuery.whereEqualTo("sid", sid);
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (list != null && list.size() > 0) {
                        int tid = (int) list.get(0).get("tid");
                        carInfoModel.setTask_id(tid);
                        carInfoModel.setAudit_id(sid);
                        carInfoModel.setTime(getDateString((Date) list.get(0).get("tcreatetime")));
                        final int carid = (int) list.get(0).get("cid");
                        AVQuery<AVObject> avQuery = new AVQuery<>("Car");
                        avQuery.whereEqualTo("carid", carid);
                        avQuery.findInBackground(new FindCallback<AVObject>() {
                            @Override
                            public void done(List<AVObject> list, AVException e) {
                                if (list != null && list.size() > 0) {
                                    String cmodels = (String) list.get(0).get("cmodels");
                                    carInfoModel.setCarType(cmodels);
                                    carInfoModel.setCar_id(carid);
                                    pos++;
                                    mList.add(carInfoModel);
                                    Log.e("aaa", (pos == data.size()) + "," + pos);
                                    if (pos == data.size()) {
                                        mPSZWaiteAssessAdapter.upRes(mList);
                                        pretimr = System.currentTimeMillis();
                                    } else {
                                        setData(data);
                                    }
                                }
                            }
                        });

                    }
                }
            });
        }
    }

    private String getDateString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
