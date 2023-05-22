/* Group 1. NH4, TTH2, Object Oriented Programming course, INTERNATIONAL UNIVERSITY - VIETNAM NATIONAL UNIVERSITY

Le Ngoc Quy 		ITITIU21296
Nguyen Thi Hai Yen 	ITITIU21353
Nguyen Dy Nien 		ITITIU21272
Nguyen Do Hoang Phi 	ITITIU21275
 Purpose: The purpose of the CandyLevel class is to define and manage the specific characteristics and behaviors of each level in the Candy game. 
It represents an individual level with its unique configuration, goals, and challenges.
*/


public class CandyLevel{
	
	private static int level;
	private static int target;
	private static int moves;
	private static int score;
	

	private static int checkLevel =0;

	public static int getMoves(){
		return moves;
	}
	public static void setMoves(int movess){
		moves=movess;
	}
	public static void addMoves(int num){
		moves += num;
	}


	public static int getScore(){
		return score;
	}
	public static void setScore(int s){
		score=s;
	}
	public static void addScore(int points){
		score+= points;
	}

	public static int getLevel(){
		return level;
	}
	public static void levelUp(){
		level+=1;
	}

	public static int getTarget(){
		return target;
	}
	public static void setTarget(int targetPoints){
		target = targetPoints;
	}

}
