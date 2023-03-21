package net.oritoitsuki.bakedmochochomod.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.world.World

object BakedMochocho : Item(
    FabricItemSettings()
        .maxCount(64)
        .food(FoodComponent.Builder().hunger(4).saturationModifier(6F).alwaysEdible().snack().build())
) {

    @Override
    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        tooltip?.add(Text.translatable("item.bakedmochochomod.baked_mochocho.tooltip-1"))
        tooltip?.add(Text.translatable("item.bakedmochochomod.baked_mochocho.tooltip-2"))
    }
}
