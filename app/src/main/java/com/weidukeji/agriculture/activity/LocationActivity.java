package com.weidukeji.agriculture.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseActivity;
import com.weidukeji.agriculture.fragment.LocationFragment;

public class LocationActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_container);
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        actionBar.setDisplayHomeAsUpEnabled(true);//显示左边的小箭头
        getSupportFragmentManager().beginTransaction().add(R.id.container, new LocationFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
