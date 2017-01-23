package br.com.gingaone.spidermain.view.listener;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.gingaone.spidermain.helper.enuns.ControlFrags;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
public interface OnMainActivityView {

    void registrerFragment(ControlFrags frags, int layoutId, boolean addBackStack);

    void transferFragment(ControlFrags frags, int layoutId, boolean addBackStack);

    void transferFragment(ControlFrags frags, int layoutId, boolean addBackStack, Bundle bundle);

    void onBackPressed();

    Toolbar getToolbar();




}
