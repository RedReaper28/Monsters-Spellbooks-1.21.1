package net.redreaper.monsterspellbooks.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.redreaper.monsterspellbooks.init.ModBlocks;
import net.redreaper.monsterspellbooks.init.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);}

    @Override
    protected void generate() {
        dropSelf(ModBlocks.RAW_OCEANITE_BLOCK.get());
        dropSelf(ModBlocks.OCEANITE_BLOCK.get());
        dropSelf(ModBlocks.ROSE_GOLD_BLOCK.get());
        dropSelf(ModBlocks.POSEIDON_PEARL_BLOCK.get());
        dropSelf(ModBlocks.RAW_ORICHALCUM_BLOCK.get());
        dropSelf(ModBlocks.ORICHALCUM_BLOCK.get());
        dropSelf(ModBlocks.DEATHSILVER_BLOCK.get());
        dropSelf(ModBlocks.SANGUINITE_BLOCK.get());
        dropSelf(ModBlocks.DWARVEN_ALLOY_BLOCK.get());
        dropSelf(ModBlocks.WHITE_GOLD_BLOCK.get());
        dropSelf(ModBlocks.RAW_SCORCHED_METAL_BLOCK.get());
        dropSelf(ModBlocks.SCORCHED_METAL_BLOCK.get());
        dropSelf(ModBlocks.RAW_VILE_IRON_BLOCK.get());
        dropSelf(ModBlocks.VILE_IRON_BLOCK.get());
        dropSelf(ModBlocks.NETHER_PYRITE_BLOCK.get());
        dropSelf(ModBlocks.NETHER_RUBY_BLOCK.get());
        dropSelf(ModBlocks.SPECTRITE_BLOCK.get());
        dropSelf(ModBlocks.WITHER_ALLOY_BLOCK.get());
        dropSelf(ModBlocks.BLAZESTEEL_BLOCK.get());

        dropSelf(ModBlocks.VILESTONE.get());

        add(ModBlocks.OCEANITE_ORE_CLAY.get(),
                block -> createOreDrop(ModBlocks.OCEANITE_ORE_CLAY.get(), ModItems.RAW_OCEANITE.get()));
        add(ModBlocks.OCEANITE_ORE_SAND.get(),
                block -> createOreDrop(ModBlocks.OCEANITE_ORE_SAND.get(), ModItems.RAW_OCEANITE.get()));
        add(ModBlocks.OCEANITE_ORE_GRAVEL.get(),
                block -> createOreDrop(ModBlocks.OCEANITE_ORE_SAND.get(), ModItems.RAW_OCEANITE.get()));

        add(ModBlocks.POSEIDON_PEARL_ORE.get(),
                block -> createOreDrop(ModBlocks.POSEIDON_PEARL_BLOCK.get(), ModItems.POSEIDON_PEARL.get()));
        add(ModBlocks.ORICHALCUM_ORE.get(),
                block -> createOreDrop(ModBlocks.ORICHALCUM_ORE.get(), ModItems.RAW_ORICHALCUM.get()));
        add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get(), ModItems.RAW_ORICHALCUM.get()));
        add(ModBlocks.SCORCHED_METAL_ORE.get(),
                block -> createOreDrop(ModBlocks.SCORCHED_METAL_ORE.get(), ModItems.RAW_SCORCHED_METAL.get()));
        add(ModBlocks.VILE_IRON_ORE.get(),
                block -> createOreDrop(ModBlocks.VILE_IRON_ORE.get(), ModItems.RAW_VILE_IRON.get()));
        add(ModBlocks.NETHER_PYRITE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.NETHER_PYRITE_ORE.get(), ModItems.RAW_VILE_IRON.get(),2,6));
        add(ModBlocks.NETHER_RUBY_ORE.get(),
                block -> createOreDrop(ModBlocks.VILE_IRON_ORE.get(), ModItems.RAW_VILE_IRON.get()));
        add(ModBlocks.VOID_MATTER_ORE.get(),
                block -> createOreDrop(ModBlocks.VOID_MATTER_ORE.get(), ModItems.VOID_MATTER.get()));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
