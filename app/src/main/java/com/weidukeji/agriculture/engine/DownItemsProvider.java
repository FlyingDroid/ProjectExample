package com.weidukeji.agriculture.engine;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.utils.AndLogger;

/**
 * Created by admin on 2015/1/29.
 */
public class DownItemsProvider extends ActionProvider {
    private Context context;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public DownItemsProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        super.onPrepareSubMenu(subMenu);
        subMenu.clear();
        subMenu.add("退出").setIcon(R.drawable.icon_menu_exit_n).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AndLogger.zhshengLog().d("退出");
                return true;
            }
        });
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }
}
