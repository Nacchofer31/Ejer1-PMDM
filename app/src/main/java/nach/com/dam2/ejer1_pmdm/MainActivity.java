package nach.com.dam2.ejer1_pmdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText nombre;
    Button boton;
    RadioButton r1,r2;
    String genero,edad,valorNombre,switchStatus;
    TextView mensajeEdad;
    Switch carnetConducir;
    SeekBar valBarra;
    RatingBar valEstrellas;
    Integer progreso,estrellas;

    public static final int REQUEST_CODE=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ejer1-PasoDeDatos");
        setContentView(R.layout.activity_main);
        captarCamposSeekBar();
        captarSwitch();
        captarEstrellas();
        captarCampos();
    }

    public void captarCamposSeekBar(){
        valBarra = (SeekBar) findViewById(R.id.seekBar);
        valBarra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progreso = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Puntuación seleccionada: " + progreso + "/100", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void captarSwitch(){
        carnetConducir = (Switch) findViewById(R.id.switch1);
        switchStatus=" no";
        carnetConducir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchStatus = " sí";
                } else {
                    switchStatus = " no";
                }
            }
        });
    }

    public void captarEstrellas(){
        valEstrellas = (RatingBar) findViewById(R.id.ratingBar);
        estrellas=0;
        valEstrellas.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                estrellas = Math.round(rating);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK&&requestCode==REQUEST_CODE){
            if(data.hasExtra("edadIntent")) {
                mensajeEdad = (TextView) findViewById(R.id.textView3);
                mensajeEdad.setText(data.getExtras().getString("edadIntent"));

                nombre.setEnabled(false);
                r1.setEnabled(false);
                r2.setEnabled(false);
                boton.setEnabled(false);
                carnetConducir.setEnabled(false);
                valBarra.setEnabled(false);
                valEstrellas.setEnabled(false);
            }
        }
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

    public void captarCampos(){
        r1=(RadioButton)findViewById(R.id.radioButton);
        r2=(RadioButton)findViewById(R.id.radioButton2);
        nombre = (EditText) findViewById(R.id.editText);
        boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorNombre = nombre.getText().toString();
                if(datosCorrectos()) {
                    if (r1.isChecked() == true) {
                        genero = "Sr";
                    } else {
                        if (r2.isChecked() == true) {
                            genero = "Sra";
                        }
                    }
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    i.putExtra("nombreIntent", valorNombre);
                    i.putExtra("generoIntent", genero);
                    i.putExtra("carnetIntent",switchStatus);
                    i.putExtra("puntBarraIntent",progreso);
                    i.putExtra("puntEstrellasIntent",estrellas);
                    startActivityForResult(i, REQUEST_CODE);
                }else{
                   Toast.makeText(getApplicationContext(),"Revisa los datos. Introducelos todos",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public boolean datosCorrectos() {
        if (nombre.getText().length() <= 0) {
            return false;
        } else {
            if (!r1.isChecked() && !r2.isChecked()) {
                return false;
            }else{
                return true;
            }
        }

    }

}
