package leosin.leosinweather.utils.RxUtil;

import leosin.leosinweather.bean.WeatherBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: LeosinZhang
 * Time: 2017/5/16 9:22
 * Describle:逆地理编码
 */
public interface GeoCoderInterface {
    //  http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=39.983424,116.322987&output=json&pois=1&ak=您的ak
    @GET("geocoder/v2")
    Observable<GeoCoderInterface> getGeoCoder(@Query("callback") String callback, @Query("location") String location,
                                        @Query("output") String output, @Query("ak") String key);
}
