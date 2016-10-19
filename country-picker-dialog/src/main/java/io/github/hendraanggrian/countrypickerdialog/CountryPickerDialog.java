package io.github.hendraanggrian.countrypickerdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import io.github.hendraanggrian.countrypicker.R;

/**
 * Created by GODARD Tuatini on 07/05/15.
 */
public class CountryPickerDialog extends AppCompatDialog {

    private final CountryRecyclerAdapter adapter;

    private CountryPickerDialog(Context context, String title, boolean showDialingCode, OnPickedListener listener) {
        super(context);
        List<Country> countries = new ArrayList<>();
        for (String line : context.getResources().getStringArray(R.array.countries))
            countries.add(new Country(context, line));
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                final Locale locale = getContext().getResources().getConfiguration().locale;
                final Collator collator = Collator.getInstance(locale);
                collator.setStrength(Collator.PRIMARY);
                return collator.compare(
                        new Locale(locale.getLanguage(), country1.getIsoCode()).getDisplayCountry(),
                        new Locale(locale.getLanguage(), country2.getIsoCode()).getDisplayCountry());
            }
        });

        this.adapter = new CountryRecyclerAdapter(countries, showDialingCode, listener, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_country_picker);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_country);
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
        }
    }

    public interface OnPickedListener {
        void onPicked(Country country);
    }

    public static class Builder {
        private Context context;
        private String title;
        private boolean showCallingCode = false;
        private OnPickedListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder showCallingCode(boolean showCallingCode) {
            this.showCallingCode = showCallingCode;
            return this;
        }

        public Builder onPicked(OnPickedListener listener) {
            this.listener = listener;
            return this;
        }

        public CountryPickerDialog build() {
            return new CountryPickerDialog(context, title, showCallingCode, listener);
        }

        public CountryPickerDialog show() {
            CountryPickerDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}