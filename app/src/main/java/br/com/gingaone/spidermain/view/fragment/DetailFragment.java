package br.com.gingaone.spidermain.view.fragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.gingaone.spidermain.R;
import br.com.gingaone.spidermain.helper.log.WrapperLog;
import br.com.gingaone.spidermain.helper.proportion.DisplayUtil;
import br.com.gingaone.spidermain.model.pojo.Result;
import br.com.gingaone.spidermain.view.listener.OnMainActivityView;
/**
 * Created by Enzo Teles on 17/06/2016.
 */
@SuppressLint("NewApi")
public class DetailFragment extends AbstractFragment implements View.OnClickListener{
	//variable
	private OnMainActivityView 					onMainActivityView;
	private ImageView							imgBack, imgRev, imgFull;
	private TextView							txtTitle, txtDescription, txtPrice, txtDate, txtPages;
	String titles;
	String description;
	String date	;
	String price;
	String pages;
	String thumbail;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view 								= inflater.inflate(R.layout.detail, container, false);
		DisplayUtil								.setLayoutParams((ViewGroup) view.findViewById(R.id.layout_detail));
		onMainActivityView 						= (OnMainActivityView) getActivity();
		onMainActivityView						.getToolbar().setVisibility(View.GONE);
		imgBack									= (ImageView) view.findViewById(R.id.img_back);
		imgRev									= (ImageView) view.findViewById(R.id.img_rev);
		imgFull									= (ImageView) view.findViewById(R.id.img_full);
		txtTitle								= (TextView)  view.findViewById(R.id.txt_title);
		txtDescription							= (TextView)  view.findViewById(R.id.txt_desc);
		txtPrice								= (TextView)  view.findViewById(R.id.txt_price);
		txtDate									= (TextView)  view.findViewById(R.id.txt_published);
		txtPages								= (TextView)  view.findViewById(R.id.txt_pages);

		//priceFormate(txtPrice);

		java.text.DateFormat longDateFormat 				= DateFormat.getLongDateFormat(getActivity().getBaseContext());

		//Onclick
		imgBack									.setOnClickListener(this);
		imgRev									.setOnClickListener(this);

		if(getArguments() != null){
			titles               				= getArguments().getString(ContentFragment.RESULT_NAME);
			description             			= getArguments().getString(ContentFragment.RESULT_DESCRIPTION);
			date		               			= getArguments().getString(ContentFragment.RESULT_PUBLISHED);
			price	            		   		= getArguments().getString(ContentFragment.RESULT_PRICE);
			pages	    		           		= getArguments().getString(ContentFragment.RESULT_PAGES);
			thumbail		               		= getArguments().getString(ContentFragment.RESULT_THUMBAIL);

			//set arguments
			txtTitle							.setText(titles.toString());
			//format date
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateInString = date.toString();

			try {

				Date date = formatter.parse(dateInString);
				txtDate.setText(longDateFormat.format(date));

			} catch (ParseException e) {
				e.printStackTrace();
			}

			txtPages							.setText(pages.toString());

			//convert price
			double priceConvert = Double.parseDouble(price.toString());
			double moneyCurrency = priceConvert;
			NumberFormat baseFormat = NumberFormat.getCurrencyInstance();
			String moneyString = baseFormat.format(moneyCurrency);
			txtPrice							.setText(moneyString);

			//image load
			Picasso.with(getActivity())
					.load(thumbail)
					.into(imgRev);
			Picasso.with(getActivity())
					.load(thumbail)
					.into(imgFull);

		}

		return view;
	}

	/**
	 * onClick
	 * */
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.img_back:
				getActivity().onBackPressed();
				break;
			case R.id.img_rev:
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				Fragment prev = getFragmentManager().findFragmentByTag("dialog");
				if (prev != null) {
					ft.remove(prev);
				}
				ft.addToBackStack(null);
				// Create and show the dialog.
				DialogFragment newFragment = FullScreenFragment.newInstance(getActivity(), thumbail);
				newFragment.show(ft, "dialog");
				break;
		}
	}
}
