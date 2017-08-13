// Generated code from Butter Knife. Do not modify!
package com.miaochegu.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectPhotoActivity_ViewBinding<T extends SelectPhotoActivity> implements Unbinder {
  protected T target;

  private View view2131558577;

  private View view2131558578;

  private View view2131558600;

  private View view2131558599;

  private View view2131558604;

  private View view2131558603;

  private View view2131558608;

  private View view2131558607;

  private View view2131558614;

  private View view2131558613;

  private View view2131558618;

  private View view2131558617;

  @UiThread
  public SelectPhotoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_back, "field 'llBack' and method 'onClick'");
    target.llBack = Utils.castView(view, R.id.ll_back, "field 'llBack'", LinearLayout.class);
    view2131558577 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_ynamic, "field 'tvYnamic' and method 'onClick'");
    target.tvYnamic = Utils.castView(view, R.id.tv_ynamic, "field 'tvYnamic'", TextView.class);
    view2131558578 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.relativeLayout, "field 'relativeLayout'", RelativeLayout.class);
    target.ivA = Utils.findRequiredViewAsType(source, R.id.iv_a, "field 'ivA'", ImageView.class);
    target.ivB = Utils.findRequiredViewAsType(source, R.id.iv_b, "field 'ivB'", ImageView.class);
    target.ivC = Utils.findRequiredViewAsType(source, R.id.iv_c, "field 'ivC'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_aa, "field 'ivAa' and method 'onClick'");
    target.ivAa = Utils.castView(view, R.id.iv_aa, "field 'ivAa'", ImageView.class);
    view2131558600 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbA = Utils.findRequiredViewAsType(source, R.id.pb_a, "field 'pbA'", ProgressBar.class);
    target.tvA = Utils.findRequiredViewAsType(source, R.id.tv_a, "field 'tvA'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_a, "field 'rlA' and method 'onClick'");
    target.rlA = Utils.castView(view, R.id.rl_a, "field 'rlA'", RelativeLayout.class);
    view2131558599 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_bb, "field 'ivBb' and method 'onClick'");
    target.ivBb = Utils.castView(view, R.id.iv_bb, "field 'ivBb'", ImageView.class);
    view2131558604 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbB = Utils.findRequiredViewAsType(source, R.id.pb_b, "field 'pbB'", ProgressBar.class);
    target.tvB = Utils.findRequiredViewAsType(source, R.id.tv_b, "field 'tvB'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_b, "field 'rlB' and method 'onClick'");
    target.rlB = Utils.castView(view, R.id.rl_b, "field 'rlB'", RelativeLayout.class);
    view2131558603 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_cc, "field 'ivCc' and method 'onClick'");
    target.ivCc = Utils.castView(view, R.id.iv_cc, "field 'ivCc'", ImageView.class);
    view2131558608 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbC = Utils.findRequiredViewAsType(source, R.id.pb_c, "field 'pbC'", ProgressBar.class);
    target.tvC = Utils.findRequiredViewAsType(source, R.id.tv_c, "field 'tvC'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_c, "field 'rlC' and method 'onClick'");
    target.rlC = Utils.castView(view, R.id.rl_c, "field 'rlC'", RelativeLayout.class);
    view2131558607 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ivD = Utils.findRequiredViewAsType(source, R.id.iv_d, "field 'ivD'", ImageView.class);
    target.ivE = Utils.findRequiredViewAsType(source, R.id.iv_e, "field 'ivE'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_dd, "field 'ivDd' and method 'onClick'");
    target.ivDd = Utils.castView(view, R.id.iv_dd, "field 'ivDd'", ImageView.class);
    view2131558614 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbD = Utils.findRequiredViewAsType(source, R.id.pb_d, "field 'pbD'", ProgressBar.class);
    target.tvD = Utils.findRequiredViewAsType(source, R.id.tv_d, "field 'tvD'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_d, "field 'rlD' and method 'onClick'");
    target.rlD = Utils.castView(view, R.id.rl_d, "field 'rlD'", RelativeLayout.class);
    view2131558613 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_ee, "field 'ivEe' and method 'onClick'");
    target.ivEe = Utils.castView(view, R.id.iv_ee, "field 'ivEe'", ImageView.class);
    view2131558618 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbE = Utils.findRequiredViewAsType(source, R.id.pb_e, "field 'pbE'", ProgressBar.class);
    target.tvE = Utils.findRequiredViewAsType(source, R.id.tv_e, "field 'tvE'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_e, "field 'rlE' and method 'onClick'");
    target.rlE = Utils.castView(view, R.id.rl_e, "field 'rlE'", RelativeLayout.class);
    view2131558617 = view;
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

    target.llBack = null;
    target.tvYnamic = null;
    target.relativeLayout = null;
    target.ivA = null;
    target.ivB = null;
    target.ivC = null;
    target.ivAa = null;
    target.pbA = null;
    target.tvA = null;
    target.rlA = null;
    target.ivBb = null;
    target.pbB = null;
    target.tvB = null;
    target.rlB = null;
    target.ivCc = null;
    target.pbC = null;
    target.tvC = null;
    target.rlC = null;
    target.ivD = null;
    target.ivE = null;
    target.ivDd = null;
    target.pbD = null;
    target.tvD = null;
    target.rlD = null;
    target.ivEe = null;
    target.pbE = null;
    target.tvE = null;
    target.rlE = null;
    target.mProgess = null;

    view2131558577.setOnClickListener(null);
    view2131558577 = null;
    view2131558578.setOnClickListener(null);
    view2131558578 = null;
    view2131558600.setOnClickListener(null);
    view2131558600 = null;
    view2131558599.setOnClickListener(null);
    view2131558599 = null;
    view2131558604.setOnClickListener(null);
    view2131558604 = null;
    view2131558603.setOnClickListener(null);
    view2131558603 = null;
    view2131558608.setOnClickListener(null);
    view2131558608 = null;
    view2131558607.setOnClickListener(null);
    view2131558607 = null;
    view2131558614.setOnClickListener(null);
    view2131558614 = null;
    view2131558613.setOnClickListener(null);
    view2131558613 = null;
    view2131558618.setOnClickListener(null);
    view2131558618 = null;
    view2131558617.setOnClickListener(null);
    view2131558617 = null;

    this.target = null;
  }
}
