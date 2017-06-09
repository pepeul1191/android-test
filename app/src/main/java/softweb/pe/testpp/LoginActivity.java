package softweb.pe.testpp;

import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import models.Sesion;
import utils.Constants;
import utils.DatabaseHelper;
import utils.Httparty;
import utils.SesionDBHelper;

public class LoginActivity extends AppCompatActivity {
    private Button btnIngresar;
    private EditText txtUsuario;
    private EditText txtContrasenia;
    private TextView lblMensaje;
    private SesionDBHelper sdbh ;
    // Reference of DatabaseHelper class to access its DAOs and other components
    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.btnIngresar = (Button) findViewById(R.id.btnIngresar);
        this.txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        this.txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);
        this.lblMensaje = (TextView) findViewById(R.id.lblMensaje);

        this.sdbh = new SesionDBHelper(this);
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public void btnIngresarClick(View v) {
        String usuario = txtUsuario.getText().toString();
        String contrasenia = txtContrasenia.getText().toString();
        String urlLogin = Constants.BASE_URL + "login/acceder?usuario=" + usuario + "&contrasenia=" + contrasenia;
        String rpta = "";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Log.d("URL", urlLogin);

        try{
            Httparty httpartyLogin = new Httparty(urlLogin, "POST");
            httpartyLogin.action();

            JsonParser parser = new JsonParser();
            JsonElement rptaTokensElement = parser.parse(httpartyLogin.getRpta());
            JsonObject rptaTokensJsonObject = rptaTokensElement .getAsJsonObject();
            String existe = rptaTokensJsonObject.get("existe").getAsString();

            if(existe.equalsIgnoreCase("0")){
                lblMensaje.setText("El usuario y/o contraseña no son correctos");
                lblMensaje.setTextColor(getResources().getColor(R.color.rojoQuinua));
            }else if(existe.equalsIgnoreCase("1")){
                lblMensaje.setText("OK... =)");
                lblMensaje.setTextColor(getResources().getColor(R.color.verdeQuinua));

                try{
                    // This is how, a reference of DAO object can be done
                    final Dao<Sesion, Integer> sesionDao = getHelper().getTeacherDao();
                    Sesion token = new Sesion("token", rptaTokensJsonObject.get("token").getAsString());
                    Sesion sesion = new Sesion("autenticado", "true");
                    //This is the way to insert data into a database table
                    sesionDao.create(token);
                    sesionDao.create(sesion);
                }catch(Exception e){
                    rpta = e.toString();
                }

                new Handler().post(new Runnable(){

                    @Override
                    public void run() {
                        Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                });
            }
        }catch (Exception e){
            rpta = e.toString();
            Log.d("TRY2", e.toString());
        }

        Log.d("app", "1 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Log.d("rpta", rpta);
        Log.d("app", "2 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
