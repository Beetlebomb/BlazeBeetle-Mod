package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class deathloc implements Ideathloc{
	
	private Vec3d location = new Vec3d(0, -1000, 0);
	private NBTTagCompound nbt = new NBTTagCompound();
	private int dim_id = -1000;
	private double x = 0;
	private double y = -1000;
	private double z = 0;
	
	public void writeLoc(){
		nbt.setDouble("x", x);
		nbt.setDouble("y", y);
		nbt.setDouble("z", z);
		nbt.setInteger("dim", dim_id);
	}

	public NBTTagCompound getLocComp() {
		return nbt;
	}

	public void setLoc() {
		dim_id = nbt.getInteger("dim");
		x = nbt.getDouble("x");
		y = nbt.getDouble("y");
		z = nbt.getDouble("z");
		Vec3d temp = new Vec3d(x, y, z);
		location = temp;
	}

	public void setLoc(double x, double y, double z, int dim) {
		location = new Vec3d(x, y, z);
		this.x = x;
		this.y = y;
		this.z = z;
		dim_id = dim;
		
	}

	public Vec3d getLoc() {
		return location;
	}

	public int getDim() {
		return dim_id;
	}

}
