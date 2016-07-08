package com.example.test.baseforaproject.leftmenu;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;

import com.example.test.baseforaproject.R;
import com.example.test.baseforaproject.base.BaseFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class HomeActivity extends SlidingMenuActivity  implements OnMenuSelectedListener{

    private BaseFragment fragment;

    public static final String CURRENT_FRAGMENT = HomeActivity.class.getPackage().getName() + ".CURRENT_FRAGMENT";

    private SlidingMenu.OnOpenListener onOpenListener = new SlidingMenu.OnOpenListener() {
        @Override
        public void onOpen() {
            if (fragment != null) {
                ( fragment).onFragmentClosed();
            }
        }
    };

    private int mCurrentMenu = -1;
    private boolean mUpdateFragmentUI = false;
    private SlidingMenu.OnClosedListener onClosedListener = new SlidingMenu.OnClosedListener() {
        @Override
        public void onClosed() {
            if (fragment != null && mUpdateFragmentUI) {
                ( fragment).onFragmentOpened();
                mUpdateFragmentUI = false;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbase_content_frame);

        init();
        setupDefaults();
        setupEvents();

        int currentMenu = 0;
        if (savedInstanceState != null && savedInstanceState.containsKey(CURRENT_FRAGMENT)) {
            currentMenu = savedInstanceState.getInt(CURRENT_FRAGMENT);
        }

        Bundle args = new Bundle();
        if (getIntent() != null) {
            currentMenu = getIntent().getIntExtra(CURRENT_FRAGMENT, currentMenu);
        }

        selectMenu(currentMenu, false, false, true, args);
        getSlidingMenu().setOnClosedListener(onClosedListener);
        getSlidingMenu().setOnOpenListener(onOpenListener);
    }

    private void init() {

    }

    private void setupDefaults() {

    }

    private void setupEvents() {

    }

    public void selectMenu(int menu, boolean checkCurrentFragment, boolean isToggle, boolean refreshOnCreate, Bundle args) {
        BaseFragment f = null;

        switch (menu) {
            case 0:
                f = new HomeFragment();
                break;
            default:
                f = new HomeFragment();
                break;
        }

        if (f != null) {
            f.setArguments(args);
            addFragment(f, menu, checkCurrentFragment, isToggle);
        }
    }

    private void addFragment(BaseFragment fragment, int menu, boolean checkCurrentFragment, boolean isToggle) {
        if (checkCurrentFragment) {
            if (fragment != null && menu != mCurrentMenu) {
                mCurrentMenu = menu;
                this.fragment = fragment;

                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.replace(R.id.content_frame, fragment);
                t.commit();
                mUpdateFragmentUI = true;
            }
        } else {
            if (fragment != null) {
                mCurrentMenu = menu;
                this.fragment = fragment;
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.replace(R.id.content_frame, fragment);
                t.commit();
                mUpdateFragmentUI = true;
            }
        }

        if (isToggle) {
            getSlidingMenu().toggle();
        }
    }

    @Override
    public void onMenuSelect(int menu, boolean checkCurrentFragment, boolean isToggle) {
        selectMenu(menu, checkCurrentFragment, isToggle, false, null);
    }
}
