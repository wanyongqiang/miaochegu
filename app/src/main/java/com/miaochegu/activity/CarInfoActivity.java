package com.miaochegu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.esaysidebar.EasySideBarBuilder;
import com.limxing.library.AlertView;
import com.limxing.library.OnConfirmeListener;
import com.miaochegu.GettingStartedApp;
import com.miaochegu.R;
import com.miaochegu.adapter.CarInfoAdapter;
import com.miaochegu.adapter.CarNameAdapter;
import com.miaochegu.adapter.CarTypeAdapter;
import com.miaochegu.model.CountryModel;
import com.miaochegu.util.GsonUtils;
import com.miaochegu.util.JsonTools;
import com.miaochegu.util.SharePCach;
import com.miaochegu.util.StatusbarUtils;
import com.miaochegu.util.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.ielse.view.SwitchView;
import jxl.Sheet;
import jxl.Workbook;

import static android.content.ContentValues.TAG;

/**
 * Created by toshiba on 2017/7/11.
 */

public class CarInfoActivity extends Activity implements View.OnClickListener, OnConfirmeListener,
        CarNameAdapter.OnItemClickListener, CarTypeAdapter.OnItemClickListener, CarInfoAdapter.OnItemClickListener {

    Context context;
    SwitchView switchView;
    ImageView imageView, iv_car;
    private ProgressBar mProgerss;
    private TextView tv_next, tv_gj, tv_mine, tv_dpg, tv_wtg, tv_ytg, tv_updatepwd, username,
            tv_wwc, tv_psz, tv_title, tv_city, tv_loginout, tv_carinfo, tv_datatime, tv_editor;
    private EditText edt_vincode, edt_km, edt_money, edt_content;
    LinearLayout llMine, llMineCenter, llMineCenters, llCenMine, llMineBottom, ll_gj_head, ll_gj_center;

    private String type = "";
    private String strOperate = " 营运车辆";
    private final String[] mIndexItems = {"定位", "热门"};//头部额外的索引
    private List<CountryModel> models = new ArrayList<>();
    private String strCarName = "";
    private String strCarType = "";
    private String strCarModel = "";

    // 打开数据库
    static SQLiteDatabase sqLite = SQLiteDatabase.openOrCreateDatabase(
            "data/data/com.miaochegu/files/mycar.db", null);
    public static final String TABLE_NAME = "car";
    private RecyclerView loopView;
    private CarNameAdapter mCarNameAdapter;
    private CarTypeAdapter mCarTypeAdapter;
    private CarInfoAdapter mCarInfoAdapter;
    private Dialog bottomDialog;
    private Dialog bottomDialoga;
    private Dialog bottomDialogb;

    private static Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_car_info);
        context = this;

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        init();

        if ("mine".equals(type)) {
            tv_title.setText("我的账户");
            tv_gj.setBackgroundResource(R.color.white);
            tv_mine.setBackgroundResource(R.color.colorcccccc);
            ll_gj_head.setVisibility(View.GONE);
            tv_next.setVisibility(View.GONE);
            ll_gj_center.setVisibility(View.GONE);
            llMine.setVisibility(View.VISIBLE);
            llCenMine.setVisibility(View.VISIBLE);
            tv_loginout.setVisibility(View.VISIBLE);
            llMineCenters.setVisibility(View.VISIBLE);
            llMineCenter.setVisibility(View.VISIBLE);
            llMineBottom.setVisibility(View.GONE);
        } else {
            tv_title.setText("妙车估");
            tv_gj.setBackgroundResource(R.color.colorcccccc);
            tv_mine.setBackgroundResource(R.color.white);
            ll_gj_head.setVisibility(View.GONE);
            ll_gj_center.setVisibility(View.VISIBLE);
            tv_next.setVisibility(View.VISIBLE);
            llMine.setVisibility(View.GONE);
            username.setText(AVUser.getCurrentUser().getUsername());
            username.setClickable(false);
            username.setFocusable(false);
            llCenMine.setVisibility(View.GONE);
            tv_loginout.setVisibility(View.GONE);
            llMineCenter.setVisibility(View.GONE);
            llMineCenters.setVisibility(View.GONE);
            llMineBottom.setVisibility(View.GONE);
        }

        initView();//TODO 初始化数据

        if (models.size() == 0) {
            SharePCach.saveStringCach("MUnit", GsonUtils.toJson(queryData()));//否则去缓存
            try {
                models = JsonTools.toListBeanNoKey(SharePCach.loadStringCach("MUnit"), CountryModel.class);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // 查询数据库，将每一行的数据封装成一个person 对象，然后将对象添加到List中
    private List<CountryModel> queryData() {
        List<CountryModel> list = new ArrayList<CountryModel>();
        list.clear();
        // 调用query()获取Cursor
        Cursor c = sqLite.query(TABLE_NAME, null, null, null, "carname", null, null, null);
        while (c.moveToNext()) {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("carname"));
            // 用一个Person对象来封装查询出来的数据
            CountryModel p = new CountryModel();
            p.set_id(_id);
            p.setCarName(name);
            list.add(p);
        }
        return list;
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
        tv_carinfo.setText(strCarName + "/" + strCarType + "/" + strCarModel);
        bottomDialogb.dismiss();
    }

    private void init() {
        mProgerss = (ProgressBar) findViewById(R.id.mProgess);
        edt_vincode = (EditText) findViewById(R.id.edt_vincode);
        username = (TextView) findViewById(R.id.username);
        edt_km = (EditText) findViewById(R.id.edt_km);
        edt_money = (EditText) findViewById(R.id.edt_money);
        edt_content = (EditText) findViewById(R.id.edt_content);
        imageView = (ImageView) findViewById(R.id.iv_city);
        iv_car = (ImageView) findViewById(R.id.iv_car);
        switchView = (SwitchView) findViewById(R.id.switchView);
        tv_loginout = (TextView) findViewById(R.id.tv_loginout);
        tv_updatepwd = (TextView) findViewById(R.id.tv_updatapwd);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_carinfo = (TextView) findViewById(R.id.tv_carinfo);
        tv_datatime = (TextView) findViewById(R.id.tv_datatime);
        tv_next = (TextView) findViewById(R.id.tv_next);
        tv_editor = (TextView) findViewById(R.id.tv_editor);
        tv_gj = (TextView) findViewById(R.id.tv_gj);
        tv_mine = (TextView) findViewById(R.id.tv_mine);
        tv_dpg = (TextView) findViewById(R.id.tv_dps);
        tv_wtg = (TextView) findViewById(R.id.tv_wtg);
        tv_ytg = (TextView) findViewById(R.id.tv_ytg);
        tv_wwc = (TextView) findViewById(R.id.tv_wwc);
        tv_psz = (TextView) findViewById(R.id.tv_psz);
        tv_city = (TextView) findViewById(R.id.tv_city);
        llMine = (LinearLayout) findViewById(R.id.ll_mine);
        llMineCenter = (LinearLayout) findViewById(R.id.ll_mine_center);
        llCenMine = (LinearLayout) findViewById(R.id.ll_cen_mine);
        llMineCenters = (LinearLayout) findViewById(R.id.ll_mine_centers);
        llMineBottom = (LinearLayout) findViewById(R.id.ll_mine_buttom);
        ll_gj_head = (LinearLayout) findViewById(R.id.ll_gj_head);
        ll_gj_center = (LinearLayout) findViewById(R.id.ll_gj_center);
        edt_vincode.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        //拿缓存的资源
        try {
            models = JsonTools.toListBeanNoKey(SharePCach.loadStringCach("MUnit"), CountryModel.class);
            Log.e("CAR", ">>>>>>>>>>>>>>>" + models.get(30000).getCarInfo() + "%%%%%%" + models.get(30000).getCarType() + "%%%%%%" + models.get(30000).getCarName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        tv_next.setOnClickListener(this);
        tv_gj.setOnClickListener(this);
        tv_mine.setOnClickListener(this);
        tv_dpg.setOnClickListener(this);
        iv_car.setOnClickListener(this);
        tv_wtg.setOnClickListener(this);
        tv_ytg.setOnClickListener(this);
        tv_wwc.setOnClickListener(this);
        tv_psz.setOnClickListener(this);
        tv_loginout.setOnClickListener(this);
        tv_updatepwd.setOnClickListener(this);
        tv_datatime.setOnClickListener(this);
        imageView.setOnClickListener(this);
        tv_editor.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_updatapwd:
                startActivity(new Intent(this, UpdatePassWordActivity.class));
                break;
            case R.id.tv_editor:
                startActivity(new Intent(this, WaiteAssessActivity.class));
                break;
            case R.id.tv_next:
                if ("".equals(edt_vincode.getText().toString().trim())) {
                    ToastUtil.show("请输入17位VIN码");
                    return;
                }
                if ("".equals(edt_vincode.getText().toString().trim()) && edt_vincode.getText().toString().trim().length() != 17) {
                    ToastUtil.show("请输入正确的VIN码");
                    return;
                }
                if ("".equals(tv_carinfo.getText().toString().trim())) {
                    ToastUtil.show("请选择车辆型号");
                    return;
                }
                if ("".equals(tv_city.getText().toString().trim())) {
                    ToastUtil.show("请选择所在城市");
                    return;
                }
                if ("".equals(tv_datatime.getText().toString().trim())) {
                    ToastUtil.show("请选择上牌日期");
                    return;
                }
                if ("".equals(edt_km.getText().toString().trim())) {
                    ToastUtil.show("请输入行程里程");
                    return;
                }
                if ("".equals(edt_money.getText().toString().trim())) {
                    ToastUtil.show("请输入价格");
                    return;
                }
                mProgerss.setVisibility(View.VISIBLE);
                AVQuery<AVObject> avQuery = new AVQuery<>("Car");
                avQuery.addAscendingOrder("carid");
                avQuery.findInBackground(new FindCallback<AVObject>() {
                    private int tID = 0;

                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mProgerss.setVisibility(View.GONE);
                            }
                        });
                        final int cID = list == null || list.size() == 0 ? 1 : (Integer) list.get(list.size() - 1).get("carid") + 1;
//                        Log.e(TAG, list.get(list.size() - 1).get("carid") + "");
                        AVQuery<AVObject> avQuery = new AVQuery<>("Audit");//TODO 查询审核ID
                        avQuery.addAscendingOrder("sid");
                        avQuery.findInBackground(new FindCallback<AVObject>() {
                            @Override
                            public void done(List<AVObject> list, AVException e) {
                                final int sID = list == null || list.size() == 0 ? 1 : (Integer) list.get(list.size() - 1).get("sid") + 1;
//                                Log.e(TAG, list.get(list.size() - 1).get("sid") + "");
                                AVObject product = new AVObject("Audit");//TODO 插入审核数据
                                product.put("cid", cID);
                                product.put("sid", sID);
                                product.put("atype", 0);
                                product.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(AVException e) {
                                        if (e == null) {
                                            AVQuery<AVObject> avQuery = new AVQuery<>("Task");//TODO 查询任务ID
                                            avQuery.addAscendingOrder("tid");
                                            avQuery.findInBackground(new FindCallback<AVObject>() {
                                                @Override
                                                public void done(List<AVObject> list, AVException e) {
                                                    tID = list == null || list.size() == 0 ? 1 : (Integer) list.get(list.size() - 1).get("tid") + 1;
                                                    AVObject product = new AVObject("Task");//TODO 插入审核数据
                                                    product.put("tid", tID);
                                                    product.put("sid", sID);
                                                    product.put("uid", AVUser.getCurrentUser().get("uid"));
                                                    product.put("cid", cID);
                                                    product.put("tcreatetime", new Date());
                                                    product.saveInBackground(new SaveCallback() {
                                                        @Override
                                                        public void done(AVException e) {
                                                            if (e == null) {
                                                                AVQuery<AVObject> avQuery = new AVQuery<>("Photo");//TODO 查询任务ID
                                                                avQuery.addAscendingOrder("pid");
                                                                avQuery.findInBackground(new FindCallback<AVObject>() {
                                                                    @Override
                                                                    public void done(List<AVObject> list, AVException e) {
                                                                        final int pID = list == null || list.size() == 0 ? 1 : (Integer) list.get(list.size() - 1).get("pid") + 1;
                                                                        AVObject product = new AVObject("Photo");//TODO
                                                                        product.put("pid", pID);
                                                                        product.saveInBackground(new SaveCallback() {
                                                                            @Override
                                                                            public void done(AVException e) {
                                                                                if (e == null) {
                                                                                    setCar(cID, tID, pID);
                                                                                } else {
//                                                                                    Toast.makeText(CarInfoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            }
                                                                        });
                                                                    }
                                                                });

                                                            } else {
//                                                                Toast.makeText(CarInfoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        } else {
//                                            Toast.makeText(CarInfoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
//                startActivity(new Intent(CarInfoActivity.this, SelectPhotoActivity.class).putExtra("TID", 1034));
                break;
            case R.id.tv_gj:
                tv_title.setText("妙车估");
                tv_gj.setBackgroundResource(R.color.colorcccccc);
                tv_mine.setBackgroundResource(R.color.white);
                ll_gj_head.setVisibility(View.GONE);
                ll_gj_center.setVisibility(View.VISIBLE);
                tv_next.setVisibility(View.VISIBLE);
                llMine.setVisibility(View.GONE);
                llCenMine.setVisibility(View.GONE);
                tv_loginout.setVisibility(View.GONE);
                llMineCenter.setVisibility(View.GONE);
                llMineCenters.setVisibility(View.GONE);
                llMineBottom.setVisibility(View.GONE);
                break;
            case R.id.tv_mine:
                tv_title.setText("我的账户");
                tv_gj.setBackgroundResource(R.color.white);
                tv_mine.setBackgroundResource(R.color.colorcccccc);
                ll_gj_head.setVisibility(View.GONE);
                tv_next.setVisibility(View.GONE);
                ll_gj_center.setVisibility(View.GONE);
                username.setText(AVUser.getCurrentUser().getUsername());
                username.setClickable(false);
                username.setFocusable(false);
                llMine.setVisibility(View.VISIBLE);
                llCenMine.setVisibility(View.VISIBLE);
                tv_loginout.setVisibility(View.VISIBLE);
                llMineCenters.setVisibility(View.VISIBLE);
                llMineCenter.setVisibility(View.VISIBLE);
                llMineBottom.setVisibility(View.GONE);
                break;
            case R.id.tv_dps://待评审
                startActivity(new Intent(CarInfoActivity.this, ReviewProcessActivity.class).putExtra("type", "DPG").putExtra("NUM", 1));
                break;
            case R.id.tv_wtg://未通过
                startActivity(new Intent(CarInfoActivity.this, ReviewProcessActivity.class).putExtra("type", "WTG").putExtra("NUM", 3));//TODO  跳转可编辑
                break;
            case R.id.tv_ytg://已通过
                startActivity(new Intent(CarInfoActivity.this, ReviewProcessActivity.class).putExtra("type", "YTG").putExtra("NUM", 4));
                break;
            case R.id.tv_wwc://未完成
                startActivity(new Intent(CarInfoActivity.this, ReviewProcessActivity.class).putExtra("type", "WWC").putExtra("NUM", 0));//TODO  跳转可编辑
                break;
            case R.id.tv_psz://评审中
                startActivity(new Intent(CarInfoActivity.this, ReviewProcessActivity.class).putExtra("type", "PSZ").putExtra("NUM", 2));
                break;
            case R.id.tv_loginout://退出登录
                if (GettingStartedApp.getInstance().isLogin == true) {
                    AVUser.getCurrentUser().logOut();
                    startActivity(new Intent(CarInfoActivity.this, LoginActivity.class));
                    GettingStartedApp.getInstance().setLogin(false);
                    CarInfoActivity.this.finish();
                }
                break;
            case R.id.tv_datatime://上牌日期
                /**隐藏软键盘**/
                if (v != null) {
                    InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                new AlertView("请选择日期", context, 1966, 2017, this).show();
                break;
            case R.id.iv_car:
//                new ExcelDataLoader().execute("car.xls");
                showDialog();
                break;
            case R.id.iv_city://所在城市
                /**隐藏软键盘**/
                if (v != null) {
                    InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                new EasySideBarBuilder(CarInfoActivity.this)
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
            default:
                break;
        }
    }

    /**
     * @param cid 车辆ID
     * @param tid 任务ID
     * @param pid 照片ID
     */
    private void setCar(final int cid, final int tid, final int pid) {
        AVObject product = new AVObject("Car");
        product.put("carid", cid);
        product.put("pid", pid);
        product.put("cseries", strCarType);
        product.put("cinfo", AVUser.getCurrentUser().getUsername());
        product.put("vin", edt_vincode.getText().toString().trim());
        product.put("cbrand", strCarName);
        product.put("caddress", tv_city.getText().toString().trim());
        product.put("cyear", new Date());
        product.put("ckm", Integer.parseInt(edt_km.getText().toString().trim()));
        product.put("cmodels", strCarModel);
        product.put("cprice", Integer.parseInt(edt_money.getText().toString().trim()));
        product.put("sceneinfo", edt_content.getText().toString().trim());
        product.put("usenature", strOperate);
        product.saveInBackground(new SaveCallback() {
            @Override
            public void done(final AVException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (e == null) {
                            startActivity(new Intent(CarInfoActivity.this, SelectPhotoActivity.class).putExtra("TID", tid));
                            Log.e("run", "run");
                        } else {
                            Toast.makeText(CarInfoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //resultCode 是使用封装好的EasySideBarBuilder.CODE_SIDEREQUEST
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case EasySideBarBuilder.CODE_SIDEREQUEST:
                if (data != null) {
                    String city = data.getStringExtra("selected");
                    tv_city.setText(city);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void result(String s, String m, String d) {
        String Y = s.substring(0, s.length() - 1);
        String M = m.substring(0, m.length() - 1);
        String D = d.substring(0, d.length() - 1);
        if (Integer.parseInt(M) < 10 && Integer.parseInt(D) >= 10) {
            tv_datatime.setText(Y + "-" + "0" + M + "-" + D);
        } else if (Integer.parseInt(D) < 10 && Integer.parseInt(M) >= 10) {
            tv_datatime.setText(Y + "-" + M + "-" + "0" + D);
        } else if (Integer.parseInt(M) < 10 && Integer.parseInt(D) < 10) {
            tv_datatime.setText(Y + "-" + "0" + M + "-" + "0" + D);
        } else {
            tv_datatime.setText(Y + "-" + M + "-" + D);
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


    /*****************************************暂时不用*************************************************/
    //在异步方法中 调用
    private class ExcelDataLoader extends AsyncTask<String, Void, ArrayList<CountryModel>> {

        @Override
        protected void onPreExecute() {
            Log.e("onPreExecute", "onPreExecute");
        }

        @Override
        protected ArrayList<CountryModel> doInBackground(String... params) {
            return getXlsData(params[0], 0);
        }

        @Override
        protected void onPostExecute(ArrayList<CountryModel> countryModels) {

            if (countryModels != null && countryModels.size() > 0) {
                //存在数据
                SharePCach.saveStringCach("MUnit", GsonUtils.toJson(countryModels));//否则去缓存
                Log.e("wwww", "<><><><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            } else {
                //加载失败
            }
        }
    }

    /**
     * 获取 excel 表格中的数据,不能在主线程中调用
     *
     * @param xlsName excel 表格的名称
     * @param index   第几张表格中的数据
     */
    private ArrayList<CountryModel> getXlsData(String xlsName, int index) {
        ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
        AssetManager assetManager = getAssets();

        try {
            Workbook workbook = Workbook.getWorkbook(assetManager.open(xlsName));
            Sheet sheet = workbook.getSheet(index);

            int sheetNum = workbook.getNumberOfSheets();
            int sheetRows = sheet.getRows();
            int sheetColumns = sheet.getColumns();

            Log.d(TAG, "the num of sheets is " + sheetNum);
            Log.d(TAG, "the name of sheet is  " + sheet.getName());
            Log.d(TAG, "total rows is 行=" + sheetRows);
            Log.d(TAG, "total cols is 列=" + sheetColumns);

            for (int i = 0; i < sheetRows; i++) {
                CountryModel countryModel = new CountryModel();
                countryModel.setCarName(sheet.getCell(3, i).getContents());
                countryModel.setCarType(sheet.getCell(4, i).getContents());
                countryModel.setCarInfo(sheet.getCell(5, i).getContents());

                countryList.add(countryModel);
            }
            workbook.close();

        } catch (Exception e) {
            Log.e(TAG, "read error=" + e, e);
        }

        return countryList;
    }

/*******************************************暂时不用***************************************************/
}
