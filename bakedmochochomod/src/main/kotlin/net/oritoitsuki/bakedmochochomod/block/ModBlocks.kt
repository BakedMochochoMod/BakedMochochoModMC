package net.oritoitsuki.bakedmochochomod.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.oritoitsuki.bakedmochochomod.BakedMochochoMod
import net.oritoitsuki.bakedmochochomod.block.blocks.BakedMochochoTokenBlock
import net.oritoitsuki.bakedmochochomod.block.blocks.NekoStatueBlockMiddle
import net.oritoitsuki.bakedmochochomod.block.blocks.NekoStatueBlockTop
import net.oritoitsuki.bakedmochochomod.item.ModItemGroup

object ModBlocks {
    private val BAKED_MOCHOCHO_TOKEN_BLOCK = BakedMochochoTokenBlock
    val NEKO_STATUE_BLOCK_TOP = NekoStatueBlockTop()
    val NEKO_STATUE_BLOCK_MIDDLE = NekoStatueBlockMiddle()

    private fun registerBlock(name: String, block: Block): Block {
        registerBlockItem(name, block)
        return Registry.register(Registries.BLOCK, Identifier(BakedMochochoMod.MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block): Item {
        val blockItem = BlockItem(block, FabricItemSettings())
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.ITEM_GROUP)
            .register(ItemGroupEvents.ModifyEntries { content: FabricItemGroupEntries ->
                content.add(blockItem)
            })
        return Registry.register(Registries.ITEM, Identifier(BakedMochochoMod.MOD_ID, name), blockItem)
    }

    fun registerModBlocks() {
        BakedMochochoMod.LOGGER.debug("Registering Mod Blocks for " + BakedMochochoMod.MOD_ID)

        // BAKED_MOCHOCHO_TOKEN_BLOCK
        registerBlock("baked_mochocho_token_block", BAKED_MOCHOCHO_TOKEN_BLOCK)
        FuelRegistry.INSTANCE.add(BAKED_MOCHOCHO_TOKEN_BLOCK, 16000)

        // NEKO_STATUE
        registerBlock("neko_statue_block_top", NEKO_STATUE_BLOCK_TOP)
        registerBlock("neko_statue_block_middle", NEKO_STATUE_BLOCK_MIDDLE)
    }
}
