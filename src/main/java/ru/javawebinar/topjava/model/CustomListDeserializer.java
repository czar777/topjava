package ru.javawebinar.topjava.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomListDeserializer extends StdDeserializer<List<Meal>> {
    protected CustomListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<Meal> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        return new ArrayList<>();
    }


}
