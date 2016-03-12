package com.weidukeji.agriculture.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.fragment.AreaFragment;
import com.weidukeji.agriculture.fragment.MsgFragment;
import com.weidukeji.agriculture.fragment.NearbyFragment;
import com.weidukeji.agriculture.fragment.NewsFragment;
import com.weidukeji.agriculture.fragment.RssFragment;
import com.weidukeji.agriculture.fragment.ServiceFragment;
import com.weidukeji.agriculture.fragment.UserFragment;
import com.weidukeji.agriculture.fragment.WisdomFragment;
import com.weidukeji.agriculture.utils.AndLogger;

public class MainActivity2 extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private int index=0;
    private int currentItem =R.id.radio_btn_news;
    private BaseFragment fragment=null;
    private RadioGroup radioGroup_main;
    private LinearLayout layoutMain;
    private static final String SECTION_NUMBER = "section_number";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_solid));
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        findView();
    }

    private void findView() {
        layoutMain = (LinearLayout) findViewById(R.id.layout_main);
        final FrameLayout mainContent = (FrameLayout) findViewById(R.id.layout_main_content);
        radioGroup_main = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                currentItem=checkedId;
                switch (checkedId){
                    case R.id.radio_btn_news:
                        index=0;
                        break;
                    case R.id.radio_btn_rss:
                        index=1;
                        break;
                    case R.id.radio_btn_message:
                        index=2;
                        break;
                    case R.id.radio_btn_user:
                        index=3;
                        break;
                }

                BaseFragment currentFrag = (BaseFragment) fragmentPagerAdapter.instantiateItem(mainContent,index);
                fragmentPagerAdapter.setPrimaryItem(mainContent,0,currentFrag);
                fragmentPagerAdapter.finishUpdate(mainContent);
            }
        });
        radioGroup_main.check(currentItem);

    }
    FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    fragment = new NewsFragment();
                    break;
                case 1:
                    fragment =new NearbyFragment();
                    break;
                case 2:
                    fragment = new MsgFragment();
                    break;
                case 3:
                    fragment =new UserFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    @Override
    public void onNavigationDrawerItemSelected(int position) {//抽屉导航中的某一项被选中时调用
        AndLogger.zhshengLog().d("选择"+position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment baseFragment =null;
        switch (position){
            case 0://首页
                if (layoutMain != null) if (!layoutMain.isShown()) {
                    layoutMain.setVisibility(View.VISIBLE);
                }
                AndLogger.zhshengLog().d("选中首页");
                fragmentManager.beginTransaction().replace(R.id.container2, PlaceholderFragment.newInstance(1)).commit();
                break;
            case 1:  //我的家园// update the main content by replacing fragments
                layoutMain.setVisibility(View.INVISIBLE);
                baseFragment = new RssFragment();
                break;
            case 2:  //我的家园// update the main content by replacing fragments
                layoutMain.setVisibility(View.INVISIBLE);
                 baseFragment = new AreaFragment();
                break;
            case 3: //知识社区
                layoutMain.setVisibility(View.INVISIBLE);
                 baseFragment = new WisdomFragment();
                break;
            case 4://服务频道
                layoutMain.setVisibility(View.INVISIBLE);
                baseFragment = new ServiceFragment();
                break;
        }

        if(baseFragment!=null){
            Bundle bundle = new Bundle();
            bundle.putInt(SECTION_NUMBER,position+1);
            baseFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.container2, baseFragment)
                    .commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity2) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
