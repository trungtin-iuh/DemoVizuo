package com.example.user.demovizuo.Drawer;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.demovizuo.R;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by User on 03/23/2018.
 */

@Layout(R.layout.drawer_item)

public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_DASHBOARD = 1;
    public static final int DRAWER_MENU_ITEM_ALARM_MANAGERMENT = 2;
    public static final int DRAWER_MENU_ITEM_EVENT_MANAGERMENT = 3;
    public static final int DRAWER_MENU_ITEM_SETTING = 4;
    public static final int DRAWER_MENU_ITEM_SIGN_OUT = 5;

    @Position
    int mMenuPosition;

    @View(R.id.txtItemName)
    private TextView txtItemName;

    @View(R.id.imgItemIcon)
    private ImageView imgItemIcon;

    private Context mContext;
    private DrawerCallBack mCallBack;


    public DrawerMenuItem(Context mContext) {
        this.mContext = mContext;
    }

    @Resolve
    private void onResolved(){

        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_DASHBOARD:
                imgItemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.dashboard));
                txtItemName.setText("Dashboard");
                break;
            case DRAWER_MENU_ITEM_ALARM_MANAGERMENT:
                imgItemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.alarm));
                txtItemName.setText("Alarm Managerment");
                break;
            case DRAWER_MENU_ITEM_EVENT_MANAGERMENT:
                imgItemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.calendar));
                txtItemName.setText("Event Managerment");
                break;
            case DRAWER_MENU_ITEM_SETTING:
                imgItemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.setting));
                txtItemName.setText("Setting");
                break;
            case DRAWER_MENU_ITEM_SIGN_OUT:
                imgItemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.sign_out));
                txtItemName.setText("Sign Out");
                break;
        }
    }

    @Click(R.id.mainView)

    private void onMenuItemClick(){

        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_DASHBOARD:
                Toast.makeText(mContext, "Dashboard", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onDashBoardMenuSelected();
                break;
            case DRAWER_MENU_ITEM_ALARM_MANAGERMENT:
                Toast.makeText(mContext, "Alarm managerment", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onAlarmManagermentMenuSelected();
                break;
            case DRAWER_MENU_ITEM_EVENT_MANAGERMENT:
                Toast.makeText(mContext, "Event managerment", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onEventManagermentMenuSelected();
                break;
            case DRAWER_MENU_ITEM_SETTING:
                Toast.makeText(mContext, "Setting", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onSettingMenuSelected();
                break;
            case DRAWER_MENU_ITEM_SIGN_OUT:
                Toast.makeText(mContext, "Sign out", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onSignOutMenuSelected();
                break;
        }

    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onDashBoardMenuSelected();
        void onAlarmManagermentMenuSelected();
        void onEventManagermentMenuSelected();
        void onSettingMenuSelected();
        void onSignOutMenuSelected();

    }
}
