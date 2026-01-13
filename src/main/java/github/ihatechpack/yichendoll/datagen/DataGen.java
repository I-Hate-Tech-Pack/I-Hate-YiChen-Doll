package github.ihatechpack.yichendoll.datagen;

import com.ibm.icu.impl.locale.LocaleDistance;
import github.ihatechpack.yichendoll.IHateYiChenDollClient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.model.CompositeModel;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/11 14:49
 */
public class DataGen {

    public static void init(IEventBus mod){
        mod.addListener(DataGen::gatherData);
    }

    private static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var existingFileHelper = event.getExistingFileHelper();
        var pack = generator.getPackOutput();
        var register = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new BlockStateGenerator(pack, existingFileHelper));
        generator.addProvider(event.includeClient(),new ItemModelGenerator(pack,existingFileHelper));
        generator.addProvider(event.includeClient(),new RecipesGenerator(pack,register));
    }
}
