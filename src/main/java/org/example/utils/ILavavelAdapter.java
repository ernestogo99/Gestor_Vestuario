package org.example.utils;

import com.google.gson.*;
import org.example.interfaces.ILavavel;
import org.example.model.*;

import java.lang.reflect.Type;

public class ILavavelAdapter implements JsonDeserializer<ILavavel>, JsonSerializer<ILavavel> {

    @Override
    public ILavavel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObj = json.getAsJsonObject();
        String tipo = jsonObj.get("tipo").getAsString();

        switch (tipo) {
            case "Camisa":
                return context.deserialize(jsonObj, Camisa.class);
            case "Calca":
                return context.deserialize(jsonObj, Calca.class);
            case "Cueca":
                return context.deserialize(jsonObj, Cueca.class);
            case "Calcinha":
                return context.deserialize(jsonObj, Calcinha.class);
            case "Casaco":
                return context.deserialize(jsonObj, Casaco.class);
            case "Saia":
                return context.deserialize(jsonObj, Saia.class);
            default:
                throw new JsonParseException("Tipo desconhecido para ILavavel: " + tipo);
        }
    }

    @Override
    public JsonElement serialize(ILavavel src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = context.serialize(src).getAsJsonObject();
        obj.addProperty("tipo", src.getClass().getSimpleName());
        return obj;
    }
}
