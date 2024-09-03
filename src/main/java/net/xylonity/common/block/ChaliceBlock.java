package net.xylonity.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.xylonity.common.block.tracker.ChaliceBlockTracker;
import net.xylonity.common.entity.boss.NethermanEntity;
import net.xylonity.config.values.KQConfigValues;
import net.xylonity.registry.KnightQuestBlocks;
import net.xylonity.registry.KnightQuestEntities;
import net.xylonity.registry.KnightQuestItems;
import net.xylonity.registry.KnightQuestParticles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ChaliceBlock extends Block {
    public static final IntProperty fill = IntProperty.of("level", 1, 9);

    private static final VoxelShape SHAPE_N = Stream.of(
        Block.createCuboidShape(0, 7, 0, 2, 16, 16),
        Block.createCuboidShape(14, 7, 0, 16, 16, 16),
        Block.createCuboidShape(0, 0, 0, 16, 2, 16),
        Block.createCuboidShape(0, 5, 0, 16, 7, 16),
        Block.createCuboidShape(2, 7, 0, 14, 16, 2),
        Block.createCuboidShape(2, 7, 14, 14, 16, 16),
        Block.createCuboidShape(4, 2, 4, 12, 5, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public ChaliceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_N;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(fill);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.item.knightquest.great_chalice"));
        super.appendTooltip(stack, context, tooltip, options);
    }

    /**
     * Handles the right-click interaction with a `great_essence` item when used on the great chalice.
     * Depending on the current state of the great chalice, it generates `starset` particles with a width
     * proportional to its state. The great chalice has five predefined states, with the fifth state making
     * the chalice emit a bright light, as defined in the block registration.
     *
     * @see KnightQuestBlocks#GREAT_CHALICE
     */

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        Block block = world.getBlockState(pos).getBlock();
        Item item = stack.getItem();

        if (block.equals(KnightQuestBlocks.GREAT_CHALICE) && item.equals(KnightQuestItems.GREAT_ESSENCE) && (Arrays.asList(1, 2, 3, 4).contains(state.get(fill)))) {

            if (!world.isClient()) {
                double centerX = pos.getX() + 0.5;
                double centerZ = pos.getZ() + 0.5;
                double initialY = pos.getY() + 0.1;

                double particleY = initialY - 0.48;

                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.getPlayers().forEach(playerEntity -> serverWorld.spawnParticles(playerEntity, KnightQuestParticles.STARSET_PARTICLE, true,
                            centerX, particleY, centerZ,
                            1, 0, 0, 0, 1));
                }

                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1f, 1f);
                world.setBlockState(pos, state.cycle(fill), 3);

                if (stack.getCount() > 1) {
                    stack.decrement(1);
                } else {
                    player.setStackInHand(hand, ItemStack.EMPTY);
                }

                if (state.get(fill) == 4) {
                    world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 1f, 1f);
                }
            }

        }

        if (block.equals(KnightQuestBlocks.GREAT_CHALICE) && item.equals(KnightQuestItems.EMPTY_GOBLET) && state.get(fill).equals(5)) {

            if (world.isClient()) {
                double radius = 0.5;
                double centerX = pos.getX() + 0.5;
                double centerZ = pos.getZ() + 0.5;
                double initialY = pos.getY();

                for (int i = 0; i < 360; i+=12) {
                    double angleRadians = Math.toRadians(i);

                    double particleX = centerX + radius * Math.cos(angleRadians);
                    double particleZ = centerZ + radius * Math.sin(angleRadians);

                    world.addParticle(ParticleTypes.EFFECT, particleX, initialY + 1, particleZ, -0.5d, 0.5d, 0.5d);
                }
            }

            if (!world.isClient()) {
                world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1f, 1f);

                if (stack.getCount() > 1) {
                    stack.decrement(1);
                } else {
                    player.setStackInHand(hand, new ItemStack(ItemStack.EMPTY.getItem()));
                }

                Entity entity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1d, pos.getZ() + 0.5, KnightQuestItems.FILLED_GOBLET.getDefaultStack());
                world.setBlockState(pos, state.with(fill, 1));
                world.spawnEntity(entity);
            }
        }

        if (block.equals(KnightQuestBlocks.GREAT_CHALICE) && item.equals(KnightQuestItems.RADIANT_ESSENCE) && state.get(fill).equals(5) && KQConfigValues.CAN_SUMMON_NETHERMAN) {
            if (!world.isClient()) {
                if (player.getStackInHand(hand).getCount() > 1) {
                    int stackCount = stack.getCount();
                    stack.setCount(--stackCount);
                } else {
                    player.setStackInHand(hand, new ItemStack(ItemStack.EMPTY.getItem()));
                }

                world.setBlockState(pos, state.cycle(fill), 3);
            }
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    /**
     * Generates random decorative particle effects for visual enhancement.
     */

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

        if (state.get(fill).equals(5)) {

            if (world.isClient()) {
                double radius = 0.37;
                double centerX = pos.getX() + 0.5;
                double centerZ = pos.getZ() + 0.5;
                double initialY = pos.getY() - 0.5;

                for (int i = 0; i < 360; i+=60) {
                    double angleRadians = Math.toRadians(i);

                    double particleX = centerX + radius * Math.cos(angleRadians);
                    double particleZ = centerZ + radius * Math.sin(angleRadians);

                    world.addParticle(ParticleTypes.EFFECT, particleX, initialY + 1, particleZ, 0d, 0.35d, 0d);
                }
            }

        }

        super.randomDisplayTick(state, world, pos, random);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int tickCount = ChaliceBlockTracker.getTickCount(pos);

        if (Arrays.asList(6, 7, 8, 9).contains(state.get(fill))) {

            if (tickCount == 0) {
                world.playSound(null, pos, SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, SoundCategory.BLOCKS, 1f, 1f);
            }

            if (tickCount % 10 == 0 && state.get(fill) != 9 && tickCount < 60) {
                world.setBlockState(pos, state.cycle(fill), 3);
                world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1f, 1f);
            }

            if (tickCount == 60) {
                world.setBlockState(pos, state.cycle(fill), 3);

                LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(world);
                if (lightningBolt != null && KQConfigValues.SPAWN_LIGHTNING_ON_SPAWN) {
                    lightningBolt.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                    world.spawnEntity(lightningBolt);
                }

                NethermanEntity entity = KnightQuestEntities.NETHERMAN.create(world);
                if (entity != null) {
                    entity.setPos(pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F);
                    world.spawnEntity(entity);
                }

                ChaliceBlockTracker.resetTickCount(pos);
            } else {
                ChaliceBlockTracker.incrementTickCount(pos);
            }

            scheduleTick(world, pos);
        }
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!world.isClient) {
            ChaliceBlockTracker.removeChalice(pos);
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!world.isClient) {
            ChaliceBlockTracker.addChalice(pos, this);
            scheduleTick(world, pos);
        }
    }

    private void scheduleTick(World pLevel, BlockPos pPos) {
        if (pLevel instanceof ServerWorld serverLevel) {
            serverLevel.scheduleBlockTick(pPos, this, 1);
        }
    }

}
