package br.com.gingaone.spidermain.helper.services;

import br.com.gingaone.spidermain.model.pojo.Comics;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
public interface RestClient {

    String REST_ENDPOINT = "http://gateway.marvel.com:80/v1/public";

    @GET("/comics?ts=1&apikey=bb4470a46d0659a43c566ac6056ed48d&hash=479474cf0a28eac9998960da4d96f06b")
            void getSpiderManEdition(Callback<Comics> edition);

}

