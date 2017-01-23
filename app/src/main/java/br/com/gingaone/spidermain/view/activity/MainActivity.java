package br.com.gingaone.spidermain.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.gingaone.spidermain.helper.enuns.ControlFrags;
import br.com.gingaone.spidermain.helper.services.RestClient;
import br.com.gingaone.spidermain.R;
import br.com.gingaone.spidermain.model.pojo.Comics;
import br.com.gingaone.spidermain.model.pojo.Result;
import br.com.gingaone.spidermain.view.listener.OnMainActivityView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
/**
 * Created by Enzo Teles on 17/06/2016.
 */
public class MainActivity extends AbstractActivity implements OnMainActivityView {

    public Toolbar               toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_toolbar);
        //toolbar
        toolbar                         = (Toolbar) findViewById(R.id.toolbar);
        toolbar                         .setTitle("MARVEL");
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            addFragment(ControlFrags.SPLASH, R.id.content, false);

        }
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.miProfile) {
            transferFragment(ControlFrags.ABOUT, R.id.content, true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * method to call the fragment
     */
    @Override
    public void registrerFragment(ControlFrags frags, int layoutId, boolean addBackStack) {
        registrerFragment(frags, layoutId, addBackStack);
    }

    /**
     * method to transfer the fragment
     */

    @Override
    public void transferFragment(ControlFrags frags, int layoutId, boolean addBackStack) {
        replaceFragment(frags, layoutId, addBackStack);
    }

    /**
     * method to send bundle to another fragment
     */

    @Override
    public void transferFragment(ControlFrags frags, int layoutId, boolean addBackStack, Bundle bundle) {
        replaceFragment(frags, layoutId, addBackStack, bundle);
    }

}
