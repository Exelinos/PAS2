package com.example.pasfix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailMovie extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;


    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;
    String id;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    Button btnbookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        extras = getIntent().getExtras();
        tvjudul = (TextView)findViewById(R.id.tvjudul);
        tvdesc = (TextView)findViewById(R.id.tvdescdetail);
        ivposter = (ImageView) findViewById(R.id.ivposter);
        btnbookmark = (Button) findViewById(R.id.btnbookmark);

        if (extras != null) {
            title = extras.getString("judul");
            id = extras.getString("id");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            tvjudul.setText(title);
            tvdesc.setText(deskripsi);
            Glide.with(DetailMovie.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(DetailMovie.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieModel = new ModelMovieRealm();
                movieModel.setStrDescriptionEN(deskripsi);
                movieModel.setStrTeam(title);
                movieModel.setStrTeamBadge(path);


                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);

            }
        });
    }
}
