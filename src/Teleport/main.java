package Teleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin {
	public void onEnable(){
		
	}
	
	public void onDisable(){
		 
	}
	
	public boolean onCommand(CommandSender sender,Command cmd,String cmdlable,String[] args){
		if(!(sender instanceof Player)){
			sender.sendMessage("請使用玩家身分輸入");
			return true;
		}
		
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("tp")){
			if(args.length == 0){
				player.sendMessage(ChatColor.RED + "使用方法： /tp <player>");
				return true;
			}		
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null){
				player.sendMessage(ChatColor.RED + "此玩家目前未上線");
				return true;
			}
			player.teleport(target);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("setspawn")){
			getConfig().set("spawn.world",player.getWorld().getName());
			getConfig().set("spawn.x",player.getLocation().getX());
			getConfig().set("spawn.y",player.getLocation().getY());
			getConfig().set("spawn.z",player.getLocation().getZ());
			saveConfig();
			player.sendMessage(ChatColor.GREEN + "重生點已設置");	
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("spawn")){
			if(getConfig().getConfigurationSection("spawn") == null){
				player.sendMessage(ChatColor.RED + "重生點尚未設定");
				return true;
			}
			World world = Bukkit.getServer().getWorld(getConfig().getString("spawn.world"));
			double x = getConfig().getDouble("spawn.x");
			double y = getConfig().getDouble("spawn.y");
			double z = getConfig().getDouble("spawn.z");
			player.teleport(new Location(world,x,y,z));
			player.sendMessage(ChatColor.GREEN + "您已傳送至重生點");
			return true;
		}
		return true;
	}

}
