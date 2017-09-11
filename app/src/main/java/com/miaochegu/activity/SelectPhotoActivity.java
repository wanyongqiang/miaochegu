package com.miaochegu.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
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
import com.avos.avoscloud.DeleteCallback;
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

public class SelectPhotoActivity extends Activity {

    private static final String TAG = "MCG";
    private static final int NEED_CAMERA = 111;
    int index = 0;
    Context context;
    @BindView(ll_back)
    LinearLayout llBack;
    @BindView(tv_ynamic)
    TextView tvYnamic;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
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
    @BindView(R.id.mProgess)
    ProgressBar mProgess;

    private String imageFileStr = "";
    private static final int CHOICE_FROM_CAMERA = 0;
    private int tid;
    boolean A = false, B = false, C = false, D = false, E = false;
    String pathA = "", pathB = "", pathC = "", pathD = "", pathE = "";
    private AVFile file;
    private int mPid = -1;

    private static Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        context = this;

        Intent intent = getIntent();
        tid = intent.getIntExtra("TID", 0);
    }

    @OnClick({R.id.ll_back, R.id.tv_ynamic, R.id.rl_a, R.id.rl_b, R.id.rl_c, R.id.rl_d, R.id.rl_e, R.id.iv_aa, R.id.iv_bb, R.id.iv_cc, R.id.iv_ee, R.id.iv_dd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_ynamic:
                if (isVerData()) return;
                startActivity(new Intent(SelectPhotoActivity.this, SelectPhotoTwoActivity.class).putExtra("TID", tid));
                break;
            case R.id.rl_a:
                if (verProgress()) return;
                index = 0;
                getPhotoPermission();
                break;
            case R.id.rl_b:
                if (verProgress()) return;
                index = 1;
                getPhotoPermission();
                break;
            case R.id.rl_c:
                if (verProgress()) return;
                index = 2;
                getPhotoPermission();
                break;
            case R.id.rl_d:
                if (verProgress()) return;
                index = 3;
                getPhotoPermission();
                break;
            case R.id.rl_e:
                if (verProgress()) return;
                index = 4;
                getPhotoPermission();
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
                deletePhoto("pocardregis");
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
                deletePhoto("ptcardregis");
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
                deletePhoto("drivingcard");
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
                deletePhoto("pchit");
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
                deletePhoto("pnameplate");
                break;
        }
    }

    private void deletePhoto(final String type) {
        file.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(AVException e) {
                AVQuery<AVObject> avQuery = new AVQuery<>("Photo");
                avQuery.whereEqualTo("pid", mPid);
                avQuery.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        AVObject avObject = list.get(0);
                        avObject.put(type, null);
                        avObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {

                            }
                        });
                    }
                });
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getPhotoPermission() {//检测是否有相机和读写文件权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, NEED_CAMERA);
        } else {
            takePhoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case NEED_CAMERA:
                // 如果权限被拒绝，grantResults 为空
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    Toast.makeText(this, "改功能需要相机和读写文件权限", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private boolean isVerData() {
        if ("".equals(pathA)) {
            ToastUtil.show("请上传完整再进行下一步");
            return true;
        }
        if ("".equals(pathB)) {
            ToastUtil.show("请上传完整再进行下一步");
            return true;
        }
        if ("".equals(pathC)) {
            ToastUtil.show("请上传完整再进行下一步");
            return true;
        }
        if ("".equals(pathD)) {
            ToastUtil.show("请上传完整再进行下一步");
            return true;
        }
        if ("".equals(pathE)) {
            ToastUtil.show("请上传完整再进行下一步");
            return true;
        }
        return false;
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
                        Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivA.setImageBitmap(bitmap);
                        pbA.setVisibility(View.VISIBLE);
                        ivAa.setVisibility(View.VISIBLE);
                        tvA.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvA.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "pocardregis");
                    }
                    if (index == 1) {

                        pathB = newPath;
                        B = true;
                        Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivB.setImageBitmap(bitmap);
                        pbB.setVisibility(View.VISIBLE);
                        ivBb.setVisibility(View.VISIBLE);
                        tvB.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvB.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "ptcardregis");
                    }
                    if (index == 2) {
                        pathC = newPath;
                        C = true;
                        Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivC.setImageBitmap(bitmap);
                        pbC.setVisibility(View.VISIBLE);
                        ivCc.setVisibility(View.VISIBLE);
                        tvC.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvC.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "drivingcard");
                    }
                    if (index == 3) {
                        pathD = newPath;
                        D = true;
                        Bitmap bitmap = BitmapFactory.decodeFile(newPath);
                        ivD.setImageBitmap(bitmap);
                        pbD.setVisibility(View.VISIBLE);
                        ivDd.setVisibility(View.VISIBLE);
                        tvD.setBackgroundResource(R.drawable.scale_black_cir_bg);
                        tvD.setTextColor(ContextCompat.getColor(context, R.color.white));
                        setPhotoData(bitmap, "pchit");
                    }
                    if (index == 4) {
                        pathE = newPath;
                        E = true;
                        Bitmap bitmap = BitmapFactory.decodeFile(newPath);
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
                try {
                    FileOutputStream out = new FileOutputStream(f);
                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 50, out);
                    }
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
                try {
                    file = AVFile.withAbsoluteLocalPath("productPic.jpg", Environment.getExternalStorageDirectory() + "/productPic.jpg");
                    file.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                AVQuery<AVObject> avQuery = new AVQuery<>("Task");//TODO 查询审核ID
                                avQuery.whereEqualTo("tid", tid);
                                avQuery.findInBackground(new FindCallback<AVObject>() {
                                    @Override
                                    public void done(List<AVObject> list, AVException e) {
                                        int cID = list == null || list.size() == 0 ? -1 : (int) list.get(0).get("cid");
                                        if (cID != -1) {
                                            final AVQuery<AVObject> avQuery = new AVQuery<>("Car");//TODO 查询审核ID
                                            avQuery.whereEqualTo("carid", cID);
                                            avQuery.findInBackground(new FindCallback<AVObject>() {
                                                @Override
                                                public void done(List<AVObject> list, AVException e) {
                                                    int pID = list == null || list.size() == 0 ? -1 : (int) list.get(0).get("pid");
                                                    if (pID != -1) {
                                                        mPid = pID;
                                                        AVQuery<AVObject> avQuery1 = new AVQuery<>("Photo");
                                                        avQuery1.whereEqualTo("pid", pID);
                                                        avQuery1.findInBackground(new FindCallback<AVObject>() {
                                                            @Override
                                                            public void done(List<AVObject> list, AVException e) {
                                                                Log.e("avQuery1", list.size() + (e != null ? e.getMessage() : "null"));
                                                                AVObject avObject = list == null || list.size() == 0 ? null : list.get(0);
                                                                avObject.put(keyName, file);
                                                                avObject.saveInBackground(new SaveCallback() {
                                                                    @Override
                                                                    public void done(final AVException e) {
                                                                        if (e == null) {
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    } else {
                                                        AVObject avObject = new AVObject("Photo");
                                                        avObject.put(keyName, file);
                                                        avObject.put("pid", pID + 1);
                                                        avObject.saveInBackground(new SaveCallback() {
                                                            @Override
                                                            public void done(final AVException e) {
                                                                if (e == null) {
                                                                }
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
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
                                });
                            }
                        }
                    }, new ProgressCallback() {
                        @Override
                        public void done(final Integer integer) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
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
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

}
