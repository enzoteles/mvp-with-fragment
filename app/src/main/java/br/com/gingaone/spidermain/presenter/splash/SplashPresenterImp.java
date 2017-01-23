package br.com.gingaone.spidermain.presenter.splash;
/**
 * Created by Enzo Teles on 17/06/2016.
 */
public class SplashPresenterImp implements OnSplashPresenter, OnSplashCallback {


	private transient OnSplashView onSplashView;
	private transient OnSplashInteractor onSplashInteractor;
	

	public SplashPresenterImp(OnSplashView onSplashView) {
		// TODO Auto-generated constructor stub
		this.onSplashView = onSplashView;
		this.onSplashInteractor = new SplashInteractorImp();

	}

	/**
	 * method to delegate activity to the interactor
	 * */
	@Override
	public boolean callSplash() {
		// TODO Auto-generated method stub
		onSplashInteractor.callSplash(this);
		return true;
		
	}
	/**
	 * method to show the return message of the callback
	 * */
	@Override
	public boolean showMsg(String msg) {
		// TODO Auto-generated method stub
		if(msg != null){
			onSplashView.showMessage("msg "+ msg);
			return true;
		}
		return false;
	}

}
