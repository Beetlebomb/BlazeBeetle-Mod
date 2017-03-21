package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class homeStorage implements IStorage<Ihomeloc>{
	@Override
	public NBTBase writeNBT(Capability<Ihomeloc> capability, Ihomeloc instance, EnumFacing side) {
		NBTTagCompound temp = new NBTTagCompound();
		instance.writeHome();
		temp = instance.getNBTComp();
		return temp;
	}

	@Override
	public void readNBT(Capability<Ihomeloc> capability, Ihomeloc instance, EnumFacing side, NBTBase nbt) {
		instance.setHome(((NBTTagCompound)nbt).getDouble("x"),((NBTTagCompound)nbt).getDouble("y"),((NBTTagCompound)nbt).getDouble("z"), ((NBTTagCompound)nbt).getDouble("yaw"), ((NBTTagCompound)nbt).getDouble("rot"), ((NBTTagCompound)nbt).getInteger("dim"));
		
	}
}
