package com.gist.demo.list.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.gist.demo.app.R;
import com.gist.demo.app.databinding.HotGistActivityBinding;
import com.gist.demo.list.main.ui.adapter.HotGistsAdapter;
import com.gist.demo.list.main.ui.viewmodel.HotGistsViewModel;
import com.gist.demo.list.main.data.HotGistModel;

import java.util.List;
/**
 * Created by KowsalyaM on 26/10/22.
 */

public class HotGistActivity extends AppCompatActivity {

    private HotGistActivityBinding binding;
    HotGistsViewModel hotGistsViewModel;
    HotGistsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.hot_gist_activity);
        populateData();

    }

    private void populateData() {
        hotGistsViewModel = ViewModelProviders.of(this).get(HotGistsViewModel.class);
        hotGistsViewModel.getHotGists();
        adapter = new HotGistsAdapter(getApplicationContext(), hotGistsViewModel);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setAdapter(adapter);

        hotGistsViewModel.gistsMutableLiveData.observe(this, new Observer<List<HotGistModel>>() {
            @Override
            public void onChanged(List<HotGistModel> models) {
                adapter.setList(models);
            }
        });

    }

}