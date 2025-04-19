package com.hanggrian.countrypicker.bottomsheet;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.hanggrian.countrypicker.bottomsheet.test.R;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Material3_DayNight_NoActionBar);
    }
}
