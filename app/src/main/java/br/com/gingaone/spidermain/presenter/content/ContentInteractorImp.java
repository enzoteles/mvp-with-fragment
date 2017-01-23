package br.com.gingaone.spidermain.presenter.content;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.com.gingaone.spidermain.helper.log.WrapperLog;
import br.com.gingaone.spidermain.helper.services.RestClient;
import br.com.gingaone.spidermain.model.adapter.ContentAdapter;
import br.com.gingaone.spidermain.model.pojo.Comics;
import br.com.gingaone.spidermain.model.pojo.Image;
import br.com.gingaone.spidermain.model.pojo.Result;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
public class ContentInteractorImp implements OnContentInteractor {
    List<Result>                mListResult;

    @Override
    public void callSplash(final OnContentCallback listener) {

        mListResult				= new ArrayList<Result>();

        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RestClient.REST_ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        restAdapter.create(RestClient.class).getSpiderManEdition(new Callback<Comics>() {
            @Override
            public void success(Comics comics, Response response) {
                WrapperLog.info("LOG " + comics.getData().getResults());
                for (Result result : comics.getData().getResults()){
                    WrapperLog.info("LOG " + result.getThumbnail().getPath()+"."+ result.getThumbnail().getExtension());
                    mListResult.add(result);
                }
                listener.getListResult(mListResult);
            }

            @Override
            public void failure(RetrofitError error) {
                WrapperLog.error("Error " + error.getMessage());
            }
        });

    }
}
