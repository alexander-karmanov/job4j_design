package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String string = "John Smith";
        byte b = 127;
        short s = 32000;
        int i = 25;
        long l = 9223372036854L;
        float f = 16.11F;
        double d = 32132131321313.65165D;
        boolean bl = true;
        char ch = '1';

        LOG.debug("Info string: {}, b : {}, i : {}, l : {}, s : {}, f : {}, d : {}, bl : {}, ch : {}",
                string, b, i, l, s, f, d, bl, ch);
    }
}
