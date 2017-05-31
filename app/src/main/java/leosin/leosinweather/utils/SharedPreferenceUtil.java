package leosin.leosinweather.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import leosin.leosinweather.App;

/**
 * Author: LeosinZhang
 * Time: 2016/12/15:16:34
 * Describle:
 */
public class SharedPreferenceUtil {
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor editor;

    private final String ViewPagerCity = "ViewPagerCity"; //ViewPager City名字
    private final String ViewPagerNum = "ViewPagerNum"; //ViewPager City个数

    public static SharedPreferenceUtil getInstance() {
        return SharedPreferenceHolder.sInstance;
    }

    private static class SharedPreferenceHolder {
        private static final SharedPreferenceUtil sInstance = new SharedPreferenceUtil();
    }

    private SharedPreferenceUtil() {
        editor = App.getContext().getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        mSharedPreferences = App.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
    }



    /**
     * 用于存放天气图标
     *
     * @param key   天气图标代码
     * @param value R.mipmap.icon 天气图标
     * @return
     */
    public SharedPreferenceUtil putInt(String key, int value) {
        editor.putInt(key, value).apply();
        return this;
    }

    /**
     * 存放当前City个数
     * @param value
     * @return
     */
    public SharedPreferenceUtil putCityNum(int value){
        editor.putInt(ViewPagerNum, value).apply();
        return this;
    }


    /**
     * 获取已存放的天气图标
     *
     * @param key      天气图标代码
     * @param defValue 获取 图标错误时，提交码
     * @return
     */
    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }



    public boolean saveArray(ArrayList arrayList) {
        editor.putInt(ViewPagerNum, arrayList.size()); /*sKey is an array*/

        for(int i=0;i<arrayList.size();i++) {
            editor.remove(ViewPagerCity + i);
            editor.putString(ViewPagerCity + i, arrayList.get(i).toString());
        }

        return editor.commit();
    }

    public ArrayList loadArray() {
        ArrayList arrayList = new ArrayList();
        int size = mSharedPreferences.getInt(ViewPagerNum, 0);

        for(int i=0;i<size;i++) {
            arrayList.add(mSharedPreferences.getString(ViewPagerCity + i, ""));
        }
        return arrayList;
    }

}
