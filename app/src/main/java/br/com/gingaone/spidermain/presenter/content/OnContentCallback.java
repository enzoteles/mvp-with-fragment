package br.com.gingaone.spidermain.presenter.content;

import java.util.List;

import br.com.gingaone.spidermain.model.pojo.Result;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
public interface OnContentCallback {
    void getListResult(List<Result> mListResult);
}
