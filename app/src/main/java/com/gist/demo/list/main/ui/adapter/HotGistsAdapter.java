package com.gist.demo.list.main.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gist.demo.app.BR;
import com.gist.demo.app.R;
import com.gist.demo.app.databinding.HotGistItemBinding;
import com.gist.demo.list.main.data.HotGistModel;
import com.gist.demo.list.main.ui.HotGistDetailActivity;
import com.gist.demo.list.main.ui.viewmodel.HotGistsViewModel;
import com.gist.demo.list.main.network.ApiClient;
import com.gist.demo.list.utils.SharedPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotGistsAdapter extends RecyclerView.Adapter<HotGistsAdapter.HotGistsViewHolder> implements GistClickListener  {
    private List<HotGistModel> hotGistModelList = new ArrayList<>();
    private Context context;

    public HotGistsAdapter(Context applicationContext, HotGistsViewModel hotGistsViewModel) {
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public HotGistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HotGistItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.hot_gist_item, parent, false);

        return new HotGistsViewHolder(binding);    }

    @Override
    public void onBindViewHolder(@NonNull HotGistsViewHolder holder, int position) {
        HotGistModel dataModel = hotGistModelList.get(position);
        holder.bind(dataModel);
        holder.gistItemBinding.setItemClickListener(this);
        //
        /**
         GET List by username
         **/
        Call<List<HotGistModel>> call = ApiClient.getINSTANCE().getHotGistsByUserName(dataModel.getOwner().getlogin());
        call.enqueue(new Callback<List<HotGistModel> >() {
            @Override
            public void onResponse(Call<List<HotGistModel> > call, Response<List<HotGistModel> > response) {
                Log.d("TAG",response.code()+"");
                int size = response.body().size();
                if(size>=5){
                    for(HotGistModel model:hotGistModelList){
                        if(model.getOwner().getlogin().equalsIgnoreCase(dataModel.getOwner().getlogin())){
                            model.setCountShared(size);
                            holder.gistItemBinding.tvHotGistShared.setVisibility(View.VISIBLE);
                            holder.gistItemBinding.tvHotGistShared.setText("Shared:\t"+ size);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<HotGistModel>> call, Throwable t) {

            }

        });
        if(checkFavouriteItem(dataModel)){

            Drawable starFilled = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_favourite_filled, null);
            starFilled.setBounds(0,0,24,24);
            holder.gistItemBinding.chkHotGistFavorite.setBackground(starFilled);

        }else{

            Drawable starEmpty = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_favourite_empty,null);
            starEmpty.setBounds(0,0,24,24);
            holder.gistItemBinding.chkHotGistFavorite.setBackground(starEmpty);
        }

        holder.gistItemBinding.chkHotGistFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFavouriteItem(dataModel)){
                    SharedPreference.getInstance().setFavourite(context,dataModel.id,true);
                    Drawable starFilled = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_favourite_filled, null);
                    starFilled.setBounds(0, 0, 24, 24);
                    view.setBackground(starFilled);

                } else {
                    SharedPreference.getInstance().setFavourite(context,dataModel.id,false);
                    Drawable starEmpty = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_favourite_empty, null);
                    starEmpty.setBounds(0, 0, 24, 24);
                    view.setBackground(starEmpty);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotGistModelList.size();
    }

    public void setList(List<HotGistModel> gistsList) {
        this.hotGistModelList = gistsList;
        notifyDataSetChanged();
    }



    private boolean checkFavouriteItem(HotGistModel model) {
        boolean check = false;

        if(model!=null){
            check = SharedPreference.getInstance().getFavourite(context,model.getId());
        }
        return check;
    }

    @Override
    public void cardClicked(HotGistModel gistModel) {
            Intent intent = new Intent(context, HotGistDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("hot_gist_model", gistModel);
            context.startActivity(intent);
    }


    public class HotGistsViewHolder extends RecyclerView.ViewHolder{

        public HotGistItemBinding gistItemBinding;

        public HotGistsViewHolder(HotGistItemBinding binding) {
            super(binding.getRoot());
            this.gistItemBinding = binding;
        }

        public void bind(Object obj) {
            gistItemBinding.setVariable(BR.model, obj);
            gistItemBinding.executePendingBindings();

        }

    }
}
