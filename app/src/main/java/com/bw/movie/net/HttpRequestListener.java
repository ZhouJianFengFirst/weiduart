package com.bw.movie.net;
/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：OkHttp请求接口
 */
public interface HttpRequestListener {
    //成功
    void SuccessRequest(String data);

    //失败
    void Filed(String msg);
}
