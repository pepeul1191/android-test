package softweb.pe.testpp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import utils.SesionDBHelper;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    private SesionDBHelper sdbh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sdbh = new SesionDBHelper(this);

        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                try{
                    SQLiteDatabase db = sdbh.getWritableDatabase();
                    if(db != null){
                        String queryString = "SELECT valor FROM sesiones WHERE llave = 'autenticado'";
                        Cursor c = db.rawQuery(queryString, new String[] {});

                        int i = 0;
                        if (c.getCount() > 0)
                        {
                            c.moveToFirst();
                            do {
                                Log.d("RPTA", c.getString(c.getColumnIndex("field_name")));
                                i++;
                            } while (c.moveToNext());
                            c.close();
                        }
                    }
                }catch(Exception e){
                    Log.d("TRY1", e.toString());
                }

                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
