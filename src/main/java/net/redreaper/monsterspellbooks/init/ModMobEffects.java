package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.ThunderstormEffect;
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
            .addAttributeModifier(AttributeRegistry.FIRE_SPELL_POWER, MonstersSpellbooks.id("mobeffect_overheat"), OverheatMobEffect.FIRE_SPELL_POWER_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> BLOODLUST = MOB_EFFECT_DEFERRED_REGISTER.register("bloodlust", () -> new OverheatMobEffect(MobEffectCategory.BENEFICIAL, 4325376)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE, MonstersSpellbooks.id("mobeffect_bloodlust"), BloodLustMobEffect.ATTACK_DAMAGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_bloodlust"), BloodLustMobEffect.ATTACK_SPEED_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_bloodlust"), BloodLustMobEffect.SPEED_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> MADNESS = MOB_EFFECT_DEFERRED_REGISTER.register("madness", () -> new MadnessMobEffect(MobEffectCategory.HARMFUL, 16736559)
            .addAttributeModifier(AttributeRegistry.MAX_MANA, MonstersSpellbooks.id("mobeffect_madness"), MadnessMobEffect.MANA_REDUCTION_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> HEAL_CUT = MOB_EFFECT_DEFERRED_REGISTER.register("heal_cut", HealCutMobEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SPACE_ANCHORED = MOB_EFFECT_DEFERRED_REGISTER.register("space_anchored", SpaceAnchoredMobEffect::new);
    public static final DeferredHolder<MobEffect,MobEffect>  SOUL_ROT=MOB_EFFECT_DEFERRED_REGISTER.register("soul_rot",SoulRotMobEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> BLIZZARD_ASPECT = MOB_EFFECT_DEFERRED_REGISTER.register("blizzard_aspect", () -> new BlizzardAspectMobEffect(MobEffectCategory.BENEFICIAL, 6653361));
    public static final DeferredHolder<MobEffect, MobEffect> CRUSHING_PRESENCE = MOB_EFFECT_DEFERRED_REGISTER.register("crushing_presence", () -> new CrushingPresenceMobEffect(MobEffectCategory.BENEFICIAL, 4798975));

    public static final DeferredHolder<MobEffect, MobEffect> CURSE = MOB_EFFECT_DEFERRED_REGISTER.register("curse", CursedMobEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> PARALYSIS = MOB_EFFECT_DEFERRED_REGISTER.register("paralysis", () -> new ParalysisMobEffect(MobEffectCategory.HARMFUL, 16777025)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, MonstersSpellbooks.id("mobeffect_paralysis"), ParalysisMobEffect.SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> HEAVY_BODY = MOB_EFFECT_DEFERRED_REGISTER.register("heavy_body", () -> new HeavyBodyMobEffect(MobEffectCategory.HARMFUL, 9175256)
            .addAttributeModifier(Attributes.GRAVITY, MonstersSpellbooks.id("mobeffect_heavy_body"), HeavyBodyMobEffect.GRAVITY_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE));
    public static final DeferredHolder<MobEffect, MobEffect> STUNNED = MOB_EFFECT_DEFERRED_REGISTER.register("stunned", () -> new StunnedMobEffect(MobEffectCategory.HARMFUL, 1315355)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_stunned"), StunnedMobEffect.ATTACK_SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> REAPER_ASPECT = MOB_EFFECT_DEFERRED_REGISTER.register("reaper_aspect", () -> new ReaperAspectMobEffect(MobEffectCategory.BENEFICIAL, 57744 ));
    public static final DeferredHolder<MobEffect, MobEffect> BLEEDING = MOB_EFFECT_DEFERRED_REGISTER.register("bleeding", () -> new BleedingMobEffect(MobEffectCategory.HARMFUL, 16711680));
    public static final DeferredHolder<MobEffect, MobEffect> BRIMSTONE_FLAME = MOB_EFFECT_DEFERRED_REGISTER.register("brimstone_flame", () -> new BrimstoneFlameMobEffect(MobEffectCategory.HARMFUL, 14896975));
    public static final DeferredHolder<MobEffect, MobEffect> HEMORRHAGE = MOB_EFFECT_DEFERRED_REGISTER.register("hemorrhage", () -> new HemorrhageMobEffect(MobEffectCategory.HARMFUL, 16711680));
    public static final DeferredHolder<MobEffect, MobEffect> STATIC = MOB_EFFECT_DEFERRED_REGISTER.register("static", () -> new StaticMobEffect(MobEffectCategory.HARMFUL, 3093151));
    public static final DeferredHolder<MobEffect, MobEffect> LETHARGY = MOB_EFFECT_DEFERRED_REGISTER.register("lethargy", () -> new LethargyMobEffect(MobEffectCategory.HARMFUL, 1315355)
            .addAttributeModifier(Attributes.MAX_HEALTH, MonstersSpellbooks.id("mobeffect_lethargy"), LethargyMobEffect.LIFE_DRAIN_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> INFLAMMABLE = MOB_EFFECT_DEFERRED_REGISTER.register("inflammable", () -> new InflammableMobEffect(MobEffectCategory.HARMFUL, 16736038)
            .addAttributeModifier(AttributeRegistry.FIRE_MAGIC_RESIST, MonstersSpellbooks.id("mobeffect_inflammable"), InflammableMobEffect.FIRE_WEAKNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> FROST_COATING = MOB_EFFECT_DEFERRED_REGISTER.register("frost_coating", () -> new FrostCoatingMobEffect(MobEffectCategory.BENEFICIAL, 14678523)
            .addAttributeModifier(Attributes.ARMOR, MonstersSpellbooks.id("mobeffect_frost_coating"), FrostCoatingMobEffect.ARMOR_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(AttributeRegistry.SPELL_RESIST, MonstersSpellbooks.id("mobeffect_frost_coating"), FrostCoatingMobEffect.SPELL_RESISTANCE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> GRAVITY_FORCE = MOB_EFFECT_DEFERRED_REGISTER.register("gravity_force", () -> new GravityForceMobEffect(MobEffectCategory.BENEFICIAL, 9175256)
            .addAttributeModifier(Attributes.ATTACK_DAMAGE, MonstersSpellbooks.id("mobeffect_gravity_force"), GravityForceMobEffect.ATTACK_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_gravity_force"), GravityForceMobEffect.ATTACK_SPEED_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> FEAR = MOB_EFFECT_DEFERRED_REGISTER.register("fear", () -> new InflammableMobEffect(MobEffectCategory.HARMFUL, 2818100)
            .addAttributeModifier(AttributeRegistry.CAST_TIME_REDUCTION, MonstersSpellbooks.id("mobeffect_fear"), FearMobEffect.CAST_TIME_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> DECAYING_TOUCH = MOB_EFFECT_DEFERRED_REGISTER.register("decaying_touch", () -> new DecayingTouch(MobEffectCategory.HARMFUL, 16711680));

    public static final DeferredHolder<MobEffect, MobEffect> SOUL_REND = MOB_EFFECT_DEFERRED_REGISTER.register("soul_rend", () -> new SoulRendMobEffect(MobEffectCategory.HARMFUL, 6354426)
            .addAttributeModifier(AttributeRegistry.MANA_REGEN, MonstersSpellbooks.id("mobeffect_soul_rend"), SoulRendMobEffect.MANA_SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


    public static final DeferredHolder<MobEffect, MobEffect> SPIRIT_FROSTBITE = MOB_EFFECT_DEFERRED_REGISTER.register("spirit_frostbite", () -> new SpiritFrostbiteMobEffect(MobEffectCategory.HARMFUL, 2818100)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, MonstersSpellbooks.id("mobeffect_spirit_frostbite"), SpiritFrostbiteMobEffect.SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(AttributeRegistry.CAST_TIME_REDUCTION, MonstersSpellbooks.id("mobeffect_spirit_frostbite"), SpiritFrostbiteMobEffect.CAST_TIME_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_SPEED, MonstersSpellbooks.id("mobeffect_spirit_frostbite"), SpiritFrostbiteMobEffect.ATTACK_SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(AttributeRegistry.MANA_REGEN, MonstersSpellbooks.id("mobeffect_spirit_frostbite"), SpiritFrostbiteMobEffect.MANA_SLOWNESS_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );



    public static final DeferredHolder<MobEffect, MobEffect> MOUNTAIN_CORPSE = MOB_EFFECT_DEFERRED_REGISTER.register("mountain_corpse", () -> new MountainCorpseMobEffect(MobEffectCategory.BENEFICIAL, 3311322)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.SPEED_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.SCALE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.SCALE_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.ENTITY_RANGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.BLOCK_RANGE_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ATTACK_KNOCKBACK, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.ATTACK_KNOCKBACK_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.KNOCKBACK_RESISTANCE_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE)
            .addAttributeModifier(Attributes.STEP_HEIGHT, MonstersSpellbooks.id("mobeffect_mountain_corpse"), MountainCorpseMobEffect.STEP_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE)


    );






    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }
}
