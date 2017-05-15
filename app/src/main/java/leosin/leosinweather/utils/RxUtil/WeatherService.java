package leosin.leosinweather.utils.RxUtil;

import leosin.leosinweather.bean.WeatherBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: LeosinZhang
 * Time: 2016/12/20:10:39
 * Describle:
 */
public interface WeatherService {

    @GET("weather/query")
    Observable<WeatherBean> getWeatherInfo(@Query("appkey") String key, @Query("city") String city);

}
