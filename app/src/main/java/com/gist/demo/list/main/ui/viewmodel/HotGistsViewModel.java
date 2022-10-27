package com.gist.demo.list.main.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gist.demo.list.main.data.HotGistModel;
import com.gist.demo.list.main.network.ApiClient;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.schedulers.Schedulers;
/**
 * Created by KowsalyaM on 26/10/22.
 */

public class HotGistsViewModel extends ViewModel {
    public MutableLiveData<List<HotGistModel>> gistsMutableLiveData = new MutableLiveData<>();

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void getHotGists() {
        Single<List<HotGistModel>> observable = ApiClient.getINSTANCE().getHotGists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        compositeDisposable.add(observable.subscribe(o -> gistsMutableLiveData.setValue(o)
                , throwable -> Log.e("TAG", "Throwable " + throwable.getMessage())));

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}