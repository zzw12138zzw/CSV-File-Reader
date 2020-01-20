/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cw3;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


/**
 *
 * @author WEI.ZHOU
 */
public class Cw3 {
	static JPanel[] p =  new JPanel[5];
	static Font f = new Font("Serif Bold", Font.PLAIN, 25);
	static ArrayList<String> Con = new ArrayList<String>();
	static ArrayList<String> Num = new ArrayList<String>();
	static Color c1 = new Color(100,50,30);
	static JPanel bot = new JPanel();
	static JFrame frame = new JFrame();
	static JLabel title;
	static ImageIcon key;
	static BufferedImage im;
	static JLabel t2,t3,t4;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		File home = new File("example.csv"); 
		JFileChooser j = new JFileChooser(home,FileSystemView.getFileSystemView()); 

		j.setDialogTitle("Choose a CSV File"); 

		j.showOpenDialog(null); 
		
		Scanner sc;
		StringTokenizer st;
		try {
			sc = new Scanner(new File(j.getSelectedFile().getAbsolutePath()));
			while (sc.hasNextLine()){
				st = new StringTokenizer(sc.nextLine(), ",");
				Con.add(st.nextToken());
				Num.add(st.nextToken());  
			}
			File g = new File("ele2.png");
			im = ImageIO.read(g);
			key = new ImageIcon(im);
			t3 = new JLabel(key);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		image image = new image();


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.setTitle("My Frame");
		frame.setLayout(new GridLayout(7,1));
		frame.setMinimumSize(new Dimension(100, 100));


		title = new JLabel("The population of elephants",JLabel.CENTER);
		title.setFont(f);
        title.setOpaque(true);
		title.setBackground(c1);
		
		t2 = new JLabel("Key  ");	
		t4 = new JLabel(" =  500 Elephants");

		t2.setFont(f);
		t4.setFont(f);
		

		bot.setOpaque(true);
		bot.setBackground(c1);
		
		frame.add(title);
		for(int i=0;i<5;i++) {
			frame.add(p[i]);
		}
		frame.add(bot);
		
		p[1].addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent ce) {
		    	if(frame.getWidth()!=800 ||frame.getHeight()!=800) {
				double widPer = (double)frame.getWidth()/800;
				double heiPer = (double)frame.getHeight()/800;

				double a = im.getWidth()*widPer;
				double b = im.getHeight()*heiPer;
				System.out.println(widPer +"  "+ heiPer +"  "+  a+"  "+  b);
				Image newImage = im.getScaledInstance((int)(im.getWidth()*widPer), (int)(im.getHeight()*heiPer), Image.SCALE_DEFAULT);
				BufferedImage newIm = new BufferedImage(newImage.getWidth(null), newImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D bGr = newIm.createGraphics();
				bGr.drawImage(newImage, 0, 0, null);
				bGr.dispose();
				key = new ImageIcon(newIm);
				bot.removeAll();
				t3 = new JLabel(key);
		    	add();
		    	double percent = (double)frame.getWidth()/800;
		    	f = new Font("Serif Bold", Font.PLAIN, (int)(25*percent));
		    	System.out.println("++++++ "+percent+ce.getComponent().getClass().getName() + " --- Resized ");
		    	
		    	t2.setFont(f);
				t4.setFont(f);
				title.setFont(f);
		    	}	
		    }
		});
		add();
		frame.setVisible(true);
	}
	
	static void add() {
		bot.add(t2);
		bot.add(t3);
		bot.add(t4);
	}



}
