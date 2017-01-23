package br.com.gingaone.spidermain.presenter.content;

import java.util.List;

import br.com.gingaone.spidermain.model.pojo.Result;
import br.com.gingaone.spidermain.view.fragment.ContentFragment;
import br.com.gingaone.spidermain.view.listener.OnMainActivityView;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
public class ContentPresenterImp implements OnContentPresenter, OnContentCallback{

    private OnContentView              mContentView;
    private OnContentInteractor        mContentInteractor;


    public ContentPresenterImp(OnContentView mContentView) {
        this.mContentView              = mContentView;
        mContentInteractor             = new ContentInteractorImp();
    }

    @Override
    public void callPresenter() {
        mContentInteractor.callSplash(this);
    }

    @Override
    public void getListResult(List<Result> mListResult) {
        mContentView.getListResult(mListResult);
    }
}
