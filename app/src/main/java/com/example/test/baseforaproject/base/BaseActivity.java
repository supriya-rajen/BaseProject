package com.example.test.baseforaproject.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.baseforaproject.BaseApp;
import com.example.test.baseforaproject.R;


public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public BaseApp getApp() {
        return (BaseApp) getApplication();
    }

    public void setPageTitle(String title) {
        TextView txt = (TextView) findViewById(R.id.txt_title);
        if (txt != null) {
            txt.setText(title);
        }
    }

    public void setPageTitle(int stringID) {
        setPageTitle(getString(stringID));
    }


    public void setLeftVisibility(int type) {
        ImageView firstLeft = (ImageView) findViewById(R.id.left_icon);
        if (firstLeft != null) {
            firstLeft.setVisibility(type);
        }
    }


    public void setRightVisibility(int type) {
        ImageView secondRight = (ImageView) findViewById(R.id.right_icon);
        if (secondRight != null) {
            secondRight.setVisibility(type);
        }
    }

    public void setLeftImage(int image) {
        ImageView firstLeft = (ImageView) findViewById(R.id.left_icon);
        if (firstLeft != null) {
            firstLeft.setVisibility(View.VISIBLE);
            firstLeft.setOnClickListener(onClick);
            firstLeft.setImageResource(image);
        }
    }

    public void setRightImage(int image) {
        ImageView secondRight = (ImageView) findViewById(R.id.right_icon);
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
    }


    public void onRightIconClick() {
    }
}