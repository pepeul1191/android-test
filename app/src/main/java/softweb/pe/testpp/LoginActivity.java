package softweb.pe.testpp;

import android.os.StrictMode;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import utils.Constants;
import utils.Httparty;

public class LoginActivity extends AppCompatActivity {
    private Button btnIngresar;
    private EditText txtUsuario;
    private EditText txtContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.btnIngresar = (Button) findViewById(R.id.btnIngresar);
        this.txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        this.txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);

        this.btnIngresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString();
                String contrasenia = txtContrasenia.getText().toString();
                String urlLogin = Constants.BASE_URL + "usuario/validar?usuario=" + usuario + "&contrasenia=" + contrasenia;

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String rpta;

                try{
                    Httparty httpartyLogin = new Httparty(urlLogin, "POST");
                    httpartyLogin.action();
                    rpta = httpartyLogin.getRpta();
                }catch (Exception e){
                    rpta = e.toString();
                }

                Log.d("MyApp", "1 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                Log.d("MyApp", "usuario : " + usuario + " - contrasenia : " + contrasenia);
                Log.d("URL : ", urlLogin);
                Log.d("MyApp", "httpartyLogin.rpta : " + rpta);
                Log.d("MyApp", "2 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        });
    }
}
