package com.hendraanggrian.appcompat.countrypicker;

import com.hendraanggrian.appcompat.widget.CountryPicker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

abstract class CountryPickerComponentImpl implements CountryPickerComponent {

    abstract void onDismiss();

    private final CountryPicker picker;

    CountryPickerComponentImpl(CountryPicker picker) {
        this.picker = picker;
    }

    @NonNull
    @Override
    public CountryPicker getPicker() {
        if (picker == null) {
            throw new IllegalStateException("Dialog must be inflated first.");
        }
        return picker;
    }

    @Override
    public void setItems(@NonNull List<Country> countries) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setItems(countries);
    }

    @Override
    public void setFlagShown(boolean shown) {
        picker.getRecyclerView().getRecycledViewPool().clear();
        picker.getAdapter().setFlagShown(shown);
    }

    @Override
    public void setOnSelectedListener(@Nullable final CountryPicker.OnSelectedListener listener) {
        picker.getAdapter().setListener(listener == null ? null : new CountryPicker.OnSelectedListener() {
            @Override
            public void onSelected(@NonNull Country country) {
                listener.onSelected(country);
                onDismiss();
            }
        });
    }
}