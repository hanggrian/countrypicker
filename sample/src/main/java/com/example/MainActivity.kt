package com.example

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment())
            .commitNow()

        WindowCompat
            .getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK !=
            Configuration.UI_MODE_NIGHT_YES
    }
}
