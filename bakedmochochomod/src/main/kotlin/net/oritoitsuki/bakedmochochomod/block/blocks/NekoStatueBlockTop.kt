package net.oritoitsuki.bakedmochochomod.block.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.oritoitsuki.bakedmochochomod.block.ModBlockEntities
import net.oritoitsuki.bakedmochochomod.block.entities.NekoStatueBlockTopEntity

class NekoStatueBlockTop :
    BlockWithEntity(FabricBlockSettings.of(Material.STONE).strength(4F).resistance(15F).requiresTool().luminance {
        if (it.get(TORCH_OFF)) 0 else 15
    }) {

    companion object {
        val FACING: DirectionProperty = Properties.HORIZONTAL_FACING
        val TORCH_OFF: BooleanProperty = BooleanProperty.of("torch_off")

        private fun makeNorthShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.0, 0.0625, 0.6875, 0.328125, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.390625, 0.328125, 0.0625, 0.609375, 0.359375, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0, 0.375, 0.125, 0.75, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.375, 0.9375, 0.75, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.875, 0.625, 0.75, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.125, 0.8125, 0.75, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0, 0.1875, 0.1875, 0.75, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0, 0.1875, 0.875, 0.75, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.078125, 0.625, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.09375, 0.375, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.09375, 0.75, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.875, 0.375, 0.75, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.875, 0.75, 0.75, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.25, 0.125, 0.75, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.625, 0.125, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 0.75, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.625, 0.90625, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.75, 0.1875, 0.75, 0.8125, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.75, 0.25, 0.25, 0.8125, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.75, 0.25, 0.8125, 0.8125, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.15625, 0.625, 0.8125, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.8125, 0.625, 0.8125, 0.84375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0.75, 0.375, 0.1875, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.75, 0.375, 0.84375, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.15625, 0.625, 0.8125, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.375, 0.03125, 0.5625, 0.4375, 0.09375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0.4375, 0.0625, 0.421875, 0.5625, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.140625, 0.4375, 0.109375, 0.25, 0.5625, 0.234375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.578125, 0.4375, 0.0625, 0.78125, 0.5625, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.71875, 0.4375, 0.109375, 0.828125, 0.5625, 0.234375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.453125, 0.046875, 0.375, 0.546875, 0.171875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.453125, 0.046875, 0.75, 0.546875, 0.171875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.125, -0.25, 0.3125, 0.25, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0625, 0.0625, 0.3125, 0.125, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.125, -0.25, 0.875, 0.25, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.0625, 0.0625, 0.875, 0.125, 0.1875))

            return shape
        }

        private fun makeSouthShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.0, 0.875, 0.6875, 0.328125, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.390625, 0.328125, 0.875, 0.609375, 0.359375, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.375, 0.9375, 0.75, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0, 0.375, 0.125, 0.75, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.0625, 0.625, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.125, 0.8125, 0.75, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0, 0.1875, 0.875, 0.75, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0, 0.1875, 0.1875, 0.75, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.875, 0.625, 0.75, 0.921875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.875, 0.75, 0.75, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.875, 0.375, 0.75, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.09375, 0.75, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.09375, 0.375, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.625, 0.90625, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 0.75, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.625, 0.125, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.25, 0.125, 0.75, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.75, 0.1875, 0.75, 0.8125, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.75, 0.25, 0.8125, 0.8125, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.75, 0.25, 0.25, 0.8125, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.8125, 0.625, 0.8125, 0.84375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.15625, 0.625, 0.8125, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.75, 0.375, 0.84375, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0.75, 0.375, 0.1875, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.8125, 0.625, 0.8125, 0.84375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.375, 0.90625, 0.5625, 0.4375, 0.96875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.578125, 0.4375, 0.8125, 0.78125, 0.5625, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.4375, 0.765625, 0.859375, 0.5625, 0.890625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0.4375, 0.8125, 0.421875, 0.5625, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.171875, 0.4375, 0.765625, 0.28125, 0.5625, 0.890625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.453125, 0.828125, 0.75, 0.546875, 0.953125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.453125, 0.828125, 0.375, 0.546875, 0.953125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.125, 0.8125, 0.875, 0.25, 1.25))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.0625, 0.8125, 0.875, 0.125, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.125, 0.8125, 0.3125, 0.25, 1.25))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0625, 0.8125, 0.3125, 0.125, 0.9375))

            return shape
        }

        private fun makeEastShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.3125, 0.9375, 0.328125, 0.6875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.328125, 0.390625, 0.9375, 0.359375, 0.609375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.0625, 0.625, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.875, 0.625, 0.75, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0, 0.375, 0.125, 0.75, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0, 0.1875, 0.875, 0.75, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.125, 0.8125, 0.75, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.8125, 0.8125, 0.75, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.375, 0.921875, 0.75, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 0.75, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.625, 0.90625, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.25, 0.125, 0.75, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.625, 0.125, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.09375, 0.75, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.09375, 0.375, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.875, 0.75, 0.75, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.875, 0.375, 0.75, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.75, 0.25, 0.8125, 0.8125, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.75, 0.1875, 0.75, 0.8125, 0.25))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.75, 0.75, 0.75, 0.8125, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.75, 0.375, 0.84375, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0.75, 0.375, 0.1875, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.15625, 0.625, 0.8125, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.8125, 0.625, 0.8125, 0.84375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.75, 0.375, 0.84375, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.90625, 0.375, 0.4375, 0.96875, 0.4375, 0.5625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.4375, 0.21875, 0.9375, 0.5625, 0.421875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.765625, 0.4375, 0.140625, 0.890625, 0.5625, 0.25))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.4375, 0.578125, 0.9375, 0.5625, 0.78125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.765625, 0.4375, 0.71875, 0.890625, 0.5625, 0.828125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.828125, 0.453125, 0.25, 0.953125, 0.546875, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.828125, 0.453125, 0.625, 0.953125, 0.546875, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.125, 0.125, 1.25, 0.25, 0.3125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0625, 0.125, 0.9375, 0.125, 0.3125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.125, 0.6875, 1.25, 0.25, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.0625, 0.6875, 0.9375, 0.125, 0.875))

            return shape
        }

        private fun makeWestShape(): VoxelShape {
            var shape = VoxelShapes.empty()
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0, 0.3125, 0.125, 0.328125, 0.6875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.328125, 0.390625, 0.125, 0.359375, 0.609375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.875, 0.625, 0.75, 0.9375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0, 0.0625, 0.625, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.375, 0.9375, 0.75, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.0, 0.1875, 0.875, 0.75, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.8125, 0.8125, 0.75, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0, 0.125, 0.8125, 0.75, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.078125, 0.0, 0.375, 0.125, 0.75, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.625, 0.125, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.09375, 0.0, 0.25, 0.125, 0.75, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.625, 0.90625, 0.75, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.875, 0.0, 0.25, 0.90625, 0.75, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.875, 0.375, 0.75, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.875, 0.75, 0.75, 0.90625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0, 0.09375, 0.375, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.0, 0.09375, 0.75, 0.75, 0.125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.75, 0.25, 0.8125, 0.8125, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.75, 0.75, 0.75, 0.8125, 0.8125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.75, 0.1875, 0.75, 0.8125, 0.25))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0.75, 0.375, 0.1875, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.75, 0.375, 0.84375, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.8125, 0.625, 0.8125, 0.84375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.75, 0.15625, 0.625, 0.8125, 0.1875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0.75, 0.375, 0.1875, 0.8125, 0.625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.03125, 0.375, 0.4375, 0.09375, 0.4375, 0.5625))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.4375, 0.578125, 0.1875, 0.5625, 0.78125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.109375, 0.4375, 0.75, 0.234375, 0.5625, 0.859375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.4375, 0.21875, 0.1875, 0.5625, 0.421875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.109375, 0.4375, 0.171875, 0.234375, 0.5625, 0.28125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.046875, 0.453125, 0.625, 0.171875, 0.546875, 0.75))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.046875, 0.453125, 0.25, 0.171875, 0.546875, 0.375))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(-0.25, 0.125, 0.6875, 0.1875, 0.25, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0625, 0.6875, 0.1875, 0.125, 0.875))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(-0.25, 0.125, 0.125, 0.1875, 0.25, 0.3125))
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0625, 0.125, 0.1875, 0.125, 0.3125))

            return shape
        }

        val SHAPE_EAST = makeEastShape()
        val SHAPE_NORTH = makeNorthShape()
        val SHAPE_SOUTH = makeSouthShape()
        val SHAPE_WEST = makeWestShape()
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
        builder.add(TORCH_OFF)
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return this.defaultState.with(FACING, ctx?.horizontalPlayerFacing?.opposite)
    }
    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return NekoStatueBlockTopEntity(pos, state)
    }

    override fun getRenderType(state: BlockState?): BlockRenderType? {
        return BlockRenderType.MODEL
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return when(state.get(FACING)) {
            Direction.EAST -> SHAPE_EAST
            Direction.NORTH -> SHAPE_NORTH
            Direction.SOUTH -> SHAPE_SOUTH
            Direction.WEST -> SHAPE_WEST
            else -> SHAPE_NORTH
        }
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            val screenHandlerFactory = state.createScreenHandlerFactory(world, pos)
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory)
            }
        }
        return ActionResult.SUCCESS
    }

    override fun onStateReplaced(
        state: BlockState,
        world: World,
        pos: BlockPos,
        newState: BlockState,
        moved: Boolean
    ) {
        if (state.block != newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is NekoStatueBlockTopEntity) {
                ItemScatterer.spawn(world, pos, blockEntity)
                world.updateComparators(pos, this)
            }
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    override fun <T : BlockEntity?> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return BlockWithEntity.checkType(type, ModBlockEntities.NEKO_STATUE_BLOCK_TOP, NekoStatueBlockTopEntity::tick)
    }

}
