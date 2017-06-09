package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class SesionDBHelper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_android.db";
    Context context;
    private static final String TABLE_ITEM = "sesiones";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "llave";
    private static final String KEY_SCORE = "valor";
    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_SCORE};

    public SesionDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("OnCreate", "CREATE!!!!!");
        db.execSQL("CREATE TABLE sesiones (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, llave TEXT NOT NULL,valor TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sesiones");
        onCreate(db);
    }
}
