package com.afoxxvi.asteorbar;

import com.afoxxvi.asteorbar.key.KeyBinding;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class AsteorBarFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(KeyBinding.TOGGLE_OVERLAY);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            KeyBinding.handleKeyInput();
        });
    }
}
