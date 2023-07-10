package net.blumbo.fireworkcontrol.mixin;

import net.blumbo.fireworkcontrol.FireworkControl;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin {

    @ModifyArg(method = "explode", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float modifyDamage(float damage) {
        return damage * (FireworkControl.damagePercentage * 0.01F);
    }
}
