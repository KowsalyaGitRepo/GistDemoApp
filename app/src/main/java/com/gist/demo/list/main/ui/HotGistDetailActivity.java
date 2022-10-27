package com.gist.demo.list.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.gist.demo.app.R;
import com.gist.demo.app.databinding.HotGistDetailActivityBinding;
import com.gist.demo.list.main.data.HotGistModel;
import com.gist.demo.list.utils.SharedPreference;
/**
 * Created by KowsalyaM on 26/10/22.
 */

public class HotGistDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private HotGistDetailActivityBinding binding;
    HotGistModel model;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.hot_gist_detail_activity);
        context = getApplicationContext();
        populateData();
    }

    private void populateData() {
        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            model = (HotGistModel) intent.getSerializableExtra("hot_gist_model");
        }
        if (model != null) {
            binding.tvHotGistId.setText(model.id);
            binding.tvHotGistName.setText(model.owner.login);
            binding.tvHotGistUrl.setText(model.url);
            if (checkFavouriteItem(model.id)) {
                Drawable starFilled = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_favourite_filled, null);
                starFilled.setBounds(0, 0, 24, 24);
                binding.chkHotGistFavorite.setBackground(starFilled);
            } else {
                Drawable starEmpty = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_favourite_empty, null);
                starEmpty.setBounds(0, 0, 24, 24);
                binding.chkHotGistFavorite.setBackground(starEmpty);
            }
            if (!model.getOwner().getlogin().isEmpty()) {
                binding.tvHotGistShared.setVisibility(View.VISIBLE);
                binding.tvHotGistShared.setText("Shared:\t" + model.getCountShared());
            }
            binding.chkHotGistFavorite.setOnClickListener(this);
        }
    }

    private boolean checkFavouriteItem(String key) {
        boolean check = false;
        if (model != null) {
            check = SharedPreference.getInstance().getFavourite(context, key);
        }
        return check;
    }

    @Override
    public void onClick(View view) {
        String tag = binding.chkHotGistFavorite.getTag().toString();
        if (tag.equalsIgnoreCase("empty")) {
            SharedPreference.getInstance().setFavourite(context, model.id, true);
            binding.chkHotGistFavorite.setTag("filled");
            Drawable starFilled = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_favourite_filled, null);
            starFilled.setBounds(0, 0, 24, 24);
            binding.chkHotGistFavorite.setBackground(starFilled);

        } else {
            SharedPreference.getInstance().setFavourite(context, model.id, false);
            binding.chkHotGistFavorite.setTag("empty");
            Drawable starEmpty = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_favourite_empty, null);
            starEmpty.setBounds(0, 0, 24, 24);
            binding.chkHotGistFavorite.setBackground(starEmpty);
        }
    }


}