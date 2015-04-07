package com.example.administrator.syncscrolllistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Administrator on 2015-04-06.
 */
public class mListView extends ListView implements OnScrollListener, SyncList {


    SyncViews syncs;
    View menubar;
    boolean doneInit = false;
    private boolean isVisible = false;
    private OnScrollListener mlistener;

    public mListView(Context context) {
        super(context);
        initScrollListener();
    }

    public mListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScrollListener();
    }

    public mListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScrollListener();
    }

    private void initScrollListener() {
        setOnScrollListener(this);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        if (!doneInit)
            super.setOnScrollListener(l);
        else
            mlistener = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mlistener != null)
            mlistener.onScrollStateChanged(view, scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (syncs != null)
            onScrollChanged();
        if (mlistener != null)
            mlistener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
    }

    @Override
    public void setSyncViews(SyncViews sync) {
        this.syncs = sync;
    }

    @Override
    public void setListHeader(float topHeight, float menubarHeight) {
        View top = new View(getContext());
        menubar = new View(getContext());
        LinearLayout holder = new LinearLayout(getContext());
        holder.setOrientation(LinearLayout.VERTICAL);
        holder.addView(top, ViewGroup.LayoutParams.MATCH_PARENT, (int) topHeight);
        holder.addView(menubar, ViewGroup.LayoutParams.MATCH_PARENT, (int) menubarHeight);
        addHeaderView(holder);
    }


    @Override
    public void addHeaderView(View v) {
        super.addHeaderView(v);
    }

    @Override
    public void addHeaderView(View v, Object data, boolean isSelectable) {
        super.addHeaderView(v, data, isSelectable);
    }

    @Override
    public boolean removeHeaderView(View v) {
        return super.removeHeaderView(v);
    }


    public void onScrollChanged() {
        View v1 = getChildAt(0);

        int top1 = (v1 == null) ? 0 : v1.getTop();

        // This check is needed because when the first element reaches the top of the window, the top values from top are not longer valid.
        if (isVisible && (syncs != null) && (menubar != null)) {
            syncs.onScrollChanged(top1, menubar.getTop(), getFirstVisiblePosition());
        }
    }

    @Override
    public void SynScrolling(int menubarTop, int topHeight) {
        View v = getChildAt(0);
        if (isVisible && (syncs == null) && (menubar == null)) {
            return;
        }
        if (v == null)
            return;
        if (menubarTop == 0f) {
            if (getFirstVisiblePosition() == 0 && v.getTop() > syncs.getScrollOffset()) {
                setSelectionFromTop(0, syncs.getScrollOffset());
            }
        } else {
            setSelectionFromTop(0, Math.max(topHeight, syncs.getScrollOffset()));
        }
    }

    @Override
    public boolean isFrontList() {
        return isVisible;
    }

    @Override
    public void setVisibleList() {
        isVisible = true;
        onScrollChanged();
    }

    @Override
    public void setUnVisibleList() {
        isVisible = false;
    }

}
