package com.miaochegu.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.miaochegu.R;
import com.miaochegu.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*****************************************
 * 修改密码
 *
 * @author wyq
 *         created at  2017/8/3 11:44
 ****************************************/
public class UpdatePassWordActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.username_register_button)
    Button usernameRegisterButton;

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        ButterKnife.bind(this);

        context = this;
    }

    @OnClick({R.id.iv_back, R.id.username_register_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                this.finish();
                break;
            case R.id.username_register_button://确定
                String pwda = username.getText().toString().trim();
                String pwdb = phone.getText().toString().trim();
                if (!pwda.equals(pwdb)) {
                    ToastUtil.show("两次密码不一致");
                    return;
                }
                AVUser avuser = AVUser.getCurrentUser();
                avuser.put("password", pwdb);
                avuser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        ToastUtil.show("修改成功");
                    }
                });
                Log.e("MCG", pwdb);
                break;
        }
    }
}
