![logo](/art/logo.png) CountryPickerDialog
==========================================
Material design dialog to pick country.

![Simple](/art/ss_feature_simple.png)
![With Country Code](/art/ss_feature_showingcountrycode.png)
![Customized Scroller](/art/ss_feature_customizedscroller.png)

Download
--------
#### Core
The `core` module displays country flag with emoji by default, although it can be modified to use your own flag images.
```gradle
compile 'io.github.hendraanggrian:countrypickerdialog-core:0.2.0'
```
#### Commons
The `commons` module has all `core` module components complete with customized flag images.
```gradle
compile 'io.github.hendraanggrian:countrypickerdialog-commons:0.2.0'
```

Core
----
![Core](/art/ss_type_core.png)

Use 'CountryPickerDialog.Builder' to show 'CountryPickerDialog'.
```java
new CountryPickerDialog.Builder(context, "Pick country")
    .onPicked(new CountryPickerDialog.OnPickedListener() {
        @Override
        public void onPicked(Country country) {
            // do something
        }
    })
    .show();
```

All customization properties that can be applied:
```java
new CountryPickerDialog.Builder(context, title)
    .showDialCode(true)                     // shows country name with dial code, default is false
    .exclude(Country.ID, Country.US)        // exclude some countries on the list
    .cancellable(true)                      // default is false
    .scrollerThumbColor(color)              // default is colorAccent of your theme
    .scrollerTrackColor(color)              // default is transparent
    .scrollerPopupBackgroundColor(color)    // default is colorAccent of your theme
    .scrollerPopupTextColor(color)          // default is colorControlNormal of your theme
    .scrollerPopupTextSize(14, true)        // use false to use 14px or true to use 14dp
    .scrollerPopupTextTypeface(typeface)
    .scrollerAutoHide(false, 0)             // default is true with 1500ms delay
    .onPicked(listener)
    .show();
```

To use custom flag images, have a drawable with name format `flag_{2-digit iso code}` in your project.
For example if you want to display US flag, the drawable name should be `flag_us`.

See ![Country.java](/core/ss_type_core.png) for all available country codes.
