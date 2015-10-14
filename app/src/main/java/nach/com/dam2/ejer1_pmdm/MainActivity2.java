package nach.com.dam2.ejer1_pmdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends Activity {

    TextView indicativo;
    EditText edadEditText;
    Button boton2;
    int edadUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edadEditText= (EditText)findViewById(R.id.editText2);
        boton2 = (Button) findViewById(R.id.button2);
        setTitle("Ejer1-PasoDeDatos");
        Bundle b=getIntent().getExtras();
        if(b!=null) {
            String nombreUsuario = b.getString("nombreIntent");
            String genero = b.getString("generoIntent");
            String carnet = b.getString("carnetIntent");
            Integer puntBarra = b.getInt("puntBarraIntent");
            Integer estrellas = b.getInt("puntEstrellasIntent");
            indicativo = (TextView)findViewById(R.id.indi);
            indicativo.setText(genero+" "+nombreUsuario+","+carnet+" tiene carnet de conducir"+", su puntución ha sido de "+puntBarra+" con "+estrellas+" estrellas, introduzca los siguientes datos:");
            continuar();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public void finish() {
        edadUsuario =Integer.parseInt(edadEditText.getText().toString());
        Intent data = new Intent();
        if(edadUsuario<18) {
            data.putExtra("edadIntent", "Tienes " + edadUsuario + " años, ATENCIÓN CONTROL PARENTAL, NO HAY NADA QUE VER AQUÍ ¿VALE?");
        }else {
            if ((edadUsuario > 18 && edadUsuario < 25)||(edadUsuario==18)) {
                data.putExtra("edadIntent", "Tienes " + edadUsuario + " años, ya eres mayor de edad.");
            } else {
                if ((edadUsuario > 25 && edadUsuario < 35) || (edadUsuario ==25)) {
                    data.putExtra("edadIntent", "Tienes " + edadUsuario + " años, ya estás en la flor de la vida.");
                } else {
                    if (edadUsuario > 35 || edadUsuario==35) {
                        data.putExtra("edadIntent", "Tienes " + edadUsuario + " años, AY AY AY.");
                    }
                }
            }
        }

        setResult(RESULT_OK, data);
        super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void continuar(){
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
