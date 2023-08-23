package com.hendraanggrian.material.countrypicker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import androidx.appcompat.app.AppCompatActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.internal.DoNotInstrument;

@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class CountryPickerDialogTest {
  private AppCompatActivity activity;

  @Before
  public void setup() {
    activity = Robolectric.buildActivity(TestActivity.class).setup().get();
  }

  @Test
  public void defaultProperties() {
    CountryPicker picker = new CountryPickerDialog.Builder(activity).build();
    assertFalse(picker.getItems().isEmpty());
    assertEquals(FlagDisplay.DEFAULT, picker.getFlagDisplay());
    assertEquals(NameDisplay.DEFAULT, picker.getNameDisplay());
  }

  @Test
  public void setItems() {
    CountryPicker picker = new CountryPickerDialog.Builder(activity)
        .setItems(Country.US)
        .build();
    assertEquals(Country.US, picker.getItems().get(0));
  }

  @Test
  public void setFlagDisplay() {
    CountryPicker picker = new CountryPickerDialog.Builder(activity)
        .setFlagDisplay(FlagDisplay.CUSTOM)
        .build();
    assertEquals(FlagDisplay.CUSTOM, picker.getFlagDisplay());
  }

  @Test
  public void setNameDisplay() {
    CountryPicker picker = new CountryPickerDialog.Builder(activity)
        .setNameDisplay(NameDisplay.ISO_CODE)
        .build();
    assertEquals(NameDisplay.ISO_CODE, picker.getNameDisplay());
  }
}
