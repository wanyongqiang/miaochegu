// Generated code from Butter Knife. Do not modify!
package com.miaochegu.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UpdatePassWordActivity_ViewBinding<T extends UpdatePassWordActivity> implements Unbinder {
  protected T target;

  private View view2131558534;

  private View view2131558664;

  @UiThread
  public UpdatePassWordActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onClick'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131558534 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.username = Utils.findRequiredViewAsType(source, R.id.username, "field 'username'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.username_register_button, "field 'usernameRegisterButton' and method 'onClick'");
    target.usernameRegisterButton = Utils.castView(view, R.id.username_register_button, "field 'usernameRegisterButton'", Button.class);
    view2131558664 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivBack = null;
    target.username = null;
    target.phone = null;
    target.usernameRegisterButton = null;

    view2131558534.setOnClickListener(null);
    view2131558534 = null;
    view2131558664.setOnClickListener(null);
    view2131558664 = null;

    this.target = null;
  }
}
