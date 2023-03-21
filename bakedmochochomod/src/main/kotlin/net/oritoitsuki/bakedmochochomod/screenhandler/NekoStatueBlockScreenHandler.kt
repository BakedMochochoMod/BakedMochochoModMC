package net.oritoitsuki.bakedmochochomod.screenhandler

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot
import net.oritoitsuki.bakedmochochomod.screenhandler.slot.PenguinStatueBlockSlot

class NekoStatueBlockScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    private val inventory: Inventory = SimpleInventory(1)
):
    ScreenHandler(ModScreenHandlers.NEKO_STATUE_BLOCK_SCREEN_HANDLER, syncId) {

    init {
        checkSize(inventory, 1)
        inventory.onOpen(playerInventory.player)

        println(inventory is SimpleInventory)

        // Our Inventory
        addSlot(PenguinStatueBlockSlot(inventory, 0, 8 + 18 * 4, 20))

        // player inventory
        for (row in 0 until 3) {
            for (col in 0 until 9) {
                addSlot(Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 18 * 3 + row * 18 - 3))
            }
        }

        // player hot bar
        for (col in 0 until 9) {
            addSlot(Slot(playerInventory, col, 8 + col * 18, 109))
        }
    }

    override fun quickMove(player: PlayerEntity?, index: Int): ItemStack {
        val slot = slots[index]
        if (slot.hasStack()) {
            val originalStack = slot.stack
            if (index < inventory.size()) {
                insertItem(originalStack, inventory.size(), slots.size, true)
            } else {
                val statueSlot = slots[0]
                if (statueSlot.canInsert(originalStack) && statueSlot.stack.count < 1) {
                    statueSlot.stack = originalStack.split(1)
                    statueSlot.markDirty()
                    slot.markDirty()
                }
            }
        }
        return ItemStack.EMPTY
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }
}
