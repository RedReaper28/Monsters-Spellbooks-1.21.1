package net.redreaper.monsterspellbooks.mixins.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.redreaper.monsterspellbooks.init.ModEntities;
import net.redreaper.monsterspellbooks.utils.ModCustomRaidMember;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Raid.RaiderType.class)
@SuppressWarnings({"ShadowTarget", "InvokerTarget"})
public class AddCustomRaidMemberMixin
{
    @Invoker("<init>")
    private static Raid.RaiderType newRaidMember(
            String internalName,
            int internalId,
            EntityType<? extends Raider> entityType,
            int[] countInWave
    ) {
        throw new AssertionError();
    }

    @Shadow
    @Final
    @Mutable
    private static Raid.RaiderType[] $VALUES;

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTSTATIC,
                    target = "Lnet/minecraft/world/entity/raid/Raid$RaiderType;$VALUES:[Lnet/minecraft/world/entity/raid/Raid$RaiderType;",
                    shift = At.Shift.AFTER
            )
    )
    private static void takesapillage$addCustomRaidMembers(CallbackInfo ci) {
        var raidMembers = new ArrayList<>(Arrays.asList($VALUES));
        var lastRaidMember = raidMembers.get(raidMembers.size() - 1);

        var enchanterRaidMember = newRaidMember(
                ModCustomRaidMember.ENCHANTER_INTERNAL_NAME,
                lastRaidMember.ordinal() + 3,
                ModEntities.ILLAGER_ENCHANTER.get(),
                ModCustomRaidMember.ENCHANTER_COUNT_IN_WAVES
        );
        ModCustomRaidMember.ENCHANTER = enchanterRaidMember;
        raidMembers.add(enchanterRaidMember);

        var iceologerRaidMember = newRaidMember(
                ModCustomRaidMember.ICEOLOGER_INTERNAL_NAME,
                lastRaidMember.ordinal() + 3,
                ModEntities.ILLAGER_ICEOLOGER.get(),
                ModCustomRaidMember.ICEOLOGER_COUNT_IN_WAVES
        );
        ModCustomRaidMember.ICEOLOGER = iceologerRaidMember;
        raidMembers.add(iceologerRaidMember);

        $VALUES = raidMembers.toArray(new Raid.RaiderType[0]);
    }
}
