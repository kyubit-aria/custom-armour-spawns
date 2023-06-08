package org.kyubit.mob;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import org.kyubit.ArmourCustomSpawns;

import java.util.List;
import java.util.UUID;

public class Armourer {
    private static ArmourModifier hyperHealth = new ArmourModifier(
            EntityAttributes.GENERIC_MAX_HEALTH,
            "Hyper Health",
            10,
            EntityAttributeModifier.Operation.ADDITION
    );
    private static ArmourModifier hyperArmour = new ArmourModifier(
            EntityAttributes.GENERIC_ARMOR,
            "Hyper Armour",
            20,
            EntityAttributeModifier.Operation.ADDITION
    );
    private static ArmourModifier hyperKnockbackResistance = new ArmourModifier(
            EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
            "Hyper Knockback Resistance",
            20,
            EntityAttributeModifier.Operation.ADDITION
    );
    private static ArmourModifier hyperSpeed = new ArmourModifier(
            EntityAttributes.GENERIC_MOVEMENT_SPEED,
            "Hyper Speed",
            0.20f,
            EntityAttributeModifier.Operation.MULTIPLY_BASE
    );

    private static List<ArmourModifier> hyperArmourModifiers = List.of(
            hyperHealth,
            hyperArmour,
            hyperKnockbackResistance,
            hyperSpeed
    );

    public static void equipMobWithArmour(MobEntity mobEntity) {
        if (mobEntity == null) {
            return;
        }
        ZombieEntity zombie = new ZombieEntity(ArmourCustomSpawns.theWorld);
        zombie.refreshPositionAndAngles(mobEntity.getBlockPos(), 0, 0);

        if (!ArmourCustomSpawns.theWorld.isClient) {
            ItemStack helmet =new ItemStack(Items.DIAMOND_HELMET);
            ItemStack chestplate = new ItemStack(Items.DIAMOND_CHESTPLATE);
            ItemStack leggings = new ItemStack(Items.DIAMOND_LEGGINGS);
            ItemStack boots = new ItemStack(Items.DIAMOND_BOOTS);

            Armour testHelmet = new Armour(helmet, EquipmentSlot.HEAD);
            Armour testChestplate = new Armour(chestplate, EquipmentSlot.CHEST);
            Armour testLeggings = new Armour(leggings, EquipmentSlot.LEGS);
            Armour testBoots = new Armour(boots, EquipmentSlot.FEET);

            testHelmet.addModifiers(hyperArmourModifiers);
            testChestplate.addModifiers(hyperArmourModifiers);
            testLeggings.addModifiers(hyperArmourModifiers);
            testBoots.addModifiers(hyperArmourModifiers);

            LivingEntity entity = (LivingEntity) ArmourCustomSpawns.theWorld.getEntity(mobEntity.getUuid());
            entity.equipStack(EquipmentSlot.HEAD, testHelmet.getArmourPiece());
            entity.equipStack(EquipmentSlot.CHEST, testChestplate.getArmourPiece());
            entity.equipStack(EquipmentSlot.LEGS, testLeggings.getArmourPiece());
            entity.equipStack(EquipmentSlot.FEET, testBoots.getArmourPiece());
            entity.addStatusEffect(
                    new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 2, 200));
            ArmourCustomSpawns.LOGGER.info(String.valueOf(entity.getHealth()));
        } else
        {
            System.out.println("Client side");
        }
    }

    public static void modifyArmour(ItemStack items,
                                    ArmourModifier armourModifier,
                                    EquipmentSlot slot) {
        items.addAttributeModifier(
                armourModifier.getAttribute(),
                new EntityAttributeModifier(
                        UUID.randomUUID(),
                        armourModifier.getModifierName(),  // The name of the modifier
                        armourModifier.getModifierValue(), // The armor bonus
                        armourModifier.getModifierOperation() // Operation
                ),
                slot
        );
    }

}
