package com.example.pasfix;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final ModelMovieRealm movieModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(ModelMovieRealm.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    movieModel.setIdTeam(nextId);
                    ModelMovieRealm model = realm.copyToRealm(movieModel);
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }
// untuk memanggil semua data
        public List<ModelMovieRealm> getAllMahasiswa () {
            RealmResults<ModelMovieRealm> results = realm.where(ModelMovieRealm.class).findAll();
            return results;
        }
}
