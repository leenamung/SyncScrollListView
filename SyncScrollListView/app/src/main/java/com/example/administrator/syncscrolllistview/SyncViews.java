package com.example.administrator.syncscrolllistview;

/**
 * Created by Administrator on 2015-04-06.
 */
public interface SyncViews {
    /**
     * use at SyncScrollView to sync Viewpager children
     *
     * @param topHeight            this height is top_image in activity_main.xml
     * @param menubarTop           this height is menu_container in activity_main.xml
     * @param firstVisiblePosition is visible item in listview first position
     */
    public void onScrollChanged(int topHeight, int menubarTop, int firstVisiblePosition);

    public int getScrollOffset();
}
