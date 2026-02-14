package com.afoxxvi.asteorbar.mixin;

import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerEntity.class)
public abstract class ServerEntityMixin {
    private static final float EPS = 0.01f;

    @Shadow
    @Final
    private Entity entity;

    @Unique
    private float asteorBar$lastAbsorption = -1.0f;

    @Inject(method = "sendChanges", at = @At("TAIL"))
    public void onSendChanges(CallbackInfo ci) {
        Entity selfEntity = entity;
        if (!(selfEntity instanceof LivingEntity livingEntity)) {
            return;
        }
        float currentAbsorption = livingEntity.getAbsorptionAmount();
        if (Math.abs(currentAbsorption - asteorBar$lastAbsorption) > EPS) {
            asteorBar$lastAbsorption = currentAbsorption;
            // Network functionality removed - absorption tracking only
        }
    }
}
