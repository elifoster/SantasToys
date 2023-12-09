package io.github.elifoster.santastoys.items;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

public class ItemMatch extends Item {
    ItemMatch() {
        super(new Item.Properties()
          .stacksTo(32));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        ItemStack match = context.getItemInHand();

        boolean used = false;

        if (canLightBlock(state)) {
            level.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            used = true;
        }

        if (!used && state.is(Blocks.TNT)) {
            state.getBlock().onCaughtFire(state, level, pos, context.getClickedFace(), player);
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            used = true;
        }

        BlockPos offsetPos = pos.relative(context.getClickedFace());
        if (!used && BaseFireBlock.canBePlacedAt(level, offsetPos, context.getHorizontalDirection())) {
            BlockState fire = BaseFireBlock.getState(level, offsetPos);
            level.setBlock(offsetPos, fire, 11);
            level.gameEvent(player, GameEvent.BLOCK_PLACE, pos);
            used = true;
        }

        if (used) {
            playFlintSound(level, player, pos);
            if (!(player == null || player.isCreative())) {
                match.shrink(1);
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        } else {
            return InteractionResult.FAIL;
        }
    }

    /**
     * Replacement for vanilla's idiotic hardcoded `!CampfireBlock.canLight(blockstate) &&
     * !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)` check in ItemFlintAndSteel.
     * <br/>
     * All of those methods do exactly the same thing. They check what block it is (irrelevant for flint and steel - it
     * should only care if it's lightable) and the waterlogged and lit blockstate properties.
     * <br/>
     * Using this method instead of vanilla's stupid campfire/candle/cake check also allows us to light mod blocks that
     * can be lit, or any new blocks that vanilla adds that can be lit, without having to hardcode each individual block
     * <br/>
     * I repeat, vanilla's method is really dumb.
     * @param state The blockstate for the activated item
     * @return Whether the match can light this block. Checks if it can be lit (has the blockstate property), then if it
     * is already lit or already waterlogged (waterlogged blocks cannot be lit, obviously).
     */
    private boolean canLightBlock(BlockState state) {
        if (!state.hasProperty(BlockStateProperties.LIT)) {
            return false;
        }
        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && Boolean.TRUE.equals(state.getValue(BlockStateProperties.WATERLOGGED))) {
            return false;
        }
        return !state.getValue(BlockStateProperties.LIT);
    }

    /**
     * Plays the flint and steel sound.
     * @param level the level
     * @param player the player using the item
     * @param pos the block set on fire
     */
    private void playFlintSound(Level level, Player player, BlockPos pos) {
        level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
    }
}