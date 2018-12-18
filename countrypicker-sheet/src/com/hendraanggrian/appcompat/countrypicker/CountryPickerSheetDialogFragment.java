package com.hendraanggrian.appcompat.countrypicker;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CountryPickerSheetDialogFragment extends BottomSheetDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new CountryPickerSheetDialog(
            Preconditions.checkNotNull(getContext(), "Fragment is not yet attached"),
            getTheme()
        );
    }
}