package net.xylonity.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
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
import net.xylonity.registry.KnightQuestBlocks;
import net.xylonity.registry.KnightQuestItems;
import net.xylonity.registry.KnightQuestParticles;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ChaliceBlock extends Block {
    public static final IntProperty fill = IntProperty.of("level", 1, 5);

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

            double centerX = pos.getX() + 0.5;
            double centerZ = pos.getZ() + 0.5;
            double initialY = pos.getY() + 0.1;

            if (world.isClient()) {
                int fillValue = state.get(fill);

                double xOffset = switch (fillValue) {
                    case 1 -> 1d;
                    case 2 -> 1.2d;
                    case 3 -> 1.4d;
                    case 4 -> 1.8d;
                    default -> 0d;
                };

                if (xOffset != 0d) {
                    world.addParticle(KnightQuestParticles.STARSET_PARTICLE, centerX, initialY - 0.48, centerZ, xOffset, 0d, 0d);
                }
            }

            if (!world.isClient()) {

                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1f, 1f);
                world.setBlockState(pos, state.cycle(fill));

                if (stack.getCount() > 1) {
                    stack.decrement(1);
                } else {
                    player.setStackInHand(hand, new ItemStack(ItemStack.EMPTY.getItem()));
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
                world.setBlockState(pos, state.cycle(fill));
                world.spawnEntity(entity);
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

}
