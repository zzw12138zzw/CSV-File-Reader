/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cw3;


import java.awt.*;
import java.awt.event.*;

import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 *
 * @author WEI.ZHOU
 */


public class image extends Cw3 {
	Font font = new Font("Serif Bold", Font.PLAIN, 15);
	JLabel[] cons = new JLabel[Con.size()];
	JPanel[] nums = new JPanel[Num.size()];
	ImageIcon[] icons;

	ImageIcon icon;
	int num, n, r;
	Image newImage;


	public image(){
		if(Con.size() > 0) {    		
			for(int i=0; i<Con.size();i++) {
				cons[i] = new JLabel(Con.get(i), JLabel.CENTER);
				cons[i].setFont(font);
				cons[i].setBorder(new MatteBorder(0,0,0,1, Color.BLACK));
				cons[i].setPreferredSize(new Dimension(150, 15));
			}
			for(int i=0;i<5;i++) {
				p[i] = new JPanel();}
  
			icon = new ImageIcon(im);

			doNum(im, icon);


			p[0].addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent ce) {
					System.out.println(ce.getComponent().getClass().getName() + " --- Resized ");    

					if(frame.getWidth() !=800 || frame.getHeight()!= 800) {
						double widPer = (double)frame.getWidth()/800;
						double heiPer = (double)frame.getHeight()/800;

						double a = im.getWidth()*widPer;
						double b = im.getHeight()*heiPer;
						System.out.println(widPer +"  "+ heiPer +"  "+  a+"  "+  b);
						newImage = im.getScaledInstance((int)(im.getWidth()*widPer), (int)(im.getHeight()*heiPer), Image.SCALE_DEFAULT);
						BufferedImage newIm = new BufferedImage(newImage.getWidth(null), newImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
						Graphics2D bGr = newIm.createGraphics();
						bGr.drawImage(newImage, 0, 0, null);
						bGr.dispose();
						for(int i=0; i<Num.size();i++) {
							nums[i].removeAll();
						}
						icon = new ImageIcon(newIm);
						doNum(newIm,icon);
						
						double percent = (double)frame.getWidth()/800;
						font = new Font("Serif Bold", Font.PLAIN, (int)(15*percent));
						for(int i=0;i<5;i++) {
							BorderLayout layout = (BorderLayout)p[i].getLayout();
							p[i].remove(layout.getLayoutComponent(BorderLayout.CENTER));
							p[i].add(nums[i], BorderLayout.CENTER);
							cons[i].setFont(font);
						}
						
					}

					System.out.println(frame.getWidth()+" + "+frame.getHeight());
				}
			});

		}

		for(int i=0;i<5;i++) {

			p[i].setLayout(new BorderLayout()); 
			p[i].add(cons[i],BorderLayout.WEST); 
			p[i].add(nums[i], BorderLayout.CENTER);
			p[i].setOpaque(true); 
			p[i].setBackground(c1);
			p[i].setBorder(BorderFactory.createMatteBorder(1,0,1,0, Color.BLACK));
		}

	} 
	void doNum(BufferedImage im2, ImageIcon ic) {
		for(int i=0; i<Num.size();i++) {
			nums[i] = new JPanel();
			nums[i].setOpaque(true);
			nums[i].setBackground(c1);
			num = Integer.parseInt(Num.get(i));
			n = num/500;
			r = num%500;
			double per = ((double)r)/500;

			addImage(i, ic);

			if(r>=100 && r<=400) {
				BufferedImage img = im2.getSubimage(0, 0, (int) (im2.getWidth()*per), im2.getHeight());
				ImageIcon ii = new ImageIcon(img);
				JLabel fra = new JLabel(ii);
				nums[i].add(fra);    					

			}else if(r>400) {

				JLabel la = new JLabel(ic);
				nums[i].add(la);
			}
		}
	}
	void addImage(int i, ImageIcon icon) {
		for(int j=0;j<n;j++) {
			JLabel ics;
			ics = new JLabel(icon);
			nums[i].add(ics);
		}
	}

}
