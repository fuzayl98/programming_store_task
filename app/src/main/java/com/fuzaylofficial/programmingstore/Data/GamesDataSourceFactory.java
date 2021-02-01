package com.fuzaylofficial.programmingstore.Data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.fuzaylofficial.programmingstore.RetrofitData.Games;


public class GamesDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<String, Games.Game>> itemLiveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<String, Games.Game> create() {
        GamesDataSourse dataSourse = new GamesDataSourse();
        itemLiveDataSource.postValue(dataSourse);
        return dataSourse;
    }

    public MutableLiveData<PageKeyedDataSource<String, Games.Game>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
