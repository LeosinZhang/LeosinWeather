package leosin.leosinweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import leosin.leosinweather.utils.Const;
import leosin.leosinweather.utils.RxUtil.RetrofitMethods;
import leosin.leosinweather.utils.SharedPreferenceUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Administrator on 2016/11/25.
 */
public class App extends android.app.Application {
    public static Context context;
    public static String font;


    //https://github.com/kaku2015/WeatherAlarmClock
    //https://github.com/li2/Update_Replace_Fragment_In_ViewPager

    @Override
    public void onCreate() {
        super.onCreate();
        context = App.this;
        font = Const.ArefRuqaa;
        Logger
                .init("LogInfo")              // 默认为PRETTYLOGGER，可以设置成为自定义tag
                .setMethodCount(2)             // logger所在方法显示开关 0 为不显示，1、2 为不同的方法信息显示样式
                .hideThreadInfo()              // 线程信息显示，默认打开
                .setLogLevel(LogLevel.FULL)    // 默认是打开日志显示（FULL），关闭（NONE）
                .setMethodOffset(2);           // 默认为0 ,方法体样式

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(font)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Logger.d("App  onCreat");
    }


    public static void existSys() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public static Context getContext() {
        return context;
    }

}
