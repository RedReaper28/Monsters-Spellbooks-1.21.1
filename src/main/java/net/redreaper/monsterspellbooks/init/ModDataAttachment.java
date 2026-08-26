package net.redreaper.monsterspellbooks.init;

import com.mojang.serialization.Codec;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.redreaper.monsterspellbooks.effect.StaticEffectData;

public class ModDataAttachment {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, IronsSpellbooks.MODID);

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<StaticEffectData>> STATIC_STRIKES_DATA = ATTACHMENT_TYPES.register("static_strikes_data",
            () -> AttachmentType.builder(StaticEffectData::new).serialize(Codec.INT.xmap(StaticEffectData::new, StaticEffectData::getHitCount), StaticEffectData::hasHitsRemaining).build());

}
