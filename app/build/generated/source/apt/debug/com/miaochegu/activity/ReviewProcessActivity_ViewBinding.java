// Generated code from Butter Knife. Do not modify!
package com.miaochegu.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.androidkun.xtablayout.XTabLayout;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReviewProcessActivity_ViewBinding<T extends ReviewProcessActivity> implements Unbinder {
  protected T target;

  private View view2131558535;

  @UiThread
  public ReviewProcessActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tabFindFragmentTitle = Utils.findRequiredViewAsType(source, R.id.tab_FindFragment_title, "field 'tabFindFragmentTitle'", XTabLayout.class);
    target.vpFindFragmentPager = Utils.findRequiredViewAsType(source, R.id.vp_FindFragment_pager, "field 'vpFindFragmentPager'", ViewPager.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onClick'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view2131558535 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mProgess = Utils.findRequiredViewAsType(source, R.id.mProgess, "field 'mProgess'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tabFindFragmentTitle = null;
    target.vpFindFragmentPager = null;
    target.tvTitle = null;
    target.ivBack = null;
    target.mProgess = null;

    view2131558535.setOnClickListener(null);
    view2131558535 = null;

    this.target = null;
  }
}
