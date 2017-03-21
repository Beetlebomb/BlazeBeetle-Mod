package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class namestuffProvider implements ICapabilitySerializable<NBTBase>{
	
	@CapabilityInject(Inamestuff.class)
	public static final Capability<Inamestuff> NAMESTUFF_CAP = null;
	private Inamestuff instance = NAMESTUFF_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == NAMESTUFF_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == NAMESTUFF_CAP ? NAMESTUFF_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return NAMESTUFF_CAP.getStorage().writeNBT(NAMESTUFF_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		NAMESTUFF_CAP.getStorage().readNBT(NAMESTUFF_CAP, this.instance, null, nbt);

	}
}
