package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class deathProvider implements ICapabilitySerializable<NBTBase>{
	
	@CapabilityInject(Ideathloc.class)
	public static final Capability<Ideathloc> DEATHLOC_CAP = null;
	private Ideathloc instance = DEATHLOC_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == DEATHLOC_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == DEATHLOC_CAP ? DEATHLOC_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return DEATHLOC_CAP.getStorage().writeNBT(DEATHLOC_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		DEATHLOC_CAP.getStorage().readNBT(DEATHLOC_CAP, this.instance, null, nbt);
	}

}
