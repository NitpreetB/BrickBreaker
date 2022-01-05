package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX =310;
	
	private int ballposX=120;
	private int ballposY = 350;
	private int ballXdir= -1;
	private int  ballYdir = -2;
	
	private MapGenerator gamemap;
	
	public Gameplay() {
		
		gamemap=new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer (delay,this);
		timer.start();
	}
	
	public void paint (Graphics g) {
		
		//background 
		
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		//drawing map 
		
		gamemap.draw((Graphics2D)g);
		
		//boarders
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0 , 3, 592);
		g.fillRect(0, 0 , 692, 3);
		g.fillRect(683, 0 , 3, 592);
		
		//scores 
		
		g.setColor(Color.white);
		g.setFont(new Font ("serif",Font.BOLD,25));
		g.drawString(""+score, 590, 30);
		
		//user rectangle 
		
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//the ball 
		
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		
		//you won 
		
		if (totalBricks<=0)
		{
			play = false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font ("serif",Font.BOLD,30));
			g.drawString("YOU WON", 190, 300);
			
			g.setFont(new Font ("serif",Font.BOLD,20));
			g.drawString("Press enter to restart :", 230, 350);
		}
		
		//you lost 
		if (ballposY>570)
		{
			play = false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font ("serif",Font.BOLD,30));
			g.drawString("GAME OVER, Scores :", 190, 300);
			
			g.setFont(new Font ("serif",Font.BOLD,20));
			g.drawString("Press enter to restart :", 230, 350);
		}
		
		
		g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		timer.start();
		
		if (play) 
		{
			if (new Rectangle (ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)) )
			{
				ballYdir=-ballYdir;
			}
			
			A: for ( int x=0; x<gamemap.map.length;x++)
			{
				for(int y=0;y<gamemap.map[0].length; y++)
				{
					if (gamemap.map[x][y] >0)
					{
						int brickX= y*gamemap.brickwidth+80;
						int brickY= y*gamemap.brickheight+60;
						int brickwidth = gamemap.brickwidth;
						int brickheight = gamemap.brickheight;
						
						Rectangle rect = new Rectangle ( brickX,brickY, brickwidth, brickheight);
						Rectangle ballRect =  new Rectangle (ballposX,ballposY,20,20);
						Rectangle brickRect= rect;
						
						if (ballRect.intersects(brickRect))
						{
							gamemap.setBrickValue(0, x, y);
							totalBricks --;
							score += 5;
							
							if (ballposX+19 <=brickRect.x || ballposX +1 >= brickRect.x + brickRect.width)
							{
								ballXdir = -ballXdir;
							}
							else 
							{
								ballYdir = -ballYdir;
							}
							
							//break Lablel
							
							break A;
						}
					}
				}
			}
			
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if (ballposX<0)
			{
				ballXdir=-ballXdir;
			}
			if (ballposY<0)
			{
				ballYdir=-ballYdir;
			}
			if (ballposX>670)
			{
				ballXdir=-ballXdir;
			}
			
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if (playerX>=600) 
			{
				playerX=600;	
			}
			else 
			{
				moveRight();
			}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if (playerX<=10) 
			{
				playerX=10;	
			}
			else 
			{
				moveLeft();
			}
		}
			
		if (e.getKeyCode()== KeyEvent.VK_ENTER) 
		{
			if (!play)
			{
				play = true;
				ballposX= 120;
				ballposY=350;
				ballXdir=-1;
				ballYdir= -2;
				playerX=310;
				score = 0;
				totalBricks = 21;
				gamemap= new MapGenerator(3,7);
			}
		}
	}
	
	
	public void moveRight()
	{
		play = true;
		playerX+=20;
	}
	
	public void moveLeft()
	{
		play = true;
		playerX-=20;
	}

}