package com.example.user.demovizuo.Drawer;

import android.widget.TextView;
import com.example.user.demovizuo.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by User on 03/23/2018.
 */


    @NonReusable
    @Layout(R.layout.drawer_header)
    public class DrawerHeader {

    @View(R.id.txtUserName)
    private TextView txtUserName;

    @View(R.id.txtTime)
    private TextView txtTime;

    @View(R.id.txtDate)
    private TextView txtDate;

    public TextView getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(TextView txtUserName) {
        this.txtUserName = txtUserName;
    }

    public TextView getTxtTime() {
        return txtTime;
    }

    public void setTxtTime(TextView txtTime) {
        this.txtTime = txtTime;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(TextView txtDate) {
        this.txtDate = txtDate;
    }

    @Resolve
    private void onResolved() {
        txtUserName.setText("KichBan");
        txtTime.setText("");
        txtDate.setText("");
    }

}
