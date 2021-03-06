package com.example.user.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.user.criminalintent.CrimeDBSchema.CrimeTable.Cols.UUID;

/**
 * Created by USER on 19-06-2016.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    //private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static CrimeLab get(Context context)
    {
        if(sCrimeLab==null)
        {
            sCrimeLab=new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context)
    {
        mContext=context.getApplicationContext();
        mDatabase=new CrimeBaseHelper(mContext).getWritableDatabase();
        //mCrimes=new ArrayList<>();
    }
    public void addCrime(Crime c)
    {
        ContentValues values=getContentValues(c);

        mDatabase.insert(CrimeDBSchema.CrimeTable.NAME,null,values);
        //mCrimes.add(c);
    }
    public void deleteCrime(Crime c)
    {
        //mCrimes.remove(c);
        String uuidString = c.getId().toString();
        mDatabase.delete(CrimeDBSchema.CrimeTable.NAME, CrimeDBSchema.CrimeTable.Cols.UUID+"= ?",new String[]{uuidString});
    }
    public List getCrimes()
    {
        //return mCrimes;
        //return new ArrayList<>();
        List<Crime> crimes=new ArrayList<>();

        CrimeCursorWrapper cursor=queryCrimes(null,null);
        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }
        return crimes;
    }
    public Crime getCrime(UUID id)
    {
        /*for(Crime crime:mCrimes)
        {
            if(crime.getId().equals(id))
            {
                return crime;
            }
        }*/
        //return null;
        CrimeCursorWrapper cursor=queryCrimes(CrimeDBSchema.CrimeTable.Cols.UUID+" = ?",new String[] {id.toString()});
        try
        {
            if(cursor.getCount()==0)
            {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        }
        finally
        {
            cursor.close();
        }
    }
    private static ContentValues getContentValues(Crime crime)
    {
        ContentValues values=new ContentValues();
        values.put(UUID,crime.getId().toString());
        values.put(CrimeDBSchema.CrimeTable.Cols.TITLE,crime.getTitle());
        values.put(CrimeDBSchema.CrimeTable.Cols.DATE,crime.getDate().getTime());
        values.put(CrimeDBSchema.CrimeTable.Cols.SOLVED,crime.isSolved()?1:0);
        values.put(CrimeDBSchema.CrimeTable.Cols.SUSPECT,crime.getSuspect());
        return values;
    }
    public void updateCrime(Crime crime)
    {
        String uuidString=crime.getId().toString();
        ContentValues values=getContentValues(crime);

        mDatabase.update(CrimeDBSchema.CrimeTable.NAME,values, CrimeDBSchema.CrimeTable.Cols.UUID+"=?",new String[]{uuidString});
    }
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs)
    {
        Cursor cursor= mDatabase.query(CrimeDBSchema.CrimeTable.NAME,null,whereClause,whereArgs,null,null,null);
        return new CrimeCursorWrapper(cursor);
    }
}
