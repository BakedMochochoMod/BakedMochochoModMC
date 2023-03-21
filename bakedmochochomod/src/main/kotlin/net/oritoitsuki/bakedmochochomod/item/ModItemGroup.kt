package net.oritoitsuki.bakedmochochomod.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object ModItemGroup {
    val ITEM_GROUP: ItemGroup = FabricItemGroup.builder(Identifier("bakedmochochomod", "item"))
        .icon {ItemStack(ModItems.BAKED_MOCHOCHO_TOKEN)}
        .build()
}
