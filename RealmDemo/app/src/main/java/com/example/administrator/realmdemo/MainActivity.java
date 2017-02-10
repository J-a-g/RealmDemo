package com.example.administrator.realmdemo;

import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Dog dog;
    Realm realm;
    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        dog = new Dog();
        dog.setAge(100);
        dog.setName("二号");



        realm = Realm.getDefaultInstance();
        if(!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        dog.setAge(50);
        dog.setName("wahaha");
        realm.copyToRealm(dog);

       /* Person person = realm.createObject(Person.class,1); // Create managed objects directly
        person.setName("scj");*/



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


       // test();


    }

    private void test(){
        Log.v("TAG","test --------------->"+new Date().getTime());
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                Dog dog = null;
                for(int i=0;i<500;i++){
                    dog = new Dog();
                    dog.setName("test_"+i);
                    dog.setAge(i);
                    dog.setDsc("这是第一只狗"+ new Date().getTime());
                    //realm.beginTransaction();
                    realm.copyToRealm(dog);
                }
            }
        });
        Log.v("TAG","test --------------->"+new Date().getTime());//1486710011074
                                                               //1486710018061
    }

    private void test1(){
        Dog dog = null;
        Log.v("TAG","test1 --------------->"+new Date().getTime());
        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        for(int i=0;i<500;i++){
            dog = new Dog();
            dog.setName("test1_"+i);
            dog.setAge(i);
            dog.setDsc("这是第二只狗"+ new Date().getTime());
            //realm.beginTransaction();
            realm.copyToRealm(dog);
        }
        realm.commitTransaction();
        Log.v("TAG","test1 --------------->"+new Date().getTime());
    }
    private void test2(){
        Dog dog = null;
        Log.v("TAG","test2 --------------->"+new Date().getTime());
        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        for(int i=0;i<500;i++){
            dog = new Dog();
            dog.setName("test2_"+i);
            dog.setAge(i);
            dog.setDsc("这是第三只狗"+ new Date().getTime());
            //realm.beginTransaction();
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }
            realm.copyToRealm(dog);
            realm.commitTransaction();
        }
        Log.v("TAG","test2 --------------->"+new Date().getTime());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                test();
                break;
            case R.id.button2:
                test2();
                break;
            case R.id.button3:
                test1();
                break;
            default:
                break;
        }
    }

    //1486710766016
    //1486710766183

}
