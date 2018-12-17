package com.example.coolweather.util;

import android.text.TextUtils;
import android.webkit.WebView;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;
import com.example.coolweather.gson.Weather;
import com.google.gson.Gson;

import junit.framework.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 徐磊 on 2018/12/15.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvince = new JSONArray(response);
                for(int i = 0 ; i < allProvince.length() ; i++){
                    JSONObject provinceObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理返回的市级数据
     */

    public static boolean handleCityResponse(String response , int provinceId){
        if(! TextUtils.isEmpty(response)){
           try{
               JSONArray allCities = new JSONArray(response);
               for (int i = 0 ; i < allCities.length() ; i++){
                   JSONObject cityObject =allCities.getJSONObject(i);
                   City city = new City();
                   city.setCityName(cityObject.getString("name"));
                   city.setCityCode(cityObject.getInt("id"));
                   city.setProvinceId(provinceId);
                   city.save();
               }
               return true;

           }catch (JSONException e){
               e.printStackTrace();
           }
        }
        return false;
    }

    /**
     * 处理和解析服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response , int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0 ; i < allCounties.length() ; i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherCount = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherCount,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
