package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

	public int map[][];
	public int brickwidth;
	public int brickheight;
	
	public MapGenerator (int row, int col)
	{
		map = new int [row][col];
		for (int rows = 0; rows< map.length; rows++)
		{
			for ( int cols = 0; cols< map[0].length; cols++)
			{
				map[rows][cols]=1;
			}
		}
		
		brickwidth = 540/col;
		brickheight = 150/row;
		
	}
	
	
	public void draw (Graphics2D g)
	{
		for (int rows = 0; rows< map.length; rows++)
		{
			for ( int cols = 0; cols< map[0].length; cols++)
			{
				if(map[rows][cols]>0)
				{
					g.setColor(Color.white);
					g.fillRect(cols*brickwidth+80, rows*brickheight+50, brickwidth, brickheight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(cols*brickwidth+80, rows*brickheight+50, brickwidth, brickheight);
				}
			}
		}
	}
	
	public void setBrickValue (int value, int row, int col)
	{
		map[row][col]=value;
		
	}
}
