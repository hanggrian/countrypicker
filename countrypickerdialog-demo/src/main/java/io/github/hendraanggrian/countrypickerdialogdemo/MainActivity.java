package io.github.hendraanggrian.countrypickerdialogdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import io.github.hendraanggrian.countrypickerdialog.Country;
import io.github.hendraanggrian.countrypickerdialog.CountryPickerDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountryPickerDialog.Builder(MainActivity.this, "Pick country")
                        .onPicked(new CountryPickerDialog.OnPickedListener() {
                            @Override
                            public void onPicked(@NonNull Country country) {
                                Toast.makeText(MainActivity.this, country.toString(MainActivity.this), Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        findViewById(R.id.button_noflag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountryPickerDialog.Builder(MainActivity.this, "Pick country")
                        .showFlags(false)
                        .onPicked(new CountryPickerDialog.OnPickedListener() {
                            @Override
                            public void onPicked(@NonNull Country country) {
                                Toast.makeText(MainActivity.this, country.toString(MainActivity.this), Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        findViewById(R.id.button_showdialcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountryPickerDialog.Builder(MainActivity.this, "Pick country code")
                        .showDialCode(true)
                        .onPicked(new CountryPickerDialog.OnPickedListener() {
                            @Override
                            public void onPicked(@NonNull Country country) {
                                Toast.makeText(MainActivity.this, country.getDialCode(), Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        findViewById(R.id.button_customizedscroller).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountryPickerDialog.Builder(MainActivity.this, "Awesome Scroller")
                        .scrollerAutoHide(false)
                        .scrollerThumbColor(R.color.colorPrimary)
                        .scrollerPopupBackgroundColor(R.color.colorPrimary)
                        .scrollerPopupTextColor(android.R.color.white)
                        .onPicked(new CountryPickerDialog.OnPickedListener() {
                            @Override
                            public void onPicked(@NonNull Country country) {
                                Toast.makeText(MainActivity.this, country.getDialCode(), Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
}