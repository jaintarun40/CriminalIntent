package com.example.user.criminalintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.criminalintent.CrimeDBSchema.CrimeTable;

/**
 * Created by USER on 24-06-2016.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + CrimeTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CrimeTable.Cols.UUID + ", " +
                CrimeTable.Cols.TITLE + ", " +
                CrimeTable.Cols.DATE + ", " +
                CrimeTable.Cols.SOLVED +", "+
                CrimeDBSchema.CrimeTable.Cols.SUSPECT+
                ")"
        );
        //db.execSQL("create table"+ CrimeTable.NAME+"("+"_id integer primary key autoincrement, "+CrimeTable.Cols.UUID+", "+CrimeTable.Cols.TITLE+", "+CrimeTable.Cols.DATE+", "+CrimeTable.Cols.SOLVED+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {

    }
}
