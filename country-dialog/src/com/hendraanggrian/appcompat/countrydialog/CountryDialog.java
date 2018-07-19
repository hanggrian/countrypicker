package com.hendraanggrian.appcompat.countrydialog;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountryDialog extends AppCompatDialog {

    private OnSelectedListener listener;
    private final RecyclerView recyclerView;

    public CountryDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CountryDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);

        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        /*recyclerView.setAdapter(new CountryAdapter(getContext(), builder.countries, builder.showFlags, builder.showDialCode) {
            @Override
            public void onBindViewHolder(@NonNull final TextHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.viewGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onSelected(countries.get(holder.getAdapterPosition()));
                        }
                        dismiss();
                    }
                });
            }
        });*/

        /*if (TextUtils.isEmpty(title)) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(recyclerView);
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
        @NonNull private final List<Country> countries;
        private boolean showFlags = true;
        private boolean showDialCode = false;
        @Nullable private String title;
        @Nullable private OnSelectedListener listener;

        public Builder(@NonNull Context context) {
            this(context, Arrays.asList(Country.values()));
        }

        public Builder(@NonNull Context context, @NonNull Collection<Country> countries) {
            this.context = context;
            this.countries = new ArrayList<>(countries); // ensure collection is mutable
        }

        @NonNull
        public Builder title(@Nullable String title) {
            this.title = title;
            return this;
        }

        @NonNull
        public Builder exclude(@NonNull String... isoCodes) {
            for (String isoCode : isoCodes)
                this.countries.remove(Country.fromIsoCode(isoCode));
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
        public Builder onSelected(OnSelectedListener listener) {
            this.listener = listener;
            return this;
        }

        @NonNull
        public CountryDialog build() {
            return new CountryDialog(context);
        }

        @NonNull
        public CountryDialog show() {
            CountryDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}