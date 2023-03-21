package net.oritoitsuki.bakedmochochomod

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.oritoitsuki.bakedmochochomod.screen.ModScreens

@Environment(EnvType.CLIENT)
object BakedMochochoModClient: ClientModInitializer {
    override fun onInitializeClient() {
        ModScreens.registerScreens()
    }
}
