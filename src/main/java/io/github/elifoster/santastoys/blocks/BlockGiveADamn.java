package io.github.elifoster.santastoys.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class BlockGiveADamn extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BlockGiveADamn() {
        super(Properties.of()
          .strength(1F)
          .explosionResistance(1F)
          .sound(SoundType.GRASS));
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(FACING);
    }
}
