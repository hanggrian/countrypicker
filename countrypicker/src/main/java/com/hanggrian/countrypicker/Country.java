package com.hanggrian.countrypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.SparseIntArray;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;

import static android.os.Build.VERSION_CODES.N;

/**
 * Represents countries around the world according to registered ISO 2 codes.
 */
public enum Country {
    AD("AD", "376"),
    AE("AE", "971"),
    AF("AF", "93"),
    AG("AG", "1"),
    AI("AI", "1"),
    AL("AL", "355"),
    AM("AM", "374"),
    AO("AO", "244"),
    AQ("AQ", "672"),
    AR("AR", "54"),
    AS("AS", "1"),
    AT("AT", "43"),
    AU("AU", "61"),
    AW("AW", "297"),
    AX("AX", "358"),
    AZ("AZ", "994"),
    BA("BA", "387"),
    BB("BB", "1"),
    BD("BD", "880"),
    BE("BE", "32"),
    BF("BF", "226"),
    BG("BG", "359"),
    BH("BH", "973"),
    BI("BI", "257"),
    BJ("BJ", "229"),
    BL("BL", "590"),
    BM("BM", "1"),
    BN("BN", "673"),
    BO("BO", "591"),
    BQ("BQ", "599"),
    BR("BR", "55"),
    BS("BS", "1"),
    BT("BT", "975"),
    BV("BV", "55"),
    BW("BW", "267"),
    BY("BY", "375"),
    BZ("BZ", "501"),
    CA("CA", "1"),
    CC("CC", "891"),
    CD("CD", "243"),
    CF("CF", "236"),
    CG("CG", "242"),
    CH("CH", "41"),
    CI("CI", "225"),
    CK("CK", "682"),
    CL("CL", "56"),
    CM("CM", "237"),
    CN("CN", "86"),
    CO("CO", "57"),
    CR("CR", "506"),
    CU("CU", "53"),
    CV("CV", "238"),
    CW("CW", "599"),
    CX("CX", "61"),
    CY("CY", "357"),
    CZ("CZ", "420"),
    DE("DE", "49"),
    DJ("DJ", "253"),
    DK("DK", "45"),
    DM("DM", "1"),
    DO("DO", "1"),
    DZ("DZ", "213"),
    EC("EC", "593"),
    EE("EE", "372"),
    EG("EG", "20"),
    EH("EH", "212"),
    ER("ER", "291"),
    ES("ES", "34"),
    ET("ET", "251"),
    FI("FI", "358"),
    FJ("FJ", "679"),
    FK("FK", "500"),
    FM("FM", "691"),
    FO("FO", "298"),
    FR("FR", "33"),
    GA("GA", "241"),
    GB("GB", "44"),
    GD("GD", "1"),
    GE("GE", "995"),
    GF("GF", "594"),
    GG("GG", "44"),
    GH("GH", "233"),
    GI("GI", "350"),
    GL("GL", "299"),
    GM("GM", "220"),
    GN("GN", "224"),
    GP("GP", "590"),
    GQ("GQ", "240"),
    GR("GR", "30"),
    GS("GS", "500"),
    GT("GT", "502"),
    GU("GU", "1"),
    GW("GW", "245"),
    GY("GY", "592"),
    HK("HK", "852"),
    HM("HM", "61"),
    HN("HN", "504"),
    HR("HR", "385"),
    HT("HT", "509"),
    HU("HU", "36"),
    ID("ID", "62"),
    IE("IE", "353"),
    IL("IL", "972"),
    IM("IM", "44"),
    IN("IN", "91"),
    IO("IO", "246"),
    IQ("IQ", "964"),
    IR("IR", "98"),
    IS("IS", "354"),
    IT("IT", "39"),
    JE("JE", "44"),
    JM("JM", "1"),
    JO("JO", "962"),
    JP("JP", "81"),
    KE("KE", "254"),
    KG("KG", "996"),
    KH("KH", "855"),
    KI("KI", "686"),
    KM("KM", "269"),
    KN("KN", "1"),
    KP("KP", "850"),
    KR("KR", "82"),
    KW("KW", "965"),
    KY("KY", "965"),
    KZ("KZ", "7"),
    LA("LA", "856"),
    LB("LB", "961"),
    LC("LC", "1"),
    LI("LI", "423"),
    LK("LK", "94"),
    LR("LR", "231"),
    LS("LS", "266"),
    LT("LT", "370"),
    LU("LU", "352"),
    LV("LV", "371"),
    LY("LY", "218"),
    MA("MA", "212"),
    MC("MC", "377"),
    MD("MD", "373"),
    ME("ME", "382"),
    MF("MF", "1"),
    MG("MG", "261"),
    MH("MH", "692"),
    MK("MK", "389"),
    ML("ML", "223"),
    MM("MM", "95"),
    MN("MN", "976"),
    MO("MO", "853"),
    MP("MP", "1"),
    MQ("MQ", "596"),
    MR("MR", "222"),
    MS("MS", "1"),
    MT("MT", "356"),
    MU("MU", "230"),
    MV("MV", "960"),
    MW("MW", "265"),
    MX("MX", "52"),
    MY("MY", "60"),
    MZ("MZ", "258"),
    NA("NA", "264"),
    NC("NC", "687"),
    NE("NE", "227"),
    NF("NF", "672"),
    NG("NG", "234"),
    NI("NI", "505"),
    NL("NL", "31"),
    NO("NO", "47"),
    NP("NP", "977"),
    NR("NR", "674"),
    NU("NU", "683"),
    NZ("NZ", "64"),
    OM("OM", "968"),
    PA("PA", "507"),
    PE("PE", "51"),
    PF("PF", "689"),
    PG("PG", "675"),
    PH("PH", "63"),
    PK("PK", "92"),
    PL("PL", "48"),
    PM("PM", "508"),
    PN("PN", "64"),
    PR("PR", "1"),
    PS("PS", "970"),
    PT("PT", "351"),
    PW("PW", "680"),
    PY("PY", "595"),
    QA("QA", "974"),
    RE("RE", "262"),
    RO("RO", "40"),
    RS("RS", "381"),
    RU("RU", "7"),
    RW("RW", "250"),
    SA("SA", "966"),
    SB("SB", "677"),
    SC("SC", "248"),
    SD("SD", "249"),
    SE("SE", "46"),
    SG("SG", "65"),
    SH("SH", "290"),
    SI("SI", "386"),
    SJ("SJ", "47"),
    SK("SK", "421"),
    SL("SL", "232"),
    SM("SM", "378"),
    SN("SN", "221"),
    SO("SO", "252"),
    SR("SR", "597"),
    SS("SS", "211"),
    ST("ST", "239"),
    SV("SV", "503"),
    SX("SX", "1"),
    SY("SY", "963"),
    SZ("SZ", "268"),
    TC("TC", "1"),
    TD("TD", "235"),
    TF("TF", "262"),
    TG("TG", "228"),
    TH("TH", "66"),
    TJ("TJ", "992"),
    TK("TK", "690"),
    TL("TL", "670"),
    TM("TM", "993"),
    TN("TN", "216"),
    TO("TO", "676"),
    TR("TR", "90"),
    TT("TT", "1"),
    TV("TV", "688"),
    TW("TW", "886"),
    TZ("TZ", "255"),
    UA("UA", "380"),
    UG("UG", "256"),
    UM("UM", "1"),
    US("US", "1"),
    UY("UY", "598"),
    UZ("UZ", "998"),
    VA("VA", "379"),
    VC("VC", "1"),
    VE("VE", "58"),
    VG("VG", "1"),
    VI("VI", "1"),
    VN("VN", "84"),
    VU("VU", "678"),
    WF("WF", "681"),
    WS("WS", "685"),
    YE("YE", "967"),
    YT("YT", "262"),
    ZA("ZA", "27"),
    ZM("ZM", "260"),
    ZW("ZW", "263");

