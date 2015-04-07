package com.example.administrator.syncscrolllistview;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015-04-06.
 */
public class SyncScrollPagerAdapter extends PagerAdapter {

    mListView[] synclist;

    public SyncScrollPagerAdapter(mListView[] synclist) {
        this.synclist = synclist;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(synclist[position], 0);
        return synclist[position];
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return synclist.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
}
