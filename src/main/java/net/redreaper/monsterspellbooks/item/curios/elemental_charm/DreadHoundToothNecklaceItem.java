package net.redreaper.monsterspellbooks.item.curios.elemental_charm;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironslib.registry.IronsLibRegistries;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.compat.Curios;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.items.curios.FlatCooldownPassiveAbilityCurio;
import net.acetheeldritchking.aces_spell_utils.utils.ASRarities;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.redreaper.monsterspellbooks.init.ModItems;
import top.theillusivec4.curios.api.SlotContext;

@EventBusSubscriber
public class DreadHoundToothNecklaceItem extends FlatCooldownPassiveAbilityCurio {
    public DreadHoundToothNecklaceItem() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).fireResistant().rarity(ASRarities.ACCURSED_RARITY_PROXY.getValue()), Curios.NECKLACE_SLOT);
    }

    @Override
    protected int getCooldownTicks() {
        return 15 * 20;
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(AttributeRegistry.BLOOD_SPELL_POWER, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attr.put(IronsLibRegistries.AttributeRegistry.HEALING_RECEIVED, new AttributeModifier(id, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return attr;
    }

    @SubscribeEvent
    public static void refundMana(SpellOnCastEvent event)
    {
        var originalManaCost = event.getOriginalManaCost();
        var spell = SpellRegistry.getSpell(event.getSpellId());
        var caster = event.getEntity();


        var CURIOS = ((DreadHoundToothNecklaceItem) ModItems.DREADHOUND_TOOTH_NECKLACE.get());
        if (CURIOS.isEquippedBy(caster))
        {
            if (spell.getSchoolType() == SchoolRegistry.BLOOD.get())
            {
                caster.hurt(DamageSources.get(caster.level(), ISSDamageTypes.HEARTSTOP), originalManaCost*0.1f);
                int newManaCost = originalManaCost / 3;
                event.setManaCost(newManaCost);
            }
        }
    }

}
