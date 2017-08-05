package com.miaochegu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.miaochegu.R;
import com.miaochegu.util.ListItemClickHelp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*****************************************
 * 企业下的建筑详情
 *
 * @author liuteng
 *         created at  2016/5/12 15:31
 ****************************************/
public class WaiteAssessAdapter extends RecyclerView.Adapter<WaiteAssessAdapter.MyViewHolder> {
    private final LayoutInflater mInflater;
    Context context;
    List<AVObject> entity;
    private ListItemClickHelp callback;
    private OnItemClickListener mOnItemeClickLstener;
    private View inflate;

    public WaiteAssessAdapter(Context context, List<AVObject> entity, ListItemClickHelp callback) {
        this.context = context;
        this.callback = callback;
        this.entity = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    public void upRes(List<AVObject> list) {
        this.entity.clear();
        if (list != null) {
            this.entity.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addRes(List<AVObject> list) {
        int size = this.entity.size();
        if (list != null) {
            this.entity.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = mInflater.inflate(R.layout.activity_waite_assess_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final AVObject rowsEntity = entity.get(position);
        holder.tv_name.setText("任务单号：" + entity.get(position).get("tid"));
        String tcreatetime = entity.get(position).get("tcreatetime").toString();
        holder.tv_time.setText(tcreatetime);
        final String cid = entity.get(position).get("cid").toString();
        final String sid = entity.get(position).get("sid").toString();
        AVQuery<AVObject> avQuery = new AVQuery<>("Car");
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null && list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (cid.equals(list.get(i).get("carid").toString())) {
                            holder.tv_content.setText(list.get(i).get("cmodels").toString());
                            AVQuery<AVObject> avQuery = new AVQuery<>("Audit");
                            avQuery.orderByDescending("createdAt");
                            avQuery.findInBackground(new FindCallback<AVObject>() {
                                @Override
                                public void done(List<AVObject> list, AVException e) {
                                    if (e == null && list != null) {
                                        for (int i = 0; i < list.size(); i++) {
                                            if (sid.equals(list.get(i).get("sid").toString()) && (int) list.get(i).get("atype") == 0 || (int) list.get(i).get("atype") == 2) {
                                                holder.tvfs.setText(list.get(i).get("appraiser") == null ? "" : list.get(i).get("appraiser") + "正在处理");
                                            }
                                        }
                                    } else {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });

        if (mOnItemeClickLstener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    holder.itemView.setTag(rowsEntity);
                    mOnItemeClickLstener.onItemeClick(holder.itemView, layoutPosition);
                }
            });
        }

        final int p = position;
        final int viewId = holder.tvfs.getId();

        holder.tvfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(inflate, p, viewId, cid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entity != null ? entity.size() : 0;
    }

    public void setmOnItemeClickListener(OnItemClickListener listener) {
        this.mOnItemeClickLstener = listener;
    }

    public interface OnItemClickListener {
        void onItemeClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content, tv_chakan, tvfs, tv_name, tv_time;
        View v_line;
        LinearLayout ll_ll_wtg;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_chakan = (TextView) view.findViewById(R.id.tv_chakan);
            tvfs = (TextView) view.findViewById(R.id.tv_fs);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            v_line = view.findViewById(R.id.v_line);
            ll_ll_wtg = (LinearLayout) view.findViewById(R.id.ll_wtg);
        }
    }

    private Date formatDate(String string) {
        SimpleDateFormat resultSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat resultSdfdate = new SimpleDateFormat("yyyy-MM-dd");
        if (string != null) {
            if (string.contains("CST")) {
                long d2 = Date.parse(string);
                Date datetime = new Date(d2);

                return datetime;

            } else if (string.contains("Z")) {
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd'T'hh:mm:ss'.'sss'Z'");
                Date datetime;
                try {
                    datetime = sdf.parse(string);
                    return (Date) datetime;
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else if (string.contains("-") && string.contains(":")) {
                Date newDate;
                try {
                    newDate = resultSdf.parse(string);
                    return newDate;
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (string.contains("-") && !string.contains(":")) {
                Date newDate;
                try {
                    newDate = resultSdfdate.parse(string);
                    return newDate;
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Date longDate = new Date(Long.parseLong(string));

                return longDate;
            }
        }
        return null;
    }

}
