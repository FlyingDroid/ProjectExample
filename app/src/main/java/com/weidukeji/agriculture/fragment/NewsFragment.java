package com.weidukeji.agriculture.fragment;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.base.BasePager;
import com.weidukeji.agriculture.widget.viewpageindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends BaseFragment {

    private View newsView;
    private ViewPager lazyViewPager;
    private TabPageIndicator tabPageIndicator;
    private int currentItem = 0;
    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        newsView = inflater.inflate(R.layout.fragment_home_news, container, false);
        return newsView;
    }

    @Override
    protected void findView(View showView) {
        lazyViewPager = (ViewPager) showView.findViewById(R.id.lazyViewpager);
        tabPageIndicator = (TabPageIndicator) showView.findViewById(R.id.indicator);
    }

    @Override
    public void initData() {
        final List<BasePager> pagers = new ArrayList<>();
        pagers.add(new NewsHotPager(context));
        pagers.add(new NewsNativePager(context));
        lazyViewPager.setAdapter(new NearbyPagerAdapter(pagers));
        tabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                BasePager basePager = pagers.get(position);
                if (!basePager.has_success_data) {
                    basePager.initData();
                    basePager.has_success_data = true;
                }
                currentItem = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagers.get(0).initData();
        pagers.get(0).has_success_data=true;
        tabPageIndicator.setViewPager(lazyViewPager);
        tabPageIndicator.setCurrentItem(currentItem);
    }
    private class NearbyPagerAdapter extends PagerAdapter{
        List<BasePager> pagers;
        public NearbyPagerAdapter(List<BasePager> pagers) {
            this.pagers =pagers;
        }

        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return "热点报道";
            }else{
                return "本地新事";
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(pagers.get(position).getRootView(),0);
            return pagers.get(position).getRootView();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(pagers.get(position).getRootView());
        }
    }
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
