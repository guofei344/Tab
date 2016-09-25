package com.huaweicompany.guofei.tab.movefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.snowdream.android.widget.SmartImageView;
import com.huaweicompany.guofei.tab.R;
import com.huaweicompany.guofei.tab.Uitil.XmlUtils;
import com.huaweicompany.guofei.tab.bean.Tweet;
import com.huaweicompany.guofei.tab.bean.TweetsList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by guofei on 2016/9/18.
 */
public class MyFragment extends Fragment {

    private SwipeRefreshLayout srl_swiperrefresh;
    private PullToLoadMoreListView lv_listview_move;
    private List<Tweet> list;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.move_listview,null);
        initView(view);
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requetDate();
    }
    //请求网络数据
    public void requetDate() {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        String url = "http://192.168.81.213:8080/oschina/list/mytweet/page0.xml";
        httpClient.get(url, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getContext(),"网络异常",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);
                TweetsList tweetsList = XmlUtils.toBean(TweetsList.class, responseString.getBytes());
                if(tweetsList!=null){
                    list = tweetsList.getList();
                }
                lv_listview_move.setAdapter(new mAdapter());
            }
        });

    }

    private void initView(View view) {
        srl_swiperrefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_swiperefresh);
        lv_listview_move = (PullToLoadMoreListView) view.findViewById(R.id.lv_listview_move);
    }

    private class mAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
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
            SmartImageView iv_pht;
        }

        /**
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView==null){
                holder = new ViewHolder();
                convertView = View.inflate(context,R.layout.listview_item,null);
                holder.iv_item_image = (SmartImageView) convertView.findViewById(R.id.iv_item_image);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                holder.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
                holder.tv_good = (TextView) convertView.findViewById(R.id.tv_good);
                holder.iv_pht = (SmartImageView) convertView.findViewById(R.id.iv_pht);
                convertView.setTag(holder);

            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.iv_item_image.setImageUrl(list.get(position).getPortrait(),null);
            holder.tv_title.setText(list.get(position).getAuthor());
            //获取资源里面的颜色
            holder.tv_title.setTextColor(getResources().getColor(R.color.color_title));
            //展示描述的富文本
            String body = list.get(position).getBody();
            Spanned spanned = Html.fromHtml(body);
            holder.tv_desc.setText(spanned);
            holder.tv_count.setText(list.get(position).getCommentCount());
            //获取user的集合，判断是否有需要显示的点赞数据，
            //Tweet里面的方法。
            list.get(position).setLikeUsers(context,holder.tv_good,true);
            //判断是否有照片需要展示
            String img = list.get(position).getImgSmall();
            //System.out.println("img"+img);
            if(!TextUtils.isEmpty(img)){
                holder.iv_pht.setVisibility(View.VISIBLE);
                holder.iv_pht.setImageUrl(img,null);
            }else{
                holder.iv_pht.setVisibility(View.GONE);
            }

            return convertView;
        }
    }
}
