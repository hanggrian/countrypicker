package com.example.static

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.R
import com.hanggrian.countrypicker.FlagDisplay.CUSTOM
import com.hanggrian.countrypicker.NameDisplay.DIAL_CODE
import com.hanggrian.countrypicker.bottomsheet.CountryPickerSheetDialog

class RegistrationActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var buttonContainer: ViewGroup
    private lateinit var buttonImage: ImageView
    private lateinit var buttonText: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        editText = findViewById(R.id.editText)
        buttonContainer = findViewById(R.id.button_container)
        buttonImage = findViewById(R.id.button_image)
        buttonText = findViewById(R.id.button_text)

        editText.setOnClickListener {
            CountryPickerSheetDialog
                .Builder(this)
                .setFlagDisplay(CUSTOM)
                .setNameDisplay(DIAL_CODE)
                .setOnSelectedListener {
                    editText.setText("${it.getName(this)} (${it.dialCode})")
                    buttonContainer.background =
                        ContextCompat.getDrawable(this, R.drawable.bg_gradient)
                    buttonImage.isEnabled = true
                    buttonText.isEnabled = true
                }.show()
        }

        WindowCompat
            .getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
    }
}
