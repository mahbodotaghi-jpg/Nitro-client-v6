# NitroClient — Fabric 1.21.4 starter

A client-side ClickGUI starter inspired by the supplied screenshot.

## Included
- Right Shift opens/closes the GUI.
- Four module panels: Combat, Misc, Basefinding, Render.
- Search field filters module names across panels.
- Click a row to toggle its module state.
- Module registry is separate from the GUI so actual behavior can be added incrementally.

The fifth concept, Search, is represented by the search panel/input rather than a separate module category.

## Build
1. Install JDK 21.
2. Install Gradle, or generate a Gradle wrapper with `gradle wrapper`.
3. From this folder run `gradle build`.
4. The jar will be in `build/libs/`.
5. Put the jar in your Fabric 1.21.4 `mods` folder with Fabric API installed.

## Important
This is a GUI/module-framework starter. The listed modules are registered as toggles, but most do not perform gameplay actions yet. Implement behavior in `ModuleManager.tick()` or dedicated module classes, and use the client thread for Minecraft state changes. Test in singleplayer or a private server where you have permission.
