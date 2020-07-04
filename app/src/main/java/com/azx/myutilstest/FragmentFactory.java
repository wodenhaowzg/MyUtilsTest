package com.azx.myutilstest;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentFactory {

    public static final String FRAGMENT_TAG_MAIN = "主界面";
    public static final String FRAGMENT_TAG_SCRIM_TEST = "ScrimUtil测试";
    private FragmentManager mFragmentManager;
    private int mLayoutID;
    private String mShowingFragmentTag = FRAGMENT_TAG_MAIN;

    private List<String> mFragmentTags = new ArrayList<>();

    public FragmentFactory(FragmentManager fragmentManager, int mLayoutID) {
        this.mFragmentManager = fragmentManager;
        this.mLayoutID = mLayoutID;
    }

    public void initFragment() {
        MainFragment mMainFragment = new MainFragment();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(mLayoutID, mMainFragment, FRAGMENT_TAG_MAIN).commit();
        mFragmentTags.add(FRAGMENT_TAG_MAIN);
    }

    public void switchFragment(String mTargetFragmentTag) {
        if (!mShowingFragmentTag.equals(mTargetFragmentTag)) {
            Fragment currentFragment = mFragmentManager.findFragmentByTag(mShowingFragmentTag);
            Fragment targetFragment = mFragmentManager.findFragmentByTag(mTargetFragmentTag);
            if (targetFragment == null) {
                targetFragment = createFragment(mTargetFragmentTag);
            }

            if (targetFragment == null) {
                return;
            }

            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            if (!targetFragment.isAdded()) { // 先判断是否被add过
                if (currentFragment == null) {
                    transaction.add(mLayoutID, targetFragment, mTargetFragmentTag).addToBackStack(mTargetFragmentTag).commit();
                } else {
                    transaction.hide(currentFragment)
                            .add(mLayoutID, targetFragment, mTargetFragmentTag).addToBackStack(mTargetFragmentTag).commit(); // 隐藏当前的fragment，add下一个到Activity中
                }
                mFragmentTags.add(mTargetFragmentTag);
            } else {
                transaction.hide(currentFragment).show(targetFragment).addToBackStack(mTargetFragmentTag).commit(); // 隐藏当前的fragment，显示下一个
            }
            mShowingFragmentTag = mTargetFragmentTag;
        }
    }

    private Fragment createFragment(String mTargetFragmentTag) {
        switch (mTargetFragmentTag) {
            case FRAGMENT_TAG_SCRIM_TEST:
                return new ScrimTestFragment();
        }
        return null;
    }

    public void reInitFragment() {
        for (String mFragmentTag : mFragmentTags) {
            if (!mFragmentTag.equals(mShowingFragmentTag)) {
                Fragment temp = mFragmentManager.findFragmentByTag(mFragmentTag);
                mFragmentManager.beginTransaction().hide(temp).commit();
            }
        }

        Fragment temp = mFragmentManager.findFragmentByTag(mShowingFragmentTag);
        mFragmentManager.beginTransaction().show(temp).commit();
    }

    public void destoryCurrentFragment() {
        mFragmentTags.remove(mShowingFragmentTag);
        mShowingFragmentTag = "";
    }
}
