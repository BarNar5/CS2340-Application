package model;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * This class is used by the gson library to correctly save the abstract
 * Report class to the JSON file
 *
 */
public class ReportAdapter implements JsonSerializer<Report>, JsonDeserializer<Report> {

    @Override
    public JsonElement serialize(Report src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public Report deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName("model." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}