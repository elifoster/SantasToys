package santa.toys;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
    public static boolean enableEnderBlaster;
    public static boolean enableNetherBlaster;
    public static boolean enableMatch;
    public static boolean enableGiveADamn;
    public static boolean enableGlowstone;
    public static boolean enableSpicedSand;
    public static boolean enableSpicedSandGeneration;

    public static int damageDealtBySand;

    static void load(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        enableEnderBlaster = config.get("Feature", "Toggle the Ender Blaster. Disabling this will disable the Nether Blaster", true).getBoolean();
        enableNetherBlaster = config.get("Feature", "Toggle the Nether Blaster. This is useless if the Ender Blaster is already disabled", true).getBoolean();
        enableMatch = config.get("Feature", "Toggle the Match", true).getBoolean();
        enableGiveADamn = config.get("Feature", "Toggle the Give a Damn Block", true).getBoolean();
        enableGlowstone = config.get("Feature", "Toggle the Heavy Light", true).getBoolean();
        enableSpicedSand = config.get("Feature", "Toggle the Spiced Sand. Disabling this will also disable the generation of it, even if that is enabled.", true).getBoolean();

        enableSpicedSandGeneration = config.get("World", "Toggle Spiced Sand generation", true).getBoolean();

        damageDealtBySand = config.get("Balance", "The amount of damage that the Spiced Sand deals", 3).getInt();

        config.save();
    }
}
