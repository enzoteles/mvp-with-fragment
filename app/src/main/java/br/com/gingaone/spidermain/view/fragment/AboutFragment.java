package br.com.gingaone.spidermain.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import br.com.gingaone.spidermain.R;
import br.com.gingaone.spidermain.helper.enuns.ControlFrags;
import br.com.gingaone.spidermain.helper.proportion.DisplayUtil;
import br.com.gingaone.spidermain.view.listener.OnMainActivityView;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
public class AboutFragment extends AbstractFragment {

    private OnMainActivityView 					onMainActivityView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.about, container, false);
        DisplayUtil                             .setLayoutParams((ViewGroup) view.findViewById(R.id.layout_about));
        onMainActivityView 						= (OnMainActivityView) getActivity();
        //toolbar
        onMainActivityView						.getToolbar().setVisibility(View.VISIBLE);
        onMainActivityView                      .getToolbar().setTitle("Profile");
        onMainActivityView                      .getToolbar().setNavigationIcon(R.drawable.arrow_left);
        onMainActivityView                      .getToolbar().getMenu().findItem(R.id.miProfile).setVisible(false);

        onMainActivityView.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;

    }
}
