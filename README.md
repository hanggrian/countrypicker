Android Country Picker Dialog
=============================

Material design dialog to pick country.

<img width="256" src="https://raw.githubusercontent.com/hendraanggrian/countrypickerdialog/master/screenshot/simple.png">
<img width="256" src="https://raw.githubusercontent.com/hendraanggrian/countrypickerdialog/master/screenshot/showingcountrycode.png">
<img width="256" src="https://raw.githubusercontent.com/hendraanggrian/countrypickerdialog/master/screenshot/customizedscroller.png">


Download
--------

```gradle
compile 'io.github.hendraanggrian:countrypickerdialog:0.1.1'
```

This library is dependent on [FastScroll](https://github.com/FutureMind/recycler-fast-scroll).
Therefore if you have `FastScroll` on your project, use this gradle instead:

```gradle
compile('io.github.hendraanggrian:countrypickerdialog:0.1.1') {
    transitive = false
}
```


Usage
-----

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
    .exclude("ID", "IN", "US")              // exclude some countries on the list
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