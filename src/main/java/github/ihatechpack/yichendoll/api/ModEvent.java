package github.ihatechpack.yichendoll.api;

/**
 * @description: TODO
 * @author: HowXu
 * @date: 2026/1/13 16:09
 */
public @interface ModEvent {
    enum Side {ServerSide,ClientSide}
    Side side() default Side.ClientSide;
}
