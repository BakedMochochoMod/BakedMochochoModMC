package net.oritoitsuki.bakedmochochomod.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.oritoitsuki.bakedmochochomod.BakedMochochoMod


object ModItems {
    val BAKED_MOCHOCHO_TOKEN = BakedMochochoTokenItem
    private val BAKED_MOCHOCHO = BakedMochocho
    private val BAKED_MOCHOCHO_CHISEL = BakedMochochoChisel

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(BakedMochochoMod.MOD_ID, name), item)
    }

    fun registerModItems() {
        BakedMochochoMod.LOGGER.debug("Registering Mod Items for " + BakedMochochoMod.MOD_ID)

        // BAKED_MOCHOCHO_TOKEN_ITEM
        registerItem("baked_mochocho_token", BAKED_MOCHOCHO_TOKEN)
        FuelRegistry.INSTANCE.add(BAKED_MOCHOCHO_TOKEN, 1600)

        // BAKED_MOCHOCHO_MANJU_ITEM
        registerItem("baked_mochocho", BAKED_MOCHOCHO)

        // BAKED_MOCHOCHO_CHISEL
        registerItem("baked_mochocho_chisel", BAKED_MOCHOCHO_CHISEL)

        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.ITEM_GROUP)
            .register(ModifyEntries { content: FabricItemGroupEntries ->
                content.add(BAKED_MOCHOCHO_TOKEN)
                content.add(BAKED_MOCHOCHO)
                content.add(BAKED_MOCHOCHO_CHISEL)
            })
    }
}
