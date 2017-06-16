package com.mmednet.www.customdemos.activity;

import com.mmednet.www.customdemos.BaseActivity;
import com.mmednet.www.customdemos.view.QuanQuanView;

/**
 * Created by Administrator on 2017/5/11.
 */
public class QuanQuanActivity extends BaseActivity{
    @Override
    protected void initView() {

        QuanQuanView  quanView=  new QuanQuanView(this);
        setContentView(quanView);
        quanView.moveView();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

}
