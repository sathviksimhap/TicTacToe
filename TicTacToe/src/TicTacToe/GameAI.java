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
		
		int t = tripler();
		if(t!=-1) return t;
		
		int d = doubler();
		if(d!=-1) return d;
		
		return singler();
	}
	private int singler()
	{
		
		int[] order_arr = {4, 1, 3, 5, 7, 0, 2, 6, 8};
		for(int i : order_arr)
		{
			if(spaces_left.contains(i))
				return i;
		}
		return -1;
	}
	public int doubler()
	{
		return -1;
	}
	private int tripler()
	{
		//rows
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if((board[i][j] == board[i][(j+1)%3])&&(board[i][j] != ' ')&&(board[i][(j+2)%3] == ' '))
					//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
					return i*3 + ((j+2)%3);	
		
		//columns
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if((board[j][i] == board[(j+1)%3][i])&&(board[j][i] != ' ')&&(board[(j+2)%3][i] == ' '))
					//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
					return ((j+2)%3)*3 + i;
		
		//diagonals
		//diagonal1
		for(int i=0; i<3; i++)
		{
			if((board[i][i] == board[(i+1)%3][(i+1)%3])&&(board[i][i] != ' ')&&(board[(i+2)%3][(i+2)%3] == ' '))
				//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
				return ((i+2)%3*3) + ((i+2)%3);	
		}
		//diagonal2 
		int[] is = {0, 1, 2}, js = {2, 1, 0};//using these arrays to get appropriate kth [i][j] pair | is[k+n], js[k+n] represent i+n, j+n from above
		for(int k=0; k<3; k++)
		{	
			if((board[is[k]][js[k]] == board[is[(k+1)%3]][js[(k+1)%3]])&&(board[is[k]][js[k]] != ' ')&&(board[is[(k+2)%3]][js[(k+2)%3]] == ' '))
			{
				System.out.println("d2, k:"+ k);
				//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
				return (is[(k+2)%3])*3 + (js[(k+2)%3]);	
			}
		}
		return -1;
	}
}
