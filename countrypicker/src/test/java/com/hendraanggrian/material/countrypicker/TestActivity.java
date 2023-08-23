package com.hendraanggrian.material.countrypicker;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.hendraanggrian.material.countrypicker.test.R;

public class TestActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(R.style.Theme_MaterialComponents_DayNight_NoActionBar);
  }
}
