package com.example.test.baseforaproject.leftmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.test.baseforaproject.R;
import com.example.test.baseforaproject.base.BaseFragment;
import com.example.test.baseforaproject.utils.DeviceUtils;

import java.util.ArrayList;

public class LeftMenuFragment extends BaseFragment {

    private View rootView;
    private ListView listView;
    private ArrayList<String> listMenuItems;
    private OnMenuSelectedListener mOnMenuSelectCallback;

    public static Fragment newInstance() {
        LeftMenuFragment fragment = new LeftMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listMenuItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listMenuItems.add("Menu Item " + i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_left_menu, container, false);
        listView = (ListView) rootView.findViewById(R.id.lsv_menu);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            mOnMenuSelectCallback = (OnMenuSelectedListener) activity;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListViewAdapter();
    }

    private void setListViewAdapter() {
        SlidingMenuAdapter adapter = new SlidingMenuAdapter( getActivity(), R.layout.left_menu_item_view, listMenuItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener());
        DeviceUtils.setSystemUiVisibility(listView);
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mOnMenuSelectCallback.onMenuSelect(position, true, true);
            }
        };
    }

    /**
     * private class to make a listview adapter based on ArrayAdapter
     */
    private class SlidingMenuAdapter extends ArrayAdapter<String> {

        private FragmentActivity activity;
        private ArrayList<String> items;

        public SlidingMenuAdapter(FragmentActivity activity, int resource, ArrayList<String> objects) {
            super(activity, resource, objects);
            this.activity = activity;
            this.items = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            // If holder not exist then locate all view from UI file.
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.left_menu_item_view, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.itemName.setText(getItem(position));

            return convertView;
        }

        private class ViewHolder {
            private TextView itemName;

            public ViewHolder(View v) {
                itemName = (TextView) v.findViewById(R.id.name);
            }
        }
    }
}