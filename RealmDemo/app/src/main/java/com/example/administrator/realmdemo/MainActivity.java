package com.example.administrator.realmdemo;

import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {
    Dog dog;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dog = new Dog();
        dog.setAge(100);
        dog.setName("二号");



        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        dog.setAge(50);
        dog.setName("wahaha");
        realm.copyToRealm(dog);

        Person person = realm.createObject(Person.class,1); // Create managed objects directly
        person.setName("scj");



        RealmResults<Dog> realmResults = realm.where(Dog.class).findAll();
        Log.v("TAG","realmResult------------>"+realmResults.size());
        for(int i=0;i<realmResults.size();i++){
            Log.v("TAG","Dog---->"+realmResults.get(i).toString());
            Dog dog1 = realmResults.get(i);
            dog1.setName("kele");
        }
      //  realm.commitTransaction();


        Log.v("TAG","thread name----->"+Thread.currentThread().getName());

        for(int i=0;i<realmResults.size();i++){
            Log.v("TAG","Dog---->"+realmResults.get(i).toString());

        }
        Log.v("TAG","thread name----->"+Thread.currentThread().getName());
        RealmResults<Person> results = realm.where(Person.class).findAll();
        for(int i=0;i<results.size();i++){
            Log.v("TAG","Person---->"+results.get(i).toString());

        }
        realm.commitTransaction();

      /*  realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.v("TAG","thread name----->"+Thread.currentThread().getName());
                RealmResults<Dog> realmResults = realm.where(Dog.class).findAll();
                ((MyApplication)getApplication()).setDog(realmResults.get(0));
                Log.v("TAG","dogName--------->"+((MyApplication)getApplication()).getDog().getName());

                dog.setName("onepices");
                Log.v("TAG","dog name---->"+dog.getName());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                Log.v("TAG","onSuccess.....");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.v("TAG","onError.....");
            }
        });*/
       // new MyThread().start();
        //Log.v("TAG","dogName--------->"+((MyApplication)getApplication()).getDog().getName());

        dog = null;
       // realm.close();
      //  Log.v("TAG","dog.isValy----------->"+dog.isValid()+"     "+dog.getName());

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("key","value");
        bundle.putInt("Int",888888);
        intent.putExtra("bundle",bundle);
        Log.v("TAG",intent.toString());



    }
}
