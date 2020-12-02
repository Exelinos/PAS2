package com.example.pasfix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListDataFavorite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private DataAdapterFavourite adapter;
    private TextView tvnodata;
    private List<ModelMovieRealm> DataArrayList; //kit add kan ke adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm =Realm.getInstance(configuration);
        realmHelper =new RealmHelper(realm);
        DataArrayList = realmHelper.getAllMahasiswa();
        if (DataArrayList.size() == 0){
            tvnodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            tvnodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new DataAdapterFavourite((ArrayList<ModelMovieRealm>) DataArrayList, new DataAdapterFavourite.Callback() {
                @Override
                public void onClick(int position) {
                    Intent move = new Intent(getApplicationContext(), DetailMovie.class);
                    move.putExtra("judul",DataArrayList.get(position).getStrTeam());
                    move.putExtra("path",DataArrayList.get(position).getStrTeamBadge());
                    move.putExtra("date",DataArrayList.get(position).getStrStadium());
                    move.putExtra("deskripsi",DataArrayList.get(position).getStrDescriptionEN());

                    startActivity(move);
                }

                @Override
                public void test() {

                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFavorite.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
