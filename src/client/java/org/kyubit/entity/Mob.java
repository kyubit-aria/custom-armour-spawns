package org.kyubit.entity;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.PassiveEntity;
import org.kyubit.entity.util.EntityUtil;

import java.util.stream.Stream;

public class Mob {
    public static Mob INSTANCE;
    public static Stream<MobEntity> getHostileMobs() {
        return EntityUtil.getEntityStream()
                .filter(e -> e instanceof Monster).map(e -> (MobEntity)e);
    }

    public static Stream<PassiveEntity> getPassiveMobs() {
        return EntityUtil.getEntityStream()
                .filter(e -> e instanceof PassiveEntity).map(e -> (PassiveEntity)e);
    }
}
