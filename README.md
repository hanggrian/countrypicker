Android Country Picker Dialog
=============================

Material design dialog to pick country.

<img width="256" src="https://raw.githubusercontent.com/hendraanggrian/country-picker-dialog/master/screenshot/simple.png">
<img width="256" src="https://raw.githubusercontent.com/hendraanggrian/country-picker-dialog/master/screenshot/showingcountrycode.png">
<img width="256" src="https://raw.githubusercontent.com/hendraanggrian/country-picker-dialog/master/screenshot/customizedscroller.png">

Download
--------

```gradle
compile 'io.github.hendraanggrian:country-picker-dialog:0.1.0'
```


Usage
-----



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


Acknowledges
------------

Thanks to:

 * [FastScroll](https://github.com/FutureMind/recycler-fast-scroll) for its awesome scroller.
 * [Android-country-picker](https://github.com/heetch/Android-country-picker) for its country data.
