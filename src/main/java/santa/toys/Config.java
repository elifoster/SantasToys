package santa.toys;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraftforge.common.config.Configuration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Config {
    public static boolean enableEnderBlaster;
    public static boolean enableNetherBlaster;
//    public static boolean enableSaplingBlaster;
    public static boolean enableMatch;
    public static boolean enableGiveADamn;
    public static boolean enableGlowstone;
    public static boolean enableSpicedSand;
    public static boolean enableSpicedSandGeneration;
//    public static boolean enableMuffler;

    public static int damageDealtBySand;

    public static void load(FMLPreInitializationEvent event){
        File configurationDir = ReflectionHelper.getPrivateValue(FMLPreInitializationEvent.class, event, 2);
        File oldConfigFile = new File(configurationDir, "santastoys.cfg");
        if (oldConfigFile.exists()){
            try {
                FileUtils.copyFile(new File(configurationDir, "santastoys.cfg"), new File(configurationDir, "SantasToys-try.cfg"));
            } catch (IOException e){
                e.printStackTrace();
            }
            oldConfigFile.delete();
        }

        Configuration config = new Configuration(new File(configurationDir, "SantasToys-new.cfg"));
        config.load();

        enableEnderBlaster = config.get("Feature", "Toggle the Ender Blaster. Disabling this will disable the Nether Blaster", true).getBoolean(true);
        enableNetherBlaster = config.get("Feature", "Toggle the Nether Blaster. This is useless if the Ender Blaster is already disabled", true).getBoolean(true);
//        enableSaplingBlaster = config.get("Feature", "Toggle the Sapling Blaster", true).getBoolean(true);
        enableMatch = config.get("Feature", "Toggle the Match", true).getBoolean(true);
        enableGiveADamn = config.get("Feature", "Toggle the Give a Damn Block", true).getBoolean(true);
        enableGlowstone = config.get("Feature", "Toggle the Heavy Light", true).getBoolean(true);
        enableSpicedSand = config.get("Feature", "Toggle the Spiced Sand. Disabling this will also disable the generation of it, even if that is enabled.", true).getBoolean(true);
        enableSpicedSandGeneration = config.get("World", "Toggle Spiced Sand generation", true).getBoolean(true);
        damageDealtBySand = config.get("Balance", "The amount of damage that the Spiced Sand deals", 3).getInt();
//        enableMuffler = config.get("Enable", "Toggle the Muffler", true).getBoolean(true);

        config.save();
    }


}
