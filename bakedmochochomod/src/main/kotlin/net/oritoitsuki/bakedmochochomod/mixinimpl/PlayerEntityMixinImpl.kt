package net.oritoitsuki.bakedmochochomod.mixinimpl

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

object PlayerEntityMixinImpl {
    fun onEatFood(self: PlayerEntity, world: World, stack: ItemStack, callbackInfoReturnable: CallbackInfoReturnable<ItemStack>) {
        if (self.world.isClient) return
        if (stack.registryEntry.key.get().value.namespace != "bakedmochochomod" ||
            stack.registryEntry.key.get().value.path != "baked_mochocho") return
        self.damage(world.damageSources.explosion(null, null), 3.4e38F)
        val pos = self.pos
        self.world.createExplosion(null, pos.x, pos.y + 1.5F, pos.z, 2F, World.ExplosionSourceType.BLOCK)
    }
}
