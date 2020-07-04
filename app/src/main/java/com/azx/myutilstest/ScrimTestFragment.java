package com.azx.myutilstest;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azx.myutilstest.utils.ScrimUtil;

import androidx.annotation.Nullable;

/**
 * Created by wangzhiguo on 18/6/11.
 */

public class ScrimTestFragment extends BaseFragment {

    private int count;
    private View icon;
    private TextView numTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.fragment_scrim, null);

        icon = rooView.findViewById(R.id.scri_text_icon);
        numTV = rooView.findViewById(R.id.scri_change_num);
        rooView.findViewById(R.id.scri_add_change).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count++;
                numTV.setText("当前层数" + count);
                icon.setBackground(
                        ScrimUtil.makeCubicGradientScrimDrawable(
                                getResources().getColor(R.color.text_scri_color), //颜色
                                count, //渐变层数
                                Gravity.BOTTOM)); //起始方向
            }
        });

        icon.setBackground(
                ScrimUtil.makeCubicGradientScrimDrawable(
                        getResources().getColor(R.color.text_scri_color), //颜色
                        0, //渐变层数
                        Gravity.BOTTOM)); //起始方向
        return rooView;
    }
}
