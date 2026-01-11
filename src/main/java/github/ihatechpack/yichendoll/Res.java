package github.ihatechpack.yichendoll;

import net.minecraft.resources.ResourceLocation;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2025/9/21 11:59
 */
public class Res {
    public static ResourceLocation rl(String pPath){
        return ResourceLocation.tryBuild(IHateYiChenDoll.MOD_ID,pPath);
    }
}
