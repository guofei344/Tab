package com.huaweicompany.guofei.tab.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.huaweicompany.guofei.tab.R;
import com.huaweicompany.guofei.tab.Uitil.XmlUtils;
import com.huaweicompany.guofei.tab.bean.Tweet;
import com.huaweicompany.guofei.tab.bean.TweetsList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by guofei on 2016/9/21.
 */
public class itemDetailActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private TextView detailText;
    private ImageButton imageButton_arrow;
    private ImageButton imageButton_image;
    private EditText detailEditText;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        initView();
        initToolBar();
    }

    //初始化控件
    private void initView() {
        getSupportActionBar();
        //View itemView = View.inflate(this, R.layout.item_detail, null);
        toolBar = (Toolbar) findViewById(R.id.tb_toolbar);
        detailText = (TextView) findViewById(R.id.tv_detail_text);
        imageButton_arrow = (ImageButton) findViewById(R.id.ib_imagebutton_arrow);
        imageButton_image = (ImageButton) findViewById(R.id.ib_imagebutton_images);
        detailEditText = (EditText) findViewById(R.id.et_detail_edittext);
        requestDate();
    }

    private void requestDate() {

        AsyncHttpClient httpClient = new AsyncHttpClient();
        String url = "http://192.168.81.213:8080/oschina/detail/tweet_detail/6066159.xml";
        httpClient.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getBaseContext(),"请求数据失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);
                TweetsList tweetsList = XmlUtils.toBean(TweetsList.class, responseString.getBytes());
                List<Tweet> tweetList = tweetsList.getList();
            }
        });
    }

    //初始化toolbar数据
    private void initToolBar() {
        //设置title的标题
        toolBar.setTitle("动弹详情");
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.actionbar_back_icon_normal);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
             finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
