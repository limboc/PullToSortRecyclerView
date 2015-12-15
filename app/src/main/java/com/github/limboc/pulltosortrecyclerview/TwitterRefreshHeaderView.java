package com.github.limboc.pulltosortrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class TwitterRefreshHeaderView extends SwipeRefreshHeaderLayout {


    private ImageView ivSuccess, iv_1, iv_2, iv_3;

    private ProgressBar progressBar;

    private int mHeaderHeight;
    private int interval;
    public int sortItem= 0;
    private SparseArray<Integer> ivArray = new SparseArray<>();


    public TwitterRefreshHeaderView(Context context) {
        this(context, null);
    }

    public TwitterRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwitterRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_twitter);
        interval = getResources().getDimensionPixelOffset(R.dimen.refresh_header_interval);
        ivArray.put(1, 1);
        ivArray.put(2, 2);
        ivArray.put(3, 3);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        iv_1 = (ImageView) findViewById(R.id.iv_1);
        iv_2 = (ImageView) findViewById(R.id.iv_2);
        iv_3 = (ImageView) findViewById(R.id.iv_3);
    }

    @Override
    public void onRefresh() {
        ivSuccess.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        setSortImg();
    }

    @Override
    public void onPrepare() {
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }

    @Override
    public void onSwipe(int y, boolean isComplete) {
        if (!isComplete) {

        }
    }

    @Override
    public void onScroll(int y, int status) {
        /*Log.d("y:", y + "");
        Log.d("status:", status + "");*/
        if(status != SwipeToLoadLayout.STATUS.STATS_REFRESH_COMPLETE){
            progressBar.setVisibility(GONE);
            ivSuccess.setVisibility(GONE);
        }
        if(status == SwipeToLoadLayout.STATUS.STATUS_SWIPING_TO_REFRESH){
            initImgs();
        }
        if(status == SwipeToLoadLayout.STATUS.STATUS_RELEASE_TO_REFRESH){
            initImgs();
            if(y <= mHeaderHeight){

            }else if (y > mHeaderHeight && y< mHeaderHeight + interval) {
                sortItem = ivArray.get(1);
                setSortImg();

            } else if (y < mHeaderHeight + interval * 2) {
                sortItem = ivArray.get(2);
                setSortImg();

            }else if (y < mHeaderHeight + interval * 3){
                sortItem = ivArray.get(3);
                setSortImg();
            }else{
                sortItem = ivArray.get(1);
                setSortImg();
            }
        }
    }

    private void initImgs(){
        iv_1.setImageResource(R.mipmap.head_1_grey);
        iv_2.setImageResource(R.mipmap.head_2_grey);
        iv_3.setImageResource(R.mipmap.head_3_grey);
    }

    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
        setSortImg();
    }

    @Override
    public void complete() {
        ivSuccess.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        initImgs();
        resetIvArray();
    }

    @Override
    public void onReset() {
        ivSuccess.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    private void setSortImg(){
        switch (sortItem){
            case 1:
                iv_1.setImageResource(R.mipmap.head_1);
                break;
            case 2:
                iv_2.setImageResource(R.mipmap.head_2);
                break;
            case 3:
                iv_3.setImageResource(R.mipmap.head_3);
                break;
        }
    }

    private void resetIvArray(){
        for(int i = 1; i <= 3; i++){
            ivArray.put(i, sortItem);
            sortItem++;
            if(sortItem > 3){
                sortItem = 1;
            }
        }
    }

}