    private final String isoCode;
    private final String dialCode;

    Country(@NonNull String iso, @NonNull String dial) {
        isoCode = iso;
        dialCode = dial;
    }

    @NonNull
    public String getIsoCode() {
        return isoCode;
    }

    @NonNull
    public String getDialCode() {
        return '+' + dialCode;
    }

    public boolean isFlagDrawableAvailable(@NonNull Context context) {
        return getFlagDrawableRes(context) != 0;
    }

    @DrawableRes
    @SuppressLint("DiscouragedApi")
    public int getFlagDrawableRes(@NonNull Context context) {
        return context.getResources().getIdentifier(
            ("flag_" + isoCode.toLowerCase(Locale.ENGLISH)).toLowerCase(Locale.ENGLISH),
            "drawable",
            context.getPackageName()
        );
    }

    @NonNull
    public String getFlagSymbols() {
        return getSymbol(isoCode.charAt(0)) + getSymbol(isoCode.charAt(1));
    }

    @NonNull
    public String getName(@NonNull Context context) {
        return getLocale(context).getDisplayCountry();
    }

    @NonNull
    public Locale getLocale(@NonNull Context context) {
        final Configuration config = context.getResources().getConfiguration();
        return new Locale(Build.VERSION.SDK_INT < N
            ? config.locale.getLanguage()
            : config.getLocales().get(0).getLanguage(), isoCode
        );
    }

