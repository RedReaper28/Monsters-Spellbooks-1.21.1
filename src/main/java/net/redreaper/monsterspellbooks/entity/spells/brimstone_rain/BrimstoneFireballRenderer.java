package net.redreaper.monsterspellbooks.entity.spells.brimstone_rain;

import io.redspace.ironsspellbooks.entity.spells.fireball.FireballRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Projectile;
import net.redreaper.monsterspellbooks.MonstersSpellbooks;

public class BrimstoneFireballRenderer extends FireballRenderer {
    private final static ResourceLocation BASE_TEXTURE = MonstersSpellbooks.id("textures/entity/brimstone_fire_ball/brimstone_slag.png");
    private final static ResourceLocation[] FIRE_TEXTURES = {
            MonstersSpellbooks.id("textures/entity/brimstone_fire_ball/fire_1.png"),
            MonstersSpellbooks.id("textures/entity/brimstone_fire_ball/fire_2.png"),
            MonstersSpellbooks.id("textures/entity/brimstone_fire_ball/fire_3.png"),
            MonstersSpellbooks.id("textures/entity/brimstone_fire_ball/fire_4.png")
    };

    public BrimstoneFireballRenderer(EntityRendererProvider.Context context, float scale) {
        super(context, scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Projectile entity) {
        return BASE_TEXTURE;
    }

    public ResourceLocation getFireTextureLocation(Projectile entity) {
        int frame = (entity.tickCount / 2) % FIRE_TEXTURES.length;
        return FIRE_TEXTURES[frame];
    }

}