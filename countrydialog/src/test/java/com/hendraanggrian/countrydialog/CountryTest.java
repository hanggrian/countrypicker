package com.hendraanggrian.countrydialog;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public class CountryTest {

    @Test
    public void simple() throws Exception {
        Assert.assertEquals(Country.ID.getDialCode(), "+62");
    }
}