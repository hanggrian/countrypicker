package com.hanggrian.countrypicker;

import androidx.appcompat.app.AppCompatActivity;
import com.hanggrian.countrypicker.test.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.internal.DoNotInstrument;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class CountryPickerLayoutTest {
    private AppCompatActivity activity;
    private CountryPickerLayout layout;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(TestActivity.class).setup().get();
        layout =
            (CountryPickerLayout) activity
                .getLayoutInflater()
                .inflate(R.layout.activity_test, null);
    }

    @Test
    public void defaultProperties() {
        assertFalse(layout.getAdapter().getCountries().isEmpty());
        assertEquals(FlagDisplay.DEFAULT, layout.getAdapter().getFlagDisplay());
        assertEquals(NameDisplay.DEFAULT, layout.getAdapter().getNameDisplay());
    }
}
