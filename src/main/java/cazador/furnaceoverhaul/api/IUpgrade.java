package cazador.furnaceoverhaul.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.IEnergyStorage;



public abstract interface IUpgrade extends IEnergyStorage{

	public abstract int getItemBurnTime(ItemStack stack);
	
	public abstract void smeltItem();
}
