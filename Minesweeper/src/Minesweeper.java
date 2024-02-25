import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.Iterator;


public class Minesweeper implements MouseInputListener{
	JFrame frame;
	Buttons[][] board = new Buttons[10][10];
	int countOpenButton;
	
	public Minesweeper() {
		countOpenButton = 0;
		frame = new JFrame();
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10,10));
		
		addButtons();
		generateMine();
		updateCount();
		//printMine();
		//print();
		
		//frame.addMouseListener(this);
		
		frame.setVisible(true);
	}
	
	public void generateMine() {
		int i = 0 ;
		while(i < 10) {
			int randRow = (int) (Math.random() * board.length);
			int randCol = (int) (Math.random() * board[0].length);
			
			while(board[randRow][randCol].isMine()) {
				randRow = (int) (Math.random() * board.length);
				randCol = (int) (Math.random() * board[0].length);
			}
			
			board[randRow][randCol].setMine(true);
			i++;
		}
	}
	
	public void print() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("mine.png"));
				} else {
					board[row][col].setText(board[row][col].getCount()+"");
					board[row][col].setEnabled(false);
				}
			}
		}
	}
	
	public void addButtons() {
		for (int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				Buttons button = new Buttons(row, col);
				frame.add(button);
				board[row][col] = button;
				button.addMouseListener(this);
			}
		}
	}

	public void updateCount() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					counting(row,col);
				}
			}
		}
	}
	
	public void counting(int row, int col) {
		for (int r = row-1; r <=row + 1; r++ ) {
			for (int c = col-1; c <= col+1; c++) {
				try {
 					int value = board[r][c].getCount();
					board[r][c].setCount(++value);
				} catch (Exception e) {
					// TODO: handle exception
				}
			
			}	
		}
	}
	
	public void open(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col].getText().length() > 0
				|| board[row][col].isEnabled() == false) {
			return;
		} else if(board[row][col].getCount() != 0) {
			board[row][col].setText(board[row][col].getCount()+"");
			board[row][col].setEnabled(false);
			countOpenButton++;
		} else {
			countOpenButton++;
			board[row][col].setEnabled(false);
			open(row-1, col);
			open(row+1, col);
			open(row, col-1);
			open(row, col+1);
		}
	}
	
	public void printMine() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("mine.png"));
				}
			}
		}}

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Buttons buttons = (Buttons) e.getComponent();
		if (e.getButton()==1)
		{
			if (buttons.isMine()) {
				JOptionPane.showMessageDialog(frame,"Game Over","Game Over",JOptionPane.INFORMATION_MESSAGE);
				print();
			}
			 else { 
					open(buttons.getRow(), buttons.getCol());
					if (countOpenButton == (board.length * board[0].length)-10) {
						JOptionPane.showMessageDialog(frame,"Won the Game");
					}
			 }
		}
		else if (e.getButton()==3) {
			if (!buttons.isFlag()) {
				buttons.setIcon(new ImageIcon("flag.png"));
				buttons.setFlag(true);
			}
			else {
				buttons.setIcon(null);
				buttons.setFlag(false);
			}
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
