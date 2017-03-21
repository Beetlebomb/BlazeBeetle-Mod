package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class namestuffStorage implements IStorage<Inamestuff>{
	
	@Override
	public NBTBase writeNBT(Capability<Inamestuff> capability, Inamestuff instance, EnumFacing side) {
		NBTTagCompound temp = new NBTTagCompound();
		temp = instance.getNBTComp();
		return temp;
	}

	@Override
	public void readNBT(Capability<Inamestuff> capability, Inamestuff instance, EnumFacing side, NBTBase nbt) {
		instance.initNameStuff(((NBTTagCompound)nbt).getString("prefix"),((NBTTagCompound)nbt).getString("pCol"),((NBTTagCompound)nbt).getString("nickname"), ((NBTTagCompound)nbt).getString("nickCol"), ((NBTTagCompound)nbt).getString("suffix"), ((NBTTagCompound)nbt).getString("sCol"));
	}

}
