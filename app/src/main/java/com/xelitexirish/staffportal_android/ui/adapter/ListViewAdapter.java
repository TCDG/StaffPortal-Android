package com.xelitexirish.staffportal_android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xelitexirish.staffportal_android.R;
import com.xelitexirish.staffportal_android.api.PunishmentObject;

import java.util.List;

/**
 * Created by XeliteXirish on 05/12/2016 (www.xelitexirish.com)
 */

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private Context mContext;
    private List<PunishmentObject> punishmentObjects;

    public ListViewAdapter(Context context, List<PunishmentObject> punishmentObjects){
        this.mContext = context;
        this.punishmentObjects = punishmentObjects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recyclerView = inflater.inflate(R.layout.item_punishment, parent, false);
        return new ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return punishmentObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
