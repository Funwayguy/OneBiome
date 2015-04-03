package onebiome.handlers;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import onebiome.core.OB_Settings;
import onebiome.core.OneBiome;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandler
{
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equals(OneBiome.MODID))
		{
			ConfigHandler.config.save();
			ConfigHandler.initConfigs();
		}
	}
	
	@SubscribeEvent
	public void onGenerate(WorldTypeEvent.InitBiomeGens event)
	{
		if(OB_Settings.biomeID < 0)
		{
			return;
		}
		GenLayerSingle singleLayer = new GenLayerSingle(event.seed, OB_Settings.biomeID);
		event.newBiomeGens[0] = singleLayer;
		event.newBiomeGens[1] = singleLayer;
		event.newBiomeGens[2] = singleLayer;
	}
	
	public class GenLayerSingle extends GenLayer
	{
		int biomeID = 0;
		
		public GenLayerSingle(long seed, int biomeID)
		{
			super(seed);
			this.biomeID = biomeID;
		}

		@Override
		public int[] getInts(int x, int y, int l, int w)
		{
			int[] vals = new int[l*w];
			
			for(int i = 0; i < vals.length; i++)
			{
				vals[i] = biomeID;
			}
			
			return vals;
		}
	}
}
