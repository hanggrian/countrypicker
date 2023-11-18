package com.hendraanggrian.material.countrypicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.os.Build;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.hendraanggrian.material.countrypicker.test.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.internal.DoNotInstrument;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP)
@DoNotInstrument
public class CountryPickerLayoutTest {
  private AppCompatActivity activity;
  private CountryPickerLayout layout;

  @Before
  public void setup() {
    activity = Robolectric.buildActivity(TestActivity.class).setup().get();
    layout = (CountryPickerLayout) activity.getLayoutInflater()
        .inflate(R.layout.activity_test, null);
  }

  @Test
  public void defaultProperties() {
    assertFalse(layout.getAdapter().countries.isEmpty());
    assertEquals(FlagDisplay.DEFAULT, layout.getAdapter().flagDisplay);
    assertEquals(NameDisplay.DEFAULT, layout.getAdapter().nameDisplay);
  }
}
