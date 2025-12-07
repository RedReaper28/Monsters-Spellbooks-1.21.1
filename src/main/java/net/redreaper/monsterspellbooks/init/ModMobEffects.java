package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.SpiderAspectEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;
import net.redreaper.monsterspellbooks.effect.*;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect>MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, MonstersSpellbooks.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> OVERHEAT = MOB_EFFECT_DEFERRED_REGISTER.register("overheat", () -> new OverheatMobEffect(MobEffectCategory.BENEFICIAL, 3311322)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE, MonstersSpellbooks.id("mobeffect_overheat"), OverheatMobEffect.ATTACK_DAMAGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(AttributeRegistry.SPELL_POWER, MonstersSpellbooks.id("mobeffect_overheat"), OverheatMobEffect.FIRE_SPELL_POWER_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> MADNESS = MOB_EFFECT_DEFERRED_REGISTER.register("madness", () -> new MadnessMobEffect(MobEffectCategory.HARMFUL, 3311322)
            .addAttributeModifier(AttributeRegistry.MAX_MANA, MonstersSpellbooks.id("mobeffect_madness"), MadnessMobEffect.MANA_REDUCTION_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> HEAL_CUT = MOB_EFFECT_DEFERRED_REGISTER.register("heal_cut", HealCutMobEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> CURSE = MOB_EFFECT_DEFERRED_REGISTER.register("curse", CursedMobEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> PARALYSIS = MOB_EFFECT_DEFERRED_REGISTER.register("paralysis", () -> new ParalysisMobEffect(MobEffectCategory.HARMFUL, 3311322)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, MonstersSpellbooks.id("mobeffect_paralysis"), ParalysisMobEffect.SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> STUNNED = MOB_EFFECT_DEFERRED_REGISTER.register("stunned", () -> new StunnedMobEffect(MobEffectCategory.HARMFUL, 3311322)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_stunned"), StunnedMobEffect.ATTACK_SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> REAPER_ASPECT = MOB_EFFECT_DEFERRED_REGISTER.register("reaper_aspect", () -> new ReaperAspectMobEffect(MobEffectCategory.BENEFICIAL, 4800826));


    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }
}
