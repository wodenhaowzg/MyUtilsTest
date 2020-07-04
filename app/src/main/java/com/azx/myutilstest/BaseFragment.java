package com.azx.myutilstest;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangzhiguo on 18/6/11.
 */

public class BaseFragment extends Fragment {

    protected MainActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        Log.d("wzg", "onCreate ...." + this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("wzg", "onCreateView ...." + this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("wzg", "onStart ...." + this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("wzg", "onStop ...." + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.mFragmentFactory.destoryCurrentFragment();
        Log.d("wzg", "onDestroy ...." + this);
    }
}
