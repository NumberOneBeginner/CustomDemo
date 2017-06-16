package com.mmednet.www.customdemos;

import android.content.Intent;
import android.support.v4.widget.ListViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.mmednet.www.customdemos.activity.BoWenActivity;
import com.mmednet.www.customdemos.activity.QuanQuanActivity;
import com.mmednet.www.customdemos.activity.TongJiActivity;
import com.mmednet.www.customdemos.activity.ZheLineActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv_view_demo;
    private List<String> itemName;
    private NameAdapter nameAdapter;
    private List<Class> activitys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addName();
        nameAdapter=new NameAdapter();
        lv_view_demo= (ListView) this.findViewById(R.id.lv_view_demo);
        lv_view_demo.setAdapter(nameAdapter);
        lv_view_demo.setOnItemClickListener(this);
    }

    private void addName(){
        itemName=new ArrayList<>();
        activitys=new ArrayList<>();
        itemName.add("钟表圈圈");
        itemName.add("统计柱");
        itemName.add("折线表");
        itemName.add("波纹效果");
        activitys.add(QuanQuanActivity.class);
        activitys.add(TongJiActivity.class);
        activitys.add(ZheLineActivity.class);
        activitys.add(BoWenActivity.class);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(MainActivity.this,activitys.get(i)));
    }

    private class NameAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return itemName.size();
        }

        @Override
        public Object getItem(int i) {
            return itemName.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv=new TextView(MainActivity.this);
            tv.setTextSize(20);

            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , 80);
//            LinearLayoutCompat.LayoutParams params=new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
//            , 80);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(params);
            tv.setText(itemName.get(i));

            return tv;
        }
    }
}
