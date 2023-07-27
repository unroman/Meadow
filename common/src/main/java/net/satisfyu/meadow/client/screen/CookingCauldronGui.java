package net.satisfyu.meadow.client.screen;


import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.recipebook.CookingCauldronRecipeBook;
import net.satisfyu.meadow.client.screen.handler.CookingCauldronGuiHandler;


public class CookingCauldronGui extends AbstractRecipeBookGUIScreen<CookingCauldronGuiHandler> {
    private static final ResourceLocation BACKGROUND;

    public CookingCauldronGui(CookingCauldronGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new CookingCauldronRecipeBook(), BACKGROUND);
        font = Minecraft.getInstance().font;
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        int progress = this.menu.getScaledProgress(17);
        guiGraphics.blit(BACKGROUND, leftPos + 92, topPos + 10, 178, 16, progress, 29); //Position Arrow (moved 8 pixels to the left)
    }

    @Override
    protected void renderBurnIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (menu.isBeingBurned()) {
            guiGraphics.blit(BACKGROUND, posX + 124, posY + 51, 176, 0, 17, 15); //fire (moved 5 pixels up)
        }
    }


    static {
        BACKGROUND = new ResourceLocation(Meadow.MOD_ID, "textures/gui/cooking_cauldron_gui.png");
    }
}