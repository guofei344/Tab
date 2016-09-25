package com.huaweicompany.guofei.tab.movefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.snowdream.android.widget.SmartImageView;
import com.huaweicompany.guofei.tab.Activity.itemDetailActivity;
import com.huaweicompany.guofei.tab.R;
import com.huaweicompany.guofei.tab.Uitil.XmlUtils;
import com.huaweicompany.guofei.tab.bean.Tweet;
import com.huaweicompany.guofei.tab.bean.TweetsList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by guofei on 2016/9/18.
 */
public class MostNewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<Tweet> list;
    private SwipeRefreshLayout srl_swiperefresh;
    private PullToLoadMoreListView lv_listview_move;
    private Context context;
    private boolean isFresh;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.move_listview,null);
        initView(view);

        context = getContext();
        return view;
    }
    //定义一个
    int j = 0;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requetDate();
    }
    //请求网络数据
    public void requetDate() {
        AsyncHttpClient httpClient = new AsyncHttpClient();

        String url = "http://192.168.81.213:8080/oschina/list/tweet_list/page"+j+".xml";
        httpClient.get(url, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    srl_swiperefresh.setRefreshing(false);
                Toast.makeText(getContext(),"请求数据失败",Toast.LENGTH_SHORT).show();
            }

            /**
             * @param statusCode
             * @param headers
             * @param responseString
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //System.out.println(responseString);
                TweetsList tweetsList = XmlUtils.toBean(TweetsList.class, responseString.getBytes());
                if(tweetsList!=null){
                    list = tweetsList.getList();
                    lv_listview_move.setAdapter(new mAdapter());
                    //scrollview里面嵌套listview
                    /*ListAdapter adapter = lv_listview_move.getAdapter();
                    View view = adapter.getView(2, null, lv_listview_move);
                    view.measure(0,0);
                    view.getMeasuredHeight();*/
                }

                srl_swiperefresh.setRefreshing(false);
            }
        });
    }

    private void initView(View view) {
        srl_swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_swiperefresh);
        lv_listview_move = (PullToLoadMoreListView) view.findViewById(R.id.lv_listview_move);
        srl_swiperefresh.setColorSchemeResources(R.color.colorPrimaryDark,R.color.colorAccent,
                R.color.color_gray,R.color.color_green);
        srl_swiperefresh.setOnRefreshListener(this);
        lv_listview_move.setOnLoadingMoreDatasListener(new PullToLoadMoreListView.OnLoadingMoreDatasListener() {
            @Override
            public void loadMore() {
                j += 1;
                requetDate();
                lv_listview_move.finish();
            }
        });
        //设置item的点击跳转页面
        lv_listview_move.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,itemDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     *设置监听刷新成功的时候将刷新头隐藏
     */
    @Override
    public void onRefresh() {
        //设置刷新成功
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!srl_swiperefresh.isRefreshing()){
                    requetDate();
                }
                srl_swiperefresh.setRefreshing(false);
            }
        },3000);
        requetDate();
        j=0;
    }

    private class mAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list == null?0:list.size();
        }
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        class ViewHolder{
            SmartImageView iv_item_image;
            TextView tv_title;
            TextView tv_desc;
            TextView tv_count;
            TextView tv_good;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if(convertView==null){
                holder = new ViewHolder();
                convertView = View.inflate(context,R.layout.listview_item,null);
                holder.iv_item_image = (SmartImageView) convertView.findViewById(R.id.iv_item_image);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                holder.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
                holder.tv_good = (TextView) convertView.findViewById(R.id.tv_good);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.iv_item_image.setImageUrl(list.get(position).getPortrait(),null);
            holder.tv_title.setText(list.get(position).getAuthor());
            //获取资源里面的颜色
            holder.tv_title.setTextColor(getResources().getColor(R.color.color_title));
            Spanned spanned = Html.fromHtml(list.get(position).getBody());
            holder.tv_desc.setText(spanned);
            holder.tv_count.setText(list.get(position).getCommentCount());
            //展示点赞人
            list.get(position).setLikeUsers(context,holder.tv_good,true);
            //设置跳转页面
           /*convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,itemDetailActivity.class);
                    startActivity(intent);
                }
            });*/
            return convertView;


        }
    }

}
