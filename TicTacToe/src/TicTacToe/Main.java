package TicTacToe;
import java.util.*;

public class Main 
{	
	public static void printArray(char[][] arr)
	{
		System.out.println("Current Board: ");
		for(char[] i : arr)
		{
			for(char j : i)
				System.out.print("[" + j + "]");
			System.out.println("");
		}
	}
	public static int gameOver(char[][] board)
	{
		//Rows
		for(int i=0; i<3; i++) 
		{	
			if((board[i][0] == 'X')&&(board[i][1] == 'X') &&(board[i][2] == 'X'))
				return 1;
			if((board[i][0] == 'O')&&(board[i][1] == 'O') &&(board[i][2] == 'O'))
				return -1;
		}
		//Columns
		for(int i=0; i<3; i++) 
		{	
			if((board[0][i] == 'X')&&(board[1][i] == 'X') &&(board[2][i] == 'X'))
				return 1;
			if((board[0][i] == 'O')&&(board[1][i] == 'O') &&(board[2][i] == 'O'))
				return -1;
		}
		//Diagonals
		if(board[1][1] == 'X')
		{
			if(((board[0][0] == 'X')&&(board[2][2] == 'X')) || ((board[0][2] == 'X')&&(board[2][0] == 'X')))
				return 1;
		}
		if(board[1][1] == 'O')
		{
			if(((board[0][0] == 'O')&&(board[2][2] == 'O')) || ((board[0][2] == 'O')&&(board[2][0] == 'O')))
				return -1;
		}
		//Game still running
		for(char i[] : board)
			for(char j : i)
				if(j==' ')
					return 2;
		//Draw
		return 0;	
	}
	public static boolean checkGameOver(char[][] board)
	{
		int game_over = gameOver(board);
		if(game_over == 1) 
		{
			printArray(board);
			System.out.println("Player 1 Wins!");
			return true;
		}
		else if(game_over == -1)	
		{
			printArray(board);
			System.out.println("Computer Wins!");
			return true;
		}
		else if(game_over == 0)
		{
			printArray(board);
			System.out.println("Its a Draw!");
			return true;
		}
		else
			return false;
	}
	public void player()
	{
			
	}
	public void computer()
	{
		
	}
	public static void main(String[] args) 
	{
		char board[][] = {{' ', ' ',' '},{' ', ' ',' '},{' ', ' ',' '}};
		Set<Integer> spots_used = new HashSet<>();
		
		GameAI ai = new GameAI(board);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input for your choices: ");
		System.out.println("[0][1][2]\n[3][4][5]\n[6][7][8]");
		System.out.println("Would You Like To Go First(f) or Second(s): ");
		
		char fs = sc.next().charAt(0);
		boolean fsb = false;
		if(fs=='f') fsb = true;
		
		while(fsb)
		{	
			printArray(board);
			//Player
			System.out.println("Player's Turn: ");
			int player_choice = sc.nextInt();

			while((spots_used.contains(player_choice)) || (player_choice > 8))
			{
				System.out.println("Invalid Input Choose Another Spot: ");
				player_choice = sc.nextInt();
			}
			spots_used.add(player_choice);
			int player_i = player_choice / 3;
			int player_j = player_choice % 3;
			
			board[player_i][player_j] = 'X';
			
			if(checkGameOver(board))
				break;
			
			printArray(board);
			//Computer
			//waiting for dramatic effect
			printArray(board);
			System.out.println("Thinking...");
			
			try {
			    Thread.sleep(3000); // 1000 milliseconds = 1 second
			} 
			catch (InterruptedException e) {
			    e.printStackTrace();
			}
			
			//Computer	
			int comp_choice = ai.chooseSpot(board);

			spots_used.add(comp_choice);
			int comp_i = comp_choice / 3;
			int comp_j = comp_choice % 3;
			
			board[comp_i][comp_j] = 'O';
			
			if(checkGameOver(board))
				break;
		}
		while(!fsb)
		{	
			//waiting for dramatic effect
			printArray(board);
			System.out.println("Thinking...");
			
			try {
			    Thread.sleep(3000); // 1000 milliseconds = 1 second
			} 
			catch (InterruptedException e) {
			    e.printStackTrace();
			}
			
			//Computer	
			int comp_choice = ai.chooseSpot(board);

			spots_used.add(comp_choice);
			int comp_i = comp_choice / 3;
			int comp_j = comp_choice % 3;
			
			board[comp_i][comp_j] = 'O';
			
			if(checkGameOver(board))
				break;
			
			printArray(board);
			//Player 1			
			System.out.println("Player's Turn: ");
			int player_choice = sc.nextInt();

			while((spots_used.contains(player_choice)) || (player_choice > 8))
			{
				System.out.println("Invalid Input Choose Another Spot: ");
				player_choice = sc.nextInt();
			}
			spots_used.add(player_choice);
			int player_i = player_choice / 3;
			int player_j = player_choice % 3;
			
			board[player_i][player_j] = 'X';
			
			if(checkGameOver(board))
				break;			
		}
		sc.close();
	}
}
