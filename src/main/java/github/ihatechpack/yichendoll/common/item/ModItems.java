package github.ihatechpack.yichendoll.common.item;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.common.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 13:26
 */
public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(Registries.ITEM, IHateYiChenDoll.MOD_ID);
    public static final List<DeferredHolder<Item,? extends Item>> ITEMS = new LinkedList<>();

    public static DeferredHolder<Item,Item> DOLL_ENTITY_ITEM = REGISTER.register("item_doll_entity", DollEntityItem::new);

    public static DeferredHolder<Item,BlockItem> aaa = REGISTER.register("yichen_mm",() -> new DollItem(ModBlocks.YiChen.get()));

    private static DeferredHolder<Item,? extends Item> create(String name, Supplier<? extends Item> sup){
        var tmp = REGISTER.register(name,sup);
        ITEMS.add(tmp);
        return tmp;
    }
}
