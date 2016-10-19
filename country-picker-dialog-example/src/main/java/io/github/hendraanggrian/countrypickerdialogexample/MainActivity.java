package io.github.hendraanggrian.countrypickerdialogexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.github.hendraanggrian.countrypickerdialog.Country;
import io.github.hendraanggrian.countrypickerdialog.CountryPickerDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountryPickerDialog.Builder(this)
                .title("Hello!")
                .onPicked(new CountryPickerDialog.OnPickedListener() {
                    @Override
                    public void onPicked(Country country) {
                        Toast.makeText(MainActivity.this, country.getName(), Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}