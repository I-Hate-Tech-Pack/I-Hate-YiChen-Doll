package github.ihatechpack.yichendoll.common;

import github.ihatechpack.yichendoll.IHateYiChenDoll;
import github.ihatechpack.yichendoll.Res;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/12 13:18
 */
public class ModSounds {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(Registries.SOUND_EVENT, IHateYiChenDoll.MOD_ID);

    public static final DeferredHolder<SoundEvent,SoundEvent> DOLL = regSound("block.doll",16);

    public static DeferredHolder<SoundEvent,SoundEvent> regSound(String name,int prange){
        return REGISTER.register(name,() -> SoundEvent.createFixedRangeEvent(Res.rl(name), prange));
    }
}
