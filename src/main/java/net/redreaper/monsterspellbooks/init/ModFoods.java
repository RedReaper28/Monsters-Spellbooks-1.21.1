package net.redreaper.monsterspellbooks.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoods {
    public static final FoodProperties LEMON=new FoodProperties.Builder().nutrition(4)
            .saturationModifier(2.4f).build();
    public static final FoodProperties LEMON_PIE=new FoodProperties.Builder().nutrition(8)
            .saturationModifier(4.8f).build();
    public static final FoodProperties ROCK_CANDY=new FoodProperties.Builder().nutrition(4)
            .saturationModifier(5).effect(()->new MobEffectInstance(MobEffects.DIG_SPEED,5000),1f)
            .effect(()->new MobEffectInstance(MobEffects.NIGHT_VISION, 2400, 1), 1F)
            .effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEdible().build();
    public static final FoodProperties ROCK_CANDY_SYRUP=new FoodProperties.Builder().nutrition(4)
            .saturationModifier(5).effect(()->new MobEffectInstance(MobEffects.DIG_SPEED,5000,1),1f)
            .effect(()->new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0), 1F)
            .effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 6000, 0), 1.0F).alwaysEdible().build();
    public static final FoodProperties ROSE_GOLD_APPLE=new FoodProperties.Builder().nutrition(4)
            .saturationModifier(5).effect(()->new MobEffectInstance(MobEffects.REGENERATION,5000,2),1f)
            .effect(()->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0), 1F)
            .effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 6000, 0), 1.0F).alwaysEdible().build();
    public static final FoodProperties GOLDEN_COD=new FoodProperties.Builder().nutrition(4)
            .saturationModifier(2).effect(()->new MobEffectInstance(MobEffects.DOLPHINS_GRACE,60),100f).build();
    public static final FoodProperties COOKED_GOLDEN_COD=new FoodProperties.Builder().nutrition(4)
            .saturationModifier(5).effect(()->new MobEffectInstance(MobEffects.CONDUIT_POWER,5000),1f)
            .effect(()->new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1F)
            .effect(()->new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEdible().build();
    public static final FoodProperties HELL_PEPPER=new FoodProperties.Builder().nutrition(1)
            .saturationModifier(0.1f).effect(()->new MobEffectInstance(MobEffects.HARM,1),1f)
            .effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1F).alwaysEdible().build();
    public static final FoodProperties SOUL_PEPPER=new FoodProperties.Builder().nutrition(1)
            .saturationModifier(0.1f).effect(()->new MobEffectInstance(MobEffects.HARM,1),1f)
            .effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1F).alwaysEdible().build();
    public static final FoodProperties BASALT_RADISH = (new FoodProperties.Builder()).nutrition(4)
            .saturationModifier(0.8F).build();
    public static final FoodProperties NETHER_CORN = (new FoodProperties.Builder()).nutrition(4)
            .saturationModifier(0.8F).build();
    public static final FoodProperties NETHER_TORTILLA = (new FoodProperties.Builder()).nutrition(6)
            .saturationModifier(0.8F).build();
    public static final FoodProperties NETHER_TORTILLA_CHIPS = (new FoodProperties.Builder()).nutrition(4)
            .saturationModifier(0.8F).fast().build();
    public static final FoodProperties PAN_DE_MUERTO = (new FoodProperties.Builder()).nutrition(6)
            .saturationModifier(0.6F).build();
    public static final FoodProperties HOGLIN_CORN_DOG = (new FoodProperties.Builder()).nutrition(8)
            .saturationModifier(1.0F).usingConvertsTo(Items.STICK).build();
    public static final FoodProperties MAGMA_ELOTE=new FoodProperties.Builder().nutrition(8)
            .saturationModifier(0.8F).effect(()->new MobEffectInstance(MobEffects.HARM,1),1f)
            .effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 0), 1F).alwaysEdible().usingConvertsTo(Items.STICK).build();
    public static final FoodProperties BATTLE_TACO=new FoodProperties.Builder().nutrition(4)
            .saturationModifier(5).effect(()->new MobEffectInstance(MobEffects.FIRE_RESISTANCE,5000),1f)
            .effect(()->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0), 1F)
            .effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 1.0F).alwaysEdible().build();
    public static final FoodProperties GOLD_TRUFFLE_SLICE = (new FoodProperties.Builder()).nutrition(10)
            .saturationModifier(1.5F).build();
}
