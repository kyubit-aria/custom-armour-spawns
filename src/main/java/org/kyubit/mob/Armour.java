package org.kyubit.mob;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Armour {
    ItemStack armourPiece;
    List<ArmourModifier> modifiers;
    EquipmentSlot slot;
    public Armour(ItemStack armourPiece, EquipmentSlot slot) {
        this.armourPiece = armourPiece;
        modifiers = new ArrayList<>();
        if(modifiers != null) {
            for(ArmourModifier modifier : modifiers) {
                this.modifiers.add(modifier);
            }
        }
        this.slot = slot;
    }

    public Armour(ItemStack armourPiece, List<ArmourModifier> modifiers, EquipmentSlot slot) {
        this.armourPiece = armourPiece;
        modifiers = new ArrayList<>();
        if(modifiers != null) {
            for(ArmourModifier modifier : modifiers) {
                this.modifiers.add(modifier);
            }
        }
        this.slot = slot;
    }

    public void addModifier(ArmourModifier modifier) {
        this.modifiers.add(modifier);
    }

    public void addModifiers(List<ArmourModifier> modifiers) {
        this.modifiers.addAll(modifiers);
    }

    public void removeModifier(ArmourModifier modifier) {
        this.modifiers.remove(modifier);
    }

    private ItemStack applyModifiers() {
        ItemStack armourPiece = this.armourPiece.copy();
        if(modifiers.isEmpty()) {
            return armourPiece;
        }
        for (ArmourModifier modifier : this.modifiers) {
            Armourer.modifyArmour(armourPiece, modifier, slot);
        }
        return armourPiece;
    }

    public ItemStack getArmourPiece() {
        return this.applyModifiers();
    }
}
