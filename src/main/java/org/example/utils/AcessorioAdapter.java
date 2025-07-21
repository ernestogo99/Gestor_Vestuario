package org.example.utils;

import com.google.gson.*;
import org.example.model.*;

import java.lang.reflect.Type;

public class AcessorioAdapter implements JsonSerializer<Acessorio>, JsonDeserializer<Acessorio> {

    @Override
    public JsonElement serialize(Acessorio item, Type type, JsonSerializationContext context) {
        JsonObject jsonObject=context.serialize(item).getAsJsonObject();
        jsonObject.addProperty("tipo",item.getClass().getSimpleName());
        return jsonObject;
    }

    @Override
    public Acessorio deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject=jsonElement.getAsJsonObject();
        String tipo=jsonObject.get("tipo").getAsString();
        Class<? extends Item> classe;
        switch (tipo) {
            case "Pulseira":
                classe = Pulseira.class;
                break;
            case "Relogio":
                classe = Relogio.class;
                break;

            default:
                throw new JsonParseException("Tipo de item desconhecido: " + tipo);
        }

        Acessorio item = context.deserialize(jsonElement, classe);



        return item;

    }
}
