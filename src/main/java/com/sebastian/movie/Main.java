package com.sebastian.movie;

import java.util.Scanner;


public class Main 
{
    public static void main( String[] args )
    {
    	ClientMovie movie;
		String searchTerm = "";			
		Scanner input = new Scanner(System.in);			
		
		System.out.println("search term: ");
		searchTerm = input.nextLine().replace(" ", "+");	
		
		if(searchTerm.equals("")) {
			System.out.println("ERROR: You must provide a search term.");
		}
		else {
			System.out.println("");			
			movie = new ClientMovie(searchTerm);			
			movie.executeClientMovie();
		}
		
		input.close();
    }
}
