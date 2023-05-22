/* Group 1. NH4, TTH2, Object Oriented Programming course, INTERNATIONAL UNIVERSITY - VIETNAM NATIONAL UNIVERSITY

Le Ngoc Quy 		ITITIU21296
Nguyen Thi Hai Yen 	ITITIU21353
Nguyen Dy Nien 		ITITIU21272
Nguyen Do Hoang Phi 	ITITIU21275
 Purpose: The purpose of the Candy class is to provide the core logic and functionality for the Candy game. It serves as the main class that handles the game mechanics, interactions, and rules.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;



public class Candy implements ActionListener{

	private CandyGUI candyGUI;
	
	private static JButton[][] bn;
	private Color[] cl;
	private ImageIcon[] icons;
	private URL []url;


	private int x=-1;
	private int y=-1;
	private int a =0;
	private int b =0;
	private int z2 =0;	
	

	private static final int ROWS = 6;
	private static final int COLS = 10;

//================================================================= C O N S T R U C T O R
	public Candy(){   

 
		switch(CandyLevel.getLevel()){
			case 0:
				System.out.println("Level 0");
				CandyLevel.setTarget(300);
				CandyLevel.setMoves(15);
				CandyLevel.setScore(0);
				break;
			case 1:
				System.out.println("Level 1");
				CandyLevel.setTarget(400);
				CandyLevel.setMoves(15);
				CandyLevel.setScore(0);
				break;
			case 2:
				System.out.println("Level 2");
				CandyLevel.setTarget(500);
				CandyLevel.setMoves(15);
				CandyLevel.setScore(0);
				break;
			case 3:
				System.out.println("Level 3");
				CandyLevel.setTarget(550);
				CandyLevel.setMoves(20);
				CandyLevel.setScore(0);
				break;
			case 4:
				System.out.println("Level 4");
				CandyLevel.setTarget(600);
				CandyLevel.setMoves(25);
				CandyLevel.setScore(0);
				break;
			case 5:
				System.out.println("Level 5");
				CandyLevel.setTarget(1000);
				CandyLevel.setMoves(40);
				CandyLevel.setScore(0);
				break;
			case 6:
				int check = JOptionPane.showConfirmDialog(null, "You have cleared the game, congratulation!");
				System.exit(0);
		}	

		candyGUI = new CandyGUI();		
		createGame();
		
		blockAppears();
		
	}

//=================================== Getter and Setter
	public static int getRows(){
		return ROWS;
	}
	public static int getCols(){
		return COLS;
	}

	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x =x;
	}
	public int getY(){
		return this.y;
	}
	public void setY(int y){
		this.y =y;
	}


//=======================================================



	public void createGame(){

		url = new URL[7];
		for (int i = 0; i < 6; i++)
        		url[i] = getClass().getResource("/pngegg(" + i + ").png");
		
		url[6] = getClass().getResource("/block.png");
		

		icons = new ImageIcon[7];
		for(int i = 0 ; i < 7; i++)
			icons[i] = new ImageIcon(url[i]);

		cl = new Color[6];
		for(int i = 0;i<6;i++)
			cl[i] = Color.WHITE;
		

		bn = new JButton[6][10];

		boardGenerate();
		
		setText(CandyLevel.getScore(),CandyLevel.getMoves(),CandyLevel.getLevel());
		initialize();
		initialize();
		initialize();

		candyGUI.setVisible();
		
	}

	private void swapAndCheck(int i, int j, Color tmp, String tmp1, String tmp12, ImageIcon tmp2, Color tmpZ, String tmp1Z, ImageIcon tmp2Z) {
		//swap the positions
	   
		bn[i][j].setBackground(bn[x][y].getBackground());
		bn[x][y].setBackground(tmp);

		bn[i][j].setActionCommand(bn[x][y].getActionCommand());
		bn[x][y].setActionCommand(tmp1);

		bn[i][j].setIcon(new ImageIcon(url[Integer.parseInt(tmp12)]));
		bn[x][y].setIcon(tmp2);

		check();
		if(z2 <= 0){
			// Reset selected candies
			x = -1;
			y = -1;
			// Check for matches
			check();
			CandyLevel.addMoves(-1);


		}
		else{                                            // if the 2 selected balls do not have the ability to create combos, they will be changed back to the original position
			if(z2>0){
				
				bn[i][j].setBackground(tmp);
				bn[x][y].setBackground(tmpZ);

				bn[i][j].setActionCommand(tmp1);
				bn[x][y].setActionCommand(tmp1Z);
				
				bn[i][j].setIcon(tmp2);
				bn[x][y].setIcon(tmp2Z);
				System.out.println("Illegal move!");
				
			}
		}
		z2=0;
		
	}

	
	public void checkLevel(){
		if (CandyLevel.getScore() >= CandyLevel.getTarget()) {
			int notif1 = JOptionPane.showConfirmDialog(null, "You have completed level "+CandyLevel.getLevel());
			CandyLevel.levelUp();
			//CandyLevel.setScore(0);
		
			if (notif1 == JOptionPane.YES_OPTION) {
				
				new Candy();
				
				b=1;

				candyGUI.dispose();
			} else if (notif1 == JOptionPane.NO_OPTION) {
				System.out.println("Have a good day!");
				System.exit(0);
			}
		}
	}
	

	public void boardGenerate(){
		for(int i=0; i < getRows(); i++)
			for(int j=0; j < getCols(); j++ ){
				int d = rand();
				bn[i][j] = new JButton();
				bn[i][j].setActionCommand(String.valueOf(d));
				bn[i][j].setBackground(cl[d]);
				bn[i][j].addActionListener(this);
				bn[i][j].setIcon(new ImageIcon(url[d]));
				bn[i][j].setOpaque(false);
				bn[i][j].setBorderPainted(false);


				JPanel jp = candyGUI.getJp();
				jp.add(bn[i][j]);
			}
	}

	// scan the game at initialization so that no matching strings are randomly generated at the start of the game
	public void initialize(){			
		int d;
		for(int i = 0; i < getRows(); i++){           //Horizontally scan
			for(int j = 0;j< getCols()-2; j++){
				if( bn[i][j].getActionCommand().matches(bn[i][j + 1].getActionCommand()) && bn[i][j].getActionCommand().matches(bn[i][j + 2].getActionCommand()) ){
					
					if(j+ 3 < getCols() && (bn[i][j].getActionCommand().matches(bn[i][j + 3].getActionCommand()))){
            				
						if(j + 4 < getCols() && (bn[i][j].getActionCommand().matches(bn[i][j + 4].getActionCommand()))){
							d = rand();
							bn[i][j+4].setActionCommand(String.valueOf(d));
							bn[i][j+4].setBackground(cl[d]);
							bn[i][j+4].setIcon(icons[d]);
						}
						d = rand();
						bn[i][j+3].setActionCommand(String.valueOf(d));
						bn[i][j+3].setBackground(cl[d]);
						bn[i][j+3].setIcon(icons[d]);
					}
					d = rand();
					bn[i][j].setActionCommand(String.valueOf(d));   
					bn[i][j].setBackground(cl[d]);
					bn[i][j].setIcon(icons[d]);	

					d = rand();
					bn[i][j+1].setActionCommand(String.valueOf(d));
					bn[i][j+1].setBackground(cl[d]);
  					bn[i][j+1].setIcon(icons[d]);
	
					d = rand();
					bn[i][j+2].setActionCommand(String.valueOf(d));
					bn[i][j+2].setBackground(cl[d]);
					bn[i][j+2].setIcon(icons[d]);           
				}
			}
		}
		for(int j = 0;  j< getCols(); j++){           //Vertically scan
			for(int i = 0;i< getRows()-2; i++){
           			if(bn[i][j].getActionCommand().matches(bn[i + 1][j].getActionCommand()) && bn[i][j].getActionCommand().matches(bn[i + 2][j].getActionCommand())){

					if(i+ 3 <  getRows() && (bn[i][j].getActionCommand().matches(bn[i+3][j].getActionCommand()))){
						if(i+ 4 <  getRows() && (bn[i][j].getActionCommand().matches(bn[i+4][j].getActionCommand()))){
							d = rand();
							bn[i+4][j].setActionCommand(String.valueOf(d));
 							bn[i+4][j].setBackground(cl[d]);
							bn[i+4][j].setIcon(icons[d]);
						}
						d = rand();
						bn[i+3][j].setActionCommand(String.valueOf(d));
						bn[i+3][j].setBackground(cl[d]);
						bn[i+3][j].setIcon(icons[d]); 
						}
					d = rand();
					bn[i][j].setActionCommand(String.valueOf(d));
					bn[i][j].setBackground(cl[d]);
					bn[i][j].setIcon(icons[d]);
						
					d = rand();
					bn[i+1][j].setActionCommand(String.valueOf(d));
					bn[i+1][j].setBackground(cl[d]);
					bn[i+1][j].setIcon(icons[d]);
					
 					d = rand();
					bn[i+2][j].setActionCommand(String.valueOf(d));
					bn[i+2][j].setBackground(cl[d]);
					bn[i+2][j].setIcon(icons[d]);
				}
			}
		}
	System.out.println("Initializing!...");	
	}
	
//=======================================================================================
	public void check(){

		boolean checkAgain = false;

		z2+=1;     
		fillGaps();                         // insert items into the gaps created from the previous check;

		for(int i=0; i<getRows();i++){ 			// Horizontally scan
			for(int j = 0; j < getCols()-2; j++){
				if(bn[i][j].getActionCommand().matches(bn[i][j + 1].getActionCommand()) && bn[i][j].getActionCommand().matches(bn[i][j + 2].getActionCommand())){
					System.out.println("Match!");
					z2-=3;
					int d;
					
					if(j+ 3 < getCols() && (bn[i][j].getActionCommand().matches(bn[i][j + 3].getActionCommand()))){	
						if(j + 4 < getCols() && (bn[i][j].getActionCommand().matches(bn[i][j + 4].getActionCommand()))){
							d = rand();
							bn[i][j+4].setActionCommand(String.valueOf(d+1));
							bn[i][j+4].setBackground(cl[d]);
                   				bn[i][j+4].setIcon(null);
							System.out.println("Combo 5: +20 points");

							CandyLevel.addScore(20);
						}
						d = rand();
						bn[i][j+3].setActionCommand(String.valueOf(d+2));
						bn[i][j+3].setBackground(cl[d]);
                   			bn[i][j+3].setIcon(null);
						System.out.println("Combo 4: +10 points");
						CandyLevel.addScore(10);
					}
					d = rand();
					bn[i][j].setActionCommand(String.valueOf(d+3));
					bn[i][j].setBackground(cl[d]);
					bn[i][j].setIcon(null);
					
					bn[i][j+1].setActionCommand(String.valueOf(d+4));
					bn[i][j+1].setBackground(cl[d]);
					bn[i][j+1].setIcon(null);
					
					bn[i][j+2].setActionCommand(String.valueOf(d+5));
					bn[i][j+2].setBackground(cl[d]);
					bn[i][j+2].setIcon(null);
					
					CandyLevel.addScore(15);
					System.out.println("Combo 3: +15 points");
					
					applyGravity();	// Apply gravity to pull the candies down
					checkAgain = true;
					
				}
			}
		}
		for(int j=0; j<getCols();j++){ 			// Vertically scan
			for(int i = 0; i < getRows()-2; i++){
				if(bn[i][j].getActionCommand().matches(bn[i+1][j].getActionCommand()) && bn[i][j].getActionCommand().matches(bn[i+2][j].getActionCommand())){
					System.out.println("Match!");
					z2-=3;
					int d;
					
					if(i+ 3 < getRows() && (bn[i][j].getActionCommand().matches(bn[i+3][j].getActionCommand()))){	
						if(i + 4 < getRows() && (bn[i][j].getActionCommand().matches(bn[i+4][j].getActionCommand()))){
							d = rand();
							bn[i+4][j].setActionCommand(String.valueOf(d+6));
							bn[i+4][j].setBackground(cl[d]);
                   				bn[i+4][j].setIcon(null);
							System.out.println("Combo 5: +20 points");
							CandyLevel.addScore(20);
						}
						d = rand();
						bn[i+3][j].setActionCommand(String.valueOf(d+7));
						bn[i+3][j].setBackground(cl[d]);
                   			bn[i+3][j].setIcon(null);
						System.out.println("Combo 4: +10 points");
						CandyLevel.addScore(10);
					}
					d = rand();
					bn[i][j].setActionCommand(String.valueOf(d+8));
					bn[i][j].setBackground(cl[d]);
					bn[i][j].setIcon(null);
					
					bn[i+1][j].setActionCommand(String.valueOf(d+9));
					bn[i+1][j].setBackground(cl[d]);
					bn[i+1][j].setIcon(null);
					
					bn[i+2][j].setActionCommand(String.valueOf(d+10));
					bn[i+2][j].setBackground(cl[d]);
					bn[i+2][j].setIcon(null);
					
					CandyLevel.addScore(15);
					System.out.println("Combo 3: +15 points");
					
					applyGravity();	// Apply gravity to pull the candies down
					checkAgain = true;
					
				}
			}
		}
		if(checkAgain == true)
			check();
	}




	public void setText(int score, int moves, int level){
		JLabel jb = candyGUI.getJb();
		jb.setText("   Score: "+CandyLevel.getScore()+"               Moves: "+CandyLevel.getMoves()+"               Level: "+CandyLevel.getLevel()+" ("+CandyLevel.getTarget()+" points)");
	
	}

	public void applyGravity() {
    		for (int j = 0; j < getCols(); j++) { // scan through each column
			int emptySpaces = 0; // Number of empty cells in column

			for (int i = getRows()-1; i >= 0; i--) { // Start from bottom to top
			       if(bn[i][j].getActionCommand().matches("block")){
					break;
 				}
				if (bn[i][j].getIcon() == null) { // If the cell is empty

					emptySpaces++; // Increase the number of empty cells
		
				} else if (emptySpaces > 0) { // If it meets a position that is not empty and there is an empty position below
					
					// Move a non-blank position down "emptySpace" positions
					
					String tmp = bn[i][j].getActionCommand();
					bn[i + emptySpaces][j].setActionCommand(tmp);
					bn[i + emptySpaces][j].setBackground(bn[i][j].getBackground());
					bn[i + emptySpaces][j].setIcon(bn[i][j].getIcon());
					// (Delete old cell)
					
 					bn[i][j].setBackground(cl[1]);
					bn[i][j].setIcon(null);
					
				}

			}
		}
	}

	public void fillGaps(){
		for(int j=0; j<getCols();j++){//scan through each column
			for(int i=0; i<getRows(); i++){// Start from top to bottom
			   
				if(bn[i][j].getActionCommand().matches("block")){    //If it meets an obstacle block
					for(int i1=i; i1<getRows(); i1++){// Start from top to bottom
						if(bn[i1][j].getIcon() == null){ // If it is an empty position
							bn[i1][j].setEnabled(false);
						}
					}
					break; 
				}
	
	
				if(bn[i][j].getIcon() == null){ // if the position is empty will create new items
					int d = rand();
					bn[i][j].setActionCommand(String.valueOf(d));
					bn[i][j].setBackground(cl[d]);
					bn[i][j].setIcon(icons[d]);
				}
			   
			}
		}
	}

	
	public void refreshBoard() {
		a+=1;
		if(a==1){
			System.out.println("Refresh!: -50 points");
			
    			candyGUI.panelRemoveAll(); // Remove all elements in jp
    			boardGenerate();
			initialize();
			initialize();
			initialize();
			CandyLevel.addScore(- 50);
			blockAppears();
   			candyGUI.panelRevalidate(); // Update the interface of jp
    			
		}	
		
	}

	public void blockAppears(){
		switch(CandyLevel.getLevel()){
			case 0:
				blockGenerate(0);
				break;
			case 1:
				blockGenerate(1);
				break;
			case 2:
				blockGenerate(2);
				break;
			case 3:
				blockGenerate(3);
				break;
			case 4:
				blockGenerate(4);
				break;
			case 5:
				blockGenerate(5);
				break;
		}
	}
	
	public void blockGenerate(int num){
		if(num != 0 || num < 10){
			for(int z =0; z< num; z++){
				int i = rand();
				int j = rand(10);
				bn[i][j].setActionCommand("block");
				bn[i][j].setBackground(cl[5]);
				bn[i][j].setIcon(icons[6]);
				//bn[i][j].setEnabled();
			}
		}
	}

	public int rand(){
		Random r = new Random();
		return r.nextInt(6);
	}
	public int rand(int ten){
		Random r = new Random();
		return r.nextInt(ten);
	}

	//===================================================================== A C T I O N 

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton f5 = candyGUI.getF5();
		f5.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
       			refreshBoard();
    			}
		});	

		for(int i=0; i < getRows(); i++)
			for(int j=0; j < getCols(); j++ ){
				a=0;
					
				if(e.getSource() == bn[i][j]){

					if(bn[i][j].getActionCommand().matches("block")){
						break;
					}

					if (x == -1 && y == -1) {
                      	      	x = i;
                          		y = j;
                           		check();
							
					} else {
						if(bn[i][j].getIcon() != null){
							
							Color tmp = bn[i][j].getBackground();
 							String tmp1 = bn[i][j].getActionCommand();
							String tmp12 = bn[x][y].getActionCommand();
							ImageIcon tmp2 = new ImageIcon(url[Integer.parseInt(tmp1)]);
							 
							Color tmpZ = bn[x][y].getBackground();
							String tmp1Z = bn[x][y].getActionCommand();
							ImageIcon tmp2Z = new ImageIcon(url[Integer.parseInt(tmp1Z)]);

							if((i == x + 1 && j == y) || (i == x - 1 && j == y) || (i == x  && j == y - 1) || (i == x && j == y + 1)){	
 									
								swapAndCheck(i, j, tmp, tmp1, tmp12, tmp2, tmpZ, tmp1Z, tmp2Z);
									
							}
							
                            			else{ 
                               			x = i;
                               			y = j;
                            			}
							   
						}
						
					}
					setText(CandyLevel.getScore(),CandyLevel.getMoves(),CandyLevel.getLevel());
					checkLevel();
									

					if (CandyLevel.getMoves() == 0) {
						if(b==0){
							int check = JOptionPane.showConfirmDialog(null, "Your Score is: " +CandyLevel.getScore() +" (Level: "+CandyLevel.getLevel()+") "+ " \nDo you want to Play again?");

							if (check == JOptionPane.YES_OPTION) {
								System.out.println("New game!");
								new Candy();
								candyGUI.dispose();

							} else if (check == JOptionPane.NO_OPTION) {
								System.out.println("Have a good day!");
								System.exit(0);
							}
						}
					}
				}
				 
					
			}	
	}
	
//===================================================================== A C T I O N 

}
