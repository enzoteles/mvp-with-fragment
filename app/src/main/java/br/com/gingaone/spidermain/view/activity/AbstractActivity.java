package br.com.gingaone.spidermain.view.activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.gingaone.spidermain.helper.enuns.ControlFrags;
import br.com.gingaone.spidermain.helper.log.WrapperLog;
import br.com.gingaone.spidermain.helper.proportion.DisplayUtil;
import br.com.gingaone.spidermain.view.fragment.AbstractFragment;
/**
 * Created by Enzo Teles on 17/06/2016.
 */
@SuppressLint("NewApi")
public abstract class AbstractActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;
    private AbstractFragment abstractFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //screen orientation
        /*
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        */
        DisplayUtil.init(this);
        fragmentManager = getFragmentManager();

    }

    /**
     * method to add fragment in backstack
     * */
    public void addFragment(ControlFrags control, int layoutId,
                            boolean addBackStack) {

        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        try {
            abstractFragment = control.getClassFrag().newInstance();

            fragmentTransaction.add(layoutId, abstractFragment, control.getName());
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            WrapperLog.error(e.getMessage());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            WrapperLog.error(e.getMessage());
        }

        if (addBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

    }

    /**
     * method to replace fragment in backstack
     * */

    public void replaceFragment(ControlFrags control, int layoutId,
                                boolean addBackStack) {

        WrapperLog.info("replaceFragment");
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        try {
            abstractFragment = control.getClassFrag().newInstance();

            fragmentTransaction.replace(layoutId, abstractFragment, control.getName());
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            WrapperLog.error(e.getMessage());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            WrapperLog.error(e.getMessage());
        }

        if (addBackStack) {
            WrapperLog.info("addBackStack");
            fragmentTransaction.addToBackStack(null);
        }
        if (!isFinishing()){
            fragmentTransaction.commit();
        }

    }


    /**
     * method to send bundle to another fragment
     * */

    public void replaceFragment(ControlFrags control, int layoutId,
                                boolean addBackStack, Bundle bundle) {

        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        try {
            abstractFragment = control.getClassFrag().newInstance();
            abstractFragment.setArguments(bundle);
            fragmentTransaction.replace(layoutId, abstractFragment, control.getName());
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            WrapperLog.error(e.getMessage());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            WrapperLog.error(e.getMessage());
        }

        if (addBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        if (!isFinishing()){
            fragmentTransaction.commit();
        }


    }

    /**
     * method to remove fragment in backstack
     * */

    public void removeFragment(ControlFrags control, int layoutId) {

        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.remove(abstractFragment);
        fragmentTransaction.commit();

    }

    /**
     * implemented backstack of application
     * */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {

            /*
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            DialogFragment newFragment = DialogOptionsFragment.newInstance(true, this);
            newFragment.show(ft, "dialog");
            */
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
