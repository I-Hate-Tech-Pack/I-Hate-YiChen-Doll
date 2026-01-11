package github.ihatechpack.yichendoll.common.entity;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 13:46
 */
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, IHateYiChenDoll.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, IHateYiChenDoll.MOD_ID);

    public static DeferredHolder<EntityType<?>, EntityType<DollEntity>> DOLL = ENTITY_TYPES.register("doll_entity", () ->
            EntityType.Builder.<DollEntity>of(DollEntity::new, MobCategory.MISC)
                    .sized(0.75f, 0.75f)
                    .clientTrackingRange(10)
                    .build("doll_entity"));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return builder.build(id);
    }
}
