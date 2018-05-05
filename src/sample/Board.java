package sample;


import java.awt.Point;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mario
 */
public class Board {
        int rows, cols;
		char[][] board;

        public Board(int n_rows, int n_cols){
		this.rows = n_rows;
		this.cols = n_cols;
		this.board=new char[n_rows][n_cols];
		
		//fill the board up with blanks
		for(int i=0; i < n_rows; i++)
			for(int j=0; j< n_cols; j++)
				this.board[i][j]='.';
	}

		public void setBoard(char[][] board){
        	for(int i=0; i < rows; i++){
        		for(int j=0; j < cols; j++)
					{
						if (board[i][j] == 'R')
							 this.board[i][j] = 'X';
						else if(board[i][j] == 'Y')
							this.board[i][j] = 'O';
					}
			}
		}


        public Object clone() throws CloneNotSupportedException {
        Board new_state = new Board(this.rows, this.cols);
		for (int i = 0; i< this.rows; i++)
			new_state.board[i] = (char[]) this.board[i].clone();
		return new_state;
	}

        public ArrayList<Integer> getLegalActions(){
		ArrayList<Integer> actions = new ArrayList<Integer>();
		for(int j=0; j < this.cols; j++)
			if(this.board[0][j]=='.')
				actions.add(j);
		return actions;
	}

        public Board generateSuccessor(char agent, int action) throws CloneNotSupportedException{
		
		int row;
		for(row=0; row<this.rows && this.board[row][action]!='X' && this.board[row][action]!='O'; row++);
		Board new_state=(Board)this.clone();
   
		new_state.board[row-1][action]=agent;
		
		return new_state;
	}

        public boolean isGoal(char agent){
	
		String find=""+agent+""+agent+""+agent+""+agent;
		
		//check rows
		for(int i=0; i<this.rows; i++)
			if(String.valueOf(this.board[i]).contains(find))
				return true;
		
		//check cols
		for(int j=0; j<this.cols; j++){
			String col="";
			for(int i=0; i<this.rows; i++)
				col+=this.board[i][j];
				
			if(col.contains(find))
				return true;
		}
		
		//check diags
		ArrayList<Point> pos_right=new ArrayList<Point>();
		ArrayList<Point> pos_left=new ArrayList<Point>();
		
		for(int j=0; j<this.cols-4+1; j++)
			pos_right.add(new Point(0,j));
		for(int j=4-1; j<this.cols; j++)
			pos_left.add(new Point(0,j));	
		for(int i=1; i<this.rows-4+1; i++){
			pos_right.add(new Point(i,0));
			pos_left.add(new Point(i,this.cols-1));
		}
	
		//check right diags
		for (Point p : pos_right) {
			String d="";
			int x=p.x, y=p.y;
			while(true){				
				if (x>=this.rows||y>=this.cols)
					break;
				d+=this.board[x][y];
				x+=1; y+=1;
			}
			if(d.contains(find))
				return true;
		}
		
		//check left diags
		for (Point p : pos_left) {
			String d="";
			int x=p.x, y=p.y;
			while(true){
				if(y<0||x>=this.rows||y>=this.cols)
					break;
				d+=this.board[x][y];
				x+=1; y-=1;
			}
			if(d.contains(find))
				return true;
		}
		
		return false;
		
	}

        public double evaluationFunction(){
	
		if (this.isGoal('O'))
			return 1000.0;
		if (this.isGoal('X'))
			return -1000.0;
		
		return 0.0;
	}
}
