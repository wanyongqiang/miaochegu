package com.miaochegu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.miaochegu.GettingStartedApp;
import com.miaochegu.R;
import com.miaochegu.fragment.BaseFragment;
import com.miaochegu.fragment.DPSProcessFragment;
import com.miaochegu.fragment.PSZProcessFragment;
import com.miaochegu.fragment.WTGProcessFragment;
import com.miaochegu.fragment.WWCProcessFragment;
import com.miaochegu.fragment.YTGProcessFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by roztop on 2017/7/17.
 */

public class ReviewProcessActivity extends AppCompatActivity {

    @BindView(R.id.tab_FindFragment_title)
    XTabLayout tabFindFragmentTitle;
    @BindView(R.id.vp_FindFragment_pager)
    ViewPager vpFindFragmentPager;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.mProgess)
    ProgressBar mProgess;

    private String[] mTabTitles = new String[]{"未完成", "待审核", "评审中", "未通过", "已通过"};
    private BaseFragment[] fragments = {new WWCProcessFragment(), new DPSProcessFragment(), new PSZProcessFragment()
            , new WTGProcessFragment(), new YTGProcessFragment()};
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_result);
        ButterKnife.bind(this);

        //注册EventBus
        EventBus.getDefault().register(this);
//        mProgess.setVisibility(View.VISIBLE);
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

        vpFindFragmentPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        vpFindFragmentPager.setOffscreenPageLimit(0);
        //设置TabLayout的模式
        tabFindFragmentTitle.setTabMode(TabLayout.MODE_FIXED);
        tabFindFragmentTitle.setupWithViewPager(vpFindFragmentPager);

        BaseFragment fragment = fragments[0];
        fragment.initData("");
        vpFindFragmentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 方案二：页面选中时才去加载数据
                BaseFragment fragment = fragments[position];
                fragment.initData(type);
//                EventBus.getDefault().post(new String(type));
                if (position == 1) {
                    tvTitle.setText("待评审");
                    GettingStartedApp.getInstance().setTempStr("DPG");
                } else if (position == 3) {
                    tvTitle.setText("未通过");
                    GettingStartedApp.getInstance().setTempStr("WTG");
                } else if (position == 4) {
                    tvTitle.setText("已通过");
                    GettingStartedApp.getInstance().setTempStr("YTG");
                } else if (position == 0) {
                    tvTitle.setText("未完成");
                    GettingStartedApp.getInstance().setTempStr("WWC");
                } else if (position == 2) {
                    tvTitle.setText("评审中");
                    GettingStartedApp.getInstance().setTempStr("PSZ");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String event) {
        mProgess.setVisibility(View.GONE);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                this.finish();
                break;
        }
    }

    private class FragmentAdapter extends FragmentPagerAdapter {
        // FragmentPagerAdapter与FragmentStatePagerAdapter区别
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return mTabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (mTabTitles != null) {
                return mTabTitles[position];
            }
            return super.getPageTitle(position);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
