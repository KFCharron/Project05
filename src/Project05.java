
// GUI-related imports

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Project05 extends Frame implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//program description displayed in About menu
	String[] description = new String[]
			{
				"This program implements and displays ",
				"the results of the following algorithms ",
				"using recursion:",
				"",
				"-Summing Series",
				"-Combination",
				"-Towers of Hanoi"
			};
		
	//Recursion file
	Recursion recur = new Recursion();
	
	String command = "";
	String userInput;
	boolean properInput;
	int N;
	int K;
	int numOfDisks;
	float SSResult;
	float combResult;
	int towersResult;
	long SSTimeElapsed;
	long combTimeElapsed;
	long towersTimeElapsed;
	char A = 'A';
	char B = 'B';
	char C = 'C';
	DecimalFormat df = new DecimalFormat();

		
	public static void main(String[] args)
	{
		Frame frame = new Project05();
		
			
		frame.setResizable(true);
		frame.setSize(700,900);
		frame.setVisible(true);
		
	}
	
	public Project05()
	{
		setTitle("Recursive Functions");
		
		// Create Menu Bar and menu items
		MenuBar mb = new MenuBar();
		setMenuBar(mb);
		
		// Create Menu Group Labeled "File"
		
		Menu FileMenu = new Menu("File");
		
		// Add it to Menu Bar
		
		mb.add(FileMenu);
		
		// Create Menu Items
		// Add action Listener 
		// Add to "File" Menu Group
		
		MenuItem miAbout = new MenuItem("About");
		miAbout.addActionListener(this);
		FileMenu.add(miAbout);
						
		MenuItem miExit = new MenuItem("Exit");
		miExit.addActionListener(this);
		FileMenu.add(miExit);

		// Create Menu Group Labeled "File"
		
		Menu AlgorithmMenu = new Menu("Recursion");
		
		// Add it to Menu Bar
		
		mb.add(AlgorithmMenu);
		
		// Create Menu Items
		// Add action Listener 
		// Add to "Search" Menu Group
		
		MenuItem miSummingSeries = new MenuItem("Summing Series");
		miSummingSeries.addActionListener(this);
		AlgorithmMenu.add(miSummingSeries);
		
		MenuItem miCombination = new MenuItem("Combination(N,K)");
		miCombination.addActionListener(this);
		AlgorithmMenu.add(miCombination);
		
		MenuItem miTowers = new MenuItem("Towers of Hanoi");
		miTowers.addActionListener(this);
		AlgorithmMenu.add(miTowers);
		
		WindowListener l = new WindowAdapter()
		{
						
			public void windowClosing(WindowEvent ev)
			{
				System.exit(0);
			}
			
			public void windowActivated(WindowEvent ev)
			{
				repaint();
			}
			
			public void windowStateChanged(WindowEvent ev)
			{
				repaint();
			}
		
		};
		
		ComponentListener k = new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e) 
			{
        		repaint();           
    		}
		};
		
		// register listeners
			
		this.addWindowListener(l);
		this.addComponentListener(k);

	}
	
