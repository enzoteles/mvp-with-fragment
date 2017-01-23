package br.com.gingaone.spidermain.model.adapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

import br.com.gingaone.spidermain.R;
import br.com.gingaone.spidermain.helper.proportion.DisplayUtil;
import br.com.gingaone.spidermain.model.pojo.Result;

/**
 * Created by Enzo Teles on 17/06/2016.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.Holder> {

    private List<Result>            mListResult;
    private Activity                activity;

    public ContentAdapter(List<Result> mListResult, Activity activity) {
        this.mListResult            = mListResult;
        this.activity               = activity;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView               = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
        DisplayUtil                 .setLayoutParams((ViewGroup) itemView.findViewById(R.id.layout_item_content));
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Result mReuslt              = mListResult.get(position);
        String path                 = mReuslt.getThumbnail().getPath()+"."+ mReuslt.getThumbnail().getExtension();
        holder.numberSerie          .setText("#" + mReuslt.getIssueNumber()+"");
        Picasso.with(activity)
                .load(path)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(holder.revImage);
    }

    @Override
    public int getItemCount() {
        return mListResult.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView            revImage;
        private TextView             numberSerie;

        public Holder(View itemView) {
            super(itemView);
            revImage                 = (ImageView) itemView.findViewById(R.id.img_rev);
            numberSerie              = (TextView)  itemView.findViewById(R.id.number_serie);


        }
    }

    public Drawable getDrawable(String bitmapUrl) {
        try {
            URL url = new URL(bitmapUrl);
            Drawable d =new BitmapDrawable(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
            return d;
        }
        catch(Exception ex) {return null;}
    }
}
