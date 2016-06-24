package com.example.user.criminalintent;

/**
 * Created by USER on 24-06-2016.
 */
public class CrimeDBSchema {
    public static final class CrimeTable
    {
        public static final String NAME="crimes";
        public static final class Cols
        {
            public static final String UUID="uuid";
            public static final String TITLE="title";
            public static final String DATE="date";
            public static final String SOLVED="solved";
        }
    }
}
