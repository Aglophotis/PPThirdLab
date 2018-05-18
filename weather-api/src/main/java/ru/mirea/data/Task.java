package ru.mirea.data;

import java.util.Date;


public interface Task {
    void setID(int tmpID);

    void setDate(Date tmpDate);

    void setCity(String tmpCity);

    void setWeather(String tmpWeather);

    int getID();

    Date getDate();

    String getCity();

    String getWeather();
}
