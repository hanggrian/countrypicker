package com.hendraanggrian.material.countrypicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

/**
 * Base interface for country picker popups.
 */
public interface CountryPicker {
  /**
   * @return picker view.
   */
  @NonNull
  CountryPickerLayout getLayout();

  /**
   * Get country items registered in this picker, or empty if items are not yet set.
   *
   * @return countries, can't be null.
   */
  @NonNull
  List<Country> getItems();

  /**
   * Populate countries to picker.
   *
   * @param countries to set, can't be null.
   */
  void setItems(@NonNull List<Country> countries);

  /**
   * Convenient method to set items.
   *
   * @param countries to set, can't be null.
   */
  void setItems(@NonNull Country... countries);

  /**
   * Get flag image display behavior.
   *
   * @return display mode, can't be null.
   */
  @NonNull
  FlagDisplay getFlagDisplay();

  /**
   * Set flag image display behavior.
   *
   * @param display display mode, default is {@link FlagDisplay#DEFAULT}.
   */
  void setFlagDisplay(@NonNull FlagDisplay display);

  /**
   * Get name text display behavior.
   *
   * @return display mode, can't be null.
   */
  @NonNull
  NameDisplay getNameDisplay();

  /**
   * Set name text display behavior.
   *
   * @param display display mode, default is {@link NameDisplay#DEFAULT}.
   */
  void setNameDisplay(@NonNull NameDisplay display);

  /**
   * Set listener to be called when a country is selected.
   *
   * @param listener to invoke, may be null.
   */
  void setOnSelectedListener(@Nullable CountryPickerLayout.OnSelectedListener listener);
}
