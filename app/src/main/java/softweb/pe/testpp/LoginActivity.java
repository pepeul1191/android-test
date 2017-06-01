package softweb.pe.testpp;

import android.graphics.Color;
import android.os.StrictMode;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import utils.Constants;
import utils.Httparty;

public class LoginActivity extends AppCompatActivity {
    private Button btnIngresar;
    private EditText txtUsuario;
    private EditText txtContrasenia;
    private TextView lblMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.btnIngresar = (Button) findViewById(R.id.btnIngresar);
        this.txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        this.txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);
        this.lblMensaje = (TextView) findViewById(R.id.lblMensaje);

        this.btnIngresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString();
                String contrasenia = txtContrasenia.getText().toString();
                String urlLogin = Constants.BASE_URL + "usuario/validar?usuario=" + usuario + "&contrasenia=" + contrasenia;
                String rpta;

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                try{
                    Httparty httpartyLogin = new Httparty(urlLogin, "POST");
                    httpartyLogin.action();
                    rpta = httpartyLogin.getRpta();

                    if(httpartyLogin.getRpta().equalsIgnoreCase("0")){
                        lblMensaje.setText("El usuario y/o contraseña no son correctos");
                        lblMensaje.setTextColor(getResources().getColor(R.color.rojoQuinua));
                    }else if(httpartyLogin.getRpta().equalsIgnoreCase("1")){
                        lblMensaje.setText("OK... =)");
                        lblMensaje.setTextColor(getResources().getColor(R.color.verdeQuinua));
                    }
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
