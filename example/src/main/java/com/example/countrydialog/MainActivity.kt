package com.example.countrydialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.hendraanggrian.countrydialog.CountryDialog

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.button_simple).setOnClickListener {
            CountryDialog.Builder(this)
                    .onSelected { country -> Toast.makeText(this, country.getName(this), Toast.LENGTH_SHORT).show() }
                    .show()
        }

        findViewById(R.id.button_noflag).setOnClickListener {
            CountryDialog.Builder(this)
                    .title("No flags")
                    .showFlags(false)
                    .onSelected { country -> Toast.makeText(this, country.getName(this), Toast.LENGTH_SHORT).show() }
                    .show()
        }

        findViewById(R.id.button_showdialcode).setOnClickListener {
            CountryDialog.Builder(this)
                    .title("With dial code")
                    .showDialCode(true)
                    .onSelected { country -> Toast.makeText(this, country.getDialCode(), Toast.LENGTH_SHORT).show() }
                    .show()
        }

        findViewById(R.id.button_customizedscroller).setOnClickListener {
            CountryDialog.Builder(this)
                    .title("With scroller")
                    .scrollerAutoHide(false)
                    .scrollerThumbColor(R.color.colorPrimary)
                    .scrollerPopupBackgroundColor(R.color.colorPrimary)
                    .scrollerPopupTextColor(android.R.color.white)
                    .onSelected { country -> Toast.makeText(this, country.getDialCode(), Toast.LENGTH_SHORT).show() }
                    .show()
        }
    }
}