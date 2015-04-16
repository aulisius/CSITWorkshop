package com.example.faizaan.gallerydemo;

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

        if( v.getId() == R.id.radioButton)
            fah.setChecked(false);
        else
            fah.setChecked(true);
    }
    public void calc(View v) {

        Double n = Double.parseDouble( num.toString() ), t;

        if(cel.isChecked()) {
            t = (n - 32) * (5/9);
        }
        else {
            t = (n * (9 / 5)) + 32;
        }

        Toast.makeText( getApplicationContext(), t.toString(), Toast.LENGTH_LONG ).show();
    }

    }