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
