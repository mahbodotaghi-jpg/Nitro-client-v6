package com.example.nitroclient;

import java.util.List;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public final class ClickGuiScreen extends Screen {
    private static final int PANEL_W = 190;
    private static final int PANEL_GAP = 12;
    private static final int HEADER_H = 25;
    private static final int ROW_H = 25;
    private static final int PANEL_BG = 0xE61A1B20;
    private static final int ROW_BG = 0xFF202127;
    private static final int CYAN = 0xFF76C8FF;
    private static final int WHITE = 0xFFE5E5E5;
    private static final int MUTED = 0xFF9B9BA3;

    private ClientModule.Category selectedCategory = ClientModule.Category.COMBAT;
    private TextFieldWidget search;
    private int panelY = 34;
    private int panelX;

    public ClickGuiScreen() {
        super(Text.literal("NitroClient"));
    }

    @Override
    protected void init() {
        panelX = Math.max(8, (width - (PANEL_W * 4 + PANEL_GAP * 3)) / 2);
        search = new TextFieldWidget(textRenderer, width - 208, 35, 190, 24, Text.literal("Search modules"));
        search.setPlaceholder(Text.literal("Search modules..."));
        search.setMaxLength(48);
        addDrawableChild(search);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        context.fill(0, 0, width, height, 0x55202432);

        ClientModule.Category[] cats = {
            ClientModule.Category.COMBAT, ClientModule.Category.MISC,
            ClientModule.Category.BASEFINDING, ClientModule.Category.RENDER
        };
        int visiblePanels = 4;
        int totalWidth = visiblePanels * PANEL_W + (visiblePanels - 1) * PANEL_GAP;
        int startX = Math.max(8, (width - totalWidth) / 2);

        for (int i = 0; i < cats.length; i++) {
            drawPanel(context, startX + i * (PANEL_W + PANEL_GAP), panelY, cats[i], mouseX, mouseY);
        }

        int sx = width - 208;
        context.fill(sx, panelY, sx + PANEL_W, panelY + HEADER_H, 0xFF17181D);
        context.drawText(textRenderer, "SEARCH", sx + 12, panelY + 8, CYAN, false);
        context.drawText(textRenderer, "Right Shift: close/open", 12, height - 20, MUTED, false);
        super.render(context, mouseX, mouseY, delta);
    }

    private void drawPanel(DrawContext ctx, int x, int y, ClientModule.Category category, int mouseX, int mouseY) {
        List<ClientModule> list = NitroClient.MODULES.inCategory(category, search == null ? "" : search.getText());
        int panelH = HEADER_H + Math.min(list.size(), Math.max(1, (height - y - 30) / ROW_H)) * ROW_H;
        ctx.fill(x, y, x + PANEL_W, y + panelH, PANEL_BG);
        ctx.fill(x, y, x + PANEL_W, y + HEADER_H, 0xFF17181D);
        String heading = category.name();
        ctx.drawText(textRenderer, "▣", x + 8, y + 8, 0xFFFFB84D, false);
        ctx.drawText(textRenderer, heading, x + 24, y + 8, 0xFF8FE8ED, false);

        int maxRows = Math.max(1, (height - y - 30) / ROW_H);
        for (int i = 0; i < Math.min(list.size(), maxRows); i++) {
            ClientModule module = list.get(i);
            int ry = y + HEADER_H + i * ROW_H;
            ctx.fill(x + 1, ry, x + PANEL_W - 1, ry + ROW_H - 1, ROW_BG);
            if (module.enabled()) ctx.fill(x + 1, ry + 2, x + 4, ry + ROW_H - 2, CYAN);
            int color = module.enabled() ? CYAN : WHITE;
            ctx.drawText(textRenderer, trim(module.name(), 19), x + 10, ry + 8, color, false);

            int tx = x + PANEL_W - 34;
            int ty = ry + 8;
            ctx.fill(tx, ty, tx + 20, ty + 10, module.enabled() ? 0xFF315A93 : 0xFF3A3B42);
            int knobX = module.enabled() ? tx + 12 : tx + 2;
            ctx.fill(knobX, ty + 2, knobX + 6, ty + 8, module.enabled() ? CYAN : 0xFFB6B6BB);
            if (mouseX >= x && mouseX <= x + PANEL_W && mouseY >= ry && mouseY < ry + ROW_H) {
                ctx.fill(x + 1, ry, x + PANEL_W - 1, ry + ROW_H - 1, 0x332FC5FF);
                ctx.drawText(textRenderer, module.description(), x + 8, ry + ROW_H - 7, MUTED, false);
            }
        }
    }

    private String trim(String value, int max) {
        return value.length() <= max ? value : value.substring(0, max - 1) + "…";
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) return true;
        if (button != 0) return false;

        ClientModule.Category[] cats = {
            ClientModule.Category.COMBAT, ClientModule.Category.MISC,
            ClientModule.Category.BASEFINDING, ClientModule.Category.RENDER
        };
        int totalWidth = cats.length * PANEL_W + (cats.length - 1) * PANEL_GAP;
        int startX = Math.max(8, (width - totalWidth) / 2);
        int maxRows = Math.max(1, (height - panelY - 30) / ROW_H);

        for (int c = 0; c < cats.length; c++) {
            int x = startX + c * (PANEL_W + PANEL_GAP);
            if (mouseX < x || mouseX > x + PANEL_W || mouseY < panelY + HEADER_H) continue;
            int row = (int) ((mouseY - panelY - HEADER_H) / ROW_H);
            List<ClientModule> list = NitroClient.MODULES.inCategory(cats[c], search.getText());
            if (row >= 0 && row < Math.min(list.size(), maxRows)) {
                list.get(row).toggle();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldPause() { return false; }
}
