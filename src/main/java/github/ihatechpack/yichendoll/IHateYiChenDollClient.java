package github.ihatechpack.yichendoll;

import github.ihatechpack.yichendoll.integration.curios.CuriosExtension;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 14:31
 */
public class IHateYiChenDollClient {

    public static void init(IEventBus mod){
        mod.addListener(CuriosExtension::addEntityLayers);
    }

}
