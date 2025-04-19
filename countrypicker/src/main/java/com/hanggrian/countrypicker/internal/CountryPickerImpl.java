package com.hanggrian.countrypicker.internal;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.hanggrian.countrypicker.Country;
import com.hanggrian.countrypicker.CountryPicker;
import com.hanggrian.countrypicker.CountryPickerLayout;
import com.hanggrian.countrypicker.FlagDisplay;
import com.hanggrian.countrypicker.NameDisplay;
import com.hanggrian.countrypicker.R;
import java.util.Arrays;
import java.util.List;

public final class CountryPickerImpl implements CountryPicker {
    private final Dialog dialog;
    private final CountryPickerLayout picker;

    public CountryPickerImpl(Dialog dialog, Context context) {
        this.dialog = dialog;
        this.picker = new CountryPickerLayout(context);
    }

    @NonNull
    @Override
    public CountryPickerLayout getLayout() {
        return picker;
    }

    @NonNull
    @Override
    public List<Country> getItems() {
        return picker.getAdapter().getCountries();
    }

    @Override
    public void setItems(@NonNull List<Country> countries) {
        picker.<RecyclerView>findViewById(R.id.countrypicker_list).getRecycledViewPool().clear();
        picker.getAdapter().setCountries(countries);
    }

    @Override
    public void setItems(@NonNull Country... countries) {
        setItems(Arrays.asList(countries));
    }

    @NonNull
    @Override
    public FlagDisplay getFlagDisplay() {
        return picker.getAdapter().getFlagDisplay();
    }

    @Override
    public void setFlagDisplay(@NonNull FlagDisplay display) {
        picker.<RecyclerView>findViewById(R.id.countrypicker_list).getRecycledViewPool().clear();
        picker.getAdapter().setFlagDisplay(display);
    }

    @NonNull
    @Override
    public NameDisplay getNameDisplay() {
        return picker.getAdapter().getNameDisplay();
    }

    @Override
    public void setNameDisplay(@NonNull NameDisplay display) {
        picker.<RecyclerView>findViewById(R.id.countrypicker_list).getRecycledViewPool().clear();
        picker.getAdapter().setNameDisplay(display);
    }

    @Override
    public void setOnSelectedListener(
        @Nullable final CountryPickerLayout.OnSelectedListener listener
    ) {
        picker.getAdapter().setListener(
            listener == null
                ? null
                : country -> {
                listener.onSelected(country);
                dialog.dismiss();
            }
        );
    }
}
