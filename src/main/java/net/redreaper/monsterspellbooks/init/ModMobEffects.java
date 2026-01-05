package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
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

    public static final DeferredHolder<MobEffect, MobEffect> OVERHEAT = MOB_EFFECT_DEFERRED_REGISTER.register("overheat", () -> new OverheatMobEffect(MobEffectCategory.BENEFICIAL, 16736559)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE, MonstersSpellbooks.id("mobeffect_overheat"), OverheatMobEffect.ATTACK_DAMAGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(AttributeRegistry.SPELL_POWER, MonstersSpellbooks.id("mobeffect_overheat"), OverheatMobEffect.FIRE_SPELL_POWER_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> BLOODLUST = MOB_EFFECT_DEFERRED_REGISTER.register("bloodlust", () -> new OverheatMobEffect(MobEffectCategory.BENEFICIAL, 4325376)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE, MonstersSpellbooks.id("mobeffect_bloodlust"), BloodLustMobEffect.ATTACK_DAMAGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_bloodlust"), BloodLustMobEffect.ATTACK_SPEED_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> MADNESS = MOB_EFFECT_DEFERRED_REGISTER.register("madness", () -> new MadnessMobEffect(MobEffectCategory.HARMFUL, 16736559)
            .addAttributeModifier(AttributeRegistry.MAX_MANA, MonstersSpellbooks.id("mobeffect_madness"), MadnessMobEffect.MANA_REDUCTION_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> HEAL_CUT = MOB_EFFECT_DEFERRED_REGISTER.register("heal_cut", HealCutMobEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> CURSE = MOB_EFFECT_DEFERRED_REGISTER.register("curse", CursedMobEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> PARALYSIS = MOB_EFFECT_DEFERRED_REGISTER.register("paralysis", () -> new ParalysisMobEffect(MobEffectCategory.HARMFUL, 16777025)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, MonstersSpellbooks.id("mobeffect_paralysis"), ParalysisMobEffect.SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> STUNNED = MOB_EFFECT_DEFERRED_REGISTER.register("stunned", () -> new StunnedMobEffect(MobEffectCategory.HARMFUL, 1315355)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_stunned"), StunnedMobEffect.ATTACK_SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> REAPER_ASPECT = MOB_EFFECT_DEFERRED_REGISTER.register("reaper_aspect", () -> new ReaperAspectMobEffect(MobEffectCategory.BENEFICIAL, 57744 ));
    public static final DeferredHolder<MobEffect, MobEffect> BLEEDING = MOB_EFFECT_DEFERRED_REGISTER.register("bleeding", () -> new BleedingMobEffect(MobEffectCategory.HARMFUL, 16711680));
    public static final DeferredHolder<MobEffect, MobEffect> BRIMSTONE_FLAME = MOB_EFFECT_DEFERRED_REGISTER.register("brimstone_flame", () -> new BrimstoneFlameMobEffect(MobEffectCategory.HARMFUL, 14896975));
    public static final DeferredHolder<MobEffect, MobEffect> HEMORRHAGE = MOB_EFFECT_DEFERRED_REGISTER.register("hemorrhage", () -> new HemorrhageMobEffect(MobEffectCategory.HARMFUL, 16711680));
    public static final DeferredHolder<MobEffect, MobEffect> LETHARGY = MOB_EFFECT_DEFERRED_REGISTER.register("lethargy", () -> new LethargyMobEffect(MobEffectCategory.HARMFUL, 1315355)
            .addAttributeModifier(Attributes.MAX_HEALTH, MonstersSpellbooks.id("mobeffect_lethargy"), LethargyMobEffect.LIFE_DRAIN_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> INFLAMMABLE = MOB_EFFECT_DEFERRED_REGISTER.register("inflammable", () -> new InflammableMobEffect(MobEffectCategory.HARMFUL, 16736038)
            .addAttributeModifier(AttributeRegistry.FIRE_MAGIC_RESIST, MonstersSpellbooks.id("mobeffect_inflammable"), InflammableMobEffect.FIRE_WEAKNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> MOUNTAIN_CORPSE = MOB_EFFECT_DEFERRED_REGISTER.register("mountain_corpse", () -> new MountainCorpseMobEffect(MobEffectCategory.BENEFICIAL, 3311322)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.ATTACK_DAMAGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.ATTACK_SPEED_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.SCALE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.SCALE_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.ENTITY_RANGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.BLOCK_RANGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_KNOCKBACK, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.ATTACK_KNOCKBACK_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.KNOCKBACK_RESISTANCE_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(Attributes.GRAVITY, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.GRAVITY_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(AttributeRegistry.SPELL_POWER, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.NATURE_SPELL_POWER_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));






    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }
}
