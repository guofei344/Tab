package com.huaweicompany.guofei.tab.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.huaweicompany.guofei.tab.bean.ViewPageInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guofei on 2016/9/18.
 * 使用的是fragmentpagerAdapter返回的是fragment，而viewpager需要返回的是view
 */
public class ViewPageFragmentAdapter extends FragmentPagerAdapter {

    public List<ViewPageInfo> mPager = new ArrayList<>();
    public Context context;
    public ViewPageFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void addTab(String title,Class<?> clazz){
        mPager.add(new ViewPageInfo(title,clazz));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPager.get(position).title;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mPager.get(position);
        return Fragment.instantiate(context,info.clss.getName());
    }

    @Override
    public int getCount() {
        return mPager.size();
    }
}
