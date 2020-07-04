package com.azx.myutilstest;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.azx.myutilstest.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * Created by wangzhiguo on 18/6/11.
 */

public class MainActivity extends FragmentActivity {

    public FragmentFactory mFragmentFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentFactory = new FragmentFactory(mFragmentManager, R.id.fragment_ly);
        stateCheck(savedInstanceState);

        final ArrayList<PermissionUtils.PermissionBean> mPermissionList = new ArrayList<>();
        mPermissionList.add(new PermissionUtils.PermissionBean(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储权限"));
        mPermissionList.add(new PermissionUtils.PermissionBean(Manifest.permission.READ_EXTERNAL_STORAGE, "存储权限"));
        boolean mPermisssionOK = PermissionUtils.checkPermission(this, new PermissionUtils.PermissionUtilsInter() {
            @Override
            public List<PermissionUtils.PermissionBean> getApplyPermissions() {
                return mPermissionList;
            }

            @Override
            public AlertDialog.Builder getTipAlertDialog() {
                return null;
            }

            @Override
            public Dialog getTipDialog() {
                return null;
            }

            @Override
            public AlertDialog.Builder getTipAppSettingAlertDialog() {
                return null;
            }

            @Override
            public Dialog getTipAppSettingDialog() {
                return null;
            }
        });

        if (mPermisssionOK) {
            init();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean isOk = PermissionUtils.onActivityResults(this, requestCode);
        if (isOk) {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isOk = PermissionUtils.onRequestPermissionsResults(this, requestCode, permissions, grantResults);
        if (isOk) {
            init();
        }
    }

    private void init() {
    }

    /**
     * 状态检测 用于内存不足的时候保证fragment不会重叠
     */
    private void stateCheck(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragmentFactory.initFragment();
        } else {
            mFragmentFactory.reInitFragment();
        }
    }

    public void changeFragment(String tag) {
        mFragmentFactory.switchFragment(tag);
    }
}
