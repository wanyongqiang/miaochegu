package com.miaochegu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaochegu.R;
import com.miaochegu.model.CountryModel;
import com.miaochegu.util.ListItemClickHelp;

import java.util.ArrayList;
import java.util.List;


/*****************************************
 * 企业下的建筑详情
 *
 * @author liuteng
 *         created at  2016/5/12 15:31
 ****************************************/
public class CarTypeAdapter extends RecyclerView.Adapter<CarTypeAdapter.MyViewHolder> {
    private final LayoutInflater mInflater;
    Context context;
    List<CountryModel> entity;
    private ListItemClickHelp callback;
    private OnItemClickListener mOnItemeClickLsteners;
    private View inflate;

    public CarTypeAdapter(Context context, List<CountryModel> entity) {
        this.context = context;
        this.entity = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    public void upRes(List<CountryModel> list) {
        this.entity.clear();
        if (list != null) {
            this.entity.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void addRes(List<CountryModel> list) {
        int size = this.entity.size();
        if (list != null) {
            this.entity.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = mInflater.inflate(R.layout.acrivity_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CountryModel rowsEntity = entity.get(position);

        holder.tv_name.setText(rowsEntity.getCarType());

        if (mOnItemeClickLsteners != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    holder.itemView.setTag(rowsEntity);
                    mOnItemeClickLsteners.onItemeClickType(holder.itemView, layoutPosition, rowsEntity.getCarType());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return entity != null ? entity.size() : 0;
    }

    public void setmOnItemeClickListener(OnItemClickListener listener) {
        this.mOnItemeClickLsteners = listener;
    }

    public interface OnItemClickListener{
        void onItemeClickType(View view, int position, String carType);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
