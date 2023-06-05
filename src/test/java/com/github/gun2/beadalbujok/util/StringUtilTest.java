package com.github.gun2.beadalbujok.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class StringUtilTest {

    @Test
    void uuidGenTest() {
        /** given */

        /** when */
        String uuid12 = StringUtil.createUUID12();

        /** then */
        log.info(uuid12);
        assertThat(uuid12.replaceAll("-", "").length()).isEqualTo(12);
    }
}