package github.ihatechpack.yichendoll.common.block;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 13:11
 */
public class ModBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.Blocks.createBlocks(IHateYiChenDoll.MOD_ID);//.create(Registries.BLOCK, IHateYiChenDoll.MOD_ID);
    public static final List<DeferredHolder<Block,? extends Block>> BLOCKS = new LinkedList<>();

    public static final DeferredHolder<Block,? extends Block> YiChen = REGISTER.register("yichen_mm",Doll::new);

    private static DeferredHolder<Block,? extends Block> create(String name,Supplier<? extends Block> sup){
        var tmp = REGISTER.register(name,sup);
        BLOCKS.add(tmp);
        return tmp;
    }
}
