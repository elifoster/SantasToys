package io.github.elifoster.santastoys.blocks.blockentities;

import io.github.elifoster.santastoys.blocks.BlockHandler;
import io.github.elifoster.santastoys.blocks.LiquidSensorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LiquidSensorBlockEntity extends BlockEntity {
    public LiquidSensorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockHandler.LIQUID_SENSOR_BE_TYPE.get(), pos, state);
    }

    public static <T extends BlockEntity> void onServerTick(Level level, BlockPos pos, BlockState state, T t) {
        if (level.getGameTime() % 20 == 0) {
            updatePoweredState(level, state, pos);
        }
    }

    public static void updatePoweredState(Level level, BlockState state, BlockPos pos) {
        BlockPos[] surroundingBlockPositions = new BlockPos[]{
          pos.above(),
          pos.north(),
          pos.east(),
          pos.south(),
          pos.west(),
          pos.below()
        };

        for (BlockPos adjacentPos : surroundingBlockPositions) {
            if (level.getBlockState(adjacentPos).liquid()) {
                BlockState newBlockState = state.setValue(LiquidSensorBlock.POWERED, !state.getValue(LiquidSensorBlock.INVERTED));
                if (state.getValue(LiquidSensorBlock.POWERED) != newBlockState.getValue(LiquidSensorBlock.POWERED)) {
                    level.setBlockAndUpdate(pos, newBlockState);
                }
                return;
            }
        }
        BlockState newBlockState = state.setValue(LiquidSensorBlock.POWERED, state.getValue(LiquidSensorBlock.INVERTED));
        if (state.getValue(LiquidSensorBlock.POWERED) != newBlockState.getValue(LiquidSensorBlock.POWERED)) {
            level.setBlockAndUpdate(pos, newBlockState);
        }
    }
}
