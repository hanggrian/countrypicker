Android Country Picker Dialog
=============================

Material design dialog to pick country.

[simple] [showingcountrycode] [customizedscroller]

[simple]: https://raw.githubusercontent.com/hendraanggrian/country-picker-dialog/master/screenshot/simple.png "Simple"
[showingcountrycode]: https://raw.githubusercontent.com/hendraanggrian/country-picker-dialog/master/screenshot/showingcountrycode.png "Showing country code"
[customizedscroller]: https://raw.githubusercontent.com/hendraanggrian/country-picker-dialog/master/screenshot/customizedscroller.png "Customized scroller"


Download
--------

```gradle
compile 'io.github.hendraanggrian:country-picker-dialog:0.1.0'
```


Usage
-----

Request single or multiple permissions:

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