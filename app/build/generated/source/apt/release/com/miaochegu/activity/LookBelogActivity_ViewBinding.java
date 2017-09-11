// Generated code from Butter Knife. Do not modify!
package com.miaochegu.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LookBelogActivity_ViewBinding<T extends LookBelogActivity> implements Unbinder {
  protected T target;

  @UiThread
  public LookBelogActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.vLine = Utils.findRequiredView(source, R.id.v_line, "field 'vLine'");
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvFs = Utils.findRequiredViewAsType(source, R.id.tv_fs, "field 'tvFs'", TextView.class);
    target.tvVincode = Utils.findRequiredViewAsType(source, R.id.tv_vincode, "field 'tvVincode'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'tvDate'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.tvKm = Utils.findRequiredViewAsType(source, R.id.tv_km, "field 'tvKm'", TextView.class);
    target.tvB = Utils.findRequiredViewAsType(source, R.id.tv_b, "field 'tvB'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvA = Utils.findRequiredViewAsType(source, R.id.tv_a, "field 'tvA'", TextView.class);
    target.tvAccident = Utils.findRequiredViewAsType(source, R.id.tv_accident, "field 'tvAccident'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivBack = null;
    target.tvNumber = null;
    target.tvContent = null;
    target.vLine = null;
    target.tvTime = null;
    target.tvFs = null;
    target.tvVincode = null;
    target.tvDate = null;
    target.tvAddress = null;
    target.tvKm = null;
    target.tvB = null;
    target.tvPrice = null;
    target.tvA = null;
    target.tvAccident = null;

    this.target = null;
  }
}
