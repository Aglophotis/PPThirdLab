package ru.mirea.data;

import java.util.Date;


public class TaskImpl implements Task {
    private int id;
    private Date date;
    private String city;
    private String weather;

    public TaskImpl(int id, Date date, String city){
        this.id = id;
        this.date = date;
        this.city = city;
    }

    public TaskImpl(){};

    public void setID(int tmpID){
        id = tmpID;
        return;
    }

    public void setDate(Date tmpDate){
        date = tmpDate;
        return;
    }

    public void setCity(String tmpCity){
        city = tmpCity;
        return;
    }

    public void setWeather(String tmpWeather){
        weather = tmpWeather;
        return;
    }

    public int getID(){
        return id;
    }

    public Date getDate(){
        return date;
    }

    public String getCity(){
        return city;
    }

    public String getWeather(){
        return weather;
    }
}
