package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.EquipmentValueTelelog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Core {

    private static final Type EQUIPMENT_TYPE = new TypeToken<List<EquipmentValueTelelog>>() {
    }.getType();

    public static void loadInitialConfiguration() {
        Gson gson = new Gson();
        try {
            File configJson = new File("config.json");
            if (configJson.exists()) {
                JsonReader reader = new JsonReader(new FileReader("config.json"));
                List<EquipmentValueTelelog> telelogList = gson.fromJson(reader, EQUIPMENT_TYPE);
                System.out.println("");
            } else {
                try {
                    configJson.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
