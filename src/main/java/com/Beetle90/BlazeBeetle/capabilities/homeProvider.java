package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class homeProvider implements ICapabilitySerializable<NBTBase>{
	
	@CapabilityInject(Ihomeloc.class)
	public static final Capability<Ihomeloc> HOMELOC_CAP = null;
	private Ihomeloc instance = HOMELOC_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == HOMELOC_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == HOMELOC_CAP ? HOMELOC_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return HOMELOC_CAP.getStorage().writeNBT(HOMELOC_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		HOMELOC_CAP.getStorage().readNBT(HOMELOC_CAP, this.instance, null, nbt);
	}
}
