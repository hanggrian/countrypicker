package com.example.static

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.R

class CompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowCompat
            .getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
    }
}
