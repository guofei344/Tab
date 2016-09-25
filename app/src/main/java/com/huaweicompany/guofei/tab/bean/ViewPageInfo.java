package com.huaweicompany.guofei.tab.bean;

/**
 * Created by guofei on 2016/9/18.
 */
public class ViewPageInfo {
    public String tag;
    public Class<?> clss;
    public String title;

    public ViewPageInfo(String title, Class<?> clss) {
        this.title = title;
        this.clss = clss;
    }
}
