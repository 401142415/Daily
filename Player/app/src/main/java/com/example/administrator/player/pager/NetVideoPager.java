package com.example.administrator.player.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.player.base.BasePager;

/**
 * Created by Administrator on 2017/4/30.
 */

public class NetVideoPager extends BasePager{
    private TextView textView;
    public NetVideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextSize(25);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("网络视频数据设置");
    }
}
