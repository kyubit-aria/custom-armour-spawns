package org.kyubit.entity.util;

import net.minecraft.client.MinecraftClient;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class EntityUtil {
    public static Stream<net.minecraft.entity.Entity> getEntityStream() {
        return StreamSupport.stream(MinecraftClient.getInstance().world.getEntities().spliterator(), true);
    }
}
