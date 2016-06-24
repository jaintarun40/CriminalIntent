package com.example.user.criminalintent;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by USER on 24-06-2016.
 */
public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }

    public Crime getCrime()
    {
        String uuidString=getString(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.UUID));
        String title=getString(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.TITLE));
        long date=getLong(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.DATE));
        int isSolved=getInt(getColumnIndex(CrimeDBSchema.CrimeTable.Cols.SOLVED));

        Crime crime=new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setSolved(isSolved!=0);
        crime.setDate(new Date(date));
        return crime;
    }
}
