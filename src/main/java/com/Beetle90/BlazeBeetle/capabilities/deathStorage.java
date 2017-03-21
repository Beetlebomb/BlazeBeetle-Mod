package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class deathStorage implements IStorage<Ideathloc>{

	@Override
	public NBTBase writeNBT(Capability<Ideathloc> capability, Ideathloc instance, EnumFacing side) {
		NBTTagCompound temp = new NBTTagCompound();
		instance.writeLoc();
		temp = instance.getLocComp();
		return temp;
	}

	@Override
	public void readNBT(Capability<Ideathloc> capability, Ideathloc instance, EnumFacing side, NBTBase nbt) {
		instance.setLoc(((NBTTagCompound)nbt).getDouble("x"),((NBTTagCompound)nbt).getDouble("y"),((NBTTagCompound)nbt).getDouble("z"), ((NBTTagCompound)nbt).getInteger("dim"));
		
	}
}
