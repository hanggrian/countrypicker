package com.hendraanggrian.countrydialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public abstract class CountryAdapter extends FastScrollRecyclerView.Adapter<CountryAdapter.TextHolder> implements CountryDialog.OnSelectedListener, FastScrollRecyclerView.SectionedAdapter {

    private final static int TYPE_TEXT = 1;
    private final static int TYPE_IMAGE = 2;
    private final static int TYPE_EMOJI = 3;

    @NonNull private final Context context;
    @NonNull private final List<Country> countries;
    private final boolean showFlags;
    private final boolean showDialCode;

    public CountryAdapter(@NonNull Context context) {
        this(context, true, false);
    }

    public CountryAdapter(@NonNull Context context, boolean showFlags, boolean showDialCode) {
        this(context, Arrays.asList(Country.values()), showFlags, showDialCode);
    }

    public CountryAdapter(@NonNull Context context, @NonNull List<Country> countries, boolean showFlags, boolean showDialCode) {
        this.context = context;
        this.countries = countries;
        this.showFlags = showFlags;
        this.showDialCode = showDialCode;
    }

    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TEXT:
                return new TextHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_country_text, parent, false));
            case TYPE_EMOJI:
                return new EmojiHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_country_emoji, parent, false));
            default:
                return new ImageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_country_image, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final TextHolder textHolder, int position) {
        textHolder.viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelected(countries.get(textHolder.getAdapterPosition()));
            }
        });
        textHolder.textViewName.setText(showDialCode
                ? String.format("%s (%s)", countries.get(position).getName(context), countries.get(position).getDialCode())
                : String.format("%s", countries.get(position).getName(context)));

        if (textHolder instanceof ImageHolder)
            ((ImageHolder) textHolder).imageViewFlag.setImageResource(countries.get(position).getFlagDrawableRes(context));
        else if (textHolder instanceof EmojiHolder)
            ((EmojiHolder) textHolder).textViewFlag.setText(countries.get(position).getFlagEmoji());
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

    @NonNull
    @Override
    public String getSectionName(int position) {
        return countries.get(position).getName(context).substring(0, 1).toUpperCase(Locale.ENGLISH);
    }

    protected static final class ImageHolder extends TextHolder {
        private final ImageView imageViewFlag;

        ImageHolder(View itemView) {
            super(itemView);
            imageViewFlag = (ImageView) itemView.findViewById(com.hendraanggrian.countrydialog.R.id.imageview_country_flag);
        }
    }

    protected static final class EmojiHolder extends TextHolder {
        private final TextView textViewFlag;

        EmojiHolder(View itemView) {
            super(itemView);
            textViewFlag = (TextView) itemView.findViewById(com.hendraanggrian.countrydialog.R.id.textview_country_flag);
        }
    }

    protected static class TextHolder extends RecyclerView.ViewHolder {
        private final ViewGroup viewGroup;
        private final TextView textViewName;

        TextHolder(View itemView) {
            super(itemView);
            viewGroup = (ViewGroup) itemView.findViewById(com.hendraanggrian.countrydialog.R.id.viewgroup_country);
            textViewName = (TextView) itemView.findViewById(com.hendraanggrian.countrydialog.R.id.textview_country_name);
        }
    }
}