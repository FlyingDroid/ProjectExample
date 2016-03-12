package com.weidukeji.agriculture.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.engine.GdMapLocationListener;
import com.weidukeji.agriculture.fragment.AreaFragment;
import com.weidukeji.agriculture.fragment.DiscoveryFragment;
import com.weidukeji.agriculture.fragment.MsgFragment;
import com.weidukeji.agriculture.fragment.NearbyFragment;
import com.weidukeji.agriculture.fragment.NewsFragment;
import com.weidukeji.agriculture.fragment.RssFragment;
import com.weidukeji.agriculture.fragment.ServiceFragment;
import com.weidukeji.agriculture.fragment.UserFragment;
import com.weidukeji.agriculture.fragment.WisdomFragment;
import com.weidukeji.agriculture.utils.AndLogger;
import com.weidukeji.agriculture.widget.navigationdrawer.NavigationDrawerItem;
import com.weidukeji.agriculture.widget.navigationdrawer.NavigationDrawerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends ActionBarActivity {

    private CharSequence mTitle;
    private int index = 0;
    private int currentItem = R.id.radio_btn_news;
    private BaseFragment fragment = null;
    private RadioGroup radioGroup_main;
    private LinearLayout layoutMain;
    private static final String SECTION_NUMBER = "section_number";
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private int currentSelectedPosition = 1;
    private NavigationDrawerView navigationDrawerView;
    private DrawerLayout drawerLayout;
    private RelativeLayout mLinearDrawerLayout;
    private boolean mUserLearnedDrawer;
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView leftDrawerListView;
    private List<NavigationDrawerItem> navigationItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_solid));
        mTitle = getTitle();
        findView();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container3, PlaceholderFragment.newInstance(1)).commit();
        } else {
            currentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
        }
        addDrawMenuItem();
        navigationDrawerView = (NavigationDrawerView) findViewById(R.id.navigationDrawerListViewWrapper);
        navigationDrawerView.replaceWith(navigationItems);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawerListView = (ListView) findViewById(R.id.leftDrawerListView);
        leftDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (drawerLayout.isDrawerOpen(mLinearDrawerLayout) && position != 0) {
                    drawerLayout.closeDrawer(mLinearDrawerLayout);
                    onNavigationDrawerItemSelected(position);
                    selectItem(position);
                }
            }
        });
        mLinearDrawerLayout = (RelativeLayout) findViewById(R.id.linearDrawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                drawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                selectItem(currentSelectedPosition);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(MainActivity3.this);
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }
                AndLogger.zhshengLog().d("打开");
            }
        };
        if (!mUserLearnedDrawer) {
            drawerLayout.openDrawer(mLinearDrawerLayout);
        }

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        selectItem(currentSelectedPosition);
        initLocationMap();
    }

    private void addDrawMenuItem() {
        navigationItems = new ArrayList<>();
        navigationItems.add(new NavigationDrawerItem(getString(R.string.title_section1), R.drawable.selector_menu_zhci, false));
        navigationItems.add(new NavigationDrawerItem(getString(R.string.title_section1), R.drawable.selector_menu_zhci, false));
        navigationItems.add(new NavigationDrawerItem(getString(R.string.title_section2), R.drawable.selector_menu_jiage, false));
        navigationItems.add(new NavigationDrawerItem(getString(R.string.title_section3), R.drawable.selector_menu_jishu, false));
        navigationItems.add(new NavigationDrawerItem(getString(R.string.title_section4), R.drawable.selector_menu_jiage, false));
        navigationItems.add(new NavigationDrawerItem(getString(R.string.title_section5), R.drawable.selector_menu_tianqi, false));
    }

    private void selectItem(int position) {
        if (leftDrawerListView != null) {
            leftDrawerListView.setItemChecked(position, true);

            navigationItems.get(currentSelectedPosition).setSelected(false);
            navigationItems.get(position).setSelected(true);

            currentSelectedPosition = position;
            getSupportActionBar()
                    .setTitle(navigationItems.get(currentSelectedPosition).getItemName());
        }

        if (mLinearDrawerLayout != null) {
            drawerLayout.closeDrawer(mLinearDrawerLayout);
        }
    }

    private void findView() {
        layoutMain = (LinearLayout) findViewById(R.id.layout_main);
        final FrameLayout mainContent = (FrameLayout) findViewById(R.id.layout_main_content);
        radioGroup_main = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                currentItem = checkedId;
                switch (checkedId) {
                    case R.id.radio_btn_news:
                        index = 0;
                        break;
                    case R.id.radio_btn_rss:
                        index = 1;
                        break;
                    case R.id.radio_btn_message:
                        index = 2;
                        break;
                    case R.id.radio_btn_user:
                        index = 3;
                        break;
                }

                BaseFragment currentFrag = (BaseFragment) fragmentPagerAdapter.instantiateItem(mainContent, index);
                fragmentPagerAdapter.setPrimaryItem(mainContent, 0, currentFrag);
                fragmentPagerAdapter.finishUpdate(mainContent);
            }
        });
        radioGroup_main.check(currentItem);
        findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndLogger.zhshengLog().d("click setting btn");
            }
        });
        View layout_Header = findViewById(R.id.userDrawerHeader);
        layout_Header.setClickable(true);
        layout_Header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndLogger.zhshengLog().d("登录=====");
            }
        });
    }

    FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment = new NearbyFragment();
                    break;
                case 1:
                    fragment = new DiscoveryFragment();
                    break;
                case 2:
                    fragment = new MsgFragment();
                    break;
                case 3:
                    fragment = new UserFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };


    public void onNavigationDrawerItemSelected(int position) {//抽屉导航中的某一项被选中时调用
        AndLogger.zhshengLog().d("选择" + position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment baseFragment = null;
        switch (position) {
            case 1://首页
                if (layoutMain != null) if (!layoutMain.isShown()) {
                    layoutMain.setVisibility(View.VISIBLE);
                }
                fragmentManager.beginTransaction().replace(R.id.container3, PlaceholderFragment.newInstance(1)).commit();
                break;
            case 2:  //附近交通// update the main content by replacing fragments
                layoutMain.setVisibility(View.INVISIBLE);
                baseFragment = new RssFragment();
                break;
            case 3:  //互联设备// update the main content by replacing fragments
                layoutMain.setVisibility(View.INVISIBLE);
                baseFragment = new AreaFragment();
                break;
            case 4: //订阅相关
                layoutMain.setVisibility(View.INVISIBLE);
                baseFragment = new WisdomFragment();
                break;
            case 5://服务频道
                layoutMain.setVisibility(View.INVISIBLE);
                baseFragment = new ServiceFragment();
                break;
        }

        if (baseFragment != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(SECTION_NUMBER, position + 1);
            baseFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.container3, baseFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, currentSelectedPosition);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(mLinearDrawerLayout)) {
            getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_menu_location:
                startActivity(new Intent(this, LocationActivity.class));
                break;

            case R.id.menu_examine:
                AndLogger.zhshengLog().d("setting_menu1");
                break;
        }

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            AndLogger.zhshengLog().d("actionbar drawer toggle" + item.getItemId());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.layout_main_activity, container, false);
            return rootView;
        }

    }
    private LocationManagerProxy mLocationManagerProxy;
    private GdMapLocationListener mapLocationListener;
    private void initLocationMap(){
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        mapLocationListener = GdMapLocationListener.getWBaidDuLocationListener();
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 5*1000, 15, mapLocationListener);
        mLocationManagerProxy.setGpsEnable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocation();
    }
    private void stopLocation() {
        if (mLocationManagerProxy != null) {
            mLocationManagerProxy.removeUpdates(mapLocationListener);
            mLocationManagerProxy.destroy();
        }
        mLocationManagerProxy = null;
    }
}
