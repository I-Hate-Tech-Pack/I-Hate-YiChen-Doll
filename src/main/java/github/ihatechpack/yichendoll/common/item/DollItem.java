package github.ihatechpack.yichendoll.common.item;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.common.block.Doll;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import java.util.List;

public class DollItem extends BlockItem {
    private final int tooltip_size;
    private final int rarity;

    public DollItem(Block block, int tooltip_size, int rarity) {
        super(block, new Properties().rarity(switch (rarity){
            case 1->Rarity.UNCOMMON;
            case 2->Rarity.RARE;
            case 3->Rarity.EPIC;
            default -> Rarity.COMMON;
        }));
        this.tooltip_size = tooltip_size;
        this.rarity = rarity;
    }

    public int getTooltip_size() {
        return tooltip_size;
    }

    public int getRarity() {
        return rarity;
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        for (int i = 0; i < tooltip_size; i++) {
            tooltipComponents.add(Component.translatable("item."+ IHateYiChenDoll.MOD_ID+".doll_tip."+((Doll)this.getBlock()).getReg_name()+"."+i).withStyle(
                    switch (this.rarity){
                        case 1 -> ChatFormatting.AQUA;
                        case 2 -> ChatFormatting.GREEN;
                        case 3 -> ChatFormatting.GOLD;
                        default -> ChatFormatting.LIGHT_PURPLE;
                    }
            ));
        }
    }
}
