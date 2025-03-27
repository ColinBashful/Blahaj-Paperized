package de.cjdev.blahajPaperized;

import de.cjdev.papermodapi.api.item.CustomItem;
import de.cjdev.papermodapi.api.util.ActionResult;
import de.cjdev.papermodapi.api.util.ItemUsageContext;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import io.papermc.paper.inventory.tooltip.TooltipContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CuddlyItem extends CustomItem {

    private final Component tooltip;

    public CuddlyItem(Settings settings, String tooltip) {
        super(settings.maxCount(1).equippableUnswappable(EquipmentSlot.HEAD).attributeModifiers(createAttributeModifiers()));
        this.tooltip = tooltip == null ? null : Component.translatable(tooltip).color(TextColor.color(0xAAAAAA));
    }

    @Override
    public @NotNull ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() == null)
            return ActionResult.FAIL;
        return this.use(context.getWorld(), context.getPlayer(), context.getHand());
    }

    @Override
    public @NotNull ActionResult use(World world, Player player, EquipmentSlot hand) {
        player.getWorld().playSound(BlahajSounds.getRandomSqueak(((CraftWorld)world).getHandle().getRandom()), player);
        return ActionResult.CONSUME;
    }

    @Override
    public void onCraftByPlayer(ItemStack stack, World world, Player player) {
        if (player != null) {
            BlahajPaperized.OWNER.set(stack, player.getName());
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, List<Component> tooltip, TooltipContext context) {
        if (this.tooltip != null) {
            tooltip.add(this.tooltip);
        }

        BlahajPaperized.OWNER.getOptional(stack).ifPresent(ownerName -> {
            Component customName = stack.getData(DataComponentTypes.CUSTOM_NAME);
            if(customName == null)
                tooltip.add(Component.translatable("tooltip.blahaj.owner.craft", Component.text(ownerName)).color(TextColor.color(0xAAAAAA)));
            else
                tooltip.add(Component.translatable("tooltip.blahaj.owner.rename", customName, Component.text(ownerName)).color(TextColor.color(0xAAAAAA)));
        });
    }

    public static final NamespacedKey MINING_SPEED_MODIFIER_ID = BlahajPaperized.key("mining_speed");
    public static final NamespacedKey BASE_ATTACK_DAMAGE_MODIFIER_ID = BlahajPaperized.key("base_attack_damage");

    public static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.itemAttributes()
                .addModifier(Attribute.BLOCK_BREAK_SPEED, new AttributeModifier(MINING_SPEED_MODIFIER_ID, -3.0, AttributeModifier.Operation.ADD_SCALAR), EquipmentSlotGroup.MAINHAND)
                .addModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, -2.0, AttributeModifier.Operation.ADD_SCALAR), EquipmentSlotGroup.MAINHAND)
                .build();
    }

}
