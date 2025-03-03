package net.thefluffycart.litavis.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
public class TerraformerItem extends ToolItem {
    public static final Identifier BASE_REACH_RANGE_MODIFIER_ID = Identifier.ofVanilla("base_player_block_interaction_range");

    public TerraformerItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers(int baseReachRange) {
        return AttributeModifiersComponent.builder().add(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(BASE_REACH_RANGE_MODIFIER_ID, (float)baseReachRange, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.OFFHAND).build();
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return super.useOnBlock(context);
    }
}


