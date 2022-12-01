package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConverterLocalDate implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {
        LocalDate localDate = DateTimeUtil.parseLocalDate(source);
        return localDate;
    }
}
