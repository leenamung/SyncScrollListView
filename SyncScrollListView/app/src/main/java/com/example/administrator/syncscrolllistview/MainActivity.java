package com.example.administrator.syncscrolllistview;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements SyncViews {
    ImageView top_image;
    RelativeLayout menu_container;
    ViewPager scrolling_pager;
    LinearLayout menu_box;
    View point_bar;
    FrameLayout.LayoutParams menu_lp, top_lp;

    int pagerOffset = 0;

    RelativeLayout.LayoutParams point_param;
    mListView[] syncList = new mListView[3];

    SyncScrollPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            initAPI11();
            initSyncListAPI11();
        } else {
            init();
            initSyncList();
        }

    }

    private void init() {
        top_image = (ImageView) findViewById(R.id.top_image);
        menu_container = (RelativeLayout) findViewById(R.id.menu_contain);
        scrolling_pager = (ViewPager) findViewById(R.id.scrolling_pager);
        menu_box = (LinearLayout) findViewById(R.id.menu_box);
        point_bar = findViewById(R.id.point_bar);

        menu_lp = (FrameLayout.LayoutParams) menu_container.getLayoutParams();
        menu_lp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());

        top_lp = (FrameLayout.LayoutParams) top_image.getLayoutParams();
    }

    private void initAPI11() {
        top_image = (ImageView) findViewById(R.id.top_image);
        menu_container = (RelativeLayout) findViewById(R.id.menu_contain);
        scrolling_pager = (ViewPager) findViewById(R.id.scrolling_pager);
        menu_box = (LinearLayout) findViewById(R.id.menu_box);
        point_bar = findViewById(R.id.point_bar);


        menu_container.setTranslationY((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics()));
    }

    private void initSyncList() {

        float top_imageHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        float menu_containerHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < 50; i++)
            temp.add("menu " + (i + 1));

        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, temp);
        for (int i = 0; i < syncList.length; i++) {

            syncList[i] = new mListView(this);
            syncList[i].setSyncViews(this);
            syncList[i].setListHeader(top_imageHeight, menu_containerHeight);
            syncList[i].setAdapter(listadapter);
        }

        adapter = new SyncScrollPagerAdapter(syncList);


        TextView[] txt_v = new TextView[syncList.length];
        for (int i = 0; i < syncList.length; i++) {

            txt_v[i] = new TextView(this);
            txt_v[i].setText("menu " + (i + 1));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            menu_box.addView(txt_v[i], lp);
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels / 3;
        point_param = (RelativeLayout.LayoutParams) point_bar.getLayoutParams();

        pagerOffset = width;
        point_param.width = width;

        scrolling_pager.setAdapter(adapter);
        scrolling_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
                float offset = positionOffest * pagerOffset;
                int p = position * pagerOffset;
                //if api level 11 use it
//                point_bar.setTranslationX(p + offset);

                point_param.setMargins((int) (p + offset), 0, 0, 0);
                point_bar.setLayoutParams(point_param);
                for (int i = 0; i < syncList.length; i++) {
                    if (i == position)
                        syncList[i].setVisibleList();
                    else
                        syncList[i].setUnVisibleList();
                }
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void initSyncListAPI11() {

        float top_imageHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        float menu_containerHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < 50; i++)
            temp.add("menu " + (i + 1));

        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, temp);
        for (int i = 0; i < syncList.length; i++) {

            syncList[i] = new mListView(this);
            syncList[i].setSyncViews(this);
            syncList[i].setListHeader(top_imageHeight, menu_containerHeight);
            syncList[i].setAdapter(listadapter);
        }

        adapter = new SyncScrollPagerAdapter(syncList);


        TextView[] txt_v = new TextView[syncList.length];
        for (int i = 0; i < syncList.length; i++) {

            txt_v[i] = new TextView(this);
            txt_v[i].setText("menu " + (i + 1));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            menu_box.addView(txt_v[i], lp);
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels / 3;
        point_param = (RelativeLayout.LayoutParams) point_bar.getLayoutParams();

        pagerOffset = width;
        point_param.width = width;

        scrolling_pager.setAdapter(adapter);
        scrolling_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {
                float offset = positionOffest * pagerOffset;
                int p = position * pagerOffset;
                point_bar.setTranslationX(p + offset);

                for (int i = 0; i < syncList.length; i++) {
                    if (i == position)
                        syncList[i].setVisibleList();
                    else
                        syncList[i].setUnVisibleList();
                }
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }


    @Override
    public void onScrollChanged(int topHeight, int menubarTop, int firstVisiblePosition) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ScrollChangedAPI11(topHeight, menubarTop, firstVisiblePosition);
        } else {
            ScrollChanged(topHeight, menubarTop, firstVisiblePosition);
        }
    }

    private void ScrollChanged(int topHeight, int menubarTop, int firstVisiblePosition) {
        if (firstVisiblePosition == 0) {
            menu_lp.setMargins(0, Math.max(0, menubarTop + topHeight), 0, 0);
            top_lp.setMargins(0, topHeight / 2, 0, 0);
        } else {
            menu_lp.setMargins(0, 0, 0, 0);
            top_lp.setMargins(0, -(top_image.getMeasuredHeight() / 2), 0, 0);
        }
        menu_container.setLayoutParams(menu_lp);
        top_image.setLayoutParams(top_lp);
        for (SyncList sync : syncList) {
            sync.SynScrolling(menu_lp.topMargin, topHeight);
        }
    }

    private void ScrollChangedAPI11(int topHeight, int menubarTop, int firstVisiblePosition) {
        if (firstVisiblePosition == 0) {
            menu_container.setTranslationY(Math.max(0, menubarTop + topHeight));
            top_image.setTranslationY(topHeight / 2);
        } else {
            menu_container.setTranslationY(0);
            top_image.setTranslationY(-(top_image.getMeasuredHeight() / 2));
        }
        for (SyncList sync : syncList) {
            sync.SynScrolling((int)menu_container.getTranslationY(), topHeight);
        }
    }

    @Override
    public int getScrollOffset() {
        return -top_image.getHeight();
    }
}
