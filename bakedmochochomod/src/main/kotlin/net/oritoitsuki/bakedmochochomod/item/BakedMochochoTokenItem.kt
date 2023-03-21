package net.oritoitsuki.bakedmochochomod.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import kotlin.random.Random

object BakedMochochoTokenItem : Item(
    FabricItemSettings().maxCount(64)
) {
    @Override
    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (world == null) return TypedActionResult.success(user?.getStackInHand(hand))

        if (!world.isClient) {
            if (Random.nextFloat() < 0.95) {
                world.playSound(null, user!!.blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.NEUTRAL, 1F, Random.nextFloat() * 50F)
            } else {
                world.playSound(null, user!!.blockPos, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.NEUTRAL, 1F, 15F)
            }
        }

        return TypedActionResult.success(user?.getStackInHand(hand))
    }

    @Override
    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        tooltip?.add(Text.translatable("item.bakedmochochomod.baked_mochocho_token.tooltip"))
    }
}
