package com.weidukeji.agriculture.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;

/**
 * Created by admin on 2015/2/3.
 */
public class InforDetailFragment extends BaseFragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    private WebView webView_detail;

    @Override
    protected void findView(View showView) {

        webView_detail = (WebView) showView.findViewById(R.id.webView_detail);
        webView_detail.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        webView_detail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = webView_detail.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView_detail.loadUrl("http://www.baidu.com");
    }

    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        View detailView = inflater.inflate(R.layout.fragment_infor_detail, container, false);
        return detailView;
    }

    @Override
    public void initData() {

    }

    public boolean onKeyDown(int keyCode) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView_detail.canGoBack()) {
            webView_detail.goBack();
            return true;
        }
        return false;
    }

}
