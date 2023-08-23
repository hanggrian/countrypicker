package com.hendraanggrian.material.countrypicker;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.l4digital.fastscroll.FastScroller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class CountryPickerAdapter extends RecyclerView.Adapter<CountryPickerAdapter.ViewHolder>
    implements FastScroller.SectionIndexer, Comparator<Country>, Filterable {

  private final static int TYPE_TEXT = 0;
  private final static int TYPE_TEXT_IMAGE = 1;
  private final static int TYPE_TEXT_EMOJI = 2;
  private final static int TYPE_EMPTY = 3;

  private final Context context;
  private List<Country> filteredCountries;
  private Filter filter;

  List<Country> countries;
  FlagDisplay flagDisplay = FlagDisplay.DEFAULT;
  NameDisplay nameDisplay = NameDisplay.DEFAULT;
  CountryPickerLayout.OnSelectedListener selectedListener;

  CountryPickerAdapter(@NonNull Context context) {
    this.context = context;
    replaceItems(new ArrayList<>(Arrays.asList(Country.values())));
  }

  public void setItems(@NonNull List<Country> countries) {
    replaceItems(countries);
    notifyDataSetChanged();
  }

  public void setFlagDisplay(@NonNull FlagDisplay display) {
    flagDisplay = display;
    notifyDataSetChanged();
  }

  public void setNameDisplay(@NonNull NameDisplay display) {
    nameDisplay = display;
    notifyDataSetChanged();
  }

  public void setListener(@Nullable CountryPickerLayout.OnSelectedListener listener) {
    selectedListener = listener;
  }

  private void replaceItems(List<Country> countries) {
    Collections.sort(countries, this);
    this.countries = countries;
    this.filteredCountries = countries;
  }

  @NonNull
  @Override
  public CountryPickerAdapter.ViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent,
      int viewType
  ) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    switch (viewType) {
      case TYPE_TEXT:
        return new TextHolder(inflater.inflate(R.layout.countrypicker_item_text,
          parent, false));
      case TYPE_TEXT_IMAGE:
        return new TextImageHolder(inflater.inflate(R.layout.countrypicker_item_image,
          parent, false));
      case TYPE_TEXT_EMOJI:
        return new TextEmojiHolder(inflater.inflate(R.layout.countrypicker_item_emoji,
          parent, false));
      default:
        return new ViewHolder(inflater.inflate(R.layout.countrypicker_item_empty,
          parent, false));
    }
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    if (!(holder instanceof TextHolder)) {
      return;
    }
    final Country country = getItem(position);

    if (holder instanceof TextImageHolder) {
      ((TextImageHolder) holder).imageView.setImageResource(country.getFlagDrawableRes(context));
    } else if (holder instanceof TextEmojiHolder) {
      ((TextEmojiHolder) holder).emojiView.setText(country.getFlagSymbols());
    }

    switch (nameDisplay) {
      case DIAL_CODE -> ((TextHolder) holder).textView
        .setText(String.format("%s (%s)", country.getName(context), country.getDialCode()));
      case ISO_CODE -> ((TextHolder) holder).textView
        .setText(String.format("%s (%s)", country.getName(context), country.getIsoCode()));
      default -> ((TextHolder) holder).textView.setText(country.getName(context));
    }

    holder.itemView.setOnClickListener(view -> {
      if (selectedListener != null) {
        selectedListener.onSelected(country);
      }
    });
  }

  @Override
  public int getItemViewType(int position) {
    if (filteredCountries.isEmpty()) {
      return TYPE_EMPTY;
    }
    switch (flagDisplay) {
      case DEFAULT -> {
        // emoji support is after 21
        return Build.VERSION.SDK_INT < 21 ? TYPE_TEXT : TYPE_TEXT_EMOJI;
      }
      case CUSTOM -> {
        if (filteredCountries.get(position).isFlagDrawableAvailable(context)) {
          return TYPE_TEXT_IMAGE;
        }
        // emoji support is after 21
        return Build.VERSION.SDK_INT < 21 ? TYPE_TEXT : TYPE_TEXT_EMOJI;
      }
      default -> {
        return TYPE_TEXT;
      }
    }
  }

  @Override
  public int getItemCount() {
    if (filteredCountries.isEmpty()) {
      return 1;
    }
    return filteredCountries.size();
  }

  @Override
  public CharSequence getSectionText(int position) {
    return String.valueOf(getItem(position).getName(context).charAt(0));
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
          final String s = charSequence.toString();
          if (s.isEmpty()) {
            values = countries;
          } else {
            final List<Country> filteredList = new ArrayList<>();
            for (final Country country : countries) {
              final Locale locale = country.getLocale(context);
              if (country.getName(context).toLowerCase(locale).contains(s.toLowerCase(locale))) {
                filteredList.add(country);
              }
            }
            values = filteredList;
          }

          final FilterResults filterResults = new FilterResults();
          filterResults.values = values;
          return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
          filteredCountries = (List<Country>) results.values;
          notifyDataSetChanged();
        }
      };
    }
    return filter;
  }

  private Country getItem(int index) {
    return filteredCountries.get(index);
  }

  static class TextImageHolder extends TextHolder {
    final ImageView imageView;

    TextImageHolder(View itemView) {
      super(itemView);
      imageView = itemView.findViewById(android.R.id.icon);
    }
  }

  static class TextEmojiHolder extends TextHolder {
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
