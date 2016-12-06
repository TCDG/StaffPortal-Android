package com.xelitexirish.staffportal_android.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xelitexirish.staffportal_android.R;
import com.xelitexirish.staffportal_android.api.ApiHandler;
import com.xelitexirish.staffportal_android.api.PunishmentObject;
import com.xelitexirish.staffportal_android.ui.adapter.ListViewAdapter;

import java.util.List;

/**
 * Created by XeliteXirish on 05/12/2016 (www.xelitexirish.com)
 */

public class FragmentPunishment extends Fragment {

    public static SwipeRefreshLayout swipeRefreshLayout;

    ContentMenuRecyclerView recyclerView;
    public static ListViewAdapter adapterPunishments;
    public static List<PunishmentObject> punishmentObjects = ApiHandler.getPunishments();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_punishment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        adapterPunishments = new ListViewAdapter(getContext(), punishmentObjects);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ApiHandler.setupLists(getContext());
            }
        });

        setupArrayAdapter(view);
    }

    private void setupArrayAdapter(View view) {

        recyclerView = (ContentMenuRecyclerView) view.findViewById(R.id.recycler_punishments);
        recyclerView.setAdapter(adapterPunishments);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Seperator
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        registerForContextMenu(recyclerView);
    }

    public static void updateRyclerView(Context context, List<PunishmentObject> newPunishmentObjects) {
        punishmentObjects.clear();

        punishmentObjects.addAll(newPunishmentObjects);
        adapterPunishments.notifyDataSetChanged();

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
