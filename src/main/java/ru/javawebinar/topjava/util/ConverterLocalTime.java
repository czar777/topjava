package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ConverterLocalTime implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        LocalTime localTime = DateTimeUtil.parseLocalTime(source);
        return localTime;
    }
}
