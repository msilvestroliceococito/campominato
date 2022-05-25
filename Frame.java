package campo_minato;

import java.util.Random;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Frame extends JFrame{
	int[][] matr = new int[9][9];
	JButton[][] b = new JButton[9][9];
	JButton nuovapar = new JButton("NUOVA PARTITA");
	JPanel bottoni = new JPanel();
	int nbombe = 10;
	JLabel l = new JLabel("Benvenuto in campo minato");
	
	public Frame() {
		
		//for(int )
	      Random rand = new Random(); //instance of random class
	      int upperbound = 25;
	        //generate random values from 0-24
	      int int_random = rand.nextInt(upperbound); 
		
		
		bottoni.setLayout(new GridLayout(9,9));
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				b[i][j] = new JButton();
				bottoni.add(b[i][j]);
				
			}
			
		}
		

		
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setFont(new Font("Comic Sans",Font.BOLD,25));
		this.setTitle("CAMPO MINATO");
		this.setLayout(new BorderLayout());
		this.getContentPane().add(l,"North");
		this.getContentPane().add(bottoni,"Center");
		this.getContentPane().add(nuovapar,"South");
		this.setSize(600,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
