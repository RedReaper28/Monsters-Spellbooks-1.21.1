package net.redreaper.monsterspellbooks.init;

import io.redspace.ironsspellbooks.fluids.NoopFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

import java.util.function.Supplier;

public class ModFluids {
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, MonstersSpellbooks.MOD_ID);
    private static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, MonstersSpellbooks.MOD_ID);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
        FLUID_TYPES.register(eventBus);
    }


    public static final DeferredHolder<FluidType, FluidType> RAW_SANGUINITE_TYPE = FLUID_TYPES.register("raw_sanguinite", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<FluidType, FluidType> PUTRESCENCE_TYPE = FLUID_TYPES.register("putrescence", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<FluidType, FluidType> VOID_FLUID_TYPE = FLUID_TYPES.register("void_fluid", () -> new FluidType(FluidType.Properties.create()));

    public static final DeferredHolder<Fluid, NoopFluid> RAW_SANGUINITE = registerNoop("raw_sanguinite", RAW_SANGUINITE_TYPE::value);
    public static final DeferredHolder<Fluid, NoopFluid> PUTRESCENCE = registerNoop("putrescence", PUTRESCENCE_TYPE::value);
    public static final DeferredHolder<Fluid, NoopFluid> VOID_FLUID = registerNoop("void_fluid", VOID_FLUID_TYPE::value);



    private static DeferredHolder<Fluid, NoopFluid> registerNoop(String name, Supplier<FluidType> fluidType) {
        DeferredHolder<Fluid, NoopFluid> holder = DeferredHolder.create(Registries.FLUID, MonstersSpellbooks.id(name));
        BaseFlowingFluid.Properties properties = new BaseFlowingFluid.Properties(fluidType, holder::value, holder::value).bucket(() -> Items.AIR);
        FLUIDS.register(name, () -> new NoopFluid(properties));
        return holder;
    }
}
