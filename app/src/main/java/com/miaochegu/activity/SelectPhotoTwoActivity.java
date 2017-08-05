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
import com.miaochegu.util.FileCache;
import com.miaochegu.util.ImageUtils;
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
 * Created by toshiba on 2017/7/11.
 */

public class SelectPhotoTwoActivity extends Activity {

    Context context;
    @BindView(ll_back)
    LinearLayout llBack;
    @BindView(tv_ynamic)
    TextView tvYnamic;
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

    int index = 0;
    private int tid;
    private AVFile file;
    private String imageFileStr = "";
    private static final String TAG = "MCG";
    private static final int CHOICE_FROM_CAMERA = 0;
    boolean A = false, B = false, C = false, D = false, E = false;
    String pathA = "", pathB = "", pathC = "", pathD = "", pathE = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusbarUtils.enableTranslucentStatusbar(this);
        setContentView(R.layout.activity_home_two);
        ButterKnife.bind(this);
        context = this;

        Intent intent = getIntent();
        tid = intent.getIntExtra("TID", 0);
    }

    @OnClick({R.id.tv_ynamic, R.id.ll_back, R.id.rl_a, R.id.rl_b, R.id.rl_c, R.id.rl_d, R.id.rl_e,
            R.id.iv_aa, R.id.iv_bb, R.id.iv_cc, R.id.iv_ee, R.id.iv_dd, R.id.tv_up, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_ynamic:
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
            case R.id.tv_up:
                finish();
                break;
            case R.id.tv_next:
                startActivity(new Intent(this, SelectPhotoThreeActivity.class).putExtra("TID", tid));
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
                startActivity(new Intent(SelectPhotoTwoActivity.this, LookResultActivity.class));
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
                long strTimeMillis = System.currentTimeMillis();
                String newPath = ImageUtils.compressImage(imageFileStr, FileCache.setRootDirectory() + strTimeMillis + ".jpg", 50);
                File imageFile = new File(newPath);
                if (imageFile.exists()) {
                    if (index == 0) {
                        pathA = newPath;
                        A = true;
                        final Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivA.setImageBitmap(bitmap);
                        pbA.setVisibility(View.VISIBLE);
                        ivAa.setVisibility(View.VISIBLE);
                        tvA.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvA.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "plfront45");
                    }
                    if (index == 1) {
                        pathB = newPath;
                        B = true;
                        final Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivB.setImageBitmap(bitmap);
                        pbB.setVisibility(View.VISIBLE);
                        ivBb.setVisibility(View.VISIBLE);
                        tvB.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvB.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "prfront45");
                    }
                    if (index == 2) {
                        pathC = newPath;
                        C = true;
                        final Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivC.setImageBitmap(bitmap);
                        pbC.setVisibility(View.VISIBLE);
                        ivCc.setVisibility(View.VISIBLE);
                        tvC.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvC.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "pfwindscreen");
                    }
                    if (index == 3) {
                        pathD = newPath;
                        D = true;
                        final Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivD.setImageBitmap(bitmap);
                        pbD.setVisibility(View.VISIBLE);
                        ivDd.setVisibility(View.VISIBLE);
                        tvD.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvD.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "pdwindscreen");
                    }
                    if (index == 4) {
                        pathE = newPath;
                        E = true;
                        final Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivE.setImageBitmap(bitmap);
                        pbE.setVisibility(View.VISIBLE);
                        ivEe.setVisibility(View.VISIBLE);
                        tvE.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvE.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "pnameplate");
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
                            Toast.makeText(SelectPhotoTwoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }.execute();
    }
}
