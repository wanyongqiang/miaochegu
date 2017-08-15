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

public class SelectPhotoThreeActivity_ViewBinding<T extends SelectPhotoThreeActivity> implements Unbinder {
  protected T target;

  private View view2131558602;

  private View view2131558601;

  private View view2131558606;

  private View view2131558605;

  private View view2131558610;

  private View view2131558609;

  private View view2131558616;

  private View view2131558615;

  private View view2131558620;

  private View view2131558619;

  private View view2131558623;

  private View view2131558555;

  private View view2131558579;

  private View view2131558580;

  private View view2131558626;

  private View view2131558625;

  private View view2131558633;

  private View view2131558632;

  private View view2131558637;

  private View view2131558636;

  private View view2131558641;

  private View view2131558640;

  private View view2131558648;

  private View view2131558647;

  private View view2131558652;

  private View view2131558651;

  private View view2131558656;

  private View view2131558655;

  @UiThread
  public SelectPhotoThreeActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.ivA = Utils.findRequiredViewAsType(source, R.id.iv_a, "field 'ivA'", ImageView.class);
    target.ivB = Utils.findRequiredViewAsType(source, R.id.iv_b, "field 'ivB'", ImageView.class);
    target.ivC = Utils.findRequiredViewAsType(source, R.id.iv_c, "field 'ivC'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_aa, "field 'ivAa' and method 'onClick'");
    target.ivAa = Utils.castView(view, R.id.iv_aa, "field 'ivAa'", ImageView.class);
    view2131558602 = view;
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
    view2131558601 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_bb, "field 'ivBb' and method 'onClick'");
    target.ivBb = Utils.castView(view, R.id.iv_bb, "field 'ivBb'", ImageView.class);
    view2131558606 = view;
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
    view2131558605 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_cc, "field 'ivCc' and method 'onClick'");
    target.ivCc = Utils.castView(view, R.id.iv_cc, "field 'ivCc'", ImageView.class);
    view2131558610 = view;
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
    view2131558609 = view;
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
    view2131558616 = view;
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
    view2131558615 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_ee, "field 'ivEe' and method 'onClick'");
    target.ivEe = Utils.castView(view, R.id.iv_ee, "field 'ivEe'", ImageView.class);
    view2131558620 = view;
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
    view2131558619 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_up, "field 'tvUp' and method 'onClick'");
    target.tvUp = Utils.castView(view, R.id.tv_up, "field 'tvUp'", TextView.class);
    view2131558623 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_next, "field 'tvNext' and method 'onClick'");
    target.tvNext = Utils.castView(view, R.id.tv_next, "field 'tvNext'", TextView.class);
    view2131558555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_back, "field 'llBack' and method 'onClick'");
    target.llBack = Utils.castView(view, R.id.ll_back, "field 'llBack'", LinearLayout.class);
    view2131558579 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_ynamic, "field 'tvYnamic' and method 'onClick'");
    target.tvYnamic = Utils.castView(view, R.id.tv_ynamic, "field 'tvYnamic'", TextView.class);
    view2131558580 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ivF = Utils.findRequiredViewAsType(source, R.id.iv_f, "field 'ivF'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_ff, "field 'ivFf' and method 'onClick'");
    target.ivFf = Utils.castView(view, R.id.iv_ff, "field 'ivFf'", ImageView.class);
    view2131558626 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbF = Utils.findRequiredViewAsType(source, R.id.pb_f, "field 'pbF'", ProgressBar.class);
    target.tvF = Utils.findRequiredViewAsType(source, R.id.tv_f, "field 'tvF'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_f, "field 'rlF' and method 'onClick'");
    target.rlF = Utils.castView(view, R.id.rl_f, "field 'rlF'", RelativeLayout.class);
    view2131558625 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ivG = Utils.findRequiredViewAsType(source, R.id.iv_g, "field 'ivG'", ImageView.class);
    target.ivH = Utils.findRequiredViewAsType(source, R.id.iv_h, "field 'ivH'", ImageView.class);
    target.ivI = Utils.findRequiredViewAsType(source, R.id.iv_i, "field 'ivI'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_gg, "field 'ivGg' and method 'onClick'");
    target.ivGg = Utils.castView(view, R.id.iv_gg, "field 'ivGg'", ImageView.class);
    view2131558633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbG = Utils.findRequiredViewAsType(source, R.id.pb_g, "field 'pbG'", ProgressBar.class);
    target.tvG = Utils.findRequiredViewAsType(source, R.id.tv_g, "field 'tvG'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_g, "field 'rlG' and method 'onClick'");
    target.rlG = Utils.castView(view, R.id.rl_g, "field 'rlG'", RelativeLayout.class);
    view2131558632 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_hh, "field 'ivHh' and method 'onClick'");
    target.ivHh = Utils.castView(view, R.id.iv_hh, "field 'ivHh'", ImageView.class);
    view2131558637 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbH = Utils.findRequiredViewAsType(source, R.id.pb_h, "field 'pbH'", ProgressBar.class);
    target.tvH = Utils.findRequiredViewAsType(source, R.id.tv_h, "field 'tvH'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_h, "field 'rlH' and method 'onClick'");
    target.rlH = Utils.castView(view, R.id.rl_h, "field 'rlH'", RelativeLayout.class);
    view2131558636 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_ii, "field 'ivIi' and method 'onClick'");
    target.ivIi = Utils.castView(view, R.id.iv_ii, "field 'ivIi'", ImageView.class);
    view2131558641 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbI = Utils.findRequiredViewAsType(source, R.id.pb_i, "field 'pbI'", ProgressBar.class);
    target.tvI = Utils.findRequiredViewAsType(source, R.id.tv_i, "field 'tvI'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_i, "field 'rlI' and method 'onClick'");
    target.rlI = Utils.castView(view, R.id.rl_i, "field 'rlI'", RelativeLayout.class);
    view2131558640 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ivJ = Utils.findRequiredViewAsType(source, R.id.iv_j, "field 'ivJ'", ImageView.class);
    target.ivK = Utils.findRequiredViewAsType(source, R.id.iv_k, "field 'ivK'", ImageView.class);
    target.ivL = Utils.findRequiredViewAsType(source, R.id.iv_l, "field 'ivL'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_jj, "field 'ivJj' and method 'onClick'");
    target.ivJj = Utils.castView(view, R.id.iv_jj, "field 'ivJj'", ImageView.class);
    view2131558648 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbJ = Utils.findRequiredViewAsType(source, R.id.pb_j, "field 'pbJ'", ProgressBar.class);
    target.tvJ = Utils.findRequiredViewAsType(source, R.id.tv_j, "field 'tvJ'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_j, "field 'rlJ' and method 'onClick'");
    target.rlJ = Utils.castView(view, R.id.rl_j, "field 'rlJ'", RelativeLayout.class);
    view2131558647 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_kk, "field 'ivKk' and method 'onClick'");
    target.ivKk = Utils.castView(view, R.id.iv_kk, "field 'ivKk'", ImageView.class);
    view2131558652 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbK = Utils.findRequiredViewAsType(source, R.id.pb_k, "field 'pbK'", ProgressBar.class);
    target.tvK = Utils.findRequiredViewAsType(source, R.id.tv_k, "field 'tvK'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_k, "field 'rlK' and method 'onClick'");
    target.rlK = Utils.castView(view, R.id.rl_k, "field 'rlK'", RelativeLayout.class);
    view2131558651 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_ll, "field 'ivLl' and method 'onClick'");
    target.ivLl = Utils.castView(view, R.id.iv_ll, "field 'ivLl'", ImageView.class);
    view2131558656 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.pbL = Utils.findRequiredViewAsType(source, R.id.pb_l, "field 'pbL'", ProgressBar.class);
    target.tvL = Utils.findRequiredViewAsType(source, R.id.tv_l, "field 'tvL'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_l, "field 'rlL' and method 'onClick'");
    target.rlL = Utils.castView(view, R.id.rl_l, "field 'rlL'", RelativeLayout.class);
    view2131558655 = view;
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
    target.tvUp = null;
    target.tvNext = null;
    target.llBack = null;
    target.tvYnamic = null;
    target.ivF = null;
    target.ivFf = null;
    target.pbF = null;
    target.tvF = null;
    target.rlF = null;
    target.ivG = null;
    target.ivH = null;
    target.ivI = null;
    target.ivGg = null;
    target.pbG = null;
    target.tvG = null;
    target.rlG = null;
    target.ivHh = null;
    target.pbH = null;
    target.tvH = null;
    target.rlH = null;
    target.ivIi = null;
    target.pbI = null;
    target.tvI = null;
    target.rlI = null;
    target.ivJ = null;
    target.ivK = null;
    target.ivL = null;
    target.ivJj = null;
    target.pbJ = null;
    target.tvJ = null;
    target.rlJ = null;
    target.ivKk = null;
    target.pbK = null;
    target.tvK = null;
    target.rlK = null;
    target.ivLl = null;
    target.pbL = null;
    target.tvL = null;
    target.rlL = null;

    view2131558602.setOnClickListener(null);
    view2131558602 = null;
    view2131558601.setOnClickListener(null);
    view2131558601 = null;
    view2131558606.setOnClickListener(null);
    view2131558606 = null;
    view2131558605.setOnClickListener(null);
    view2131558605 = null;
    view2131558610.setOnClickListener(null);
    view2131558610 = null;
    view2131558609.setOnClickListener(null);
    view2131558609 = null;
    view2131558616.setOnClickListener(null);
    view2131558616 = null;
    view2131558615.setOnClickListener(null);
    view2131558615 = null;
    view2131558620.setOnClickListener(null);
    view2131558620 = null;
    view2131558619.setOnClickListener(null);
    view2131558619 = null;
    view2131558623.setOnClickListener(null);
    view2131558623 = null;
    view2131558555.setOnClickListener(null);
    view2131558555 = null;
    view2131558579.setOnClickListener(null);
    view2131558579 = null;
    view2131558580.setOnClickListener(null);
    view2131558580 = null;
    view2131558626.setOnClickListener(null);
    view2131558626 = null;
    view2131558625.setOnClickListener(null);
    view2131558625 = null;
    view2131558633.setOnClickListener(null);
    view2131558633 = null;
    view2131558632.setOnClickListener(null);
    view2131558632 = null;
    view2131558637.setOnClickListener(null);
    view2131558637 = null;
    view2131558636.setOnClickListener(null);
    view2131558636 = null;
    view2131558641.setOnClickListener(null);
    view2131558641 = null;
    view2131558640.setOnClickListener(null);
    view2131558640 = null;
    view2131558648.setOnClickListener(null);
    view2131558648 = null;
    view2131558647.setOnClickListener(null);
    view2131558647 = null;
    view2131558652.setOnClickListener(null);
    view2131558652 = null;
    view2131558651.setOnClickListener(null);
    view2131558651 = null;
    view2131558656.setOnClickListener(null);
    view2131558656 = null;
    view2131558655.setOnClickListener(null);
    view2131558655 = null;

    this.target = null;
  }
}
