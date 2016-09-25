package com.huaweicompany.guofei.tab.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.huaweicompany.guofei.tab.adapter.ViewPageFragmentAdapter;
import com.huaweicompany.guofei.tab.base.BaseViewPagerFragment;
import com.huaweicompany.guofei.tab.bean.ViewPageInfo;
import com.huaweicompany.guofei.tab.movefragment.MostHotFragment;
import com.huaweicompany.guofei.tab.movefragment.MostNewFragment;
import com.huaweicompany.guofei.tab.movefragment.MyFragment;
import java.util.ArrayList;

/**
 * Created by guofei on 2016/9/16.
 */
public class ShiftFragment extends BaseViewPagerFragment {


    String[] mTitles = {"最新动弹","热门动弹","我的动弹"};
    //private ArrayList<Integer> positions = new ArrayList<>();
    @Override
    public void initDate() {}

    @Override
    public void onSetUpTabAdapter(ViewPageFragmentAdapter mAdapter) {
        mAdapter.addTab(mTitles[0],MostNewFragment.class);
        mAdapter.addTab(mTitles[1],MostHotFragment.class);
        mAdapter.addTab(mTitles[2],MyFragment.class);
    }

}