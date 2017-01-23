package br.com.gingaone.spidermain.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.com.gingaone.spidermain.R;
import br.com.gingaone.spidermain.helper.enuns.ControlFrags;
import br.com.gingaone.spidermain.helper.log.WrapperLog;
import br.com.gingaone.spidermain.helper.proportion.DisplayUtil;
import br.com.gingaone.spidermain.helper.services.RestClient;
import br.com.gingaone.spidermain.model.adapter.ContentAdapter;
import br.com.gingaone.spidermain.model.pojo.Comics;
import br.com.gingaone.spidermain.model.pojo.Date;
import br.com.gingaone.spidermain.model.pojo.Price;
import br.com.gingaone.spidermain.model.pojo.Result;
import br.com.gingaone.spidermain.model.pojo.Thumbnail;
import br.com.gingaone.spidermain.presenter.content.ContentPresenterImp;
import br.com.gingaone.spidermain.presenter.content.OnContentPresenter;
import br.com.gingaone.spidermain.presenter.content.OnContentView;
import br.com.gingaone.spidermain.view.listener.OnMainActivityView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
/**
 * Created by Enzo Teles on 17/06/2016.
 */
@SuppressLint("NewApi")
public class ContentFragment extends AbstractFragment implements OnContentView {

	private OnMainActivityView 				onMainActivityView;
	private GridLayoutManager 				lLayout;
	private RecyclerView 					mRecyclerView;
	private ContentAdapter 					mContentAdapter;
	private List<Result>					mListResult;
	private OnContentPresenter				mContentPresenter;
	public static final String 				RESULT_NAME = "result_name";
	public static final String 				RESULT_DESCRIPTION = "result_description";
	public static final String 				RESULT_PUBLISHED = "result_published";
	public static final String 				RESULT_PRICE = "result_price";
	public static final String 				RESULT_PAGES = "result_pages";
	public static final String 				RESULT_THUMBAIL = "result_thumbnail";
	ProgressBar 							mProgressBar;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view 							= inflater.inflate(R.layout.content, container, false);
		DisplayUtil							.setLayoutParams((ViewGroup) view.findViewById(R.id.layout_content));

		//toolbar
		onMainActivityView 					= (OnMainActivityView) getActivity();

		onMainActivityView					.getToolbar().setVisibility(View.VISIBLE);
		onMainActivityView					.getToolbar().setTitle("MARVEL");
		onMainActivityView                  .getToolbar().setNavigationIcon(null);
		onMainActivityView                  .getToolbar().getMenu().findItem(R.id.miProfile).setVisible(true);
		mRecyclerView	   					=  (RecyclerView) view.findViewById(R.id.recyclerView);
		lLayout 							= new GridLayoutManager(getActivity(), 3);
		mContentPresenter					= new ContentPresenterImp(this);
		mProgressBar						= (ProgressBar) view.findViewById(R.id.progressBar);
		mProgressBar						.setVisibility(View.VISIBLE);
		callContent();

		return view;
	}
	/**
	 * method to call contentPresente
	 * */
	@Override
	public void callContent() {
		mContentPresenter.callPresenter();
	}

	/**
	 * method to returns a Result List coming to interacting
	 * */
	@Override
	public void getListResult(final List<Result> mListResult) {
		if(mListResult != null && mListResult.size() > 0){
			mProgressBar		.setVisibility(View.GONE);
			mContentAdapter = new ContentAdapter(mListResult, getActivity());
		}

		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(lLayout);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setAdapter(mContentAdapter);
		mContentAdapter.notifyDataSetChanged();

		mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), mRecyclerView, new ClickListener() {
			@Override
			public void onClick(View view, int position) {
				Result result = mListResult.get(position);
				String thumbail = result.getThumbnail().getPath()+"." + result.getThumbnail().getExtension();
				Bundle arguments = new Bundle();

				arguments.putString(RESULT_NAME, result.getTitle());
				arguments.putString(RESULT_DESCRIPTION, result.getTitle());
				for (Date date : result.getDates()){
					if(date.getType().equals("onsaleDate"))
						arguments.putString(RESULT_PUBLISHED, date.getDate());
				}
				for (Price price : result.getPrices()){
					if(price.getType().equals("printPrice"))
						arguments.putString(RESULT_PRICE, price.getPrice()+"");
				}
				arguments.putString(RESULT_PAGES, result.getPageCount()+"");
				arguments.putString(RESULT_THUMBAIL, thumbail);
				onMainActivityView.transferFragment(ControlFrags.DETAIL, R.id.content, true, arguments);
			}

			@Override
			public void onLongClick(View view, int position) {

			}
		}));

	}

	public interface ClickListener {
		void onClick(View view, int position);

		void onLongClick(View view, int position);
	}

	/**
	 * onClickView the RecyclerView
	 * */
	public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

		private GestureDetector gestureDetector;
		private ContentFragment.ClickListener clickListener;

		public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ContentFragment.ClickListener clickListener) {
			this.clickListener = clickListener;
			gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					return true;
				}

				@Override
				public void onLongPress(MotionEvent e) {
					View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
					if (child != null && clickListener != null) {
						clickListener.onLongClick(child, recyclerView.getChildPosition(child));
					}
				}
			});
		}

		@Override
		public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

			View child = rv.findChildViewUnder(e.getX(), e.getY());
			if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
				clickListener.onClick(child, rv.getChildPosition(child));
			}
			return false;
		}

		@Override
		public void onTouchEvent(RecyclerView rv, MotionEvent e) {
		}

		@Override
		public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

		}
	}
}
