package com.codebind;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;



public class NBA_JUnit {	
	
	public static String link = "https://www.basketball-reference.com/";
	
	
	
	public String get3P (String plyr_name) {

		Document doc = null;
		
		
		//get the website
		try {
			doc = Jsoup.connect(link+"leagues/NBA_2020_per_game.html").get();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		

		//get the table of players
		Element table_of_players = doc.getElementById("per_game_stats");
		
		//get rows of the table aka players
		Elements players = table_of_players.getElementsByTag("tr");
		
		/*players.stream()
 			.filter(player -> player.getElementsByTag("TD").size()>= 1 && player.getElementsByTag("TD").get(0).text().equals(bb_plyr))
  			.forEach(player -> {
	
				Elements data = player.getElementsByTag("TD");
	
				//check if the current player is the one we are looking for and get his href			
				Elements of_name = data.get(0).getElementsByTag("a");
				String href = of_name.get(0).attr("href");
				
				//go to the player's website
				goto_player_site(href);
			
			});
		*/
		
		
		
		//cycle through all players
		for (Element player : players) {
			
			Elements data = player.getElementsByTag("TD");
				
			//check if the current player is the one we are looking for and get his href
			if(data.size()>= 1 && data.get(0).text().equals(plyr_name)) {
				
				Elements of_name = data.get(0).getElementsByTag("a");
				String href = of_name.get(0).attr("href");
				
				return this.goto_player_site(href);
				
			}		
		}	
		return null;
			
	}
	
	
	//get 3 points average of each season for a given player
	public String goto_player_site(String href) {
		
		Document doc = null;
		
		//connect to the player's website: link+href
		try {
			doc = Jsoup.connect(link+href).get();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		
		//get the table of the seasons current player played in
		Element t_o_s = doc.getElementById("per_game");
		
		//get the body of the table
		Element table_of_seasons = t_o_s.getElementsByTag("tbody").get(0);
		
		//get rows of the table aka seasons
		Elements seasons = table_of_seasons.getElementsByTag("tr");
		
		String res = "";
		
		//cycle through all seasons (rows of the table) and print required data
		for(Element season : seasons) {
			
		//seasons.stream().forEach(season -> {
			
			Elements data = season.getElementsByTag("TD");
			Elements years = season.getElementsByTag("TH");
			
			//current season	
			res = res + (years.get(0).text()+", ");
					
			//3PA
			res = res + data.get(11).text()+", ";
			
			//team
			res = res + data.get(1).text();
			
			
			res = res + " | ";
		//)
		}
		
		return res;
		
	}
	
	
	
	
	
}
	