package com.github.limboc.pulltosortrecyclerview;


public interface SwipeTrigger {
    void onPrepare();

    void onSwipe(int y, boolean isComplete);

    void onScroll(int y, int status);

    void onRelease();

    void complete();

    void onReset();
}
