package com.huaweicompany.guofei.tab.movefragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.huaweicompany.guofei.tab.R;

/**
 * 添加刷新到底部的显示下面的数据，或当下面的数据没有的时候，显示出刷新底部
 * Created by guofei on 2016/9/21.
 */
public class PullToLoadMoreListView extends ListView implements AbsListView.OnScrollListener{

    private View footerView;
    private TextView tv_show_text;
    private int measuredHeight;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                showNoDate(false);
        }
            super.handleMessage(msg);
        }

    };

    private void showNoDate(boolean isloading) {
        //显示加载更多条目
        footerView.setPadding(0,0,0,0);
        //设置当前显示条目
        setSelection(getCount()-1);
        tv_show_text = (TextView) footerView.findViewById(R.id.tv_show_text);
        if(isloading){
            tv_show_text.setVisibility(INVISIBLE);
            handler.sendEmptyMessageDelayed(1,500);
        }else {
            tv_show_text.setVisibility(VISIBLE);
        }
    }

    public PullToLoadMoreListView(Context context) {
        this(context,null);
    }

    public PullToLoadMoreListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullToLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addFooter();
        //添加listview的监听的事件
        setOnScrollListener(this);
    }

    //将下面的布局添加到下面，隐藏起来。
    private void addFooter() {
        footerView = View.inflate(getContext(), R.layout.footview, null);
        footerView.measure(0,0);
        measuredHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0,0,0,-measuredHeight);
        addFooterView(footerView);
    }
    //当滑动到最后一个条目的时候加载更多
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    //如果滚动状态改变，而且现在是停止状态，并且当前显示的条目是最后一条，那么加载更多数据
        if(scrollState == OnScrollListener.SCROLL_STATE_IDLE && getLastVisiblePosition() == getCount()-1){
            //显示加载更多条目
            footerView.setPadding(0,0,0,0);
            //设置当前显示条目
            setSelection(getCount()-1);
            //通过回调函数加载更多数据
            if(listener != null) {
                listener.loadMore();
            }
        }
    }

    //加载完毕之后，取消加载条目
    public void finish(){
        footerView.setPadding(0,0,0,-measuredHeight);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}

    OnLoadingMoreDatasListener listener;
    public interface OnLoadingMoreDatasListener{
        public void loadMore();
    }
    public void setOnLoadingMoreDatasListener(OnLoadingMoreDatasListener listener){
        this.listener = listener;
    }
}
