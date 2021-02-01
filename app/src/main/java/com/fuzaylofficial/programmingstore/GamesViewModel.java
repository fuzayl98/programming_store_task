package com.fuzaylofficial.programmingstore;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.fuzaylofficial.programmingstore.Data.GamesDataSourceFactory;
import com.fuzaylofficial.programmingstore.RetrofitData.Games;

public class GamesViewModel extends AndroidViewModel {
    LiveData<PagedList<Games.Game>> itemPagedList;

    LiveData<PageKeyedDataSource<String, Games.Game>> liveDataSource;

    public GamesViewModel(Application application) {
        super(application);
        GamesDataSourceFactory itemDataSourceFactory = new GamesDataSourceFactory();

        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(20).build();


        itemPagedList = new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig).build();

    }


}
