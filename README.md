CountryDialog
=============
[![bintray](https://img.shields.io/badge/bintray-appcompat-brightgreen.svg)](https://bintray.com/hendraanggrian/appcompat)
[![download](https://api.bintray.com/packages/hendraanggrian/appcompat/country-dialog/images/download.svg)](https://bintray.com/hendraanggrian/appcompat/country-dialog/_latestVersion)
[![build](https://travis-ci.com/hendraanggrian/country-dialog.svg)](https://travis-ci.com/hendraanggrian/country-dialog)
[![license](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

![demo1][demo1]
![demo2][demo2]

Material design dialog to pick country.

Download
--------
```gradle
repositories {
    google()
    jcenter()
}

dependencies {
    implementation "com.hendraanggrian.appcompat:country-dialog:$version"
}
```

Usage
-----
Use 'CountryDialog.Builder' to build or show 'CountryDialog'.
```java
new CountryDialog.Builder(context)
    .title("Pick country")
    .onSelected(new CountryDialog.OnSelectedListener() {
        @Override
        public void onSelected(@NonNull Country country) {
            // do something
        }
    })
    .show();
```

All customization properties that can be applied:
```java
new CountryDialog.Builder(context)
    .title(title)                           // when not set or empty, title bar is hidden
    .showFlags(false)                       // show flag images, default is true
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

Country flags
-------------
![Emoji flags](/art/screenshot_type_emoji.png)

By default, country flags are represented in emoji to achieve lowest library size.

![Image flags](/art/screenshot_type_image.png)

To use custom flag images, have a drawable with name format `flag_{2-digit iso code}` in your project.
For example if you want to display US flag, the drawable name should be `flag_us`.

See [Country.java](/countrydialog/src/main/java/com/hendraanggrian/countrydialog/Country.java) for all available country codes.

License
-------
    Copyright 2018 Hendra Anggrian

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[demo1]: /art/demo1.gif
[demo2]: /art/demo2.gif
