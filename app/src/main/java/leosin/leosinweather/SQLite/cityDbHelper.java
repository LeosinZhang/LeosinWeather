package leosin.leosinweather.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Author: LeosinZhang
 * Time: 2017/5/25 11:22
 * Describle:
 */
public class cityDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "city.db";
    private static final int DATABASE_VERSION = 1;

    public cityDbHelper(Context context){
        //CursorFactory设置为null,使用默认值
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //数据库第一次被创建时onCreate会被调用
    @Override
    public void onCreate(SQLiteDatabase database) {
        String sql = "create table if not exists " + "trace" + " (" +
                "point integer primary key, " +
                "cityID integer" +
                "parentID integer" +
                "city TEXT)";

        database.execSQL(sql);
        Log.d("创建表", "successful");
    }

    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("ALTER TABLE person ADD COLUMN other STRING");
    }
}
