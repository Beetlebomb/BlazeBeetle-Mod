package com.Beetle90.BlazeBeetle.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

public class namestuff implements Inamestuff {
	private String prefix = "empty";
	private String pCol = "empty";
	private String nickname = "empty";
	private String nickCol = "empty";
	private String suffix = "empty";
	private String sCol = "empty";
	private NBTTagCompound nbt = new NBTTagCompound();
	

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
		this.pCol = "empty";
	}

	@Override
	public void setPrefix(String prefix, String color) {
		this.prefix = prefix;
		TextFormatting temp = TextFormatting.WHITE;
		this.pCol = temp.getValueByName(color).toString(); 
	}

	@Override
	public void setNickname(String nick) {
		this.nickname = nick;
		this.nickCol = "empty";
	}

	@Override
	public void setNickname(String nick, String color) {
		this.nickname = nick;
		TextFormatting temp = TextFormatting.WHITE;
		this.nickCol = temp.getValueByName(color).toString(); 
	}

	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
		this.sCol = "empty";
	}

	@Override
	public void setSuffix(String suffix, String color) {
		this.suffix = suffix;
		TextFormatting temp = TextFormatting.WHITE;
		this.sCol = temp.getValueByName(color).toString(); 
	}

	@Override
	public void setAllColors(String color) {
		TextFormatting temp = TextFormatting.WHITE;
		temp.getValueByName(color);
		this.pCol = temp.toString();
		this.nickCol = temp.toString();
		this.sCol = temp.toString();
	}
	
	/*@Override
	public void initNameStuff(NBTTagCompound nbt){
		this.prefix = nbt.getString("prefix");
		this.pCol = nbt.getString("pCol");
		this.nickname = nbt.getString("nick");
		this.nickCol = nbt.getString("nickCol");
		this.suffix = nbt.getString("suffix");
		this.sCol = nbt.getString("sCol");
	}*/
	
	@Override
	public void initNameStuff(String prefix, String pCol, String nick, String nickCol, String suffix, String sCol){
		this.prefix = prefix;
		this.pCol = pCol;
		this.nickname = nick;
		this.nickCol = nickCol;
		this.suffix = suffix;
		this.sCol = sCol;
	}

	@Override
	public NBTTagCompound getNBTComp() {
		nbt.setString("prefix", prefix);
		nbt.setString("pCol", pCol);
		nbt.setString("nickname", nickname);
		nbt.setString("nickCol", nickCol);
		nbt.setString("suffix", suffix);
		nbt.setString("sCol", sCol);
		return nbt;
	}

	@Override
	public String getFullPrefix() { //3 options! prefix and color, prefix, or neither.
		return pCol.equals("empty") && !prefix.equals("empty") ? TextFormatting.WHITE + prefix : !pCol.equals("empty") && !prefix.equals("empty") ? pCol + prefix : "";
	}

	@Override
	public String getFullNick() { //either no nickname, nickname with no color, or nickname with color "�f" + nickname, nickCol + nickname
		return nickCol.equals("empty") && !nickname.equals("empty") ? TextFormatting.WHITE + nickname : !nickCol.equals("empty") && !nickname.equals("empty") ? nickCol + nickname : "empty";
	}

	@Override
	public String getFullSuffix() {
		return sCol.equals("empty") && !suffix.equals("empty") ? TextFormatting.WHITE + suffix : !sCol.equals("empty") && !suffix.equals("empty") ? sCol + suffix : "";
	}

	@Override
	public String getPrefix() {
		return prefix.equals("empty") ? null : prefix;
	}

	@Override
	public String getpCol() {
		return pCol.equals("empty") ? null : pCol;
	}

	@Override
	public String getNick() {
		return nickname.equals("empty") ? "empty" : nickname;
	}

	@Override
	public String getnickCol() {
		return nickCol.equals("empty") ? null : nickCol;
	}

	@Override
	public String getSuffix() {
		return suffix.equals("empty") ? null : suffix;
	}

	@Override
	public String getsCol() {
		return sCol.equals("empty") ? null : sCol;
	}

}
