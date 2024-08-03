package net.xylonity.knightquest.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.xylonity.knightquest.common.block.tracker.ChaliceBlockTracker;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.xylonity.knightquest.registry.KnightQuestBlocks;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.registry.KnightQuestItems;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Stream;

public class ChaliceBlock extends Block {
    public static final IntegerProperty fill = IntegerProperty.create("level", 1, 9);

    private static final VoxelShape SHAPE_N = Stream.of(
        Block.box(0, 7, 0, 2, 16, 16),
        Block.box(14, 7, 0, 16, 16, 16),
        Block.box(0, 0, 0, 16, 2, 16),
        Block.box(0, 5, 0, 16, 7, 16),
        Block.box(2, 7, 0, 14, 16, 2),
        Block.box(2, 7, 14, 14, 16, 16),
        Block.box(4, 2, 4, 12, 5, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ChaliceBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return SHAPE_N;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(fill);
    }

    /**
     * Handles the right-click interaction with a `great_essence` item when used on the great chalice.
     * Depending on the current state of the great chalice, it generates `starset` particles with a width
     * proportional to its state. The great chalice has six predefined states, with the fifth state making
     * the chalice emit a bright light, as defined in the block registration.
     *
     * @see KnightQuestBlocks#GREAT_CHALICE
     */

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {

        Block block = pLevel.getBlockState(pPos).getBlock();
        ItemStack stack = pPlayer.getItemInHand(pHand);
        Item item = stack.getItem();

        if (block.equals(KnightQuestBlocks.GREAT_CHALICE.get()) && item.equals(KnightQuestItems.GREAT_ESSENCE.get()) && (Arrays.asList(1, 2, 3, 4).contains(pState.getValue(fill)))) {

            double centerX = pPos.getX() + 0.5;
            double centerZ = pPos.getZ() + 0.5;
            double initialY = pPos.getY() + 0.1;

            if (pLevel.isClientSide()) {
                int fillValue = pState.getValue(fill);

                double xOffset = switch (fillValue) {
                    case 1 -> 1d;
                    case 2 -> 1.2d;
                    case 3 -> 1.4d;
                    case 4 -> 1.8d;
                    default -> 0d;
                };

                if (xOffset != 0d) {
                    pLevel.addParticle(KnightQuestParticles.STARSET_PARTICLE.get(), centerX, initialY - 0.48, centerZ, xOffset, 0d, 0d);
                }
            }

            if (!pLevel.isClientSide()) {

                pLevel.playSound(null, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1f, 1f);
                pLevel.setBlock(pPos, pState.cycle(fill), 3);

                if (pPlayer.getItemInHand(pHand).getCount() > 1) {
                    int count = stack.getCount();
                    stack.setCount(--count);
                } else {
                    pPlayer.setItemInHand(pHand, new ItemStack(ItemStack.EMPTY.getItem()));
                }

                if (pState.getValue(fill) == 4) {
                    pLevel.playSound(null, pPos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 1f, 1f);
                }

            }

        }

        if (block.equals(KnightQuestBlocks.GREAT_CHALICE.get()) && item.equals(KnightQuestItems.EMPTY_GOBLET.get()) && pState.getValue(fill).equals(5)) {

            if (pLevel.isClientSide()) {
                double radius = 0.5;
                double centerX = pPos.getX() + 0.5;
                double centerZ = pPos.getZ() + 0.5;
                double initialY = pPos.getY();

                for (int i = 0; i < 360; i+=12) {
                    double angleRadians = Math.toRadians(i);

                    double particleX = centerX + radius * Math.cos(angleRadians);
                    double particleZ = centerZ + radius * Math.sin(angleRadians);

                    pLevel.addParticle(ParticleTypes.EFFECT, particleX, initialY + 1, particleZ, -0.5d, 0.5d, 0.5d);
                }
            }

            if (!pLevel.isClientSide()) {
                pLevel.playSound(null, pPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1f, 1f);

                if (pPlayer.getItemInHand(pHand).getCount() > 1) {
                    int stackCount = stack.getCount();
                    stack.setCount(--stackCount);
                } else {
                    pPlayer.setItemInHand(pHand, new ItemStack(ItemStack.EMPTY.getItem()));
                }

                Entity entity = new ItemEntity(pLevel, pPos.getX() + 0.5, pPos.getY() + 1d, pPos.getZ() + 0.5, KnightQuestItems.FILLED_GOBLET.get().getDefaultInstance());
                pLevel.setBlock(pPos, pState.cycle(fill), 3);
                pLevel.addFreshEntity(entity);
            }
        }

        if (block.equals(KnightQuestBlocks.GREAT_CHALICE.get()) && item.equals(KnightQuestItems.RADIANT_ESSENCE.get()) && pState.getValue(fill).equals(5)) {
            if (!pLevel.isClientSide()) {
                if (pPlayer.getItemInHand(pHand).getCount() > 1) {
                    int stackCount = stack.getCount();
                    stack.setCount(--stackCount);
                } else {
                    pPlayer.setItemInHand(pHand, new ItemStack(ItemStack.EMPTY.getItem()));
                }

                pLevel.setBlock(pPos, pState.cycle(fill), 3);
            }
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    /**
     * Generates random decorative particle effects for visual enhancement.
     */

    @Override
    public void animateTick(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if (pState.getValue(fill).equals(5)) {

            if (pLevel.isClientSide()) {
                double radius = 0.37;
                double centerX = pPos.getX() + 0.5;
                double centerZ = pPos.getZ() + 0.5;
                double initialY = pPos.getY() - 0.5;

                for (int i = 0; i < 360; i+=60) {
                    double angleRadians = Math.toRadians(i);

                    double particleX = centerX + radius * Math.cos(angleRadians);
                    double particleZ = centerZ + radius * Math.sin(angleRadians);

                    pLevel.addParticle(ParticleTypes.EFFECT, particleX, initialY + 1, particleZ, 0d, 0.35d, 0d);
                }
            }

        }

        super.animateTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void tick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        int tickCount = ChaliceBlockTracker.getTickCount(pPos);

        if (Arrays.asList(6, 7, 8, 9).contains(pState.getValue(fill))) {

            if (tickCount == 0) {
                pLevel.playSound(null, pPos, SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.BLOCKS, 1f, 1f);
            }

            if (tickCount % 10 == 0 && pState.getValue(fill) != 9 && tickCount < 60) {
                pLevel.setBlock(pPos, pState.cycle(fill), 3);
                pLevel.playSound(null, pPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1f, 1f);
            }

            if (tickCount == 60) {
                pLevel.setBlock(pPos, pState.cycle(fill), 3);

                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(pLevel);
                if (lightningBolt != null) {
                    lightningBolt.moveTo(pPos.getX() + 0.5, pPos.getY(), pPos.getZ() + 0.5);
                    pLevel.addFreshEntity(lightningBolt);
                }

                NethermanEntity entity = KnightQuestEntities.NETHERMAN.get().create(pLevel);
                if (entity != null) {
                    entity.moveTo(pPos.getX() + 0.5F, pPos.getY() + 1, pPos.getZ() + 0.5F);
                    pLevel.addFreshEntity(entity);
                }

                ChaliceBlockTracker.resetTickCount(pPos);
            } else {
                ChaliceBlockTracker.incrementTickCount(pPos);
            }

            scheduleTick(pLevel, pPos);
        }
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        if (!pLevel.isClientSide) {
            ChaliceBlockTracker.addChalice(pPos, this);
            scheduleTick(pLevel, pPos);
        }
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (!pLevel.isClientSide) {
            ChaliceBlockTracker.removeChalice(pPos);
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    private void scheduleTick(Level pLevel, BlockPos pPos) {
        if (pLevel instanceof ServerLevel serverLevel) {
            serverLevel.scheduleTick(pPos, this, 1);
        }
    }

}