package com.mmednet.www.customdemos.activity;

import android.support.v7.widget.LinearLayoutCompat;
import android.view.ViewGroup;

import com.mmednet.www.customdemos.BaseActivity;
import com.mmednet.www.customdemos.view.ZheLineView;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/5/15.
 * 折线统计表
 */
public class ZheLineActivity extends BaseActivity{
    @Override
    protected void initView() {
        ZheLineView view=new ZheLineView(this);
        LinearLayoutCompat.LayoutParams params=new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,400);
        view.setLayoutParams(params);
        setContentView(view);
        view.setYLine(0,100,20);//Y轴开始最小值 ，最大值  ，间隔
        view.setXLine(1,12,1);//X轴开始最小值 ，最大值  ，间隔
        LinkedHashMap<Integer,Integer> map=new LinkedHashMap<>();
        map.put(1,20);
        map.put(2,80);
        map.put(3,37);
        map.put(4,17);
        map.put(5,59);
        map.put(6,90);
        map.put(7,100);
        map.put(8,70);
        map.put(9,60);
        view.setData(map);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
