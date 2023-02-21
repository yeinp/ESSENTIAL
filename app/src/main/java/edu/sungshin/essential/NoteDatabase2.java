package edu.sungshin.essential;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteDatabase2 {
    private static final String TAG = "NoteDatabase2";

    private static NoteDatabase2 database2;
    public static String DATABASE_NAME2 = "todo2.db";
    public static String TABLE_NOTE2 = "NOTE2";
    public static int DATABASE_VERSION2 = 1;

    private Context context2;
    private SQLiteDatabase db2;
    private DatabaseHelper dbHelper2;


    private NoteDatabase2(Context context2){
        this.context2 = context2;
    }

    public static NoteDatabase2 getInstance(Context context2){
        if(database2 == null){
            database2 = new NoteDatabase2(context2);
        }

        return database2;
    }

    //이후 리스트를 표시할 때 현지위치를 나타내는 커서역할을 함
    public Cursor rawQuery(String SQL2){

        Cursor c1 = null;
        try{
            c1 = db2.rawQuery(SQL2,null);
        } catch (Exception ex){
            Log.e(TAG,"Exception in rawQuery",ex);
        }

        return c1;
    }

    //sql문을 실행시키는 역할을 함
    public boolean execSQL(String SQL2) {

        try {
            Log.d(TAG, "SQL : " + SQL2);
            db2.execSQL(SQL2);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in execSQL", ex);
            return false;
        }
        return true;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context2, String name2, SQLiteDatabase.CursorFactory factory2, int version2){
            super(context2, name2,factory2,version2);
        }


        @Override
        public void onCreate(SQLiteDatabase db2) {


            //테이블초기화
            String DROP_SQL2 = "drop table if exists " +TABLE_NOTE2;

            try {
                db2.execSQL(DROP_SQL2);

            } catch (Exception ex){
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            //테이블 생섣
            String CREATE_SQL2 = "create table " + TABLE_NOTE2 + "("
                    + " _id integer NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  TODO TEXT DEFAULT '' "
                    + ")";
            try{
                db2.execSQL(CREATE_SQL2);
            } catch (Exception ex){
                Log.e(TAG,"Exception in CREATE_SQL", ex);
            }

            //테이블의 인덱스 붙이기
            String CREATE_INDEX_SQL2 = "create index " + TABLE_NOTE2 + "_IDX ON " + TABLE_NOTE2 + "("
                    + "_id"
                    + ")";
            try{
                db2.execSQL(CREATE_INDEX_SQL2);
            } catch (Exception ex){
                Log.e(TAG, "Exception in CREATE_INDEX_SQL",ex);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {

        }

    }

    //데이터베이스 열기
    public boolean open2(){

        dbHelper2 = new DatabaseHelper(context2, DATABASE_NAME2, null, DATABASE_VERSION2);
        db2 = dbHelper2.getWritableDatabase();

        return true;
    }

    //데이터베이스 닫기
    public void close(){
        db2.close();
        database2 = null;
    }


}
