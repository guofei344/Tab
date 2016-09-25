 package com.huaweicompany.guofei.tab;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.huaweicompany.guofei.tab.Fragment.FindFragment;
import com.huaweicompany.guofei.tab.Fragment.MineFragment;
import com.huaweicompany.guofei.tab.Fragment.ShiftFragment;
import com.huaweicompany.guofei.tab.Fragment.SynthesisFragment;

 public class MainActivity extends AppCompatActivity {

     private DrawerLayout draw_layout;
     private ActionBarDrawerToggle mDrawertoggle;
     private Toolbar toolbar;
     private ListView lv_menu;
     private RadioGroup rg_radio;

     /**
      * @param savedInstanceState
      */
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         initView();
        initToolBar();
         //initMenuListView();
     }

     //初始化控件
     private void initView() {
         rg_radio = (RadioGroup) findViewById(R.id.rg_dadio_group);
         toolbar =  (Toolbar) findViewById(R.id.tb_toolbar);
         draw_layout = (DrawerLayout) findViewById(R.id.draw_layout);
         lv_menu = (ListView) findViewById(R.id.lv_menu);
         rg_radio.check(R.id.rb_all);
         rg_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 FragmentManager manager = getSupportFragmentManager();
                 FragmentTransaction mTransaction = manager.beginTransaction();
                 switch (checkedId){
                     case R.id.rb_all:
                        mTransaction.replace(R.id.fl_layout,new SynthesisFragment());
                         break;
                     case R.id.rb_find:
                         mTransaction.replace(R.id.fl_layout,new FindFragment());
                         break;
                     case R.id.rb_move:
                        mTransaction.replace(R.id.fl_layout,new ShiftFragment());
                         break;
                     case R.id.rb_mine:
                         mTransaction.replace(R.id.fl_layout,new MineFragment());
                         break;
                 }
                 mTransaction.commit();
             }
         });

     }
     /**
      *初始化toolbar
      */
     private void initToolBar() {
         //设置toolbar的标题
         toolbar.setTitle("开源中国");
         setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        //设置actionbar的左上角箭头，可以点击
         //getSupportActionBar().setLogo(R.drawable.ic_launcher);
        //创建抽屉的开关用于抽屉的开关
         mDrawertoggle = new ActionBarDrawerToggle(this,draw_layout, toolbar,0,0);
         mDrawertoggle.syncState();
         draw_layout.setDrawerListener(mDrawertoggle);
    }
    //设置菜单按钮
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main_activity_menu,menu);
         return super.onCreateOptionsMenu(menu);
     }
     //设置item中的点击事件、
     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         if(mDrawertoggle.onOptionsItemSelected(item)){
            return true;
         }
         switch (item.getItemId()){
             case R.id.search:
                 Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.add:
                 Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.send:
                 Toast.makeText(this,"send",Toast.LENGTH_SHORT).show();
                 break;
         }

         return super.onOptionsItemSelected(item);
     }
 }
