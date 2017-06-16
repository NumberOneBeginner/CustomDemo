package com.mmednet.www.customdemos.activity;

import android.support.v7.widget.LinearLayoutCompat;
import android.view.ViewGroup;

import com.mmednet.www.customdemos.BaseActivity;
import com.mmednet.www.customdemos.view.BoWenView;

/**
 * Created by Administrator on 2017/5/15.
 * 波纹图
 */
public class BoWenActivity extends BaseActivity{
    @Override
    protected void initView() {
        BoWenView view=new BoWenView(this);
        LinearLayoutCompat.LayoutParams params=new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,400);
        setContentView(view);
        view.moveView();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
