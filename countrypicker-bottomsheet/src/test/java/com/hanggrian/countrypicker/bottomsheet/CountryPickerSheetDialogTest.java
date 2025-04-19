package com.hanggrian.countrypicker.bottomsheet;

import androidx.appcompat.app.AppCompatActivity;
import com.hanggrian.countrypicker.Country;
import com.hanggrian.countrypicker.CountryPicker;
import com.hanggrian.countrypicker.FlagDisplay;
import com.hanggrian.countrypicker.NameDisplay;
import org.junit.Assert;
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
public class CountryPickerSheetDialogTest {
    private AppCompatActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(TestActivity.class).setup().get();
    }

    @Test
    public void defaultProperties() {
        CountryPicker picker = new CountryPickerSheetDialog.Builder(activity).build();
        assertFalse(picker.getItems().isEmpty());
        Assert.assertEquals(FlagDisplay.DEFAULT, picker.getFlagDisplay());
        Assert.assertEquals(NameDisplay.DEFAULT, picker.getNameDisplay());
    }

    @Test
    public void setItems() {
        CountryPicker picker =
            new CountryPickerSheetDialog.Builder(activity)
                .setItems(Country.US)
                .build();
        assertEquals(Country.US, picker.getItems().get(0));
    }

    @Test
    public void setFlagDisplay() {
        CountryPicker picker =
            new CountryPickerSheetDialog.Builder(activity)
                .setFlagDisplay(FlagDisplay.CUSTOM)
                .build();
        assertEquals(FlagDisplay.CUSTOM, picker.getFlagDisplay());
    }

    @Test
    public void setNameDisplay() {
        CountryPicker picker =
            new CountryPickerSheetDialog.Builder(activity)
                .setNameDisplay(NameDisplay.ISO_CODE)
                .build();
        assertEquals(NameDisplay.ISO_CODE, picker.getNameDisplay());
    }
}
