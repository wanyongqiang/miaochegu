package com.miaochegu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.miaochegu.GettingStartedApp;
import com.miaochegu.R;
import com.miaochegu.util.StatusbarUtils;
import com.miaochegu.util.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.miaochegu.R.id.ll_back;
import static com.miaochegu.R.id.tv_ynamic;

/**
 * Created by roztop on 2017/7/31.
 */

  public class SelectPhotoThreeActivity extends Activity {

    Context context;
    @BindView(R.id.iv_a)
    ImageView ivA;
    @BindView(R.id.iv_b)
    ImageView ivB;
    @BindView(R.id.iv_c)
    ImageView ivC;
    @BindView(R.id.iv_aa)
    ImageView ivAa;
    @BindView(R.id.pb_a)
    ProgressBar pbA;
    @BindView(R.id.tv_a)
    TextView tvA;
    @BindView(R.id.rl_a)
    RelativeLayout rlA;
    @BindView(R.id.iv_bb)
    ImageView ivBb;
    @BindView(R.id.pb_b)
    ProgressBar pbB;
    @BindView(R.id.tv_b)
    TextView tvB;
    @BindView(R.id.rl_b)
    RelativeLayout rlB;
    @BindView(R.id.iv_cc)
    ImageView ivCc;
    @BindView(R.id.pb_c)
    ProgressBar pbC;
    @BindView(R.id.tv_c)
    TextView tvC;
    @BindView(R.id.rl_c)
    RelativeLayout rlC;
    @BindView(R.id.iv_d)
    ImageView ivD;
    @BindView(R.id.iv_e)
    ImageView ivE;
    @BindView(R.id.iv_dd)
    ImageView ivDd;
    @BindView(R.id.pb_d)
    ProgressBar pbD;
    @BindView(R.id.tv_d)
    TextView tvD;
    @BindView(R.id.rl_d)
    RelativeLayout rlD;
    @BindView(R.id.iv_ee)
    ImageView ivEe;
    @BindView(R.id.pb_e)
    ProgressBar pbE;
    @BindView(R.id.tv_e)
    TextView tvE;
    @BindView(R.id.rl_e)
    RelativeLayout rlE;
    @BindView(R.id.tv_up)
    TextView tvUp;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_ynamic)
    TextView tvYnamic;
    @BindView(R.id.iv_f)
    ImageView ivF;
    @BindView(R.id.iv_ff)
    ImageView ivFf;
    @BindView(R.id.pb_f)
    ProgressBar pbF;
    @BindView(R.id.tv_f)
    TextView tvF;
    @BindView(R.id.rl_f)
    RelativeLayout rlF;
    @BindView(R.id.iv_g)
    ImageView ivG;
    @BindView(R.id.iv_h)
    ImageView ivH;
    @BindView(R.id.iv_i)
    ImageView ivI;
    @BindView(R.id.iv_gg)
    ImageView ivGg;
    @BindView(R.id.pb_g)
    ProgressBar pbG;
    @BindView(R.id.tv_g)
    TextView tvG;
    @BindView(R.id.rl_g)
    RelativeLayout rlG;
    @BindView(R.id.iv_hh)
    ImageView ivHh;
    @BindView(R.id.pb_h)
    ProgressBar pbH;
    @BindView(R.id.tv_h)
    TextView tvH;
    @BindView(R.id.rl_h)
    RelativeLayout rlH;
    @BindView(R.id.iv_ii)
    ImageView ivIi;
    @BindView(R.id.pb_i)
    ProgressBar pbI;
    @BindView(R.id.tv_i)
    TextView tvI;
    @BindView(R.id.rl_i)
    RelativeLayout rlI;
    @BindView(R.id.iv_j)
    ImageView ivJ;
    @BindView(R.id.iv_k)
    ImageView ivK;
    @BindView(R.id.iv_l)
    ImageView ivL;
    @BindView(R.id.iv_jj)
    ImageView ivJj;
    @BindView(R.id.pb_j)
    ProgressBar pbJ;
    @BindView(R.id.tv_j)
    TextView tvJ;
    @BindView(R.id.rl_j)
    RelativeLayout rlJ;
    @BindView(R.id.iv_kk)
    ImageView ivKk;
    @BindView(R.id.pb_k)
    ProgressBar pbK;
    @BindView(R.id.tv_k)
    TextView tvK;
    @BindView(R.id.rl_k)
    RelativeLayout rlK;
    @BindView(R.id.iv_ll)
    ImageView ivLl;
    @BindView(R.id.pb_l)
    ProgressBar pbL;
    @BindView(R.id.tv_l)
    TextView tvL;
    @BindView(R.id.rl_l)
    RelativeLayout rlL;

    int index = 0;
    private int tid;
    private AVFile file;
    private String imageFileStr = "";
    private static final String TAG = "MCG";
    private static final int CHOICE_FROM_CAMERA = 0;
    boolean A = false, B = false, C = false, D = false, E = false, F = false,
            G = false, H = false, I = false, J = false, K = false, L = false;
    String pathA = "", pathB = "", pathC = "", pathD = "", pathE = "", pathF = "",
            pathG = "", pathH = "", pathI = "", pathJ = "", pathK = "", pathL = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_home_three);
        ButterKnife.bind(this);
        context = this;

        Intent intent = getIntent();
        tid = intent.getIntExtra("TID", 0);
    }

    @OnClick({tv_ynamic, ll_back, R.id.rl_a, R.id.rl_b, R.id.rl_c, R.id.rl_d, R.id.rl_e, R.id.rl_f,
            R.id.rl_g, R.id.rl_h, R.id.rl_i, R.id.rl_j, R.id.rl_k, R.id.rl_l, R.id.iv_aa, R.id.iv_bb,
            R.id.iv_cc, R.id.iv_ee, R.id.iv_ff, R.id.iv_gg, R.id.iv_hh, R.id.iv_ii, R.id.iv_jj,
            R.id.iv_kk, R.id.iv_ll, R.id.iv_dd, R.id.tv_up, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case ll_back:
                finish();
                break;
            case tv_ynamic:
                setData();
                break;
            case R.id.rl_a:
                if (verProgress()) return;
                index = 0;
                takePhoto();
                break;
            case R.id.rl_b:
                if (verProgress()) return;
                index = 1;
                takePhoto();
                break;
            case R.id.rl_c:
                if (verProgress()) return;
                index = 2;
                takePhoto();
                break;
            case R.id.rl_d:
                if (verProgress()) return;
                index = 3;
                takePhoto();
                break;
            case R.id.rl_e:
                if (verProgress()) return;
                index = 4;
                takePhoto();
                break;
            case R.id.rl_f:
                if (verProgress()) return;
                index = 5;
                takePhoto();
                break;
            case R.id.rl_g:
                if (verProgress()) return;
                index = 6;
                takePhoto();
                break;
            case R.id.rl_h:
                if (verProgress()) return;
                index = 7;
                takePhoto();
                break;
            case R.id.rl_i:
                if (verProgress()) return;
                index = 8;
                takePhoto();
                break;
            case R.id.rl_j:
                if (verProgress()) return;
                index = 9;
                takePhoto();
                break;
            case R.id.rl_k:
                if (verProgress()) return;
                index = 10;
                takePhoto();
                break;
            case R.id.rl_l:
                if (verProgress()) return;
                index = 11;
                takePhoto();
                break;
            case R.id.iv_aa:
                A = false;
                pbA.setProgress(0);
                pbA.setVisibility(View.GONE);
                file.cancel();
                ivA.setImageBitmap(null);
                ivAa.setVisibility(View.GONE);
                ivA.setBackgroundResource(R.mipmap.icon_photo);
                tvA.setBackgroundResource(0);
                tvA.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_bb:
                B = false;
                pbB.setProgress(0);
                pbB.setVisibility(View.GONE);
                file.cancel();
                ivB.setImageBitmap(null);
                ivBb.setVisibility(View.GONE);
                ivB.setBackgroundResource(R.mipmap.icon_photo);
                tvB.setBackgroundResource(0);
                tvB.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_cc:
                C = false;
                pbC.setProgress(0);
                pbC.setVisibility(View.GONE);
                file.cancel();
                ivC.setImageBitmap(null);
                ivCc.setVisibility(View.GONE);
                ivC.setBackgroundResource(R.mipmap.icon_photo);
                tvC.setBackgroundResource(0);
                tvC.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_dd:
                D = false;
                pbD.setProgress(0);
                pbD.setVisibility(View.GONE);
                file.cancel();
                ivD.setImageBitmap(null);
                ivDd.setVisibility(View.GONE);
                ivD.setBackgroundResource(R.mipmap.icon_photo);
                tvD.setBackgroundResource(0);
                tvD.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_ee:
                E = false;
                pbE.setProgress(0);
                pbE.setVisibility(View.GONE);
                file.cancel();
                ivE.setImageBitmap(null);
                ivEe.setVisibility(View.GONE);
                ivE.setBackgroundResource(R.mipmap.icon_photo);
                tvE.setBackgroundResource(0);
                tvE.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_ff:
                F = false;
                pbF.setProgress(0);
                pbF.setVisibility(View.GONE);
                file.cancel();
                ivF.setImageBitmap(null);
                ivFf.setVisibility(View.GONE);
                ivF.setBackgroundResource(R.mipmap.icon_photo);
                tvF.setBackgroundResource(0);
                tvF.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_gg:
                G = false;
                pbG.setProgress(0);
                pbG.setVisibility(View.GONE);
                file.cancel();
                ivG.setImageBitmap(null);
                ivGg.setVisibility(View.GONE);
                ivG.setBackgroundResource(R.mipmap.icon_photo);
                tvG.setBackgroundResource(0);
                tvG.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_hh:
                H = false;
                pbH.setProgress(0);
                pbH.setVisibility(View.GONE);
                file.cancel();
                ivH.setImageBitmap(null);
                ivHh.setVisibility(View.GONE);
                ivH.setBackgroundResource(R.mipmap.icon_photo);
                tvH.setBackgroundResource(0);
                tvH.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_ii:
                I = false;
                pbI.setProgress(0);
                pbI.setVisibility(View.GONE);
                file.cancel();
                ivI.setImageBitmap(null);
                ivIi.setVisibility(View.GONE);
                ivI.setBackgroundResource(R.mipmap.icon_photo);
                tvI.setBackgroundResource(0);
                tvI.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_jj:
                J = false;
                pbJ.setProgress(0);
                pbJ.setVisibility(View.GONE);
                file.cancel();
                ivJ.setImageBitmap(null);
                ivJj.setVisibility(View.GONE);
                ivJ.setBackgroundResource(R.mipmap.icon_photo);
                tvJ.setBackgroundResource(0);
                tvJ.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_kk:
                K = false;
                pbK.setProgress(0);
                pbK.setVisibility(View.GONE);
                file.cancel();
                ivK.setImageBitmap(null);
                ivKk.setVisibility(View.GONE);
                ivK.setBackgroundResource(R.mipmap.icon_photo);
                tvK.setBackgroundResource(0);
                tvK.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.iv_ll:
                L = false;
                pbL.setProgress(0);
                pbL.setVisibility(View.GONE);
                file.cancel();
                ivL.setImageBitmap(null);
                ivLl.setVisibility(View.GONE);
                ivL.setBackgroundResource(R.mipmap.icon_photo);
                tvL.setBackgroundResource(0);
                tvL.setTextColor(ContextCompat.getColor(context, R.color.tv_b2b2b2));
                break;
            case R.id.tv_up:
                finish();
                break;
            case R.id.tv_next:
                startActivity(new Intent(this, SelectPhotoFourActivity.class).putExtra("TID", tid));
                break;
        }
    }

    private void setData() {
        AVQuery<AVObject> avQuery = new AVQuery<>("Task");
        avQuery.whereEqualTo("tid", tid);
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list != null && list.size() > 0) {
                    final int sID = (int) list.get(0).get("sid");
                    AVQuery<AVObject> avQuery = new AVQuery<>("Car");
                    avQuery.whereEqualTo("carid", list.get(0).get("cid"));
                    avQuery.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if (list != null && list.size() > 0) {
//                                        if(){}
                                AVQuery<AVObject> avQuery = new AVQuery<>("Photo");
                                avQuery.whereEqualTo("pid", list.get(0).get("pid"));
                                avQuery.findInBackground(new FindCallback<AVObject>() {
                                    @Override
                                    public void done(List<AVObject> list, AVException e) {
                                        if (list != null && list.size() > 0) {
                                            //if(){}
                                            AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
                                            avQuery.whereEqualTo("sid", sID);
                                            avQuery.findInBackground(new FindCallback<AVObject>() {
                                                @Override
                                                public void done(List<AVObject> list, AVException e) {
                                                    if (list != null && list.size() > 0) {
                                                        AVObject avObject = list.get(0);
                                                        avObject.put("atype", 1);
                                                        avObject.saveInBackground(new SaveCallback() {
                                                            @Override
                                                            public void done(AVException e) {
                                                                showDialog();
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 网络断开弹窗
     */
    private void showDialog() {
        final Dialog bottomDialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_submit_info, null);
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
                startActivity(new Intent(SelectPhotoThreeActivity.this, LookResultActivity.class));
                bottomDialog.dismiss();
            }
        });
    }

    /**
     * 验证当前上传进度是否上传完毕
     *
     * @return
     */
    private boolean verProgress() {
        if (A == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (B == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (C == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (D == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (E == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (F == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (G == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (H == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (I == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (J == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (K == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        if (L == true) {
            ToastUtil.show("亲！请等待当前上传完毕再继续");
            return true;
        }
        return false;
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        File fileDir = new File(GettingStartedApp.FILEPATH + "/img/");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        imageFileStr = fileDir + GettingStartedApp.imageName();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (StatusbarUtils.isIntentAvailable(this, intent)) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imageFileStr)));
//            intent.putExtra("return-data", false);
            startActivityForResult(intent, CHOICE_FROM_CAMERA);
        } else {
            Toast.makeText(this, "该手机无法拍照，请更换可拍照的手机", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOICE_FROM_CAMERA) {
                File imageFile = new File(imageFileStr);
                if (imageFile.exists()) {
                    switch (index) {
                        case 0:
                            pathA = imageFileStr;
                            A = true;
                            final Bitmap bitmap = BitmapFactory.decodeFile(imageFileStr);
                            ivA.setImageBitmap(bitmap);
                            pbA.setVisibility(View.VISIBLE);
                            ivAa.setVisibility(View.VISIBLE);
                            tvA.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvA.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap, "pfirewall");
                            break;
                        case 1:
                            pathB = imageFileStr;
                            B = true;
                            final Bitmap bitmap1 = BitmapFactory.decodeFile(imageFileStr);
                            ivB.setImageBitmap(bitmap1);
                            pbB.setVisibility(View.VISIBLE);
                            ivBb.setVisibility(View.VISIBLE);
                            tvB.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvB.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap1, "plwaterstents");
                            break;
                        case 2:
                            pathC = imageFileStr;
                            C = true;
                            final Bitmap bitmap2 = BitmapFactory.decodeFile(imageFileStr);
                            ivC.setImageBitmap(bitmap2);
                            pbC.setVisibility(View.VISIBLE);
                            ivCc.setVisibility(View.VISIBLE);
                            tvC.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvC.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap2, "prwaterstents");
                            break;
                        case 3:
                            pathD = imageFileStr;
                            D = true;
                            final Bitmap bitmap3 = BitmapFactory.decodeFile(imageFileStr);
                            ivD.setImageBitmap(bitmap3);
                            pbD.setVisibility(View.VISIBLE);
                            ivDd.setVisibility(View.VISIBLE);
                            tvD.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvD.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap3, "plfrontfender");
                            break;
                        case 4:
                            pathE = imageFileStr;
                            E = true;
                            final Bitmap bitmap4 = BitmapFactory.decodeFile(imageFileStr);
                            ivE.setImageBitmap(bitmap4);
                            pbE.setVisibility(View.VISIBLE);
                            ivEe.setVisibility(View.VISIBLE);
                            tvE.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvE.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap4, "prfrontfender");
                            break;
                        case 5:
                            pathF = imageFileStr;
                            F = true;
                            final Bitmap bitmap5 = BitmapFactory.decodeFile(imageFileStr);
                            ivF.setImageBitmap(bitmap5);
                            pbF.setVisibility(View.VISIBLE);
                            ivFf.setVisibility(View.VISIBLE);
                            tvF.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvF.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap5, "pldoora");
                            break;
                        case 6:
                            pathG = imageFileStr;
                            G = true;
                            final Bitmap bitmap6 = BitmapFactory.decodeFile(imageFileStr);
                            ivG.setImageBitmap(bitmap6);
                            pbG.setVisibility(View.VISIBLE);
                            ivGg.setVisibility(View.VISIBLE);
                            tvG.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvG.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap6, "pldoorbc");
                            break;
                        case 7:
                            pathH = imageFileStr;
                            H = true;
                            final Bitmap bitmap7 = BitmapFactory.decodeFile(imageFileStr);
                            ivH.setImageBitmap(bitmap7);
                            pbH.setVisibility(View.VISIBLE);
                            ivHh.setVisibility(View.VISIBLE);
                            tvH.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvH.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap7, "prdoora");
                            break;
                        case 8:
                            pathI = imageFileStr;
                            I = true;
                            final Bitmap bitmap8 = BitmapFactory.decodeFile(imageFileStr);
                            ivI.setImageBitmap(bitmap8);
                            pbI.setVisibility(View.VISIBLE);
                            ivIi.setVisibility(View.VISIBLE);
                            tvI.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvI.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap8, "prdoorc");
                            break;
                        case 9:
                            pathJ = imageFileStr;
                            J = true;
                            final Bitmap bitmap9 = BitmapFactory.decodeFile(imageFileStr);
                            ivJ.setImageBitmap(bitmap9);
                            pbJ.setVisibility(View.VISIBLE);
                            ivJj.setVisibility(View.VISIBLE);
                            tvJ.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvJ.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap9, "ptrunkdandw");
                            break;
                        case 10:
                            pathK = imageFileStr;
                            K = true;
                            final Bitmap bitmap10 = BitmapFactory.decodeFile(imageFileStr);
                            ivK.setImageBitmap(bitmap10);
                            pbK.setVisibility(View.VISIBLE);
                            ivKk.setVisibility(View.VISIBLE);
                            tvK.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvK.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap10, "plcoverrear");
                            break;
                        case 11:
                            pathL = imageFileStr;
                            L = true;
                            final Bitmap bitmap11 = BitmapFactory.decodeFile(imageFileStr);
                            ivL.setImageBitmap(bitmap11);
                            pbL.setVisibility(View.VISIBLE);
                            ivLl.setVisibility(View.VISIBLE);
                            tvL.setBackgroundResource(R.drawable.scale_black_cir_bg);
                            tvL.setTextColor(ContextCompat.getColor(context, R.color.white));
                            setPhotoData(bitmap11, "prcoverrear");
                            break;

                    }
                } else {
                    Toast.makeText(this, "获取照片失败，请从相册选择图片", Toast.LENGTH_SHORT);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setPhotoData(final Bitmap bitmap, final String keyName) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                File f = new File(Environment.getExternalStorageDirectory(), "productPic.jpg");
                if (f.exists()) {
                    f.delete();
                }
                try {
                    FileOutputStream out = new FileOutputStream(f);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, out);
                    out.flush();
                    out.close();
                    Log.i(TAG, "已经保存");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                AVObject product = new AVObject("Photo");
                try {
                    file = AVFile.withAbsoluteLocalPath("productPic.jpg", Environment.getExternalStorageDirectory() + "/productPic.jpg");
                    file.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            // 成功或失败处理...
                            if ("pocardregis".equals(keyName)) {
                                A = false;
                                pbA.setVisibility(View.GONE);
                            } else if ("ptcardregis".equals(keyName)) {
                                B = false;
                                pbB.setVisibility(View.GONE);
                            } else if ("drivingcard".equals(keyName)) {
                                C = false;
                                pbC.setVisibility(View.GONE);
                            } else if ("pchit".equals(keyName)) {
                                D = false;
                                pbD.setVisibility(View.GONE);
                            } else if ("pnameplate".equals(keyName)) {
                                E = false;
                                pbE.setVisibility(View.GONE);
                            } else if ("pocardregis".equals(keyName)) {
                                F = false;
                                pbF.setVisibility(View.GONE);
                            } else if ("ptcardregis".equals(keyName)) {
                                G = false;
                                pbG.setVisibility(View.GONE);
                            } else if ("drivingcard".equals(keyName)) {
                                H = false;
                                pbH.setVisibility(View.GONE);
                            } else if ("pchit".equals(keyName)) {
                                I = false;
                                pbI.setVisibility(View.GONE);
                            } else if ("pnameplate".equals(keyName)) {
                                J = false;
                                pbJ.setVisibility(View.GONE);
                            } else if ("pocardregis".equals(keyName)) {
                                K = false;
                                pbK.setVisibility(View.GONE);
                            } else if ("ptcardregis".equals(keyName)) {
                                L = false;
                                pbL.setVisibility(View.GONE);
                            }
                        }
                    }, new ProgressCallback() {
                        @Override
                        public void done(Integer integer) {
                            // 上传进度数据，integer 介于 0 和 100。
                            if ("pocardregis".equals(keyName)) {
                                pbA.setProgress(integer);
                            } else if ("ptcardregis".equals(keyName)) {
                                pbB.setProgress(integer);
                            } else if ("drivingcard".equals(keyName)) {
                                pbC.setProgress(integer);
                            } else if ("pchit".equals(keyName)) {
                                pbD.setProgress(integer);
                            } else if ("pnameplate".equals(keyName)) {
                                pbE.setProgress(integer);
                            } else if ("pocardregis".equals(keyName)) {
                                pbF.setProgress(integer);
                            } else if ("ptcardregis".equals(keyName)) {
                                pbG.setProgress(integer);
                            } else if ("drivingcard".equals(keyName)) {
                                pbH.setProgress(integer);
                            } else if ("pchit".equals(keyName)) {
                                pbI.setProgress(integer);
                            } else if ("pnameplate".equals(keyName)) {
                                pbJ.setProgress(integer);
                            } else if ("pocardregis".equals(keyName)) {
                                pbK.setProgress(integer);
                            } else if ("ptcardregis".equals(keyName)) {
                                pbL.setProgress(integer);
                            }
                        }
                    });
                    product.put(keyName, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                product.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                        } else {
                            Toast.makeText(SelectPhotoThreeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }.execute();
    }
}
