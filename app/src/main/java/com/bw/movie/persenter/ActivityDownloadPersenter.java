package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityDownload;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.DownLoadThread;
import com.bw.movie.utils.Logger;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * 作者：mafuyan
 * 时间：2018/12/7
 * 作用：下载apk的ActivityPersenter
 * */

public class ActivityDownloadPersenter extends AppDelegate {
    private Context context;
    private WebView webview;
    private String fileName="weidu.apk";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_download;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = ((ActivityDownload) context).getIntent();
        String url = intent.getStringExtra("url");
        Logger.i("传过来的网址",url);
        webview = (WebView) getView(R.id.download_webview);
        webview.loadUrl(url);
        //下载监听
        webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Logger.i("onDownloadStart", "url===" + url + "---userAgent=" + userAgent + "---contentDisposition=" + contentDisposition + "---mimetype=" + mimetype + "---contentLength=" + contentLength);
                Logger.e("HEHE","onDownloadStart被调用：下载链接：" + url);
                new Thread(new DownLoadThread(url)).start();
                toast(context,"下载中~");
                ((ActivityDownload)context).finish();
            }
        });
    }


}
