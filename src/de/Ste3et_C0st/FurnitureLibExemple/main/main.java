package de.Ste3et_C0st.FurnitureLibExemple.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.Ste3et_C0st.FurnitureLib.Crafting.Project;
import de.Ste3et_C0st.FurnitureLib.Events.FurnitureLateSpawnEvent;
import de.Ste3et_C0st.FurnitureLib.ShematicLoader.ProjectLoader;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
import de.Ste3et_C0st.FurnitureLib.main.Type.PlaceableSide;
import de.Ste3et_C0st.FurnitureLib.main.Type.SQLAction;

public class main extends JavaPlugin implements Listener{
	
	public static main instance;
	public List<ObjectID> idList = new ArrayList<ObjectID>();

	@Override
	public void onEnable(){
		if(!Bukkit.getPluginManager().isPluginEnabled("FurnitureLib")){Bukkit.getPluginManager().disablePlugin(this);}
		instance = this;
		
		//Check if the Project <name> is registred at the time
		if(FurnitureLib.getInstance().getFurnitureManager().getProject("tfight")==null){
			//if it not register it new
			//new Project(<name>, <plugin>, <InputStream>, <PlaceableSide>, <Class>)
			//<name> = projectName
			//<plugin> = the Plugin who manage the Object
			//<InputStream> = the FurnitureMaker File *.yml
			//<PlaceableSide> = this is the Side of the block if you can place it
			//<Class> = the generator class if you call the ProjectLoader.class this is the FurnitureMaker ProejctLoader
			//.setEditorProject(false) = if you want to Edit the Furniture after loading true/false
			new Project("tfight", this, getResource("tfight.yml"), PlaceableSide.TOP, ProjectLoader.class).setEditorProject(false);
		}
		for(ObjectID id : FurnitureLib.getInstance().getFurnitureManager().getObjectList()){
			if(id.getProjectOBJ().getName().equalsIgnoreCase("tfight")){
				if(!idList.contains(id)){
					//Check if the remove State is on
					if(id.getSQLAction().equals(SQLAction.REMOVE)) continue;
					//generate a new Object from the ObjectID
					new handler(id);
					idList.add(id);
				}
			}
		}
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){}
	
	//This is the FurnitureLateSpawn Event it will be called after all entitys and Blocks are placed
	@EventHandler
	public void onFurnitureLateSpawn(FurnitureLateSpawnEvent event){
		if(event.getProject().getName().equalsIgnoreCase("tfight")){
			if(!idList.contains(event.getID())){
				new handler(event.getID());
			}
		}
	}
}