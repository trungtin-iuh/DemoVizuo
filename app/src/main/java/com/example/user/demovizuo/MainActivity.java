package com.example.user.demovizuo;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.demovizuo.Drawer.DrawerHeader;
import com.example.user.demovizuo.Drawer.DrawerMenuItem;
import com.example.user.demovizuo.utils.DateTime;
import com.mindorks.placeholderview.PlaceHolderView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;

    private Timer mTimer;
    String strDateFormat = "dd/MM/yyyy";

    String dateUserForTimeZone = DateTime.getInstance().getDateTime(
            strDateFormat, String.valueOf(TimeZone.getTimeZone("GMT+07:00")));

    private ImageView imgMenu,imgRotate,imgRefresh,imgAddDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initOnClick();
        
        setupDrawer();

    }


    private DrawerHeader mDrawerHeader;
    private void setupDrawer() {
        mDrawerHeader = new DrawerHeader();
        mDrawerView
                .addView(mDrawerHeader)
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext()));

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                startTimer();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                stopTimer();
            }
        };

        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initOnClick() {
    }

    private void initView() {

        imgAddDashboard = findViewById(R.id.imgAddDashboard);
//        imgMenu = findViewById(R.id.imgMenu);
        imgRotate = findViewById(R.id.imgRotate);
        imgRefresh = findViewById(R.id.imgRefresh);

        mDrawer = findViewById(R.id.drawerLayout);
        mDrawerView = findViewById(R.id.drawerView);
        mToolbar = findViewById(R.id.toolbar);
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread (new Runnable(){
                @Override
                public void run() {

                    mDrawerHeader.getTxtTime().setText(DateTime.getInstance().getTimer("GMT+07:00"));
                    mDrawerHeader.getTxtDate().setText(dateUserForTimeZone);
                }
            });
        }
    }


    private void startTimer(){
        if(mTimer != null){
            mTimer.cancel();
        }

        mTimer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();
        mTimer.scheduleAtFixedRate(myTimerTask, 1000, 1000);
    }

    private void stopTimer(){
        if (mTimer!=null){
            mTimer.cancel();
            mTimer = null;
        }
    }
}
