package com.mmednet.www.customdemos;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Administrator on 2016/12/8.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        initView();
        setListener();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();

}
