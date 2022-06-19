package campo_minato;

import java.util.Random;

import java.net.URL;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	int[][] matr = new int[9][9];
	JButton[][] b = new JButton[9][9];
	JButton nuovapar = new JButton("NUOVA PARTITA");
	JLabel l = new JLabel();
	JPanel bottoni = new JPanel();
	JPanel upper = new JPanel(); 
	JLabel nflag = new JLabel();
	JLabel timerlabel = new JLabel();
	JPanel time = new JPanel();
	Random rand = new Random();
	int nbombe = 10;
	int flag_cont = 10;
	int int_random =0;
	int action_count=0;
	ImageIcon bandiera = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Roberta Borsa\\\\eclipse-workspace\\\\campo_minato\\\\src\\\\campo_minato\\\\bandiera.png")).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bombaX = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Roberta Borsa\\\\eclipse-workspace\\\\campo_minato\\\\src\\\\campo_minato\\\\bombaX.png")).getImage()).getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bomba = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Roberta Borsa\\\\eclipse-workspace\\\\campo_minato\\\\src\\\\campo_minato\\\\bomba.png")).getImage()).getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
	
	int elapsedTime = 0;
	 int seconds =0;
	 int minutes =0;
	 int hours =0;
	 boolean started = false;
	 String seconds_string = String.format("%02d", seconds);
	 String minutes_string = String.format("%02d", minutes);
	 String hours_string = String.format("%02d", hours);
	 Timer timer = new Timer(1000, new ActionListener() {
		  
		  public void actionPerformed(ActionEvent e) {
		   
		   elapsedTime=elapsedTime+1000;
		   hours = (elapsedTime/3600000);
		   minutes = (elapsedTime/60000) % 60;
		   seconds = (elapsedTime/1000) % 60;
		   seconds_string = String.format("%02d", seconds);
		   minutes_string = String.format("%02d", minutes);
		   hours_string = String.format("%02d", hours);
		   timerlabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		   
		  }
		  
		 });

	
	public Frame() {

		
		ImageIcon bandieraupper = new ImageIcon(((new ImageIcon("C:\\\\Users\\\\Roberta Borsa\\\\eclipse-workspace\\\\campo_minato\\\\src\\\\campo_minato\\\\bandiera.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		 nflag.setText(Integer.toString(flag_cont));
		 nflag.setIcon(bandieraupper);
	   
		
		timerlabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		timerlabel.setBounds(0,0,100,50);
		timerlabel.setFont(new Font("Verdana",Font.PLAIN,35));
		timerlabel.setBorder(BorderFactory.createBevelBorder(1));
		timerlabel.setOpaque(true);
		timerlabel.setHorizontalAlignment(JTextField.CENTER);
	    time.add(timerlabel);
		upper.add(nflag);
		upper.add(time);
		upper.setLayout(new FlowLayout());

	    
		nuovapar.addActionListener(this);
		nuovapar.setBackground(new Color(220,220,220));
		
		bottoni.setLayout(new GridLayout(9,9));
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				b[i][j] = new JButton();
				bottoni.add(b[i][j]);
				b[i][j].addActionListener(this);
				b[i][j].addMouseListener(new CustomMouseListener());
				b[i][j].setBackground(new Color(220,220,220));
				
			}
			
		}
		boolean stato=false;
		for(int i=0;i<10;i++) {
			stato = false;
			int_random = rand.nextInt(81); 
			while(matr[int_random/9][int_random%9] == 8) {
				int_random = rand.nextInt(81); 
			}
			
			//System.out.println(int_random+" "+int_random/9+" "+int_random%9);
			matr[int_random/9][int_random%9] = 8;
		}
		int cont=0;
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(matr[i][j]!=8) {
					for(int x=i-1;x<i+2;x++) {
						if(x>=0 && x<9) {
							for(int z=j-1;z<j+2;z++) {
								if(z>=0 &&z<9) {
									if(matr[x][z]==8) {
										cont++;
									}
								}
							}	
					}
				}
			matr[i][j] = cont;
			
			cont=0;
			}
				//System.out.print(matr[i][j]);	
		}//System.out.println();
		}

		

		this.setTitle("CAMPO MINATO");
		this.setLayout(new BorderLayout());
		this.getContentPane().add(upper,"North");
		this.getContentPane().add(bottoni,"Center");
		this.getContentPane().add(nuovapar,"South");
		this.setSize(600,600);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if (action_count==0) timer.start();
				if(e.getSource()==b[i][j]) {
					if (action_count==70) {
						l.setText("HAI VINTO!");
					}
					b[i][j].setEnabled(false);
					if(matr[i][j]!=8) {
						if(matr[i][j]!=0) {
						b[i][j].setText(Integer.toString(matr[i][j]));
						b[i][j].setBackground(Color.LIGHT_GRAY);
						b[i][j].setIcon(null);
						action_count++;
						}
						else {
							b[i][j].setBackground(Color.LIGHT_GRAY);
							b[i][j].setIcon(null);
							action_count++;
							libera_spazi(i,j);
							
						}
					}else {
						if(!l.getText().equals("HAI VINTO!")) {
							l.setText("HAI PERSO!");
						}
						
						for(int x=0;x<9;x++) {
							for(int z=0;z<9;z++) {
								if(matr[x][z]==8) {
									if(b[x][z].getIcon()==null) {
									b[x][z].setIcon(bomba);
									b[x][z].setEnabled(false);
								}}
								else {
									if(b[x][z].getIcon()==null) {
										b[x][z].setEnabled(false);
									}
									else {
										b[x][z].setIcon(bombaX);
									}
										
								}
								}
							}
						
						for(int cont=0;cont<81;cont++) {
							b[cont/9][cont%9].setEnabled(false);
						}
					}}
					
					
				}
			}
			if(e.getSource()==nuovapar) {
				this.dispose();
				Frame frame1 = new Frame();
			}
		}
		
	
	public void libera_spazi(int x, int y) {
		if(x-1>=0 && x-1<9 && b[x-1][y].isEnabled()) {
			if(matr[x-1][y]==0) {
				b[x-1][y].setEnabled(false);
				b[x-1][y].setBackground(Color.LIGHT_GRAY);
				b[x-1][y].setIcon(null);
				System.out.println(x-1+" "+y);
				action_count++;
				libera_spazi(x-1,y);
			}
		}		
		if(x+1>=0 && x+1<9 && b[x+1][y].isEnabled()) {
			if(matr[x+1][y]==0) {
				b[x+1][y].setEnabled(false);
				b[x+1][y].setBackground(Color.LIGHT_GRAY);
				b[x+1][y].setIcon(null);
				action_count++;
				libera_spazi(x+1,y);
			}
		}
		if(y+1>=0 && y+1<9 && b[x][y+1].isEnabled()) {
			if(matr[x][y+1]==0) {
				b[x][y+1].setEnabled(false);
				b[x][y+1].setBackground(Color.LIGHT_GRAY);
				b[x][y+1].setIcon(null);
				action_count++;
				libera_spazi(x,y+1);
			}
		}
		if(y-1>=0 && y-1<9 && b[x][y-1].isEnabled()) {
			if(matr[x][y-1]==0) {
				b[x][y-1].setEnabled(false);
				b[x][y-1].setBackground(Color.LIGHT_GRAY);
				b[x][y-1].setIcon(null);
				action_count++;
				libera_spazi(x,y-1);
			}
		}
		
	}

class CustomMouseListener implements MouseListener {
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
        	for(int i=0;i<9;i++) {
    			for(int j=0;j<9;j++) {
    				if(e.getSource()==b[i][j]) {
    					if(b[i][j].isEnabled()) {
    					if(b[i][j].getIcon()==null) {
    						b[i][j].setIcon(bandiera);
    						flag_cont++;
    					}
    					else {
    						b[i][j].setIcon(null);
    						if(flag_cont>0) flag_cont--;
    					}
    				}}
    			}
           
          }
       
    }}
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
}}

