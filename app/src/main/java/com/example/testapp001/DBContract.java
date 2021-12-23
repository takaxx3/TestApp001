package com.example.testapp001;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract() {}
    public static class DBEntry implements BaseColumns {
        // BaseColumns インターフェースを実装することで、内部クラスは_IDを継承できる
        public static final String TABLE_NAME = "testdb";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_NAME_UPPER = "upper";
        public static final String COLUMN_NAME_LOWER = "lower";
        public static final String COLUMN_NAME_ALARM = "alarm";

    }

}
