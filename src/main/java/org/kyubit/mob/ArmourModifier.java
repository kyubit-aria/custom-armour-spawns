package org.kyubit.mob;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;

public class ArmourModifier {
    private String modifierName;
    private float modifierValue;
    private EntityAttribute attribute;
    private EntityAttributeModifier.Operation modifierOperation;
    public ArmourModifier(EntityAttribute attribute,
                          String name,
                          float value,
                          EntityAttributeModifier.Operation operation) {
        this.attribute = attribute;
        this.modifierName = name;
        this.modifierValue = value;
        this.modifierOperation = operation;
    }

    public EntityAttribute getAttribute() {
        return this.attribute;
    }

    public String getModifierName() {
        return this.modifierName;
    }

    public float getModifierValue() {
        return this.modifierValue;
    }

    public EntityAttributeModifier.Operation getModifierOperation() {
        return this.modifierOperation;
    }
}
