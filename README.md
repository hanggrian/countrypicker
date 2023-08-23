[![Travis CI](https://img.shields.io/travis/com/hendraanggrian/material/countrypicker)](https://travis-ci.com/github/hendraanggrian/countrypicker/)
[![Codecov](https://img.shields.io/codecov/c/github/hendraanggrian/countrypicker)](https://codecov.io/gh/hendraanggrian/countrypicker/)
[![Maven Central](https://img.shields.io/maven-central/v/com.hendraanggrian.material/countrypicker)](https://repo1.maven.org/maven2/com/hendraanggrian/material/countrypicker/)
[![Nexus Snapshot](https://img.shields.io/nexus/s/com.hendraanggrian.material/countrypicker?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/hendraanggrian/material/countrypicker/)
[![Android SDK](https://img.shields.io/badge/sdk-16%2B-informational)](https://developer.android.com/studio/releases/platforms/#4.1)

# CountryPicker

![Dialog preview.](https://github.com/hendraanggrian/countrypicker/raw/assets/preview_dialog.png)
![Bottom sheet dialog.](https://github.com/hendraanggrian/countrypicker/raw/assets/preview_sheet.png)

Material design components to pick country.

- Emoji country flags to achieve lowest library size, with options to customize.
- Search box, locate button, and fast scroller.

## Download

```gradle
repositories {
    google()
    mavenCentral()
}
dependencies {
    implementation "com.hendraanggrian.material:countrypicker:$version"
}
```

## Usage

### Dialog

Use `CountryPickerDialog.Builder` to build or show `CountryPickerDialog`.

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

### Bottom Sheet

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

## Custom Flags

To use custom flag images, have a drawable with name format
`flag_{2-digit iso code}` in your project. For example if you want to display US
flag, the drawable name should be `flag_us`.

See [Country.java](/countrypicker/src/com/hendraanggrian/appcompat/countrypicker/Country.java)
for all available country codes.
