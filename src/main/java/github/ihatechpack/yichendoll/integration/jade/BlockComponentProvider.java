package github.ihatechpack.yichendoll.integration.jade;

import github.ihatechpack.yichendoll.Res;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum BlockComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    public static final ResourceLocation ID =  Res.rl("doll_block");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig pluginConfig) {
        Block block = accessor.getBlock();
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        ChatFormatting formatting;
        // def tips show
        /* int size = DollRegistryEvent.Jade_Plugin_tips.get(ForgeRegistries.BLOCKS.getKey(block).getPath()).size() + 1;
        int level = DollRegistryEvent.Jade_Plugin_levels.get(ForgeRegistries.BLOCKS.getKey(block).getPath());
        switch (level){
            case 1 -> {
                formatting = ChatFormatting.GREEN;
            }
            case 2 -> {
                formatting = ChatFormatting.BLUE;
            }
            case 3 -> {
                formatting = ChatFormatting.GOLD;
            }
            default -> {
                formatting = ChatFormatting.WHITE;
            }
        } */
        tooltip.add(Component.translatable("tooltip.yichenovo.doll"));// .withStyle(formatting));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }
}
