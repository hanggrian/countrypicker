package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.hendraanggrian.appcompat.countrydialog.R;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

import static android.text.InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
import static android.widget.ImageView.ScaleType.CENTER;

/**
 * A hacked SearchView that mimics Google's search bar.
 */
public final class SearchBar extends SearchView {

    private final View mSearchEditFrame;
    private final View mSearchPlate;
    private final View mSubmitArea;

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
        mCloseButton.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.countrydialog_searchbar_button_width);
    }

    @NonNull
    public EditText getEditText() {
        return mSearchSrcTextView;
    }
}