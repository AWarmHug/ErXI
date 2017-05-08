package com.me.en.core.yixi.model;

import com.me.en.base.Listener;
import com.me.en.entity.Base;
import com.me.en.entity.Error;
import com.me.en.entity.Lecture;
import com.me.en.entity.Video;
import com.me.en.net.api.YixiApi;
import com.me.en.net.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/5
 * 简介:
 */

public class LectureModelImpl implements LectureModel {

    @Override
    public void getLecture(int id, final Listener<Lecture> listener)  {
        RetrofitHelper.getApi(YixiApi.class).getLectureDetal("lecture",id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base<Lecture>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Base<Lecture> lectureBase) {
                        listener.success(lectureBase.getData());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.fail(new Error(-1,e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getLecturePlay(int id, final String playId , final Listener<Video> listener) {






        RetrofitHelper.getApi(YixiApi.class).getLecturePlayed(id)
                .flatMap(new Function<Base, ObservableSource<Video>>() {
                    @Override
                    public ObservableSource<Video> apply(@NonNull Base base) throws Exception {
                        return base.getMsg().equals("success")?RetrofitHelper.getApi(YixiApi.class).getPlay("http://api.yixi.tv/youku.php",playId):null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Video>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Video video) {
                        listener.success(video);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.fail(new Error(-1,e.getMessage()));

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getRelated(int id, final Listener<List<Lecture>> listener) {
        RetrofitHelper.getApi(YixiApi.class).getRelated("related",id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Base<List<Lecture>>>() {
                    @Override
                    public void onNext(@NonNull Base<List<Lecture>> listBase) {
                        listener.success(listBase.getData());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
