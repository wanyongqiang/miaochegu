// Generated code from Butter Knife. Do not modify!
package com.miaochegu.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WaiteAssessActivity_ViewBinding<T extends WaiteAssessActivity> implements Unbinder {
  protected T target;

  private View view2131558534;

  @UiThread
  public WaiteAssessActivity_ViewBinding(final T target, View source) {
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
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.rlAssess = Utils.findRequiredViewAsType(source, R.id.rl_assess, "field 'rlAssess'", XRecyclerView.class);
    target.mProgess = Utils.findRequiredViewAsType(source, R.id.mProgess, "field 'mProgess'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivBack = null;
    target.tvTitle = null;
    target.rlAssess = null;
    target.mProgess = null;

    view2131558534.setOnClickListener(null);
    view2131558534 = null;

    this.target = null;
  }
}
