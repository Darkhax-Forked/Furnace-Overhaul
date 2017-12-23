package cazador.furnaceoverhaul.handler;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import cazador.furnaceoverhaul.Reference;
import cazador.furnaceoverhaul.inventory.ContainerFO;
import cazador.furnaceoverhaul.tile.TileEntityIronFurnace;

@SideOnly(Side.CLIENT)
public class GuiFO extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/guifurnace.png");
    private final InventoryPlayer playerInventory;
    private final IInventory te;
    
	public GuiFO(InventoryPlayer player, IInventory te) {
		super(new ContainerFO(player, te));
		this.te = te;
		this.playerInventory = player;
	}

	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntityIronFurnace.isBurning(this.te)){
        	int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        	 }
        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.te.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}
	
	private int getCookProgressScaled(int pixels){
        int i = this.te.getField(2);
        int j = this.te.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels){
        int i = this.te.getField(1);

        if (i == 0){
            i = 200;
        }

        return this.te.getField(0) * pixels / i;
    }

}