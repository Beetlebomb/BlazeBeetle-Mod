package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public interface Ideathloc {
	public void writeLoc();
	public NBTTagCompound getLocComp();
	public Vec3d getLoc();
	public void setLoc();
	public void setLoc(double x, double y, double z, int dim);
	public int getDim();
}
