package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 徐磊 on 2018/12/17.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}
