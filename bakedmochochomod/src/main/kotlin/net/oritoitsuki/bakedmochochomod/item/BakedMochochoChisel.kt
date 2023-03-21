package net.oritoitsuki.bakedmochochomod.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.oritoitsuki.bakedmochochomod.block.ModBlocks
import net.oritoitsuki.bakedmochochomod.block.blocks.NekoStatueBlockMiddle
import net.oritoitsuki.bakedmochochomod.block.blocks.NekoStatueBlockTop
import kotlin.random.Random

object BakedMochochoChisel : Item(
    FabricItemSettings()
        .maxDamage(64)
) {

    @Override
    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        if (context == null) return ActionResult.SUCCESS
        val pos = context.blockPos
        val world = context.world
        val player = context.player
        val blockTop = world.getBlockState(pos)
        val blockMiddle = world.getBlockState(pos.down(1))
        val blockBottom = world.getBlockState(pos.down(2))

        if ((blockTop.block == Blocks.STONE || blockTop.block == Blocks.DEEPSLATE) &&
            (blockMiddle.block == Blocks.STONE || blockMiddle.block == Blocks.DEEPSLATE) &&
            (blockBottom.block == Blocks.STONE || blockBottom.block == Blocks.DEEPSLATE)) {
             if (!world.isClient) {
                 val direction = context.horizontalPlayerFacing.opposite
                 world.removeBlock(pos, false)
                 world.setBlockState(pos, ModBlocks.NEKO_STATUE_BLOCK_TOP.defaultState.with(NekoStatueBlockTop.FACING, direction))
                 world.setBlockState(pos.down(1), ModBlocks.NEKO_STATUE_BLOCK_MIDDLE.defaultState.with(NekoStatueBlockMiddle.FACING, direction))
                 world.setBlockState(pos.down(2), ModBlocks.NEKO_STATUE_BLOCK_MIDDLE.defaultState.with(NekoStatueBlockMiddle.FACING, direction))
                 context.stack.damage(3, player) { it?.sendToolBreakStatus(player?.activeHand)}
             }
             player?.playSound(SoundEvents.BLOCK_GRINDSTONE_USE, 1.5F, Random.nextFloat() * 50F)
        }

        return ActionResult.SUCCESS
    }
}
