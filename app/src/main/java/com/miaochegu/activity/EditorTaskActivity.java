package com.miaochegu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esaysidebar.EasySideBarBuilder;
import com.limxing.library.AlertView;
import com.limxing.library.OnConfirmeListener;
import com.miaochegu.R;
import com.miaochegu.adapter.CarInfoAdapter;
import com.miaochegu.adapter.CarNameAdapter;
import com.miaochegu.adapter.CarTypeAdapter;
import com.miaochegu.model.CountryModel;
import com.miaochegu.util.StatusbarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ielse.view.SwitchView;

/**
 * Created by roztop on 2017/8/5.
 */

public class EditorTaskActivity extends Activity implements OnConfirmeListener
        , CarNameAdapter.OnItemClickListener, CarTypeAdapter.OnItemClickListener, CarInfoAdapter.OnItemClickListener {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_nickanme)
    RelativeLayout llNickanme;
    @BindView(R.id.tv_vin)
    TextView tvVin;
    @BindView(R.id.ll_vin)
    RelativeLayout llVin;
    @BindView(R.id.tv_cartype)
    TextView tvCartype;
    @BindView(R.id.ll_cartype)
    RelativeLayout llCartype;
    @BindView(R.id.tv_first)
    TextView tvFirst;
    @BindView(R.id.ll_first)
    RelativeLayout llFirst;
    @BindView(R.id.tv_km)
    EditText tvKm;
    @BindView(R.id.ll_km)
    RelativeLayout llKm;
    @BindView(R.id.tv_price)
    EditText tvPrice;
    @BindView(R.id.ll_price)
    RelativeLayout llPrice;
    @BindView(R.id.switchView)
    SwitchView switchView;
    @BindView(R.id.ll_nature)
    RelativeLayout llNature;
    @BindView(R.id.ll_note)
    RelativeLayout llNote;

    Context context;
    private String strOperate = " 营运车辆";
    private final String[] mIndexItems = {"定位", "热门"};//头部额外的索引
    private List<CountryModel> models = new ArrayList<>();
    private String strCarName = "";
    private String strCarType = "";
    private String strCarModel = "";
    // 打开数据库
    static SQLiteDatabase sqLite = SQLiteDatabase.openOrCreateDatabase(
            "data/data/com.miaochegu/files/mycar.db", null);
    private RecyclerView loopView;
    private CarNameAdapter mCarNameAdapter;
    private CarTypeAdapter mCarTypeAdapter;
    private CarInfoAdapter mCarInfoAdapter;
    private Dialog bottomDialog;
    private Dialog bottomDialoga;
    private Dialog bottomDialogb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_editor_task);
        ButterKnife.bind(this);
        context = this;
        initData();
    }

    private void initData() {
        switchView.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                view.toggleSwitch(true); // or false
                strOperate = "非运营车辆";
            }

            @Override
            public void toggleToOff(SwitchView view) {
                view.toggleSwitch(false); // or true
                strOperate = "运营车辆";
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.ll_nickanme, R.id.ll_cartype, R.id.ll_first})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back://返回
                this.finish();
                break;
            case R.id.ll_nickanme://所在地
                /**隐藏软键盘**/
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                new EasySideBarBuilder(EditorTaskActivity.this)
                        .setTitle("城市选择")
                        //.setIndexColor(Color.BLUE)
                        .setIndexColor(0xFF0095EE)
                        // .isLazyRespond(true) //懒加载模式
