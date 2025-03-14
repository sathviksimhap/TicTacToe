package TicTacToe;
import java.util.*;

public class GameAI {
	char[][] board;
	Set<Integer> spaces_left = new HashSet<>();
	
	public GameAI(char[][] board) 
	{
		this.board = board;
		for(int i=0; i<9; i++)
			spaces_left.add(i);
	}
	private void updateBoard(char[][] board) 
	{
		this.board = board;
		int k=0;
		for(char[] i : board)
			for(char j : i)
			{
				if(j!=' ') 
					spaces_left.remove(k);
				k++;
			}
		System.out.println(spaces_left);
		
	}
	
	public int chooseSpot(char[][] board)
	{
		updateBoard(board);
		return firstFourMoves(board);
	}
	private int firstFourMoves(char[][] board)
	{
		
		int[] order_arr = {4, 1, 3, 5, 7, 0, 2, 6, 8};
		for(int i : order_arr)
		{
			if(spaces_left.contains(i))
				return i;
		}
		return -1;
	}
	public int tripler(char[][] board)
	{
		//rows
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
				if((board[i][j] == board[i][(j+1)%3])&&(board[i][j] != ' ')&&(board[i][(j+2)%3] == ' '))
					//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
					return i*3 + ((j+2)%3);		
		}
		return -1;
	}
}
