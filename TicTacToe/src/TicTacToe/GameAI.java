package TicTacToe;
import java.util.*;

public class GameAI 
{
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
		
		int[] order_arr = {4, 0, 2, 6, 8, 1, 3, 5, 7};
		for(int i : order_arr)
		{
			if(spaces_left.contains(i))
				return i;
		}
		return -1;
	}
	
	public int doubler()
	{
		if(spaces_left.size() > 5) return -1;
		int def_sol = -1, i_arr[] = {1, 0, 2}; //return defending solution only if there is no attack on the board
		
		for(int i : i_arr)
			for(int j :i_arr)
			{
				//rows
				if((board[i][j]!=' ')&&(board[i][(j+1)%3]==' ')&&(board[i][(j+2)%3]==' '))
					//cond1:1 non null value exists cond2&3: 2&3 values are null 
				{
					if(board[i][j]=='O') return i*3 + (j+1)%3;
					else def_sol = i*3 + (j+1)%3;
				}
				//columns
				if((board[i][j]!=' ')&&(board[(i+1)%3][j]==' ')&&(board[(i+2)%3][j]==' '))
					//cond1:1 non null value exists cond2&3: 2&3 values are null 
				{
					if(board[j][i]=='O') return ((i+1)%3)*3 + j;
					else def_sol = ((i+1)%3)*3 + j;
				}
			}
		return def_sol;
	}
	
	private int tripler()
	{
		int def_sol= -1;//return defending solution only if there is no win on the board
		
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++) 
			{
				//rows
				if((board[i][j] == board[i][(j+1)%3])&&(board[i][j] != ' ')&&(board[i][(j+2)%3] == ' '))
					//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
				{
					if(board[i][j]=='O') return i*3 + ((j+2)%3);	
					else def_sol = i*3 + ((j+2)%3);
				}
				//columns
				if((board[j][i] == board[(j+1)%3][i])&&(board[j][i] != ' ')&&(board[(j+2)%3][i] == ' '))
					//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
				{
					if(board[j][i]=='O') return ((j+2)%3)*3 + i;
					else def_sol = ((j+2)%3)*3 + i;
				}
			}
		//diagonal1
		for(int i=0; i<3; i++)
		{
			if((board[i][i] == board[(i+1)%3][(i+1)%3])&&(board[i][i] != ' ')&&(board[(i+2)%3][(i+2)%3] == ' '))
				//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
			{
				if(board[i][i]=='O') return ((i+2)%3*3) + ((i+2)%3);	
				else def_sol = ((i+2)%3*3) + ((i+2)%3);
			}
		}
		//diagonal2 
		int[] is = {0, 1, 2}, js = {2, 1, 0};//using these arrays to get appropriate kth [i][j] pair | is[k+n], js[k+n] represent i+n, j+n from above
		for(int k=0; k<3; k++)
		{	
			if((board[is[k]][js[k]] == board[is[(k+1)%3]][js[(k+1)%3]])&&(board[is[k]][js[k]] != ' ')&&(board[is[(k+2)%3]][js[(k+2)%3]] == ' '))
				//cond1:if 2 r matching | cond2: 2 matching r not null | cond3: 1 not matching is null
				return (is[(k+2)%3])*3 + (js[(k+2)%3]);	
		}
		return def_sol;
	}
}
