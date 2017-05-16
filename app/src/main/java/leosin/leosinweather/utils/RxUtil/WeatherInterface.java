package leosin.leosinweather.utils.RxUtil;

import leosin.leosinweather.bean.WeatherBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: LeosinZhang
 * Time: 2016/12/20:10:39
 * Describle: 请求天气的接口
 */
public interface WeatherInterface {

    @GET("weather/query")
    Observable<WeatherBean> getWeatherInfo(@Query("appkey") String key, @Query("city") String city);

}
