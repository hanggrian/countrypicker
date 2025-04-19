package com.hanggrian.countrypicker;

/** Country flag display behaviors. */
public enum FlagDisplay {
    /** Show flag alongside text, flag images are obtained from emoji. */
    DEFAULT,

    /**
     * To use custom flag images, have a drawable with name format {@code flag_${2-digit iso code}}.
     * For example, to display US flag, the drawable name should be {@code flag_us}.
     */
    CUSTOM,

    /** Show text-only. */
    NONE
}
