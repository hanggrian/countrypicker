[![CircleCI](https://img.shields.io/circleci/build/gh/hanggrian/countrypicker)](https://app.circleci.com/pipelines/github/hanggrian/countrypicker/)
[![Codecov](https://img.shields.io/codecov/c/gh/hanggrian/countrypicker)](https://app.codecov.io/gh/hanggrian/countrypicker/)
[![Maven Central](https://img.shields.io/maven-central/v/com.hanggrian/countrypicker)](https://central.sonatype.com/artifact/com.hanggrian/countrypicker/)
[![Android SDK](https://img.shields.io/badge/android-21%2B-34a853)](https://developer.android.com/tools/releases/platforms/#5.0)

# CountryPicker

![](https://github.com/hendraanggrian/countrypicker/raw/assets/preview_dialog.gif "Dialog preview")
![](https://github.com/hendraanggrian/countrypicker/raw/assets/preview_bottomsheet.gif "Bottom sheet dialog")

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
    implementation "com.hendraanggrian:countrypicker:$version"
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
