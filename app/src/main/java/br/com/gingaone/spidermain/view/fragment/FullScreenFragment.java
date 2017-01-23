package br.com.gingaone.spidermain.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.gingaone.spidermain.R;
import br.com.gingaone.spidermain.helper.proportion.DisplayUtil;
import br.com.gingaone.spidermain.model.pojo.Result;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
@SuppressLint("ValidFragment")
public class FullScreenFragment extends DialogFragment{

    private Activity                activity;
    private String                  imgFull;
    private ImageView               imgBack, imgRev;


    public FullScreenFragment(Activity activity, String imgFull) {
        this.activity                = activity;
        this.imgFull                 = imgFull;
    }

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static FullScreenFragment newInstance(Activity activity, String imgFull) {
        FullScreenFragment fullScreenFragment = new FullScreenFragment(activity, imgFull);
        return fullScreenFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v                      = inflater.inflate(R.layout.fullscreen, container, false);
        DisplayUtil                 .setLayoutParams((ViewGroup) v.findViewById(R.id.layout_fullscreen));
        getDialog()                 .getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        imgBack                     = (ImageView) v.findViewById(R.id.img_back);
        imgRev                      = (ImageView) v.findViewById(R.id.img_rev);
        imgBack                     .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        Picasso.with(getActivity())
                .load(imgFull)
                .into(imgRev);
        return v;
    }
}
