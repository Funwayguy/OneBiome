package onebiome.handlers;

import java.util.Random;
import onebiome.core.OB_Settings;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import cpw.mods.fml.common.IWorldGenerator;

public class WaterGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(OB_Settings.seaLvl == 63)
		{
			return;
		}
		
		Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
		
		boolean flag = false;
		
		for(int i = 0; i < 16; i++)
		{
			for(int y = 0; y < 256; y++)
			{
				int ebsLayer = y >> 4;
			
				ExtendedBlockStorage ebs = chunk.getBlockStorageArray()[ebsLayer];
				
				if(ebs == null)
				{
                    ebs = new ExtendedBlockStorage(y, !world.provider.hasNoSky);
                    chunk.getBlockStorageArray()[ebsLayer] = ebs;
                    flag = true;
				}
			
				for(int k = 0; k < 16; k++)
				{
					int x = (chunkX * 16) + i;
					int z = (chunkZ * 16) + k;
					Block block = world.getBlock(x, y, z);
					
					if(block.isAir(world, x, y, z) && y <= OB_Settings.seaLvl)
					{
						ebs.func_150818_a(i, y & 15, k, Blocks.water);
						ebs.setExtBlockMetadata(i, y & 15, k, 0);
					} else if((block == Blocks.water || block == Blocks.flowing_water) && y > OB_Settings.seaLvl)
					{
						ebs.func_150818_a(i, y & 15, k, Blocks.air);
						ebs.setExtBlockMetadata(i, y & 15, k, 0);
					}
				}
			}
		}
		
		if(flag)
		{
            chunk.generateSkylightMap();
		}
	}
}
