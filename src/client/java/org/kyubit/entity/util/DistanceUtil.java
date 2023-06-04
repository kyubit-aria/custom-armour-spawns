package org.kyubit.entity.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.stream.Stream;

public class DistanceUtil {
    public static MobEntity findClosestEntity(Stream<MobEntity>  stream) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return null;

        return stream.min((e1, e2) -> {
            double d1 = e1.getPos().distanceTo(player.getPos());
            double d2 = e2.getPos().distanceTo(player.getPos());
            return Double.compare(d1, d2);
        }).orElse(null);
    }
}
