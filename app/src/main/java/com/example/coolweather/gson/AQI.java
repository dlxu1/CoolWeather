package com.example.coolweather.gson;

/**
 * Created by 徐磊 on 2018/12/17.
 */

public class AQI {
    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
