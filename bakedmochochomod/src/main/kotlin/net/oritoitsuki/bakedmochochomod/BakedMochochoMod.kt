package net.oritoitsuki.bakedmochochomod

import net.fabricmc.api.ModInitializer
import net.oritoitsuki.bakedmochochomod.block.ModBlockEntities
import net.oritoitsuki.bakedmochochomod.block.ModBlocks
import net.oritoitsuki.bakedmochochomod.item.ModItems
import net.oritoitsuki.bakedmochochomod.screenhandler.ModScreenHandlers
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("UNUSED")
object BakedMochochoMod: ModInitializer {
    const val MOD_ID = "bakedmochochomod"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        ModItems.registerModItems()
        ModBlocks.registerModBlocks()
        ModBlockEntities.registerBlockEntities()
        ModScreenHandlers.registerScreenHandler()
    }
}
