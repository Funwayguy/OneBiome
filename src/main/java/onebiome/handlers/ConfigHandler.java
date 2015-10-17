package onebiome.handlers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import onebiome.core.OB_Settings;
import onebiome.core.OneBiome;
import org.apache.logging.log4j.Level;

public class ConfigHandler
{
	public static Configuration config;
	
	public static void initConfigs()
	{
		if(config == null)
		{
			OneBiome.logger.log(Level.ERROR, "Config attempted to be loaded before it was initialised!");
			return;
		}
		
		config.load();
		
		OB_Settings.biomeID = config.getInt("Biome ID", Configuration.CATEGORY_GENERAL, -1, -1, 255, "The biome ID to use for all worlds (-1 to disable)");
		OB_Settings.seaLvl = config.getInt("Sea Level", Configuration.CATEGORY_GENERAL, 63, 0, 255, "How high should sea level generate at");
		
		config.save();
		
		OneBiome.logger.log(Level.INFO, "Loaded configs...");
	}
	
	/**
	 * Returns a compound tag representing the configuration settings that need to be synchronized between server and client
	 * @return
	 */
	public static NBTTagCompound getServerConfigs()
	{
		NBTTagCompound tags = new NBTTagCompound();
		
		return tags;
	}
	
	public static void setServerConfigs(NBTTagCompound tags)
	{
	}
}
