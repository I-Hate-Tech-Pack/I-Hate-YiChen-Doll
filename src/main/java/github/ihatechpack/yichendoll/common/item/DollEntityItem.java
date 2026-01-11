package github.ihatechpack.yichendoll.common.item;


import github.ihatechpack.yichendoll.common.entity.DollEntity;
import github.ihatechpack.yichendoll.common.entity.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.List;
import java.util.function.Consumer;


/**
 * @description: TODO
 * @author: HowXu
 * @date: 2025/9/12 18:25
 */
public class DollEntityItem extends Item {
    private static final String TAG_DOLL_ENTITY = "doll_entity";

    public DollEntityItem() {
        super(new Properties());
    }

    public static ItemStack createItemWithEntity(DollEntity entity) {
        ItemStack stack = new ItemStack(ModItems.DOLL_ENTITY_ITEM.get());
        saveDollEntity(stack, entity);
        return stack;
    }

/*    public static ItemStack createItemWithBlockState(BlockState state) {
        ItemStack stack = new ItemStack(ModItems.DOLL_ENTITY_ITEM.get());
        CompoundTag entityTag = new CompoundTag();
        entityTag.put("doll_state", NbtUtils.writeBlockState(state));
        CompoundTag stackTag = stack.();
        stackTag.put(TAG_DOLL_ENTITY, entityTag);
        return stack;
    }*/

    public static void saveDollEntity(ItemStack stack, DollEntity entity) {
        if (!stack.is(ModItems.DOLL_ENTITY_ITEM.get())) {
            return;
        }

        CompoundTag entityTag = new CompoundTag();
        entity.addAdditionalSaveData(entityTag);
        entity.removePhantomRecord(entityTag);

        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, (existingData) -> {
            CompoundTag stackTag = existingData.copyTag();
            stackTag.put(TAG_DOLL_ENTITY, entityTag);
            return CustomData.of(stackTag);
        });
    }

    public static DollEntity getDollEntity(Level level, ItemStack stack) {
        if (!stack.is(ModItems.DOLL_ENTITY_ITEM.get())) {
            return new DollEntity(ModEntities.DOLL.get(), level);
        }

        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        CompoundTag stackTag = null;
        if (customData != null) {
            stackTag = customData.copyTag();
        }
        if (stackTag != null && stackTag.contains(TAG_DOLL_ENTITY)) {
            CompoundTag entityTag = stackTag.getCompound(TAG_DOLL_ENTITY);
            DollEntity entity = new DollEntity(ModEntities.DOLL.get(), level);
            entity.load(entityTag);
            return entity;
        } else {
            return new DollEntity(ModEntities.DOLL.get(), level);
        }
    }

    /*@Override
    rebuild this in client event
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private DollEntityItemRender render = null;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                Minecraft minecraft = Minecraft.getInstance();
                if (render == null) {
                    render = new DollEntityItemRender(minecraft.getBlockEntityRenderDispatcher(), minecraft.getEntityModels());
                }
                return render;
            }
        });
    }*/

    private boolean mayPlace(Player pPlayer, Direction pDirection, ItemStack pItemStack, BlockPos pPos) {
        return !pPlayer.level().isOutsideBuildHeight(pPos) && pPlayer.mayUseItemAt(pPos, pDirection, pItemStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        return InteractionResultHolder.pass(itemStack);
    }
}

