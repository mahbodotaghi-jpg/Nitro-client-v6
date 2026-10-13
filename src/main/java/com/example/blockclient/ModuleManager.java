package com.example.nitroclient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.MinecraftClient;

public final class ModuleManager {
    private final List<ClientModule> modules = new ArrayList<>();

    public void registerDefaults() {
        add("Auto Totem", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Auto Hit Crystal", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Spear Swap", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Elytra Swap", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Static Hitboxes", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Totem Offhand", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Double Anchor", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Hitbox", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Anchor Macro", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Auto Crystal", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Safe Anchor", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Mace Bomber", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Trigger Bot", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Shield Breaker", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Crystal Optimizer", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Mace Swap", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Auto Double Hand", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Hover Totem", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("No Hit Delay", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Aim Assist", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Auto Inv Totem", ClientModule.Category.COMBAT, "Module toggle scaffold");
        add("Auto Jump Reset", ClientModule.Category.COMBAT, "Module toggle scaffold");

        add("Skin Protect", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Reconnect", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Mine", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Walk", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Weather Notifier", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Loot", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Firework", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Cord Snapper", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Clicker", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Sprint", ClientModule.Category.MISC, "Simple sprint helper placeholder");
        add("Freecam", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Tool", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Key Wind Charge", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Key Pearl", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Fast Place", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Eat", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Elytra Glide", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Trap", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Auto Log", ClientModule.Category.MISC, "Module toggle scaffold");
        add("Name Protect", ClientModule.Category.MISC, "Module toggle scaffold");

        add("Suspicious ESP", ClientModule.Category.BASEFINDING, "Module toggle scaffold");
        add("Hole ESP", ClientModule.Category.BASEFINDING, "Module toggle scaffold");
        add("Light Finder", ClientModule.Category.BASEFINDING, "Module toggle scaffold");
        add("Seed Chunk Finder", ClientModule.Category.BASEFINDING, "Module toggle scaffold");
        add("Sus Chunk Finder", ClientModule.Category.BASEFINDING, "Module toggle scaffold");

        add("Player ESP", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Mob ESP", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Fullbright", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Free Look", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Block ESP", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Storage ESP", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Target HUD", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Spotify HUD", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Real Hitbox", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Ore Sim", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Jump Circles", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("HUD", ClientModule.Category.RENDER, "Client HUD toggle");
        add("Swing Speed", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Name Tags", ClientModule.Category.RENDER, "Module toggle scaffold");
        add("Pearl Trajectory", ClientModule.Category.RENDER, "Module toggle scaffold");
    }

    private void add(String name, ClientModule.Category category, String description) {
        modules.add(new ClientModule(name, category, description));
    }

    public List<ClientModule> all() { return Collections.unmodifiableList(modules); }

    public List<ClientModule> inCategory(ClientModule.Category category, String filter) {
        String query = filter == null ? "" : filter.trim().toLowerCase();
        return modules.stream()
            .filter(m -> m.category() == category)
            .filter(m -> query.isEmpty() || m.name().toLowerCase().contains(query))
            .toList();
    }

    public void tick(MinecraftClient client) {
        // Add per-tick behavior for modules here. Keep game-state changes on the client thread.
    }
}
