package com.example.test.baseforaproject.leftmenu;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.test.baseforaproject.BaseApp;
import com.example.test.baseforaproject.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class SlidingMenuActivity extends SlidingFragmentActivity {

    private LeftMenuFragment leftMenuFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbase_content_frame);
        setBehindContentView(R.layout.home_menu_frame);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            leftMenuFragment = new LeftMenuFragment();
            transaction.replace(R.id.menu_frame, leftMenuFragment);
            transaction.commit();
        } else {
            leftMenuFragment = (LeftMenuFragment) this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
        }

        SlidingMenu menu = getSlidingMenu();
        menu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
        menu.setShadowDrawable(R.drawable.left_menu_shadow);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setFadeDegree(0.35f);
        menu.setFadeEnabled(true);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
    }

    public BaseApp getRMApp() {
        return (BaseApp) getApplication();
    }

    protected LeftMenuFragment getLeftMenu() {
        return leftMenuFragment;
    }
}
