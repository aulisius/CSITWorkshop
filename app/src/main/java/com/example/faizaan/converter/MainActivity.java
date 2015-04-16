package com.example.faizaan.converter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    RadioButton cel, fah;
    EditText num;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        num = (EditText) findViewById( R.id.editText );
        cel = (RadioButton) findViewById( R.id.radioButton );
        fah = (RadioButton) findViewById( R.id.radioButton2 );

        cel.setChecked(true);
        fah.setChecked(false);

    }

    public void radioToggle(View v) {
        if( v.getId() == R.id.radioButton ) {
            cel.setChecked(true);
            fah.setChecked(false);
        }
        else {
            fah.setChecked(true);
            cel.setChecked(false);
        }
    }

    public void calc(View v) {
        try {

            float n =  Float.parseFloat(num.getText().toString()), t;
            if(!Float.isNaN(n)) {
                t = cel.isChecked() ? (float) ((n - 32) * (0.55555555555)) : (float) (n * 1.8) + 32;
                Toast.makeText( getApplicationContext(), Float.toString(t), Toast.LENGTH_LONG ).show();
            }
            else Toast.makeText(getApplicationContext(), "Wrong input", Toast.LENGTH_LONG).show();

        }
        catch(NumberFormatException e){Toast.makeText(getApplicationContext(), "Wrong input", Toast.LENGTH_SHORT).show();}



    }
}