package com.huaweicompany.guofei.tab.base;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.huaweicompany.guofei.tab.R;
import com.huaweicompany.guofei.tab.adapter.ViewPageFragmentAdapter;

/**
 * Created by guofei on 2016/9/18.
 * 布局为一个fragment里面有slidingtab,下面是一个viewpager，抽取到基类
 */
public abstract class BaseViewPagerFragment extends Fragment {

    public PagerSlidingTabStrip pager_sliding_tab;
    public ViewPager view_pager;
    public ViewPageFragmentAdapter mViewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpager_fragment,null);
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //展示pager
        pager_sliding_tab = (PagerSlidingTabStrip) view.findViewById(R.id.pager_sliding_tab);
        setTabs();
        view_pager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),getActivity());

        //设置tab选项级内容，抽象方法，子类重写,添加的fragent界面的fragment
        onSetUpTabAdapter(mViewPagerAdapter);
        view_pager.setAdapter(mViewPagerAdapter);
        //设置pagerslidingtabstrip关联
        pager_sliding_tab.setViewPager(view_pager);

        initDate();
    }
    public abstract void initDate();
    public abstract void onSetUpTabAdapter(ViewPageFragmentAdapter mViewPagerAdapter) ;

    private void setTabs() {

        // 设置页签背景
        pager_sliding_tab.setTabBackground(Color.TRANSPARENT);
        // 设置下划线高度
        pager_sliding_tab.setUnderlineHeight(1);
        // 设置下划线颜色
        pager_sliding_tab.setUnderlineColor(0x33333333);
        // 平分页签
        pager_sliding_tab.setShouldExpand(true);
        // 去掉分割线
        pager_sliding_tab.setDividerColor(Color.TRANSPARENT);
        // 设置滑动条的颜色
        pager_sliding_tab.setIndicatorColor(0xff0b9a27);
        // 设置滑动条的高度
        pager_sliding_tab.setIndicatorHeight(10);
        // 设置文字大小
        pager_sliding_tab.setTextSize(20);
        // 设置文字字体
        pager_sliding_tab.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);

        // 提供设置选中页签颜色的方法
        //pager_sliding_tab.setSelectedTabColor(0xff0b9a27);
    }

}
