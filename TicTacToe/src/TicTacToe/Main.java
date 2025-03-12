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
			if((board[i][0] == 'O')&&(board[i][1] == 'O') &&(board[i][2] == ')'))
				return -1;
		}
		//Columns
		for(int i=0; i<3; i++) 
		{	
			if((board[0][i] == 'X')&&(board[1][i] == 'X') &&(board[2][i] == 'X'))
				return 1;
			if((board[0][i] == 'O')&&(board[1][i] == 'O') &&(board[2][i] == ')'))
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
		//Draw
		for(char i[] : board)
			for(char j : i)
				if(j==' ')
					return 0;
		
		return 2;	
	}
	public static boolean checkGameOver(char[][] board)
	{
		if(gameOver(board) == 1) 
		{
			printArray(board);
			System.out.println("Player 1 Wins!");
			return true;
		}
		else if(gameOver(board) == -1)	
		{
			printArray(board);
			System.out.println("Player 2 Wins!");
			return true;
		}
		else if(gameOver(board) == 2)
		{
			printArray(board);
			System.out.println("Its a Draw!");
			return true;
		}
		else
			return false;
	}
	public static void main(String[] args) 
	{
		char board[][] = {{' ', ' ',' '},{' ', ' ',' '},{' ', ' ',' '}};
		Set<Integer> spots_left = new HashSet<>();
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Input for your choices: ");
		System.out.println("[0][1][2]\n[3][4][5]\n[6][7][8]");
		
		while(true)
		{
			//Player 1
			printArray(board);		
			System.out.println("Player 1 Turn: ");
			int player1_choice = sc.nextInt();

			while((spots_left.contains(player1_choice)) || (player1_choice > 8))
			{
				System.out.println("Invalid Input");
				System.out.println("Player 1 Turn: ");
				player1_choice = sc.nextInt();
			}
			spots_left.add(player1_choice);
			int player1_i = player1_choice / 3;
			int player1_j = player1_choice % 3;
			
			board[player1_i][player1_j] = 'X';
			
			if(checkGameOver(board))
				break;
			
			//Player 2	
			printArray(board);		
			System.out.println("Player 2 Turn: ");
			int player2_choice = sc.nextInt();

			while((spots_left.contains(player2_choice)) || (player2_choice > 8))
			{
				System.out.println("Invalid Input");
				System.out.println("Player 1 Turn: ");
				player2_choice = sc.nextInt();
			}
			spots_left.add(player2_choice);
			int player2_i = player2_choice / 3;
			int player2_j = player2_choice % 3;
			
			board[player2_i][player2_j] = 'O';
			
			if(checkGameOver(board))
				break;
		}
		sc.close();
	}
}
