package com.huaweicompany.guofei.tab.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.huaweicompany.guofei.tab.MainActivity;
import com.huaweicompany.guofei.tab.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guofei on 2016/9/16.
 */
public class SynthesisFragment extends Fragment {
   /* private List positons = new ArrayList();
    private PagerSlidingTabStrip tabs;
    private ViewPager viewPager;
    private MainActivity activity;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void onCreate( Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab1,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    private void initDate() {
        //tabs = (PagerSlidingTabStrip) activity.findViewById(R.id.tabs);
        viewPager = (ViewPager) activity.findViewById(R.id.view_pager);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(activity.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return fragments.get(position).getClass().getSimpleName();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size()-1);

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int currentCount=0;

            @Override
            public void onPageSelected(int position) {
                if(!positons.contains(position)){
                    positons.add(position);
                    Fragment fragment = fragments.get(position);
                    //fragment.initPager();
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        tabs.setViewPager(viewPager);
        tabs.setBackgroundColor(Color.GRAY);
        tabs.setTextColor(Color.WHITE);
        tabs.setTextSize(20);
        tabs.setUnderlineColor(Color.GREEN);

    }
    //将布局添加到该布局当中
    private void initView() {
        SynthesisFragment atab = new SynthesisFragment();
        ShiftFragment btab = new ShiftFragment();
        FindFragment ctab = new FindFragment();
        MineFragment dtab = new MineFragment();
        fragments.add(atab);
        fragments.add(btab);
        fragments.add(ctab);
        fragments.add(dtab);
    }
*/
}
