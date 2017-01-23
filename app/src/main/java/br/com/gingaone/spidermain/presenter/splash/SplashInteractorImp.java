package br.com.gingaone.spidermain.presenter.splash;

import android.os.Handler;
/**
 * Created by Enzo Teles on 17/06/2016.
 */
public class SplashInteractorImp implements OnSplashInteractor {
	
	/**
	 * method that implements business logic
	 * */
	
	@Override
	public void callSplash(final OnSplashCallback listener) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				new Handler().postDelayed(new Runnable() {
		            @Override public void run() {
		                String msg = "sucess!!!";
		            	listener.showMsg(msg);
		                
		                
		            }
		        }, 3000);
	}

}
