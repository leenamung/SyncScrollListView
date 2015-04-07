package com.example.administrator.syncscrolllistview;

/**
 * Created by Administrator on 2015-04-06.
 */
public interface SyncList {
    /**
     * use to calculate scroll amount
     */
    public void onScrollChanged();

    /**
     * real sync method
     *
     * @param menubarTop
     * @param topHeight  first child's Top position;
     */
    public void SynScrolling(int menubarTop, int topHeight);

    /**
     * @return if true this listview is currentItem, else false
     */
    public boolean isFrontList();

    /**
     * set this listview is currentItem
     */
    public void setVisibleList();

    /**
     * set this listview is not currentItem
     */
    public void setUnVisibleList();

    /**
     * @param sync link ViewPager;
     */
    public void setSyncViews(SyncViews sync);

    /**
     * @param topHeight     this height is top_image in activity_main.xml
     * @param menubarHeight this height is menu_container in activity_main.xml
     */
    public void setListHeader(float topHeight, float menubarHeight);
}
