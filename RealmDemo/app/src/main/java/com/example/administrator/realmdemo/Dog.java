package com.example.administrator.realmdemo;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017.1.12.
 */

public class Dog extends RealmObject{
    private String name;
    private int age;
    private String dsc;


    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dsc='" + dsc + '\'' +
                '}';
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
