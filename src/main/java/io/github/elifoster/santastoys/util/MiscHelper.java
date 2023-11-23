package io.github.elifoster.santastoys.util;

import java.util.Random;

public class MiscHelper {
    private static Random random = new Random();

    /**
     * @return A random velocity for use in entity break particles. Based on the vanilla egg.
     */
    public static float entityBreakVelocity() {
        return (random.nextFloat() - 0.5f) * 0.08f;
    }
}
