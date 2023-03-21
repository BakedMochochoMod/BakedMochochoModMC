package net.oritoitsuki.bakedmochochomod.block.entities

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.Items
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.oritoitsuki.bakedmochochomod.block.ModBlockEntities
import net.oritoitsuki.bakedmochochomod.block.blocks.NekoStatueBlockMiddle

class NekoStatueBlockMiddleEntity (pos: BlockPos?, state: BlockState?)
    : BlockEntity(ModBlockEntities.NEKO_STATUE_BLOCK_MIDDLE, pos, state) {

    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: NekoStatueBlockMiddleEntity) {
            if (!world.isClient) {
                var topBlockEntity: NekoStatueBlockTopEntity? = null
                if (world.getBlockEntity(pos.up(1)) is NekoStatueBlockTopEntity) {
                    topBlockEntity = world.getBlockEntity(pos.up(1)) as NekoStatueBlockTopEntity
                } else if (world.getBlockEntity(pos.up(2)) is NekoStatueBlockTopEntity) {
                    topBlockEntity = world.getBlockEntity(pos.up(2)) as NekoStatueBlockTopEntity
                }

                if (topBlockEntity == null) {
                    if (state.get(NekoStatueBlockMiddle.TORCH_OFF)) {
                        world.setBlockState(pos, state.with(NekoStatueBlockMiddle.TORCH_OFF, true), Block.NOTIFY_ALL)
                    }
                } else {
                    val torchOff = !topBlockEntity.inventory[0].isOf(Items.TORCH)
                    if (torchOff) {
                        if (!state.get(NekoStatueBlockMiddle.TORCH_OFF)) {
                            world.setBlockState(pos, state.with(NekoStatueBlockMiddle.TORCH_OFF, true), Block.NOTIFY_ALL)
                        }
                    } else {
                        if (state.get(NekoStatueBlockMiddle.TORCH_OFF)) {
                            world.setBlockState(pos, state.with(NekoStatueBlockMiddle.TORCH_OFF, false), Block.NOTIFY_ALL)
                        }
                    }
                }
            }
        }
    }
}
