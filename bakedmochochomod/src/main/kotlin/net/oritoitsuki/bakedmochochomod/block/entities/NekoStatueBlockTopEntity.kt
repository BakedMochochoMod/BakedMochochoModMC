package net.oritoitsuki.bakedmochochomod.block.entities

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.Packet
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.oritoitsuki.bakedmochochomod.block.ModBlockEntities
import net.oritoitsuki.bakedmochochomod.block.blocks.NekoStatueBlockTop
import net.oritoitsuki.bakedmochochomod.item.ModItems
import net.oritoitsuki.bakedmochochomod.screenhandler.NekoStatueBlockScreenHandler
import net.oritoitsuki.bakedmochochomod.utils.ImplementedInventory
import kotlin.random.Random

class NekoStatueBlockTopEntity (pos: BlockPos?, state: BlockState?)
    : BlockEntity(ModBlockEntities.NEKO_STATUE_BLOCK_TOP, pos, state),
    NamedScreenHandlerFactory, ImplementedInventory {

    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: NekoStatueBlockTopEntity) {
            // Torch
            if (!world.isClient) {
                blockEntity.torchOff = !blockEntity.inventory[0].isOf(Items.TORCH)
                if (blockEntity.torchOff) {
                    if (!state.get(NekoStatueBlockTop.TORCH_OFF)) {
                        world.setBlockState(pos, state.with(NekoStatueBlockTop.TORCH_OFF, true), Block.NOTIFY_ALL)
                    }
                } else {
                    if (state.get(NekoStatueBlockTop.TORCH_OFF)) {
                        world.setBlockState(pos, state.with(NekoStatueBlockTop.TORCH_OFF, false), Block.NOTIFY_ALL)
                    }
                }
            }

            // Give token
            blockEntity.timer--
            if (blockEntity.timer != 0) return

            for (player in world.players) {
                val distance0 = pos.getSquaredDistance(player.pos)
                val distance1 = pos.down(1).getSquaredDistance(player.pos)
                val distance2 = pos.down(2).getSquaredDistance(player.pos)
                if (distance0 < 9F || distance1 < 9F || distance2 < 9F) {
                    if (player.isSneaking) {
                        if (world.isClient) {
                            player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.3F, 100F)
                        } else {
                            player.inventory.insertStack(ItemStack(ModItems.BAKED_MOCHOCHO_TOKEN, Random.nextInt(1, 3)))
                        }
                    }
                }
            }

            blockEntity.timer = 30
        }
    }

    private var timer = 50
    val inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(1, ItemStack.EMPTY)
    var torchOff = true

    override fun getItems(): DefaultedList<ItemStack> {
        return inventory
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return NekoStatueBlockScreenHandler(syncId, inv, this)
    }

    override fun getDisplayName(): Text {
        return Text.translatable("inventory.bakedmochochomod.neko_statue_block")
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
        torchOff = nbt.getBoolean("neko_statue_torch_off")
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
        nbt.putBoolean("neko_statue_torch_off", torchOff)
    }

    override fun toUpdatePacket(): Packet<ClientPlayPacketListener>? {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }
}
