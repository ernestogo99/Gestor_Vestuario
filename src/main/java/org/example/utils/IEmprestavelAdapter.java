package org.example.utils;

import com.google.gson.*;
import org.example.interfaces.IEmprestavel;
import org.example.model.*;

import java.lang.reflect.Type;

public class IEmprestavelAdapter implements JsonSerializer<IEmprestavel>, JsonDeserializer<IEmprestavel> {

    @Override
    public JsonElement serialize(IEmprestavel item, Type type, JsonSerializationContext context) {
        JsonObject jsonObject=context.serialize(item).getAsJsonObject();
        jsonObject.addProperty("tipo",item.getClass().getSimpleName());
        return jsonObject;
    }

    @Override
    public IEmprestavel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject=jsonElement.getAsJsonObject();
        String tipo=jsonObject.get("tipo").getAsString();
        Class<? extends Item> classe;
        switch (tipo) {
            case "Calca":
                classe = Calca.class;
                break;
            case "Camisa":
                classe = Camisa.class;
                break;
            case "Casaco":
                classe = Casaco.class;
                break;
            case "Pulseira":
                classe = Pulseira.class;
                break;
            case "Relogio":
                classe = Relogio.class;
                break;
            case "Saia":
                classe = Saia.class;
                break;
            default:
                throw new JsonParseException("Tipo de item desconhecido: " + tipo);
        }



        IEmprestavel item = context.deserialize(jsonElement, classe);



        return item;

    }

}
