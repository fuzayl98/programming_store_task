package com.fuzaylofficial.programmingstore.Data;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;


import com.fuzaylofficial.programmingstore.RetrofitData.Games;
import com.fuzaylofficial.programmingstore.RetrofitData.RetroClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesDataSourse extends PageKeyedDataSource<String, Games.Game> {



    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Games.Game> callback) {
        RetroClient.getInstance()
                .getGames().getGames()
                .enqueue(new Callback<Games>() {
                    @Override
                    public void onResponse(Call<Games> call, Response<Games> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getGameList(), null, response.body().cursor.getCusor());
                        }
                    }
                    @Override
                    public void onFailure(Call<Games> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Games.Game> callback) {
        RetroClient.getInstance()
                .getGames().getPreviousGames(params.key)
                .enqueue(new Callback<Games>() {
                    @Override
                    public void onResponse(Call<Games> call, Response<Games> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getGameList(), response.body().cursor.getCusor());
                        }
                    }
                    @Override
                    public void onFailure(Call<Games> call, Throwable t) {
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Games.Game> callback) {
        RetroClient.getInstance()
                .getGames()
                .getNextGames(params.key)
                .enqueue(new Callback<Games>() {
                    @Override
                    public void onResponse(Call<Games> call, Response<Games> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getGameList(), response.body().cursor.getCusor());
                        }
                    }
                    @Override
                    public void onFailure(Call<Games> call, Throwable t) {
                    }
                });
    }
}