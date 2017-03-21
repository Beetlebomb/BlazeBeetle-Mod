package com.Beetle90.BlazeBeetle.capabilities;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public interface Ihomeloc {
	public void writeHome();
	public void setHome(double x, double y, double z, double yaw, double rot, int dim_id);
	public Vec3d getHome();
	public NBTTagCompound getNBTComp();
	public double getYaw();
	public double getRot();
	public int getDim();
	
}
