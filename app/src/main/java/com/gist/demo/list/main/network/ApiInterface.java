package com.gist.demo.list.main.network;


import com.gist.demo.list.main.data.HotGistModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/gists/public?since")
    public Single<List<HotGistModel>> getHotGists();
    @GET("/users/{username}/gists?since")
    public Call<List<HotGistModel>> getHotGistsByUserName(@Path("username") String username);

}