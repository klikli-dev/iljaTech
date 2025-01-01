package com.github.ilja615.iljatech.energy;

import com.github.ilja615.iljatech.blocks.turbine.TurbineBlockEntity;
import com.github.ilja615.iljatech.init.ModBlocks;
import com.github.ilja615.iljatech.init.ModParticles;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Heat {
    public static void emitHeat(World world, BlockPos blockPos) {
        if (world.getBlockState(blockPos).isOf(Blocks.WATER_CAULDRON) && world.getBlockState(blockPos).getBlock() instanceof LeveledCauldronBlock) {
            if (world.getBlockState(blockPos.up()).isOf(ModBlocks.TURBINE) && world.getBlockEntity(blockPos.up()) instanceof TurbineBlockEntity turbineBlockEntity) {
                if (world.getBlockState(blockPos.up()).get(MechPwrAccepter.ON_OFF_PWR) != MechPwrAccepter.OnOffPwr.ON) {
                    turbineBlockEntity.setTicks(200);
                }
            } else {
                LeveledCauldronBlock.decrementFluidLevel(world.getBlockState(blockPos), world, blockPos);
                if (!world.isClient) {
                    ((ServerWorld) world).spawnParticles(ModParticles.STEAM, blockPos.getX() + world.random.nextFloat() * 0.5f + 0.25f, blockPos.getY() + world.random.nextFloat() * 0.5f + 0.25f, blockPos.getZ() + world.random.nextFloat() * 0.5f + 0.25f, 5, 0.0f, 0.3f, 0.0f, 0.0);
                }
            }
        }
    }
}
