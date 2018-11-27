package com.bw.movie.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：BaseObserver(观察者)
 * */

public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T t) ;

    @Override
    public abstract void onError(Throwable e);

    @Override
    public void onComplete() {

    }
}
