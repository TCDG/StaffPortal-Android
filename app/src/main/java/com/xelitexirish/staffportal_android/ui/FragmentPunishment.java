package com.xelitexirish.staffportal_android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.xelitexirish.staffportal_android.R;
import com.xelitexirish.staffportal_android.api.ApiHandler;
import com.xelitexirish.staffportal_android.ui.adapter.ListViewAdapter;

import java.util.ArrayList;

/**
 * Created by XeliteXirish on 05/12/2016 (www.xelitexirish.com)
 */

public class FragmentPunishment extends Fragment{

    ContentMenuRecyclerView recyclerView;
    ListViewAdapter adapterPunishments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_punishment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupArrayAdapter(view);
    }

    private void setupArrayAdapter(View view){
        ArrayList punishments = new ArrayList();
        adapterPunishments = new ListViewAdapter(getContext(), punishments);

        recyclerView = (ContentMenuRecyclerView) view.findViewById(R.id.recycler_punishments);
        recyclerView.setAdapter(adapterPunishments);

        // Seperator
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        registerForContextMenu(recyclerView);
    }
}
