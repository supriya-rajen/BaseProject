package com.example.test.baseforaproject.leftmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.test.baseforaproject.MainActivity;
import com.example.test.baseforaproject.R;
import com.example.test.baseforaproject.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    Button btnLogout;
    public static String loggedOut = MainActivity.loggedintime;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        init(view);
        setupDefaults();
        setupEvents();
        return view;
    }

    private void init(View view) {
        setPageTitle(view,R.string.home);
        setLeftImage(view,R.drawable.ic_menu_bar_white);
        setRightVisibility(view,view.INVISIBLE);
        btnLogout = (Button) view.findViewById(R.id.logout);
    }

    @Override
    public void onLeftIconClick() {
        super.onLeftIconClick();
        getHome().getSlidingMenu().toggle();
    }

    private void setupDefaults() {
    }

    private void setupEvents() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApp().getAppPrefrence().logout();
               getActivity().finish();
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("from", "logout");
                startActivity(i);
            }
        });
    }
}
