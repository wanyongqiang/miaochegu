// Generated code from Butter Knife. Do not modify!
package com.miaochegu.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import ch.ielse.view.SwitchView;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditorTaskActivity_ViewBinding<T extends EditorTaskActivity> implements Unbinder {
  protected T target;

  private View view2131558578;

  private View view2131558586;

  private View view2131558588;

  private View view2131558589;

  private View view2131558579;

  @UiThread
  public EditorTaskActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_back, "field 'llBack' and method 'onClick'");
    target.llBack = Utils.castView(view, R.id.ll_back, "field 'llBack'", LinearLayout.class);
    view2131558578 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.relativeLayout, "field 'relativeLayout'", RelativeLayout.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_nickanme, "field 'llNickanme' and method 'onClick'");
    target.llNickanme = Utils.castView(view, R.id.ll_nickanme, "field 'llNickanme'", RelativeLayout.class);
    view2131558586 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvVin = Utils.findRequiredViewAsType(source, R.id.tv_vin, "field 'tvVin'", TextView.class);
    target.llVin = Utils.findRequiredViewAsType(source, R.id.ll_vin, "field 'llVin'", RelativeLayout.class);
    target.tvCartype = Utils.findRequiredViewAsType(source, R.id.tv_cartype, "field 'tvCartype'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_cartype, "field 'llCartype' and method 'onClick'");
    target.llCartype = Utils.castView(view, R.id.ll_cartype, "field 'llCartype'", RelativeLayout.class);
    view2131558588 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvFirst = Utils.findRequiredViewAsType(source, R.id.tv_first, "field 'tvFirst'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_first, "field 'llFirst' and method 'onClick'");
    target.llFirst = Utils.castView(view, R.id.ll_first, "field 'llFirst'", RelativeLayout.class);
    view2131558589 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvKm = Utils.findRequiredViewAsType(source, R.id.tv_km, "field 'tvKm'", EditText.class);
    target.llKm = Utils.findRequiredViewAsType(source, R.id.ll_km, "field 'llKm'", RelativeLayout.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", EditText.class);
    target.llPrice = Utils.findRequiredViewAsType(source, R.id.ll_price, "field 'llPrice'", RelativeLayout.class);
    target.switchView = Utils.findRequiredViewAsType(source, R.id.switchView, "field 'switchView'", SwitchView.class);
    target.llNature = Utils.findRequiredViewAsType(source, R.id.ll_nature, "field 'llNature'", RelativeLayout.class);
    target.llNote = Utils.findRequiredViewAsType(source, R.id.ll_note, "field 'llNote'", RelativeLayout.class);
    target.edtContent = Utils.findRequiredViewAsType(source, R.id.edt_content, "field 'edtContent'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_ynamic, "field 'tvYnamic' and method 'onClick'");
    target.tvYnamic = Utils.castView(view, R.id.tv_ynamic, "field 'tvYnamic'", TextView.class);
    view2131558579 = view;
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

    target.llBack = null;
    target.relativeLayout = null;
    target.tvName = null;
    target.llNickanme = null;
    target.tvVin = null;
    target.llVin = null;
    target.tvCartype = null;
    target.llCartype = null;
    target.tvFirst = null;
    target.llFirst = null;
    target.tvKm = null;
    target.llKm = null;
    target.tvPrice = null;
    target.llPrice = null;
    target.switchView = null;
    target.llNature = null;
    target.llNote = null;
    target.edtContent = null;
    target.tvYnamic = null;

    view2131558578.setOnClickListener(null);
    view2131558578 = null;
    view2131558586.setOnClickListener(null);
    view2131558586 = null;
    view2131558588.setOnClickListener(null);
    view2131558588 = null;
    view2131558589.setOnClickListener(null);
    view2131558589 = null;
    view2131558579.setOnClickListener(null);
    view2131558579 = null;

    this.target = null;
  }
}
