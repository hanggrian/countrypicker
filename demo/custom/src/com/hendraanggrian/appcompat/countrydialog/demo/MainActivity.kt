package com.hendraanggrian.appcompat.countrydialog.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hendraanggrian.appcompat.countrydialog.CountryDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleButton.setOnClickListener {
            CountryDialog.Builder(this)
                .setOnSelectedListener { country -> Toast.makeText(this, country.getName(this), Toast.LENGTH_SHORT).show() }
                .show()
        }

        noFlagButton.setOnClickListener {
            CountryDialog.Builder(this)
                .showFlags(false)
                .setOnSelectedListener { country -> Toast.makeText(this, country.getName(this), Toast.LENGTH_SHORT).show() }
                .show()
        }

        dialCodeButton.setOnClickListener {
            CountryDialog.Builder(this)
                .showDialCode(true)
                .setOnSelectedListener { country -> Toast.makeText(this, country.getDialCode(), Toast.LENGTH_SHORT).show() }
                .show()
        }
    }
}