//******************************************************************************
//  called by windows manager whenever the application window performs an action
//  (select a menu item, close, resize, ....
//******************************************************************************

	public void actionPerformed (ActionEvent ev)
		{
			// figure out which command was issued
			df.setMaximumFractionDigits(2);
			command = ev.getActionCommand();
			
			// take action accordingly
						
			if ("About".equals(command))
			{
				setTitle("About Project05");
				repaint();
			}
			else
			if("Exit".equals(command))
			{
				System.exit(0);
			}
			else
			if("Summing Series".equals(command))
			{
				setTitle("Summing Series");
				
				//Ask for N, check if valid
				properInput = false;
				while(!properInput)
				{
					try {
					userInput = JOptionPane.showInputDialog("What is the value of N?");
					N = Integer.parseInt(userInput);
					if (N > 0)
						properInput = true;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Improper value entered. Please enter a valid integer.");
				}
				}
				//Set up time elapsed, execute recursion
				SSTimeElapsed = System.nanoTime();
				SSResult = recur.SummingSeries(N);
				SSTimeElapsed = System.nanoTime() - SSTimeElapsed;
				
				System.out.print("SSResult: " + SSResult + " SSTimeElapsed: " + SSTimeElapsed);
				
				//Display results
				repaint();
			}
			if("Combination(N,K)".equals(command))
			{
				setTitle("Combination(N,K)");
				
				//Ask for N, check if valid
				properInput = false;
				while(!properInput)
				{
					try {
						userInput = JOptionPane.showInputDialog("What is the value of N?");
						N = Integer.parseInt(userInput);
					properInput = true;
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Improper value entered. Please enter a valid integer.");
				}
				}
				
				//Ask for K, check if valid
				properInput = false;
				while (!properInput) {
					try {
						userInput = JOptionPane.showInputDialog(" C(" + N + ",K) What is the value of K?");
						K = Integer.parseInt(userInput);
						properInput = true;
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Improper value entered. Please enter a valid integer.");
					}
				}
				
				//Start time elapsed, execute recursion
				combTimeElapsed = System.nanoTime();
				combResult = recur.Combination(N,K);
				combTimeElapsed = System.nanoTime() - combTimeElapsed;
				
				System.out.print("combResult: " + combResult + " combTimeElapsed: " + combTimeElapsed);
				
				//Display results
				repaint();
			}
			if("Towers of Hanoi".equals(command))
			{
				setTitle("Towers of Hanoi");
				//Ask for number of disks, check if valid
				properInput = false;
				while (!properInput) {
					try {
						userInput = JOptionPane.showInputDialog("How many disks would you like to move?");
						numOfDisks = Integer.parseInt(userInput);
						properInput = true;
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Improper value entered. Please enter a valid integer.");
					}
				}
				
				//Start timer, execute recursive method
				towersTimeElapsed = System.nanoTime();
				recur.TowersOfHanoi(numOfDisks, A, C, B);
				towersTimeElapsed = System.nanoTime() - towersTimeElapsed;
				int x = 0;
				while (x<recur.moveCount)
				{
					System.out.println(x + " " + recur.diskNumber[x] + " " + recur.fromShaft[x] + " " + recur.toShaft[x]);
					x++;
				}
				
				//Display results
				repaint();
			}
			
			
		}
//********************************************************
// called by repaint() to redraw the screen
//********************************************************
		
		public void paint(Graphics g)
		{
			
			int x = 240;
			int y = 280;
			
			if("About".equals(command))
			{
				for (int i=0; i<(description.length); i++)
				{
					g.drawString(description[i], x, y);
					y=y+25;
				}
			}
			
			if("Summing Series".equals(command))
			{
				g.drawString("N = "+ N, x, y);
				y=y+25;
				g.drawString("The result: " + df.format(SSResult), x, y);
				y=y+25;
				g.drawString("Elapsed Time: " + SSTimeElapsed + " nanoseconds", x, y);
				
			}
			if("Combination(N,K)".equals(command))
			{
				df.setMaximumFractionDigits(0);
				g.drawString("N = "+ N, x, y);
				y=y+25;
				g.drawString("K = "+ K, x, y);
				y=y+25;
				g.drawString("The result: " + df.format(combResult)  + " different possible combinations", x, y);
				y=y+25;
				g.drawString("Elapsed Time: " + combTimeElapsed + " nanoseconds", x, y);
			}
			
			if("Towers of Hanoi".equals(command))
			{
				x = 30;
				y = 100;
				int x2 = 60;
				int x3 = 90;
				g.drawString("Disk # | From | To",x,y);
				g.drawLine(x+3, y+7, x+40, y+7);
				g.drawLine(x+55, y+7, x+85, y+7);
				g.drawLine(x+100, y+7, x+114, y+7);
				y=y+25;
				for (int i = 0; i < recur.moveCount; i++) {
					if(y>500)
					{
						x = x+150;
						x2 = x2+150;
						x3 = x3+150;
						y = 100;
						g.drawString("Disk # | From | To",x,y);
						g.drawLine(x+3, y+7, x+40, y+7);
						g.drawLine(x+55, y+7, x+85, y+7);
						g.drawLine(x+100, y+7, x+114, y+7);
						y=y+15;
						g.drawLine(x-15, y-30, x-15, y+390);
						y=y+10;
					}
					g.drawString(Integer.toString(recur.diskNumber[i]), x+15, y);
					
					g.drawString(String.valueOf(recur.fromShaft[i]), x2+35, y);
					
					g.drawString(String.valueOf(recur.toShaft[i]), x3+44, y);
					y = y+25;
					
				}
				g.drawString("Total Number of moves = "+ (recur.moveCount), 500, 650);
				g.drawString("Elapsed Time = " + towersTimeElapsed, 500, 675);
			}
			
			
			
		}
		
		
		
		


}	





