package softweb.pe.testpp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

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

                Log.d("MyApp", "usuario : " + usuario + " - contrasenia : " + contrasenia);
            }
        });
    }
}
