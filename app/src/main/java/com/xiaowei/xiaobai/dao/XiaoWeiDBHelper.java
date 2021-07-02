package com.xiaowei.xiaobai.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xiaowei.xiaobai.utils.Constants;

import static com.xiaowei.xiaobai.utils.Constants.SUB_DESCRIPTION;
import static com.xiaowei.xiaobai.utils.Constants.SUB_TB_NAME;
import static com.xiaowei.xiaobai.utils.Constants.SUB_TITLE;

public class XiaoWeiDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "XiaoWeiDBHelper";

    public XiaoWeiDBHelper(@Nullable Context context, @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        //name数据库的名字，factory游标工厂，version版本号
        super(context, Constants.DB_NAME, factory, Constants.DB_VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate:");
        /**
         * 创建数据表，订阅相关的字段
         * 图片、title、描述、播放量、节目数量、作者名称、专辑id等等
         */
        String subTbSql = "create table " + Constants.DB_NAME + "(" +
            Constants.SUB_ID + " integer, " +
            Constants.SUB_CONVERURL + " varchar, " +
            Constants.SUB_TITLE + " varchar, " +
            Constants.SUB_DESCRIPTION + " varchar, " +
            Constants.SUB_PLAYCOUNT + " integer, " +
            Constants.SUB_TRACKSCOUNT + " integer, " +
            Constants.SUB_AUTHORNAME + " varchar, " +
            Constants.SUB_ALBUMID + " integer" +
            ")";
        db.execSQL(subTbSql);

        // 测试添加数据
        String testInsertSql = "insert into" + SUB_TB_NAME +"(" +
            SUB_TITLE +"," + SUB_DESCRIPTION +")"+ "(values('title','desciption')";
        for (int i = 10000; i > 0; i--) {
//            db.execSQL(testInsertSql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
