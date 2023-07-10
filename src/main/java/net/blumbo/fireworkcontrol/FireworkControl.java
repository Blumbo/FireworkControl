package net.blumbo.fireworkcontrol;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.blumbo.fireworkcontrol.commands.FireworkControlCmd;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FireworkControl implements ModInitializer {

    public static int damagePercentage = 100;
    public static String damagePercentageKey = "damagePercentage";

    private static final String dir = FabricLoader.getInstance().getConfigDir().toString() + "/fireworkcontrol";
    public static String fileName = "fireworkcontrol.json";

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(FireworkControlCmd::register);
        load();
    }

    private static void load() {
        try {
            String jsonString = Files.readString(Paths.get(dir + "/" + fileName));
            JsonElement element = JsonParser.parseString(jsonString);
            damagePercentage = ((JsonObject)element).get(damagePercentageKey).getAsInt();
        } catch (Exception ignored) {}
    }

    public static void save() {
        try {
            JsonObject object = new JsonObject();
            object.addProperty(damagePercentageKey, damagePercentage);

            Files.createDirectories(Paths.get(dir));
            BufferedWriter writer = new BufferedWriter(new FileWriter(dir + "/" + fileName));

            writer.write(object.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
