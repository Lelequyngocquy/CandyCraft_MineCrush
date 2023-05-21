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