package br.com.gingaone.spidermain.helper.enuns;

import br.com.gingaone.spidermain.view.fragment.AboutFragment;
import br.com.gingaone.spidermain.view.fragment.AbstractFragment;
import br.com.gingaone.spidermain.view.fragment.ContentFragment;
import br.com.gingaone.spidermain.view.fragment.DetailFragment;
import br.com.gingaone.spidermain.view.fragment.SplashFragment;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
public enum ControlFrags {

	SPLASH	     			("splash", 	SplashFragment.class),
	DETAIL	     			("detail", 	DetailFragment.class),
	ABOUT	     			("about", 	AboutFragment.class),
	CONTENT	     			("content", ContentFragment.class);

	
	private String name;
	private Class<? extends AbstractFragment> classFrag;
	
	private ControlFrags(final String name, Class<? extends AbstractFragment> classFrag) {
		this.name = name;
		this.classFrag = classFrag;
	}



	public String getName() {
		return name;
	}
	public Class<? extends AbstractFragment> getClassFrag() {
		return classFrag;
	}
}
