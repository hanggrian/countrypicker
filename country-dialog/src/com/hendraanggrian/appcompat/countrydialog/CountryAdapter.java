package com.hendraanggrian.appcompat.countrydialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.TextHolder> {

    private final static int TYPE_TEXT = 1;
    private final static int TYPE_IMAGE = 2;
    private final static int TYPE_EMOJI = 3;

    @NonNull protected final Context context;
    @NonNull protected final List<Country> countries;
    protected final boolean showFlags;
    protected final boolean showDialCode;

    public CountryAdapter(@NonNull final Context context, @NonNull List<Country> countries, boolean showFlags, boolean showDialCode) {
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName(context).compareTo(o2.getName(context));
            }
        });
        this.context = context;
        this.countries = countries;
        this.showFlags = showFlags;
        this.showDialCode = showDialCode;
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
        if (holder instanceof ImageHolder) {
            ((ImageHolder) holder).flagView.setImageResource(countries.get(position).getFlagDrawableRes(context));
        } else if (holder instanceof EmojiHolder) {
            ((EmojiHolder) holder).flagView.setText(countries.get(position).getFlagEmoji());
        }
        holder.textView.setText(showDialCode
                ? String.format("%s (%s)", countries.get(position).getName(context), countries.get(position).getDialCode())
                : countries.get(position).getName(context));
    }

    @Override
    public int getItemViewType(int position) {
        return !showFlags ? TYPE_TEXT : countries.get(position).isFlagDrawableAvailable(context)
                ? TYPE_IMAGE
                : TYPE_EMOJI;
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    protected static final class ImageHolder extends TextHolder {
        public final ImageView flagView;

        ImageHolder(View itemView) {
            super(itemView);
            flagView = itemView.findViewById(android.R.id.icon);
        }
    }

    protected static final class EmojiHolder extends TextHolder {
        public final TextView flagView;

        EmojiHolder(View itemView) {
            super(itemView);
            flagView = itemView.findViewById(android.R.id.text2);
        }
    }

    protected static class TextHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        TextHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}