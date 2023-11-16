package io.github.elifoster.santastoys.mixins;

import io.github.elifoster.santastoys.blocks.BlockHandler;
import io.github.elifoster.santastoys.blocks.DecayingLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {
    @Shadow
    private BlockState blockState;

    protected FallingBlockEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void santastoys$onTick(CallbackInfo info) {
        Level level = super.level();
        BlockPos currentPos = super.blockPosition();
        BlockPos above = currentPos.above();
        // Don't start until we've traveled down 1 block.
        if (blockState.is(BlockHandler.HEAVY_LIGHT.get()) && !level.isClientSide() && !currentPos.equals(above)) {
            BlockState stateAbove = level.getBlockState(above);
            if (stateAbove.canBeReplaced()) {
                boolean didReplace = false;
                if (stateAbove.hasProperty(FlowingFluid.LEVEL)) {
                    // Only replace source blocks. If it is a source block, waterlog the light.
                    if (stateAbove.getValue(FlowingFluid.LEVEL) == 0) {
                        level.setBlockAndUpdate(above, BlockHandler.DECAYING_LIGHT_BLOCK.get().defaultBlockState().setValue(DecayingLightBlock.WATERLOGGED, true));
                        didReplace = true;
                    }
                } else {
                    didReplace = true;
                    level.setBlockAndUpdate(above, BlockHandler.DECAYING_LIGHT_BLOCK.get().defaultBlockState());
                }
                if (didReplace) {
                    level.scheduleTick(above, BlockHandler.DECAYING_LIGHT_BLOCK.get(), 4);
                }
            }
        }
    }
}
