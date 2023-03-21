package net.oritoitsuki.bakedmochochomod.screenhandler

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.resource.featuretoggle.FeatureSet
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.oritoitsuki.bakedmochochomod.BakedMochochoMod

object ModScreenHandlers {
    val NEKO_STATUE_BLOCK_SCREEN_HANDLER: ScreenHandlerType<NekoStatueBlockScreenHandler> =
        register("neko_statue_block") { syncId, inv -> NekoStatueBlockScreenHandler(syncId, inv) }

    private fun <T : ScreenHandler> register(id: String, factory: ScreenHandlerType.Factory<T>): ScreenHandlerType<T> {
        return Registry.register(Registries.SCREEN_HANDLER, id, ScreenHandlerType(factory, FeatureSet.empty())) as ScreenHandlerType<T>
    }

    fun registerScreenHandler() {
        BakedMochochoMod.LOGGER.debug("Registering Mod Screen Handlers for " + BakedMochochoMod.MOD_ID)
    }
}
