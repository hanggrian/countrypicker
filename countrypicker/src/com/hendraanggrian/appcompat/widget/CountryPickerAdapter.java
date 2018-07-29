package com.hendraanggrian.appcompat.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.hendraanggrian.appcompat.countrypicker.Country;
import com.hendraanggrian.appcompat.countrypicker.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryPickerAdapter extends RecyclerView.Adapter<CountryPickerAdapter.ViewHolder>
        implements Comparator<Country>, Filterable {

    private final static int TYPE_TEXT = 0;
    private final static int TYPE_TEXT_IMAGE = 1;
    private final static int TYPE_TEXT_EMOJI = 2;
    private final static int TYPE_EMPTY = 3;

    private final Context context;
    private List<Country> countries;
    private boolean isShowFlag = CountryPicker.DEFAULT_SHOW_FLAG;
    private boolean isShowDial = CountryPicker.DEFAULT_SHOW_DIAL;

    public CountryPicker.OnSelectedListener listener;
    private Filter filter;
    private List<Country> filteredCountries;

    CountryPickerAdapter(@NonNull final Context context) {
        this.context = context;
        replaceItems(new ArrayList<>(Arrays.asList(Country.values())));
    }

    public void setItems(@NonNull List<Country> countries) {
        replaceItems(countries);
        notifyDataSetChanged();
    }

    public void setShowFlag(boolean isShowFlag) {
        this.isShowFlag = isShowFlag;
        notifyDataSetChanged();
    }

    public void setShowDial(boolean isShowDial) {
        this.isShowDial = isShowDial;
        notifyDataSetChanged();
    }

    private void replaceItems(List<Country> countries) {
        Collections.sort(countries, this);
        this.countries = countries;
        this.filteredCountries = countries;
    }

    @NonNull
    @Override
    public CountryPickerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_TEXT:
                return new TextHolder(inflater.inflate(R.layout.countrypicker_item_text, parent, false));
            case TYPE_TEXT_IMAGE:
                return new TextImageHolder(inflater.inflate(R.layout.countrypicker_item_image, parent, false));
            case TYPE_TEXT_EMOJI:
                return new TextEmojiHolder(inflater.inflate(R.layout.countrypicker_item_emoji, parent, false));
            default:
                return new ViewHolder(inflater.inflate(R.layout.countrypicker_item_empty, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (!(holder instanceof TextHolder)) {
            return;
        }
        final Country country = filteredCountries.get(position);
        if (holder instanceof TextImageHolder) {
            ((TextImageHolder) holder).imageView.setImageResource(country.getFlagDrawableRes(context));
        } else if (holder instanceof TextEmojiHolder) {
            ((TextEmojiHolder) holder).emojiView.setText(country.getFlagEmoji());
        }
        ((TextHolder) holder).textView.setText(isShowDial
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
        if (filteredCountries.isEmpty()) {
            return TYPE_EMPTY;
        }
        return !isShowFlag ? TYPE_TEXT : filteredCountries.get(position).isFlagDrawableAvailable(context)
                ? TYPE_TEXT_IMAGE
                : TYPE_TEXT_EMOJI;
    }

    @Override
    public int getItemCount() {
        if (filteredCountries.isEmpty()) {
            return 1;
        }
        return filteredCountries.size();
    }

    @Override
    public int compare(Country country, Country other) {
        return country.getName(context).compareTo(other.getName(context));
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    final List<Country> values;
                    String s = charSequence.toString();
                    if (s.isEmpty()) {
                        values = countries;
                    } else {
                        List<Country> filteredList = new ArrayList<>();
                        for (Country country : countries) {
                            final Locale locale = country.toLocale(context);
                            if (country.getName(context).toLowerCase(locale).contains(s.toLowerCase(locale))) {
                                filteredList.add(country);
                            }
                        }
                        values = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = values;
                    return filterResults;
                }

                @Override
                @SuppressWarnings("unchecked")
                protected void publishResults(CharSequence charSequence, FilterResults results) {
                    filteredCountries = (List<Country>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
        return filter;
    }

    static final class TextImageHolder extends TextHolder {
        final ImageView imageView;

        TextImageHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(android.R.id.icon);
        }
    }

    static final class TextEmojiHolder extends TextHolder {
        final TextView emojiView;

        TextEmojiHolder(View itemView) {
            super(itemView);
            emojiView = itemView.findViewById(android.R.id.text2);
        }
    }

    static class TextHolder extends ViewHolder {
        final TextView textView;

        TextHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}