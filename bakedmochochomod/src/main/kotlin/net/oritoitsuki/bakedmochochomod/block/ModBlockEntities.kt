package net.oritoitsuki.bakedmochochomod.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.oritoitsuki.bakedmochochomod.BakedMochochoMod
import net.oritoitsuki.bakedmochochomod.block.entities.NekoStatueBlockMiddleEntity
import net.oritoitsuki.bakedmochochomod.block.entities.NekoStatueBlockTopEntity

object ModBlockEntities {
     val NEKO_STATUE_BLOCK_TOP: BlockEntityType<NekoStatueBlockTopEntity> = FabricBlockEntityTypeBuilder
         .create(::NekoStatueBlockTopEntity, ModBlocks.NEKO_STATUE_BLOCK_TOP)
         .build(null)
    val NEKO_STATUE_BLOCK_MIDDLE: BlockEntityType<NekoStatueBlockMiddleEntity> = FabricBlockEntityTypeBuilder
        .create(::NekoStatueBlockMiddleEntity, ModBlocks.NEKO_STATUE_BLOCK_MIDDLE)
        .build(null)

    private fun <T: BlockEntity> registerBlockEntity(name: String, blockEntity: BlockEntityType<T>): BlockEntityType<T> {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier(BakedMochochoMod.MOD_ID, name), blockEntity)
    }

    fun registerBlockEntities() {
        BakedMochochoMod.LOGGER.debug("Registering Mod Block Entities for " + BakedMochochoMod.MOD_ID)

        // NEKO_STATUE_TOP
        registerBlockEntity("neko_statue_block_top", NEKO_STATUE_BLOCK_TOP)
        registerBlockEntity("neko_statue_block_middle", NEKO_STATUE_BLOCK_MIDDLE)
    }
}
