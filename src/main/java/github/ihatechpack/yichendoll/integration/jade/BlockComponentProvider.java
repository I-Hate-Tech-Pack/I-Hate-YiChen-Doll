package github.ihatechpack.yichendoll.integration.jade;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.Res;
import github.ihatechpack.yichendoll.common.block.Doll;
import github.ihatechpack.yichendoll.common.item.DollItem;
import github.ihatechpack.yichendoll.common.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum BlockComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    public static final ResourceLocation ID =  Res.rl("doll_block");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig pluginConfig) {
        if (accessor.getBlock() instanceof Doll doll){
            int it = ((DollItem)ModItems.ITEMS.get(doll.getReg_name()).get()).getTooltip_size();
            ChatFormatting formatting = switch (((DollItem)ModItems.ITEMS.get(doll.getReg_name()).get()).getRarity()){
                case 1 -> ChatFormatting.AQUA;
                case 2 -> ChatFormatting.GREEN;
                case 3 -> ChatFormatting.GOLD;
                default -> ChatFormatting.LIGHT_PURPLE;
            };
            for (int i = 0; i < it; i++) {
                tooltip.add(Component.translatable("item."+ IHateYiChenDoll.MOD_ID+".doll_tip."+doll.getReg_name()+"."+i).withStyle(formatting));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }
}
