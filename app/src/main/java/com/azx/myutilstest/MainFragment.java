package com.azx.myutilstest;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azx.myutilstest.utils.MyFileUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by wangzhiguo on 18/6/11.
 */

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null);
        rootView.findViewById(R.id.main_scrim_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.changeFragment(FragmentFactory.FRAGMENT_TAG_SCRIM_TEST);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<MyFileUtils.FileImageBean> fileImageBeans = MyFileUtils.loadImagesFromPath(Environment.getExternalStorageDirectory() + "/图片集合1");
        for (MyFileUtils.FileImageBean bean : fileImageBeans) {
//            Log.d("MyFileUtils", "bean : " + bean.toString());
        }
    }
}
