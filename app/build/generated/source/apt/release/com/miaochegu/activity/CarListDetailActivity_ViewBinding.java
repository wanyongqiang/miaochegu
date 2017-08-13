// Generated code from Butter Knife. Do not modify!
package com.miaochegu.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CarListDetailActivity_ViewBinding<T extends CarListDetailActivity> implements Unbinder {
  protected T target;

  private View view2131558534;

  private View view2131558575;

  private View view2131558576;

  @UiThread
  public CarListDetailActivity_ViewBinding(final T target, View source) {
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
    target.tvBecause = Utils.findRequiredViewAsType(source, R.id.tv_because, "field 'tvBecause'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.tvCartype = Utils.findRequiredViewAsType(source, R.id.tv_cartype, "field 'tvCartype'", TextView.class);
    target.tvCity = Utils.findRequiredViewAsType(source, R.id.tv_city, "field 'tvCity'", TextView.class);
    target.tvVin = Utils.findRequiredViewAsType(source, R.id.tv_vin, "field 'tvVin'", TextView.class);
    target.tvYear = Utils.findRequiredViewAsType(source, R.id.tv_year, "field 'tvYear'", TextView.class);
    target.tvKmAndPrice = Utils.findRequiredViewAsType(source, R.id.tv_km_and_price, "field 'tvKmAndPrice'", TextView.class);
    target.tvDesc = Utils.findRequiredViewAsType(source, R.id.tv_desc, "field 'tvDesc'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_cancel, "field 'tvCancel' and method 'onClick'");
    target.tvCancel = Utils.castView(view, R.id.tv_cancel, "field 'tvCancel'", TextView.class);
    view2131558575 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_update, "field 'tvUpdate' and method 'onClick'");
    target.tvUpdate = Utils.castView(view, R.id.tv_update, "field 'tvUpdate'", TextView.class);
    view2131558576 = view;
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
    target.tvBecause = null;
    target.tvNumber = null;
    target.tvTime = null;
    target.tvType = null;
    target.tvCartype = null;
    target.tvCity = null;
    target.tvVin = null;
    target.tvYear = null;
    target.tvKmAndPrice = null;
    target.tvDesc = null;
    target.tvCancel = null;
    target.tvUpdate = null;

    view2131558534.setOnClickListener(null);
    view2131558534 = null;
    view2131558575.setOnClickListener(null);
    view2131558575 = null;
    view2131558576.setOnClickListener(null);
    view2131558576 = null;

    this.target = null;
  }
}
