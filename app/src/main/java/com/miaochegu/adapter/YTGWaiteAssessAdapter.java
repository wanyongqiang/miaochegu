package com.miaochegu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miaochegu.R;
import com.miaochegu.model.CarInfoModel;
import com.miaochegu.util.ListItemClickHelp;

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
public class YTGWaiteAssessAdapter extends RecyclerView.Adapter<YTGWaiteAssessAdapter.MyViewHolder> {
    private final LayoutInflater mInflater;
    Context context;
    List<CarInfoModel> entity;
    private ListItemClickHelp callback;
    private OnItemClickListener mOnItemeClickLstener;
    private View inflate;

    public YTGWaiteAssessAdapter(Context context, List<CarInfoModel> entity, ListItemClickHelp callback) {
        this.context = context;
        this.callback = callback;
        this.entity = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    public void upRes(List<CarInfoModel> list) {
        this.entity.clear();
        if (list != null) {
            this.entity.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addRes(List<CarInfoModel> list) {
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
        final CarInfoModel rowsEntity = entity.get(position);
        holder.tv_chakan.setVisibility(View.VISIBLE);
        holder.tvfs.setText("审核已过");
        holder.tv_name.setText("任务单号：" + rowsEntity.getTask_id());
        String tcreatetime = rowsEntity.getTime();
        holder.tv_time.setText(tcreatetime);
        holder.tv_content.setText(rowsEntity.getCarType());

        if (mOnItemeClickLstener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    holder.itemView.setTag(rowsEntity);
                    mOnItemeClickLstener.onItemeClick(holder.itemView, layoutPosition,
                            rowsEntity.getCar_id() + "", rowsEntity.getTask_id() + "", rowsEntity.getAudit_id() + "");
                }
            });
        }

        final int p = position;
        final int viewId = holder.tv_chakan.getId();

        holder.tv_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(inflate, p, viewId, rowsEntity.getCar_id() + "", rowsEntity.getTask_id() + "");
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
        void onItemeClick(View view, int position, String cID, String tID, String sID);
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

    private String getDateString(String strOld) {
        Date date = new Date();
        date.parse(strOld);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
