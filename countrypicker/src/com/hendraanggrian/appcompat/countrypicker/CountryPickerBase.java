package com.hendraanggrian.appcompat.countrypicker;

import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Base interface for country picker popups.
 */
public interface CountryPickerBase {

    /**
     * @return picker view.
     */
    @NonNull
    CountryPicker getPicker();

    /**
     * Populate countries to picker.
     *
     * @param countries to set, can't be null.
     */
    void setItems(@NonNull List<Country> countries);

    /**
     * Set true to display flag alongside country name, default is false.
     *
     * @param shown true to display.
     */
    void setFlagShown(boolean shown);

    /**
     * Set listener to be called when a country is selected.
     *
     * @param listener to invoke, may be null.
     */
    void setOnSelectedListener(@Nullable CountryPicker.OnSelectedListener listener);
}