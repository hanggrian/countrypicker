package com.hendraanggrian.appcompat.countrypicker;

import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface CountryPickerComponent {

    @NonNull
    CountryPicker getPicker();

    void setItems(@NonNull List<Country> countries);

    void setFlagShown(boolean shown);

    void setOnSelectedListener(@Nullable CountryPicker.OnSelectedListener listener);
}