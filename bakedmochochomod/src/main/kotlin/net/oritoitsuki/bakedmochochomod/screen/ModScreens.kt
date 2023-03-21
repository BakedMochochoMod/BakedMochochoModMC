package net.oritoitsuki.bakedmochochomod.screen

import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.oritoitsuki.bakedmochochomod.BakedMochochoMod
import net.oritoitsuki.bakedmochochomod.screenhandler.ModScreenHandlers

object ModScreens {
    fun registerScreens() {
        BakedMochochoMod.LOGGER.debug("Registering Screens for " + BakedMochochoMod.MOD_ID)

        HandledScreens.register(ModScreenHandlers.NEKO_STATUE_BLOCK_SCREEN_HANDLER, ::NekoStatueBlockScreen)
    }
}
