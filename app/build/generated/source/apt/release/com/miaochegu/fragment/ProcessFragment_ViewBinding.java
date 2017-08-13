// Generated code from Butter Knife. Do not modify!
package com.miaochegu.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.miaochegu.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProcessFragment_ViewBinding<T extends ProcessFragment> implements Unbinder {
  protected T target;

  @UiThread
  public ProcessFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.rlAssess = Utils.findRequiredViewAsType(source, R.id.rl_assess, "field 'rlAssess'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlAssess = null;

    this.target = null;
  }
}
