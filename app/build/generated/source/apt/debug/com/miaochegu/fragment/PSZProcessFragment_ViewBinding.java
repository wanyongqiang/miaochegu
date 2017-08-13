// Generated code from Butter Knife. Do not modify!
package com.miaochegu.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PSZProcessFragment_ViewBinding<T extends PSZProcessFragment> implements Unbinder {
  protected T target;

  @UiThread
  public PSZProcessFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.rlAssess = Utils.findRequiredViewAsType(source, R.id.rl_assess, "field 'rlAssess'", XRecyclerView.class);
    target.mProgess = Utils.findRequiredViewAsType(source, R.id.mProgess, "field 'mProgess'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlAssess = null;
    target.mProgess = null;

    this.target = null;
  }
}
