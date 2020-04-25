[![download](https://api.bintray.com/packages/hendraanggrian/appcompat/countrypicker/images/download.svg)](https://bintray.com/hendraanggrian/appcompat/countrypicker/_latestVersion)
[![build](https://travis-ci.com/hendraanggrian/countrypicker.svg)](https://travis-ci.com/hendraanggrian/countrypicker)
[![license](https://img.shields.io/github/license/hendraanggrian/countrypicker)](http://www.apache.org/licenses/LICENSE-2.0)

CountryPicker
=============
![demo1][demo1]
![demo2][demo2]

Material design components to pick country.

Download
--------
```gradle
repositories {
    google()
    jcenter()
}

dependencies {
    implementation "com.hendraanggrian.appcompat:countrypicker:$version"
    implementation "com.hendraanggrian.appcompat:countrypicker-sheet:$version"
}
```

Usage
-----

#### Dialog

Use 'CountryPickerDialog.Builder' to build or show 'CountryPickerDialog'.

```java
new CountryPickerDialog.Builder(context)
    .setOnSelectedListener(new CountryPicker.OnSelectedListener() {
        @Override
        public void onSelected(@NonNull Country country) {
            // do something
        }
    })
    .show();
```

#### Bottom sheet

No builder here, create traditionally.

```java
BottomSheetDialog dialog = new CountryPickerSheetDialog(context);
dialog.setOnSelectedListener(new CountryPicker.OnSelectedListener() {
    @Override
    public void onSelected(@NonNull Country country) {
        // do something
    }
});
dialog.show()
```

#### Inflate manually

`CountryPicker` itself is a `LinearLayout` that may be used independently with XML or programatically.

Country flags
-------------
![Emoji flags](/art/demo1.gif)

By default, country flags are represented in emoji to achieve lowest library size.

![Image flags](/art/demo2.gif)

To use custom flag images, have a drawable with name format `flag_{2-digit iso code}` in your project.
For example if you want to display US flag, the drawable name should be `flag_us`.

See [Country.java](/countrypicker/src/com/hendraanggrian/appcompat/countrypicker/Country.java) for all available country codes.

[demo1]: /art/demo1.gif
[demo2]: /art/demo2.gif
