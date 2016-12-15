package mac.training.android.com.org.materialdesignbasic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mac.training.android.com.org.materialdesignbasic.R;
import mac.training.android.com.org.materialdesignbasic.model.Hit;

/**
 * Created by raian on 12/15/16.
 */

public class RVPixabayAdapter extends RecyclerView.Adapter<RVPixabayAdapter.ViewHolder>{
    private Context context;
    private List<Hit>lstRes;

    public RVPixabayAdapter(Context context, List<Hit> lstRes) {
        this.context = context;
        this.lstRes = lstRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(lstRes.get(position).getWebformatURL())
                .fitCenter()
                .centerCrop()
                .into(holder.mImageView);

        holder.mTextView.setText(lstRes.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return lstRes.size() > 0 ? lstRes.size() : 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.mImageView);
            mTextView = (TextView) itemView.findViewById(R.id.mTextView);

        }
    }

}
