package com.gist.demo.list.main.network;

import com.gist.demo.list.main.data.HotGistModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by KowsalyaM on 26/10/22.
 */

public class ApiClient {
    private static final String BASE_URL = "https://api.github.com";
    private ApiInterface apiInterface;

    private static ApiClient INSTANCE;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public Single<List<HotGistModel>> getHotGists() {
        return apiInterface.getHotGists();
    }

    public Call<List<HotGistModel>> getHotGistsByUserName(String username) {
        return apiInterface.getHotGistsByUserName(username);
    }
}
