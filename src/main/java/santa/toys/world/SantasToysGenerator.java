package santa.toys.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import santa.toys.Config;
import santa.toys.blocks.BlockHandler;

import java.util.Random;

public class SantasToysGenerator implements IWorldGenerator {
    public String getName() {
        return "SantasToys";
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == -1) {
            generateNether(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateNether(World world, Random random, int i, int j) {
        if (Config.enableSpicedSand && Config.enableSpicedSandGeneration) {
            for (int k = 0; k < (int) Math.random(); k++) {
                int x = i + random.nextInt(16);
                int y = random.nextInt(128);
                int z = j + random.nextInt(16);
                (new WorldGenMinable(BlockHandler.sandSpiced, 1, Blocks.soul_sand)).generate(world, random, x, y, z);
            }
        }
    }
}