    @Nullable
    public static Country valueOf(@NonNull Context context, @NonNull String iso) {
        for (final Country value : values()) {
            final Locale locale = value.getLocale(context);
            // robolectric produces error "String.toLowerCase(java.util.Locale)" because "iso" is
            // null
            try {
                if (value.isoCode.toLowerCase(locale).equals(iso.toLowerCase(locale))) {
                    return value;
                }
            } catch (NullPointerException e) {
                return US;
            }
        }
        return null;
    }

    @Nullable
    public static Country getDefault(@NonNull Context context) {
        final TelephonyManager tm =
            (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            return Country.valueOf(context, tm.getNetworkCountryIso());
        }
        return null;
    }

    /**
     * See <a href="https://en.wikipedia.org/wiki/Regional_Indicator_Symbol">
     * https://en.wikipedia.org/wiki/Regional_Indicator_Symbol
     * </a>.
     */
    private static final SparseIntArray SYMBOLS = new SparseIntArray(26);

    private static String getSymbol(char c) {
        return String.valueOf(Character.toChars(SYMBOLS.get(c)));
    }

    static {
        SYMBOLS.put('A', 0x1F1E6);
        SYMBOLS.put('B', 0x1F1E7);
        SYMBOLS.put('C', 0x1F1E8);
        SYMBOLS.put('D', 0x1F1E9);
        SYMBOLS.put('E', 0x1F1EA);
        SYMBOLS.put('F', 0x1F1EB);
        SYMBOLS.put('G', 0x1F1EC);
        SYMBOLS.put('H', 0x1F1ED);
        SYMBOLS.put('I', 0x1F1EE);
        SYMBOLS.put('J', 0x1F1EF);
        SYMBOLS.put('K', 0x1F1F0);
        SYMBOLS.put('L', 0x1F1F1);
        SYMBOLS.put('M', 0x1F1F2);
        SYMBOLS.put('N', 0x1F1F3);
        SYMBOLS.put('O', 0x1F1F4);
        SYMBOLS.put('P', 0x1F1F5);
        SYMBOLS.put('Q', 0x1F1F6);
        SYMBOLS.put('R', 0x1F1F7);
        SYMBOLS.put('S', 0x1F1F8);
        SYMBOLS.put('T', 0x1F1F9);
        SYMBOLS.put('U', 0x1F1FA);
        SYMBOLS.put('V', 0x1F1FB);
        SYMBOLS.put('W', 0x1F1FC);
        SYMBOLS.put('X', 0x1F1FD);
        SYMBOLS.put('Y', 0x1F1FE);
        SYMBOLS.put('Z', 0x1F1FF);
    }
}
