package com.weidukeji.agriculture.fragment;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.common.util.StringUtils;
import com.weidukeji.agriculture.engine.GdMapLocationListener;
import com.weidukeji.agriculture.entity.LocalInfo;
import com.weidukeji.agriculture.entity.LocalSortInfo;
import com.weidukeji.agriculture.network.CacheKey;
import com.weidukeji.agriculture.network.WdCache;
import com.weidukeji.agriculture.utils.AndLogger;
import com.weidukeji.agriculture.widget.pinyinparser.CharacterParser;
import com.weidukeji.agriculture.widget.pinyinparser.PinyinComparator;
import com.weidukeji.agriculture.widget.pinyinparser.SideBar;
import com.weidukeji.agriculture.widget.pinyinparser.SortAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LocationFragment extends BaseFragment {
    @ViewInject(R.id.lv_location)
    private ListView lv_Locaion;
    @ViewInject(R.id.et_location)
    private EditText et_Location;
    @ViewInject(R.id.dialog)
    private TextView tv_Dialog;
    @ViewInject(R.id.sidebar)
    private SideBar sideBar;
    private SortAdapter sortAdapter;
    private boolean isLocated =false;

    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        View locationView = inflater.inflate(R.layout.fragment_location, container, false);
        return locationView;
    }

    @Override
    protected void findView(View showView) {
        ViewUtils.inject(this, showView);
        sideBar.setTextView(tv_Dialog);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                if (sortAdapter != null) {
                    int forSection = sortAdapter.getPositionForSection(s.charAt(0));
                    if (forSection != -1) {
                        lv_Locaion.setSelection(forSection);
                    }
                }
            }
        });
        lv_Locaion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocalSortInfo itemAtPosition = (LocalSortInfo) lv_Locaion.getItemAtPosition(position);
                AndLogger.zhshengLog().d("city:" + itemAtPosition.getName());
            }
        });
        et_Location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                AndLogger.zhshengLog().d("beforeTextChanged:" + s + "-" + start + "-" + count + "-" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AndLogger.zhshengLog().d("onTextChanged:" + s + "-" + "-" + start + "-" + before + "-" + count);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initData() {
        List<LocalInfo> localInfos = geFileFromAssets(context, "city_list");
        String[] showedNameArr = getShowedNameArr(localInfos);
        List<LocalSortInfo> localSortInfos = filledDate(showedNameArr);
        Collections.sort(localSortInfos, new PinyinComparator());
        sortAdapter = new SortAdapter(context, localSortInfos);
        lv_Locaion.setAdapter(sortAdapter);
      //  String location = WdCache.get(context).getAsString(CacheKey.LOCATION);
        GdMapLocationListener.getWBaidDuLocationListener().registerWdLocationListener(new GdMapLocationListener.WdLocationListener() {
            @Override
            public void getLocation(AMapLocation aMapLocation) {
                AndLogger.zhshengLog().d("位置" + aMapLocation.getCity());
                if(!isLocated){
                    et_Location.setText(aMapLocation.getCity());
                    et_Location.setSelection(et_Location.getText().length());
                    isLocated =true;
                }
            }
        });
    }

    private List<LocalSortInfo> filledDate(String[] arr) {
        ArrayList<LocalSortInfo> sortInfos = new ArrayList<>();
        CharacterParser characterParser = CharacterParser.getInstance();
        LocalSortInfo sortInfo;
        for (String str : arr) {
            if (!TextUtils.isEmpty(str)) {
                sortInfo = new LocalSortInfo();
                String selling = characterParser.getSelling(str);
                String sortLetters = selling.substring(0, 1).toUpperCase();
                if (sortLetters.matches("[A-Z]")) {
                    sortInfo.setSortLetters(sortLetters);
                } else {
                    sortInfo.setSortLetters("#");
                }
                sortInfo.setName(str);
                sortInfos.add(sortInfo);
            }
        }
        return sortInfos;
    }

    private String[] getShowedNameArr(List<LocalInfo> localInfos) {
        String[] showNameArr = new String[localInfos.size()];
        if (localInfos != null) {
            for (int i = 0; i < localInfos.size(); i++) {
                showNameArr[i] = localInfos.get(i).getLocCity();
            }
        }
        return showNameArr;
    }

    public static List<LocalInfo> geFileFromAssets(Context context, String fileName) {
        if (context == null || StringUtils.isEmpty(fileName)) {
            return null;
        }
        List<LocalInfo> localInfos = new ArrayList<>();
        LocalInfo localInfo;
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                if (!TextUtils.isEmpty(line)) {
                    String[] infoArr = line.split("\\|");
                    localInfo = new LocalInfo();
                    localInfo.setLocProvience(infoArr[0]);
                    localInfo.setLocCity(infoArr[1]);
                    localInfo.setSpell(infoArr[2]);
                    localInfos.add(localInfo);
                }
            }
            return localInfos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
