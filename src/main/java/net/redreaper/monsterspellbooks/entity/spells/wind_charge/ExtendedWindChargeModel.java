package net.redreaper.monsterspellbooks.entity.spells.wind_charge;

import net.minecraft.client.model.WindChargeModel;
import net.minecraft.client.model.geom.ModelPart;

public class ExtendedWindChargeModel extends WindChargeModel {
    private static final int ROTATION_SPEED = 16;
    private final ModelPart bone;
    private final ModelPart windCharge;
    private final ModelPart wind;
    public ExtendedWindChargeModel(ModelPart root) {
        super(root);
        this.bone = root.getChild("bone");
        this.wind = this.bone.getChild("wind");
        this.windCharge = this.bone.getChild("wind_charge");
    }

    public void setupAnim(ExtendedWindChargeModel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.windCharge.yRot = -ageInTicks * 16.0F * 0.017453292F;
        this.wind.yRot = ageInTicks * 16.0F * 0.017453292F;
    }
}
