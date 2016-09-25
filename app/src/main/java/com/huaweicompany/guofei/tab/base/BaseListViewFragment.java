package com.huaweicompany.guofei.tab.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.huaweicompany.guofei.tab.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by guofei on 2016/9/19.
 */
public abstract class BaseListViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.lv_listview_move)
    protected ListView lv_listview_move;

    @InjectView(R.id.srl_swiperefresh)
    protected SwipeRefreshLayout srl_swiperefresh;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.move_listview,null);

        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        ButterKnife.inject(this,view);
        initView();
    }
    //初始化数据
    private void initView() {
        srl_swiperefresh.setOnRefreshListener(this);
        srl_swiperefresh.setColorSchemeResources(
                R.color.colorPrimaryDark, R.color.colorAccent,
                R.color.color_gray, R.color.color_green);
    }
    //下拉刷新数据
    @Override
    public void onRefresh() {
        lv_listview_move.setSelection(0);

        requestDate();
    }

    public abstract void requestDate();
}
