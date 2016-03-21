package de.Ste3et_C0st.FurnitureLibExemple.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.Ste3et_C0st.FurnitureLib.Crafting.Project;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.Type.PlaceableSide;

public class main extends JavaPlugin{
	
	public static main instance;
	/*
	 * In the onEnable tab you must be registred your project
	 * new Project(NAME,INSTANCE,RESOURCE,PLACEABLESIDE,FURNITURE.class).setSize(witdh,height,length,type)
	 */
	@Override
	public void onEnable(){
		if(!Bukkit.getPluginManager().isPluginEnabled("FurnitureLib")){Bukkit.getPluginManager().disablePlugin(this);}
		new Project("TestFurniture", this, getResource("testFurniture.yml"), PlaceableSide.TOP, test.class);
		instance = this;
		FurnitureLib.getInstance().registerPluginFurnitures(this);
	}
	
	@Override
	public void onDisable(){
		
	}
	
}