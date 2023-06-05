package com.github.gun2.beadalbujok.util;

import java.util.Locale;
import java.util.UUID;

public class StringUtil {

    /**
     * 12자의 랜덤 값을 반환한다.
     * @return
     */
    public static String createUUID12(){
        return UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, 12)
                .toUpperCase(Locale.ROOT);
    }
}
