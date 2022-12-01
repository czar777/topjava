package ru.javawebinar.topjava.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomListSerializer extends StdSerializer<List<Meal>> {

    public CustomListSerializer() {
        this(null);
    }

    protected CustomListSerializer(Class<List<Meal>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Meal> meals, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Meal> mealList = new ArrayList<>();
        for (Meal meal : meals) {
            Meal m = new Meal();
            m.setId(meal.getId());
            m.setDescription(meal.getDescription());
            m.setCalories(meal.getCalories());
            m.setDateTime(meal.getDateTime());
            mealList.add(m);
        }
        jsonGenerator.writeObject(mealList);
    }
}
