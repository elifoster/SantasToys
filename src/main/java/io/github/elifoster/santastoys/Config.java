package io.github.elifoster.santastoys;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private Config() {}

    static ModConfigSpec CONFIG;

    public static final ModConfigSpec.ConfigValue<Float> damageDealtBySand;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder().comment("Santa's Toys configuration");

        builder.comment("The amount of damage that the Spiced Sand deals");
        damageDealtBySand = builder.define("damageDealtBySpicedSand", 3f);

        CONFIG = builder.build();
    }
}
