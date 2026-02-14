package com.afoxxvi.asteorbar.utils;

import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public interface PlatformAdapter {
    Logger getLogger();

    boolean isEyeInFluid(Player player);

    boolean isModLoaded(String modId);

    @Nullable
    AppleSkinFoodValues getAppleSkinFoodValues(Player player);

    float getExhaustion(Player player);

    void setExhaustion(Player player, float exhaustion);

    record AppleSkinFoodValues(int hungerIncrement, float saturationIncrement, float healthIncrement) {
    }
}
