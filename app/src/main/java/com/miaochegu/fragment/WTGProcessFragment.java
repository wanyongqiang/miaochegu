package com.miaochegu.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.miaochegu.activity.LookBelogActivity;
import com.miaochegu.adapter.WTGWaiteAssessAdapter;
import com.miaochegu.model.CarInfoModel;
import com.miaochegu.util.ListItemClickHelp;
import com.miaochegu.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roztop on 2017/7/29.
 */

public class WTGProcessFragment extends BaseFragment implements XRecyclerView.LoadingListener, WTGWaiteAssessAdapter.OnItemClickListener,ListItemClickHelp {

    Context context;
    @BindView(R.id.rl_assess)
    XRecyclerView rlAssess;
    @BindView(R.id.mProgess)
    ProgressBar mProgess;
    private WTGWaiteAssessAdapter mWTGWaiteAssessAdapter;
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
            avQuery.whereEqualTo("atype", 4);
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

    public static WTGProcessFragment getMembers(String str) {
        WTGProcessFragment fragment = new WTGProcessFragment();
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

        mWTGWaiteAssessAdapter = new WTGWaiteAssessAdapter(context, null,this);
        mWTGWaiteAssessAdapter.setmOnItemeClickListener(this);
        rlAssess.setAdapter(mWTGWaiteAssessAdapter);
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
                startActivity(new Intent(getActivity(), LookBelogActivity.class));
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
    }

    @Override
    public void onItemeClick(View view, int position, String cID, String tID,String sID) {
        startActivity(new Intent(getActivity(), CarListDetailActivity.class)
                .putExtra("CARID", cID).putExtra("TASKID", tID).putExtra("AUDITID", sID).putExtra("TYPE", 111));
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
                        carInfoModel.setReason(data.get(pos).get("aidea").toString());
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
                                        mWTGWaiteAssessAdapter.upRes(mList);
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
}
