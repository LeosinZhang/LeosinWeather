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


    public SharedPreferenceUtil putInt(String key, int value) {
        editor.putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }



    public boolean saveArray(ArrayList arrayList) {
        editor.putInt(ViewPagerCity, arrayList.size()); /*sKey is an array*/

        for(int i=0;i<arrayList.size();i++) {
            editor.remove(ViewPagerCity + i);
            editor.putString(ViewPagerCity + i, arrayList.get(i).toString());
        }

        return editor.commit();
    }

    public ArrayList loadArray() {
        ArrayList arrayList = new ArrayList();
        int size = mSharedPreferences.getInt(ViewPagerCity, 0);

        for(int i=0;i<size;i++) {
            arrayList.add(mSharedPreferences.getString(ViewPagerCity + i, null));
        }
        return arrayList;
    }




}
