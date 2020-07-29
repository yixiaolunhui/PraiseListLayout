package com.zwl.praiselistlayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


/**
 * @author zwl
 * @date on 2020/7/28
 */
public class PraiseListLayoutManager extends RecyclerView.LayoutManager {

    public static final int TYPE_LEFT = 100;
    public static final int TYPE_RIGHT = 101;
    private int maxCount;

    private Context mContext;

    private int offset;

    private int type = TYPE_LEFT;

    public PraiseListLayoutManager(Context context, int maxCount) {
        this.mContext = context;
        this.maxCount = maxCount;
        offset = dpToPx(context, 30);

    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            int index = isRight() ? i : itemCount - 1 - i;
            View childView = recycler.getViewForPosition(i);
            addView(childView);
            measureChildWithMargins(childView, 0, 0);
            int left = offset * index;
            int top = 0;
            int right = left + getDecoratedMeasuredWidth(childView);
            int bottom = getDecoratedMeasuredHeight(childView);
            layoutDecoratedWithMargins(childView, left, top, right, bottom);
        }
    }


    public boolean isRight() {
        return type == TYPE_RIGHT;
    }


    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int dpToPx(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * (scale + 0.5f));
    }

}
