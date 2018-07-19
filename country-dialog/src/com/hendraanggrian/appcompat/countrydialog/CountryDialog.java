package com.hendraanggrian.appcompat.countrydialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.Window.FEATURE_NO_TITLE;

public class CountryDialog extends AppCompatDialog {

    private OnSelectedListener listener;
    private final CountryAdapter adapter;

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    public CountryDialog(@NonNull Context context) {
        this(context, true, false);
    }

    public CountryDialog(@NonNull Context context, boolean showFlags, boolean showDialCode) {
        this(context, Arrays.asList(Country.values()), showFlags, showDialCode);
    }

    public CountryDialog(@NonNull Context context, @NonNull List<Country> countries, boolean showFlags, boolean showDialCode) {
        super(context);
        supportRequestWindowFeature(FEATURE_NO_TITLE);
        adapter = new CountryAdapter(context, countries, showFlags, showDialCode);
    }

    public CountryDialog(@NonNull Context context, @StyleRes int theme) {
        this(context, theme, true, false);
    }

    public CountryDialog(@NonNull Context context, @StyleRes int theme, boolean showFlags, boolean showDialCode) {
        this(context, theme, Arrays.asList(Country.values()), showFlags, showDialCode);
    }

    public CountryDialog(@NonNull Context context, @StyleRes int theme, @NonNull List<Country> countries, boolean showFlags, boolean showDialCode) {
        super(context, theme);
        supportRequestWindowFeature(FEATURE_NO_TITLE);
        adapter = new CountryAdapter(context, countries, showFlags, showDialCode);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countrydialog_content);

        toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(android.R.id.list);
        if (recyclerView == null) {
            throw new IllegalStateException();
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @NonNull
    public CountryAdapter getAdapter() {
        return adapter;
    }

    @NonNull
    public Toolbar getToolbar() {
        return toolbar;
    }

    @NonNull
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setOnSelectedListener(@Nullable OnSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnSelectedListener {

        void onSelected(@NonNull Country country);
    }

    public static class Builder {
        @NonNull private final Context context;
        @NonNull private final List<Country> countries = new ArrayList<>(Arrays.asList(Country.values())); // ensure collection is mutable
        private boolean showFlags = true;
        private boolean showDialCode = false;

        @Nullable private String title;
        @Nullable private OnSelectedListener listener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        @NonNull
        public Builder setItems(@NonNull Collection<Country> countries) {
            this.countries.clear();
            this.countries.addAll(countries);
            return this;
        }

        @NonNull
        public Builder exclude(@NonNull Country... countries) {
            if (countries.length > 0) {
                this.countries.removeAll(Arrays.asList(countries));
            }
            return this;
        }

        @NonNull
        public Builder showFlags(boolean showFlags) {
            this.showFlags = showFlags;
            return this;
        }

        @NonNull
        public Builder showDialCode(boolean showCallingCode) {
            this.showDialCode = showCallingCode;
            return this;
        }

        @NonNull
        public Builder setTitle(@Nullable String title) {
            this.title = title;
            return this;
        }

        @NonNull
        public Builder setTitle(@StringRes int title) {
            return setTitle(context.getString(title));
        }

        @NonNull
        public Builder setOnSelectedListener(@Nullable OnSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        @NonNull
        public CountryDialog build() {
            CountryDialog dialog = new CountryDialog(context, countries, showFlags, showDialCode);
            if (!TextUtils.isEmpty(title)) {
                // dialog.getToolbar().setTitle(title);
            }
            return dialog;
        }

        @NonNull
        public CountryDialog show() {
            CountryDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}