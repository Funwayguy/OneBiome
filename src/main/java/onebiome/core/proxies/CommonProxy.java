package onebiome.core.proxies;

import net.minecraftforge.common.MinecraftForge;
import onebiome.handlers.EventHandler;
import onebiome.handlers.WaterGenerator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public boolean isClient()
	{
		return false;
	}
	
	public void registerHandlers()
	{
		EventHandler handler = new EventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
		MinecraftForge.TERRAIN_GEN_BUS.register(handler);
		FMLCommonHandler.instance().bus().register(handler);
		GameRegistry.registerWorldGenerator(new WaterGenerator(), 0);
	}
}
