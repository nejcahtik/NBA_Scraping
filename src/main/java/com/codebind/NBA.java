package com.codebind;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;
import java.util.Scanner;
import java.util.stream.*;

public class NBA {	
	
	public static String link = "https://www.basketball-reference.com/";
	
	
	
	public static void main (String args[]) {

		
		Document doc = null;
		
		Scanner sc = new Scanner(System.in);
		
		//name of the basketball player
		String bb_plyr = sc.nextLine();
		
		System.out.println();
		
		
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
		
		boolean plyr_found = false;
		
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
			if(data.size()>= 1 && data.get(0).text().equals(bb_plyr)) {
				
				plyr_found = true;
				
				Elements of_name = data.get(0).getElementsByTag("a");
				String href = of_name.get(0).attr("href");
				
				//go to the player's website
				goto_player_site(href);
			}		
		}	
		
		//did not find the player
		if(!plyr_found) {
			System.out.println("This player does not currently play in NBA.");
		}
		sc.close();
			
	}
	
	
	//prints 3 points average of each season of given player, data is scraped from the player's personal website
	public static void goto_player_site(String href) {
		
		Document doc = null;
		
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
		
		
		System.out.println("Season     3PA    Team");
		
		//cycle through all seasons and print required data
		for(Element season : seasons) {
		
		//seasons.stream().forEach(season -> {
			
			Elements data = season.getElementsByTag("TD");
			Elements years = season.getElementsByTag("TH");
			
			//print current season
			System.out.print(years.get(0).text()+":   ");		
							
			//print 3 points average for this season
			System.out.print(data.get(11).text());
			
			//print the club
			System.out.println("    ("+data.get(1).text()+")");
		//)
		}
		
	}	
}
	