//                        .setHotCityList(hotCityList)//热门城市列表
                        .setIndexItems(mIndexItems)//索引字母
                        .setLocationCity("北京")//定位城市
                        .setMaxOffset(60)//索引的最大偏移量
                        .start();
                break;
            case R.id.ll_cartype://车型
                showDialog();
                break;
            case R.id.ll_first:
                /**隐藏软键盘**/
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                new AlertView("请选择日期", context, 1966, 2017, this).show();
                break;
        }
    }

    @Override
    public void result(String s, String m, String d) {
        String Y = s.substring(0, s.length() - 1);
        String M = m.substring(0, m.length() - 1);
        String D = d.substring(0, d.length() - 1);
        if (Integer.parseInt(M) < 10 && Integer.parseInt(D) >= 10) {
            tvFirst.setText(Y + "-" + "0" + M + "-" + D);
        } else if (Integer.parseInt(D) < 10 && Integer.parseInt(M) >= 10) {
            tvFirst.setText(Y + "-" + M + "-" + "0" + D);
        } else if (Integer.parseInt(M) < 10 && Integer.parseInt(D) < 10) {
            tvFirst.setText(Y + "-" + "0" + M + "-" + "0" + D);
        } else {
            tvFirst.setText(Y + "-" + M + "-" + D);
        }
    }

    /**
     * 品牌弹窗
     */
    private void showDialog() {
        bottomDialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_list_carname, null);
        bottomDialog.setContentView(contentView);
        loopView = (RecyclerView) contentView.findViewById(R.id.rv_list);
        final EditText edtName = (EditText) contentView.findViewById(R.id.edt_name);
        Button btnSerach = (Button) contentView.findViewById(R.id.btn_serach);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

        //初始化管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        loopView.setLayoutManager(layoutManager);
        //设置动画
        loopView.setItemAnimator(new DefaultItemAnimator());

        mCarNameAdapter = new CarNameAdapter(context, null);
        mCarNameAdapter.setmOnItemeClickListener(this);
        loopView.setAdapter(mCarNameAdapter);

        List<CountryModel> mList = new ArrayList<CountryModel>();
        mList.clear();
        for (int i = 0; i < models.size(); i++) {
            if (i != 0) {
                mList.add(models.get(i));
            }
        }
        mCarNameAdapter.upRes(mList);

        btnSerach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edtName.getText().toString().trim();
                mCarNameAdapter.upRes(queryData(str));
            }
        });
    }

    /**
     * 车系弹窗2
     *
     * @param strCar 车系
     */
    private void showDialogType(String strCar) {
        bottomDialoga = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_list_cartype, null);
        bottomDialoga.setContentView(contentView);
        loopView = (RecyclerView) contentView.findViewById(R.id.rv_list);
        TextView tvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        tvTitle.setText("选择车系名称");
        LinearLayout llTop = (LinearLayout) contentView.findViewById(R.id.ll_top);
        llTop.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialoga.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialoga.setCanceledOnTouchOutside(true);
        bottomDialoga.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialoga.show();

        //初始化管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        loopView.setLayoutManager(layoutManager);
        //设置动画
        loopView.setItemAnimator(new DefaultItemAnimator());

        mCarTypeAdapter = new CarTypeAdapter(context, null);
        mCarTypeAdapter.setmOnItemeClickListener(this);
        loopView.setAdapter(mCarTypeAdapter);
        mCarTypeAdapter.upRes(queryDataType(strCar));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case EasySideBarBuilder.CODE_SIDEREQUEST:
                if (data != null) {
                    String city = data.getStringExtra("selected");
                    tvName.setText(city);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 车型弹窗3
     *
     * @param strType 车系
     * @param strName 车型
     */
    private void showDialogInfo(String strType, String strName) {
        bottomDialogb = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_list_cartype, null);
        bottomDialogb.setContentView(contentView);
        loopView = (RecyclerView) contentView.findViewById(R.id.rv_list);
        TextView tvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        tvTitle.setText("选择车型名称");
        LinearLayout llTop = (LinearLayout) contentView.findViewById(R.id.ll_top);
        llTop.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialogb.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialogb.setCanceledOnTouchOutside(true);
        bottomDialogb.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialogb.show();

        //初始化管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        loopView.setLayoutManager(layoutManager);
        //设置动画
        loopView.setItemAnimator(new DefaultItemAnimator());

        mCarInfoAdapter = new CarInfoAdapter(context, null);
        mCarInfoAdapter.setmOnItemeClickListener(this);
        loopView.setAdapter(mCarInfoAdapter);
        mCarInfoAdapter.upRes(queryDataInfo(strName, strType));

    }

    /**
     * 模糊查询数据
     */
    public ArrayList<CountryModel> queryData(String tempName) {
        ArrayList<CountryModel> stringList = new ArrayList<CountryModel>();
        stringList.clear();
        Cursor cursor = sqLite.rawQuery(
                "select distinct carname as carname from car where carname like '%" + tempName + "%'order by _id desc", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("carname"));
            stringList.add(new CountryModel(name, "", ""));
        }
        return stringList;
    }

    /**
     * 查询车系数据
     */
    public ArrayList<CountryModel> queryDataType(String tempName) {
        ArrayList<CountryModel> stringList = new ArrayList<CountryModel>();
        stringList.clear();
        Cursor cursor = sqLite.rawQuery(
                "select distinct cartype from car where carname='" + tempName + "'", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("cartype"));
            stringList.add(new CountryModel("", name, ""));
        }
        return stringList;
    }

    /**
     * 查询车型数据
     */
    public ArrayList<CountryModel> queryDataInfo(String tempName, String tempInfo) {
        ArrayList<CountryModel> stringList = new ArrayList<CountryModel>();
        stringList.clear();
        Cursor cursor = sqLite.rawQuery(
                "select distinct carinfo from car where carname='" + tempName + "'and cartype='" + tempInfo + "'", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("carinfo"));
            stringList.add(new CountryModel("", "", name));
        }
        return stringList;
    }

    @Override
    public void onItemeClick(View view, int position, String carName) {
        strCarName = carName;
        showDialogType(carName);
        bottomDialog.dismiss();
    }

    @Override
    public void onItemeClickType(View view, int position, String carType) {
        strCarType = carType;
        showDialogInfo(carType, strCarName);
        bottomDialoga.dismiss();
    }

    @Override
    public void onItemeClickInfo(View view, int position, String carInfo) {
        strCarModel = carInfo;
        Log.e("WWW", strCarName + "<>" + strCarType + "<>" + strCarModel);
        tvCartype.setText(strCarModel);
        bottomDialogb.dismiss();
    }
}
