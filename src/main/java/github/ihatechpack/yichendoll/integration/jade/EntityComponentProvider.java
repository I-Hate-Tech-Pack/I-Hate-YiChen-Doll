package github.ihatechpack.yichendoll.integration.jade;

import github.ihatechpack.yichendoll.Res;
import github.ihatechpack.yichendoll.common.entity.DollEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum EntityComponentProvider implements IEntityComponentProvider {
    INSTANCE;

    public static final ResourceLocation ID = Res.rl("doll_entity");

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig pluginConfig) {
        if (!(accessor.getEntity() instanceof DollEntity dollEntity)) {
            return;
        }
        Block block = dollEntity.getDisplayBlockState().getBlock();
        // same in block
        tooltip.add(Component.translatable("tooltip.yichenovo.doll"));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }
}
