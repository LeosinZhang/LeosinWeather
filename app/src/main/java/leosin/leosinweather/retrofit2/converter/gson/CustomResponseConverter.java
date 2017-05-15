package leosin.leosinweather.retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONObject;

import java.io.IOException;

import leosin.leosinweather.bean.WeatherBean;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author: LeosinZhang
 * Time: 2016/12/21:14:55
 * Describle:
 */
public class CustomResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public CustomResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String WeatherInfo = value.string();
            JSONObject json = new JSONObject(WeatherInfo);
            int ret = json.optInt("status");
            String msg = json.optString("msg", "");

            if (ret == 0) {
                WeatherBean bean = gson.fromJson(WeatherInfo, WeatherBean.class);
                return (T)bean;
            } else {
                throw new RuntimeException(msg);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}
