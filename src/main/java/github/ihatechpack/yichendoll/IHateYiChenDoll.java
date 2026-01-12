package github.ihatechpack.yichendoll;

import github.ihatechpack.yichendoll.common.ModCreativeTab;
import github.ihatechpack.yichendoll.common.ModSounds;
import github.ihatechpack.yichendoll.common.block.ModBlocks;
import github.ihatechpack.yichendoll.common.item.ModItems;
import github.ihatechpack.yichendoll.datagen.DataGen;
import github.ihatechpack.yichendoll.integration.curios.CuriosExtension;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 13:08
 */
@Mod(IHateYiChenDoll.MOD_ID)
public class IHateYiChenDoll {
    public static final String MOD_ID = "ihateyichendoll";
    public static IHateYiChenDoll instance;

    private final String[] dolls = {
            "yichen_mm",
            "howxu",
            "yuanxi_19"
    };

    public IHateYiChenDoll(IEventBus modBus){
        // 单例模式
        instance = this;
        // common reg
        ModBlocks.register(modBus,dolls);
        ModItems.register(modBus,dolls);
        ModCreativeTab.REGISTER.register(modBus);
        ModSounds.REGISTER.register(modBus);
        // client reg
        IHateYiChenDollClient.init(modBus);
        DataGen.init(modBus);
        // registry jade
        // registry curios
        modBus.addListener(IHateYiChenDoll::onSetupEvent);
    }

    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(CuriosExtension::commonSetup);
    }

}
