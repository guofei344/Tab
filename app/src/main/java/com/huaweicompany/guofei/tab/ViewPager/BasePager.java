package com.huaweicompany.guofei.tab.ViewPager;

import android.app.Activity;
/**
 * Created by guofei on 2016/9/18.
 */
public class BasePager {

    public Activity activity;

    public BasePager(Activity activity){
        this.activity = activity;

        initView();
    }

    private void initView() {
    }
}
