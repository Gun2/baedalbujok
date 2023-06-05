package com.github.gun2.beadalbujok.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Slf4j
public class DtoToModelGenerate {

    final String DTO_PQCN = "com.github.gun2.beadalbujok.dto";
    final String DTO_SUFFIX = "Dto";


    final String TARGET = "Gifticon"; // 생성할 대상 클래스명

    @Test
    void ToEntity생성() {
        try {
            Class<?> clazz = Class.forName(DTO_PQCN + "." + TARGET + DTO_SUFFIX);
            List<Field> fieldList = Arrays.stream(clazz.getDeclaredFields()).toList();
            log.info(fieldList.size() + "");

            //.seq(this.seq)
            List<String> strings = fieldList.stream()
                    .map(field -> "." + field.getName() + "(this." + field.getName() + ")")
                    .toList();

            log.info("toEntity : \n {}", String.join("\n", strings));

        } catch (ClassNotFoundException e) {
            log.error("대상을 찾을 수 없음 : {}", e);
        }
    }

    @Test
    void ToDto생성() {
        try {
            Class<?> clazz = Class.forName(DTO_PQCN + "." + TARGET + DTO_SUFFIX);
            List<Field> fieldList = Arrays.stream(clazz.getDeclaredFields()).toList();
            log.info(fieldList.size() + "");

            //.seq(this.seq)
            //this.seq = irBase.getSeq();
            List<String> strings = fieldList.stream()
                    .map(field -> "this." + field.getName()
                            + " = " + instanceGetField(field.getName()))
                    .toList();

            log.info("toDTO : \n {}", String.join("\n", strings));

        } catch (ClassNotFoundException e) {
            log.error("대상을 찾을 수 없음 : {}", e);
        }
    }

    String getInstanceName() {
        String front = TARGET.substring(0, 1).toLowerCase(Locale.ROOT);
        String back = TARGET.substring(1);

        return front + back;
    }

    String getPattern(String field) {
        String front = field.substring(0, 1).toUpperCase(Locale.ROOT);
        String back = field.substring(1);
        return "get" + front + back;
    }

    String instanceGetField(String field) {
        return getInstanceName() + "." + getPattern(field) + "();";
    }

}
