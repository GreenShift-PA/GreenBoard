package com.greenshift.greenboard.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTypeAdapter extends TypeAdapter<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

    @Override
    public void write(JsonWriter out, LocalDateTime dateTime) throws IOException {
        if (dateTime != null) {
            System.out.println("dateTime = " + dateTime);
            String formattedDateTime = dateTime.atZone(ZoneId.systemDefault()).format(formatter);
            out.value(formattedDateTime);
        } else {
            out.nullValue();
        }
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String json = in.nextString();
        System.out.println("json = " + json);
        if (json != null && !json.isEmpty()) {
            Instant instant = Instant.parse(json);
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
        return null;
    }
}
