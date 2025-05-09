package com.example.dynamic

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import com.example.R

class DynamicActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, DynamicFragment())
            .commitNow()

        WindowCompat
            .getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK ==
            Configuration.UI_MODE_NIGHT_YES
    }
}
