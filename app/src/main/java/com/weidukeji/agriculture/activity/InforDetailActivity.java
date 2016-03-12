package com.weidukeji.agriculture.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseActivity;
import com.weidukeji.agriculture.fragment.InforDetailFragment;

public class InforDetailActivity extends BaseActivity {
    private InforDetailFragment inforDetailFragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_infor_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        actionBar.setDisplayHomeAsUpEnabled(true);//显示左边的小箭头
        actionBar.setDisplayShowHomeEnabled(false);//actionBar左侧图标是否显示
        inforDetailFragment = new InforDetailFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, inforDetailFragment).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isback = inforDetailFragment.onKeyDown(keyCode);
        if (isback) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
