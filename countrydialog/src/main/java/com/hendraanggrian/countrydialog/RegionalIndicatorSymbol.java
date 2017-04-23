package com.hendraanggrian.countrydialog;

import android.support.annotation.NonNull;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
enum RegionalIndicatorSymbol {
    A('A', 0x1F1E6),
    B('B', 0x1F1E7),
    C('C', 0x1F1E8),
    D('D', 0x1F1E9),
    E('E', 0x1F1EA),
    F('F', 0x1F1EB),
    G('G', 0x1F1EC),
    H('H', 0x1F1ED),
    I('I', 0x1F1EE),
    J('J', 0x1F1EF),
    K('K', 0x1F1F0),
    L('L', 0x1F1F1),
    M('M', 0x1F1F2),
    N('N', 0x1F1F3),
    O('O', 0x1F1F4),
    P('P', 0x1F1F5),
    Q('Q', 0x1F1F6),
    R('R', 0x1F1F7),
    S('S', 0x1F1F8),
    T('T', 0x1F1F9),
    U('U', 0x1F1FA),
    V('V', 0x1F1FB),
    W('W', 0x1F1FC),
    X('X', 0x1F1FD),
    Y('Y', 0x1F1FE),
    Z('Z', 0x1F1FF);

    private final char letter;
    private final int codepoint;

    RegionalIndicatorSymbol(char letter, int codepoint) {
        this.letter = letter;
        this.codepoint = codepoint;
    }

    @Override
    public String toString() {
        return String.valueOf(Character.toChars(codepoint));
    }

    @NonNull
    public static RegionalIndicatorSymbol valueOf(char letter) {
        for (RegionalIndicatorSymbol value : values())
            if (value.letter == letter)
                return value;
        throw new IllegalArgumentException(letter + " is not a valid Regional indicator symbol!");
    }
}