package leosin.leosinweather.SQLite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static leosin.leosinweather.SQLite.cityDbHelper.TABLE_NAME;

/**
 * Author: LeosinZhang
 * Time: 2017/5/25 11:22
 * Describle:
 */
public class cityDbManager {
    private cityDbHelper helper;
    private SQLiteDatabase db;
    //private String tableName = "cityTable";
    private String tabFieldCityID = "cityID";
    private String tabFieldParentID = "parentID";
    private String tabFieldCity = "city";


    public cityDbManager(Context context) {
        helper = new cityDbHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }


    public void add(int cityID, int parentID, String city) {
        ContentValues cv = new ContentValues();//实例化一个ContentValues用来装载待插入的数据
        cv.put(tabFieldCityID, cityID);//添加cityID
        cv.put(tabFieldParentID, parentID); //添加parentID
        cv.put(tabFieldCity, city); //添加city
        db.insert(TABLE_NAME, null, cv);//执行插入操作
    }

    public List queryCity(String input) {
        List<String> cityList = new ArrayList<String>();
        // Cursor c = db.query(tableName, null, null, null, null, null, null);//查询并获得游标

        String current_sql_sel = "SELECT  * FROM " + TABLE_NAME + " where " + tabFieldCity + " like '%" + input + "%'";
        Cursor c = db.rawQuery(current_sql_sel, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String city = c.getString(c.getColumnIndex(tabFieldCity));
            if (!cityList.contains(city))   //让集合中的数据不重复;
                cityList.add(city);
            c.moveToNext();
        }
        closeDB();
        return cityList;
    }

    public boolean isExistData() {
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);//查询并获得游标
        int num = c.getCount();
        if (num == 0)
            return false;
        else
            return true;
    }


    /**
     * close database
     */
    public void closeDB() {
        //db.close();
    }

}
