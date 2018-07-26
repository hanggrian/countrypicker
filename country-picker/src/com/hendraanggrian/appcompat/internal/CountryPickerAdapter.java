package com.hendraanggrian.appcompat.internal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.hendraanggrian.appcompat.app.CountryPickerDialog;
import com.hendraanggrian.appcompat.countrypicker.Country;
import com.hendraanggrian.appcompat.countrypicker.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

@RestrictTo(LIBRARY_GROUP)
public class CountryPickerAdapter extends RecyclerView.Adapter<CountryPickerAdapter.TextHolder> implements Filterable {

    private final static int TYPE_TEXT = 1;
    private final static int TYPE_IMAGE = 2;
    private final static int TYPE_EMOJI = 3;

    private final Context context;
    private final List<Country> countries;
    private final boolean isShowFlag;
    private final boolean isShowDialCode;

    public CountryPickerDialog.OnSelectedListener listener;
    private WeakReference<Filter> filterRef = new WeakReference<>(null);
    private List<Country> filteredCountries;

    public CountryPickerAdapter(@NonNull final Context context, @NonNull List<Country> countries, boolean isShowFlag, boolean isShowDialCode) {
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName(context).compareTo(o2.getName(context));
            }
        });
        this.context = context;
        this.countries = countries;
        this.isShowFlag = isShowFlag;
        this.isShowDialCode = isShowDialCode;
        this.filteredCountries = countries;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_TEXT:
                return new TextHolder(inflater.inflate(R.layout.countrydialog_item_text, parent, false));
            case TYPE_EMOJI:
                return new EmojiHolder(inflater.inflate(R.layout.countrydialog_item_emoji, parent, false));
            default:
                return new ImageHolder(inflater.inflate(R.layout.countrydialog_item_image, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final TextHolder holder, int position) {
        final Country country = filteredCountries.get(position);
        if (holder instanceof ImageHolder) {
            ((ImageHolder) holder).flagView.setImageResource(country.getFlagDrawableRes(context));
        } else if (holder instanceof EmojiHolder) {
            ((EmojiHolder) holder).flagView.setText(country.getFlagEmoji());
        }
        holder.textView.setText(isShowDialCode
                ? String.format("%s (%s)", country.getName(context), country.getDial())
                : country.getName(context));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onSelected(country);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return !isShowFlag ? TYPE_TEXT : filteredCountries.get(position).isFlagDrawableAvailable(context)
                ? TYPE_IMAGE
                : TYPE_EMOJI;
    }

    @Override
    public int getItemCount() {
        return filteredCountries.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = filterRef.get();
        if (filter == null) {
            filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String s = charSequence.toString();
                    if (s.isEmpty()) {
                        filteredCountries = countries;
                    } else {
                        List<Country> filteredList = new ArrayList<>();
                        for (Country country : countries) {
                            final Locale locale = country.toLocale(context);
                            if (country.getName(context).toLowerCase(locale).contains(s.toLowerCase(locale))) {
                                filteredList.add(country);
                            }
                        }
                        filteredCountries = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = filteredCountries;
                    return filterResults;
                }

                @Override
                @SuppressWarnings("unchecked")
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filteredCountries = (ArrayList<Country>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
            filterRef = new WeakReference<>(filter);
        }
        return filter;
    }

    static final class ImageHolder extends TextHolder {
        final ImageView flagView;

        ImageHolder(View itemView) {
            super(itemView);
            flagView = itemView.findViewById(android.R.id.icon);
        }
    }

    static final class EmojiHolder extends TextHolder {
        final TextView flagView;

        EmojiHolder(View itemView) {
            super(itemView);
            flagView = itemView.findViewById(android.R.id.text2);
        }
    }

    static class TextHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        TextHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}