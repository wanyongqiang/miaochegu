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
import com.avos.avoscloud.SaveCallback;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miaochegu.R;
import com.miaochegu.activity.CarListDetailActivity;
import com.miaochegu.adapter.WWCWaiteAssessAdapter;
import com.miaochegu.model.CarInfoModel;
import com.miaochegu.util.ListItemClickHelp;
import com.miaochegu.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roztop on 2017/7/29.
 */

public class WWCProcessFragment extends BaseFragment implements XRecyclerView.LoadingListener, WWCWaiteAssessAdapter.OnItemClickListener, ListItemClickHelp {

    Context context;
    @BindView(R.id.rl_assess)
    XRecyclerView rlAssess;
    @BindView(R.id.mProgess)
    ProgressBar mProgess;

    int pos = 0;
    List<CarInfoModel> mList = new ArrayList<>();
    private WWCWaiteAssessAdapter mWWCWaiteAssessAdapter;

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_waite_assess_two, null);
        context = getActivity();
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        mProgess.setVisibility(View.VISIBLE);
        setRecyclerView();
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String event) {
        if ("freshData".equals(event)) {
            if (System.currentTimeMillis() - pretimr >= 3000) {
                Log.e("aaa", "hello");
                AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
                avQuery.whereEqualTo("atype", 0);
                avQuery.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        mList.clear();
                        pos = 0;
                        setData(list);
                    }
                });
            }
        }
    }

    private long pretimr = 0;

    private synchronized void getData(String str) {
        if (System.currentTimeMillis() - pretimr >= 3000) {
            Log.e("aaa", "hello");
            AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
            avQuery.whereEqualTo("atype", 0);
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    mList.clear();
                    pos = 0;
                    setData(list);
                }
            });
        }
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

        mWWCWaiteAssessAdapter = new WWCWaiteAssessAdapter(context, null, this);
        mWWCWaiteAssessAdapter.setmOnItemeClickListener(this);
        rlAssess.setAdapter(mWWCWaiteAssessAdapter);

        if (System.currentTimeMillis() - pretimr >= 3000) {
            Log.e("aaa", "hello");
            AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
            avQuery.whereEqualTo("atype", 0);
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    mList.clear();
                    pos = 0;
                    setData(list);
                }
            });
        }
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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh() {
        if (System.currentTimeMillis() - pretimr >= 3000) {
            Log.e("aaa", "hello");
            AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
            avQuery.whereEqualTo("atype", 0);
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    mList.clear();
                    mWWCWaiteAssessAdapter.addRes(mList);
                    pos = 0;
                    setData(list);
                }
            });
        }
        rlAssess.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        rlAssess.loadMoreComplete();
    }

    @Override
    public void onClick(View item, int position, int which, String id, String tID) {
        switch (which) {
            case R.id.tv_fs://提交
                AVQuery<AVObject> avQuery = new AVQuery<>("Task");
                avQuery.whereEqualTo("tid", Integer.parseInt(tID));
                avQuery.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        int sid = (int) list.get(0).get("sid");
                        AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
                        avQuery.whereEqualTo("sid", sid);
                        avQuery.findInBackground(new FindCallback<AVObject>() {
                            @Override
                            public void done(List<AVObject> list, AVException e) {
                                AVObject avObject = list.get(0);
                                avObject.put("atype", 1);
                                avObject.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(AVException e) {
                                        ToastUtil.show("提交成功");
                                        rlAssess.refresh();
                                    }
                                });
                            }
                        });
                    }
                });
                break;
        }
    }

    @Override
    public void onItemeClick(View view, int position, String cID, String tID, String sID) {
        startActivity(new Intent(getActivity(), CarListDetailActivity.class)
                .putExtra("CARID", cID).putExtra("TASKID", tID).putExtra("AUDITID", sID).putExtra("TYPE", 111));
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
                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (mProgess != null) {
                                            mProgess.setVisibility(View.GONE);
                                        }
                                    }
                                });
                                if (list != null && list.size() > 0) {
                                    String cmodels = (String) list.get(0).get("cmodels");
                                    carInfoModel.setCarType(cmodels);
                                    carInfoModel.setCar_id(carid);
                                    pos++;
                                    mList.add(carInfoModel);
                                    Log.e("aaa", (pos == data.size()) + "," + pos);
                                    if (pos == data.size()) {
                                        if (mWWCWaiteAssessAdapter != null && mList != null) {
                                            mWWCWaiteAssessAdapter.upRes(mList);
                                        }
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
        } else {
            if (mProgess != null) {
                mProgess.setVisibility(View.GONE);
                mWWCWaiteAssessAdapter.upRes(mList);
            }
        }
    }

    private String getDateString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
