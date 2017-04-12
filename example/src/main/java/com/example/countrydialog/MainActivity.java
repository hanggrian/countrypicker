package com.example.countrydialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hendraanggrian.countrydialog.Country;
import com.hendraanggrian.countrydialog.CountryDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountryDialog.Builder(MainActivity.this)
                        .onSelected(new CountryDialog.OnSelectedListener() {
                            @Override
                            public void onSelected(@NonNull Country country) {
                                Toast.makeText(MainActivity.this, country.getName(MainActivity.this), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.button_noflag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountryDialog.Builder(MainActivity.this)
                        .title("No flags")
                        .showFlags(false)
                        .onSelected(new CountryDialog.OnSelectedListener() {
                            @Override
                            public void onSelected(@NonNull Country country) {
                                Toast.makeText(MainActivity.this, country.getName(MainActivity.this), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.button_showdialcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountryDialog.Builder(MainActivity.this)
                        .title("With dial code")
                        .showDialCode(true)
                        .onSelected(new CountryDialog.OnSelectedListener() {
                            @Override
                            public void onSelected(@NonNull Country country) {
                                Toast.makeText(MainActivity.this, country.getDialCode(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.button_customizedscroller).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountryDialog.Builder(MainActivity.this)
                        .title("With scroller")
                        .scrollerAutoHide(false)
                        .scrollerThumbColor(R.color.colorPrimary)
                        .scrollerPopupBackgroundColor(R.color.colorPrimary)
                        .scrollerPopupTextColor(android.R.color.white)
                        .onSelected(new CountryDialog.OnSelectedListener() {
                            @Override
                            public void onSelected(@NonNull Country country) {
                                Toast.makeText(MainActivity.this, country.getDialCode(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}