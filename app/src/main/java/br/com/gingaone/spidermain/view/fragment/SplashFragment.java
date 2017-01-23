package br.com.gingaone.spidermain.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.gingaone.spidermain.helper.enuns.ControlFrags;
import br.com.gingaone.spidermain.R;
import br.com.gingaone.spidermain.helper.proportion.DisplayUtil;
import br.com.gingaone.spidermain.presenter.splash.OnSplashPresenter;
import br.com.gingaone.spidermain.presenter.splash.OnSplashView;
import br.com.gingaone.spidermain.presenter.splash.SplashPresenterImp;
import br.com.gingaone.spidermain.view.listener.OnMainActivityView;
/**
 * Created by Enzo Teles on 17/06/2016.
 */
@SuppressLint("NewApi")
public class SplashFragment extends AbstractFragment implements OnSplashView {
	//variable
	private OnSplashPresenter 					presenter;
	private OnMainActivityView 					onMainActivityView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view 								= inflater.inflate(R.layout.splash, container, false);
		DisplayUtil								.setLayoutParams((ViewGroup) view.findViewById(R.id.layout_splash));
		presenter 								= new SplashPresenterImp(this);
		onMainActivityView 						= (OnMainActivityView) getActivity();
		onMainActivityView						.getToolbar().setVisibility(View.GONE);
		callSplash();
		return view;
	}
	
	/**
	 * method to show the return message of the presenter  
	 * and call the ContentFragment             
	 * */
	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stubgit
		if(message != null){
			//Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
		}
		onMainActivityView.transferFragment(ControlFrags.CONTENT, R.id.content, false);
	}

	/**
	 * method that sends a request to the presenter
	 * */
	@Override
	public void callSplash() {
		
			presenter.callSplash();
		
	}
}
