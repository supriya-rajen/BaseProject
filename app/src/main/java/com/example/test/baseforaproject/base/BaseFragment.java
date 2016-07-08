package com.example.test.baseforaproject.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.baseforaproject.BaseApp;
import com.example.test.baseforaproject.R;
import com.example.test.baseforaproject.leftmenu.HomeActivity;


public class BaseFragment extends Fragment {

    private boolean isAvailable = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isAvailable = true;
    }

    public BaseApp getApp() {
        return (BaseApp) getActivity().getApplication();
    }

    public BaseFragment getActiveFragment() {
        return (BaseFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    public void onFragmentOpened() {}

    public void onFragmentClosed() {}

    public HomeActivity getHome() {
        return (HomeActivity) getActivity();
    }

    protected boolean isUIAvailable() {
        return isAvailable;
    }

    @Override
    public void onDestroy() {
        isAvailable = false;
        super.onDestroy();
    }

    public void setPageTitle(View view, String title) {
        TextView txt = (TextView) view.findViewById(R.id.txt_title);
        if (txt != null) {
            txt.setText(title);
        }
    }

    public void setPageTitle(View view, int stringID) {
        setPageTitle(view, getString(stringID));
    }

    public void setLeftVisibility(View view, int type) {
        ImageView firstLeft = (ImageView) view.findViewById(R.id.left_icon);
        if (firstLeft != null) {
            firstLeft.setVisibility(type);
        }
    }

    public void setRightVisibility(View view, int type) {
        ImageView secondRight = (ImageView) view.findViewById(R.id.right_icon);
        if (secondRight != null) {
            secondRight.setVisibility(type);
        }
    }

    public void setLeftImage(View view, int image) {
        ImageView firstLeft = (ImageView) view.findViewById(R.id.left_icon);
        if (firstLeft != null) {
            firstLeft.setVisibility(View.VISIBLE);
            firstLeft.setOnClickListener(onClick);
            firstLeft.setImageResource(image);
        }
    }

    public void setRightImage(View view, int image) {
        ImageView secondRight = (ImageView) view.findViewById(R.id.right_icon);
        if (secondRight != null) {
            secondRight.setVisibility(View.VISIBLE);
            secondRight.setOnClickListener(onClick);
            secondRight.setImageResource(image);
        }
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.left_icon:
                    onLeftIconClick();
                    break;
                case R.id.right_icon:
                    onRightIconClick();
                    break;
            }
        }
    };

    public void onLeftIconClick() {
        //Override if you want
    }

    public void onRightIconClick() {
        //Override if you want
    }
}
