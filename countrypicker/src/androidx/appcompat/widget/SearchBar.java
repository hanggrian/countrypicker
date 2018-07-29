package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.hendraanggrian.appcompat.countrypicker.R;

import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;

import static android.text.InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
import static android.widget.ImageView.ScaleType.CENTER;

/**
 * A hacked {@link SearchView} that mimics search bar in Android Settings app API 28.
 */
public final class SearchBar extends SearchView {

    private final View mSearchEditFrame;
    private final View mSearchPlate;
    private final View mSubmitArea;
    private final ImageView mCollapsedIcon;

    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.searchViewStyle);
    }

    @SuppressLint("PrivateResource")
    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mSearchEditFrame = findViewById(androidx.appcompat.R.id.search_edit_frame);
        mSearchPlate = findViewById(androidx.appcompat.R.id.search_plate);
        mSubmitArea = findViewById(androidx.appcompat.R.id.submit_area);
        mCollapsedIcon = findViewById(androidx.appcompat.R.id.search_mag_icon);

        // Set up icons and backgrounds.
        Drawable transparent = new ColorDrawable(getResources().getColor(android.R.color.transparent));
        ViewCompat.setBackground(mSearchPlate, transparent);
        ViewCompat.setBackground(mSubmitArea, transparent);

        mSearchSrcTextView.setInputType(TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        // First, remove horizontal paddings to get full width.
        MarginLayoutParams frameLp = (MarginLayoutParams) mSearchEditFrame.getLayoutParams();
        frameLp.leftMargin = 0;
        frameLp.rightMargin = 0;

        MarginLayoutParams textLp = (MarginLayoutParams) mSearchSrcTextView.getLayoutParams();
        textLp.leftMargin = getResources().getDimensionPixelSize(R.dimen.countrydialog_searchbar_search_leftmargin);

        // Buttons are wider in Google Search app.
        mCloseButton.setScaleType(CENTER);
        mCloseButton.getLayoutParams().width = getDimenAttr(context, android.R.attr.actionBarSize);

        final ColorStateList colorAccent = ColorStateList.valueOf(getColorAttr(getContext(), R.attr.colorAccent));
        mSearchSrcTextView.setTextColor(colorAccent);
        ImageViewCompat.setImageTintList(mGoButton, colorAccent);
        ImageViewCompat.setImageTintList(mSearchButton, colorAccent);
        ImageViewCompat.setImageTintList(mCloseButton, colorAccent);
        ImageViewCompat.setImageTintList(mVoiceButton, colorAccent);
        ImageViewCompat.setImageTintList(mCollapsedIcon, colorAccent);
    }

    private static int getColorAttr(Context context, int attrId) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, typedValue, true);
        return typedValue.data;
    }

    private static int getDimenAttr(Context context, int attrId) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrId, typedValue, true);
        final int[] textSizeAttr = new int[]{attrId};
        TypedArray a = context.obtainStyledAttributes(typedValue.data, textSizeAttr);
        final int value = a.getDimensionPixelSize(0, 0);
        a.recycle();
        return value;
    }
}