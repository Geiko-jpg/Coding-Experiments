package com.exp.NaiveBayes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PredictionApp {
	private JFrame primaryFrame;
	private JTable table;
	private JPanel primePanel;
	private JScrollPane scrollTable;
	private JTextField ageField, studentField, incomeField, creditField, buysField; 
	private static InputStream myStream = null;
	
	public PredictionApp(){
		primaryFrame = new JFrame();
		primaryFrame.setSize(1400, 700);
		primaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		primaryFrame.setTitle("Naive Bayes App");
		primaryFrame.getContentPane().setLayout(null);
		primaryFrame.setResizable(false); 
		
		// - - > MAIN FRAME CONTENTS
		dataTable();
		dataEntryArea();
		
		primaryFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PredictionApp();
	}
	
	public void dataEntryArea() {
		primePanel = new JPanel();
		primePanel.setSize(400, 632);
		primePanel.setLocation(980, 16);
		primePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.GRAY));
		primePanel.setLayout(null);
		
		// - - > ADD NEW FONT 
		try {
			myStream = new BufferedInputStream(new FileInputStream("src\\com\\exp\\NaiveBayes\\AccidentalPresidency.ttf"));
			Font font = Font.createFont(Font.TRUETYPE_FONT, myStream);
			JLabel mastHead = new JLabel("NAIVE BAYES APP");
			mastHead.setFont(font.deriveFont(Font.PLAIN, 48));
			
			mastHead.setLocation(80, 1);
			mastHead.setSize(250, 50);
			
			primePanel.add(mastHead);
		}catch(FontFormatException | IOException ex) {
			ex.printStackTrace();
		}
		
		// - - > INPUT DATA
		JLabel ageLabel = new JLabel("AGE:");
		ageLabel.setSize(50, 25);
		ageLabel.setLocation(10, 70);
		ageLabel.setFont(new Font("Arial", Font.BOLD, 14));
		primePanel.add(ageLabel);
		
		primaryFrame.getContentPane().add(primePanel);
	}
	
	public void dataTable() {
		Object columnsData[] = new Object[5];
		columnsData[0] = "AGE";
		columnsData[1] = "STUDENT";
		columnsData[2] = "INCOME";
		columnsData[3] = "CREDIT RATING";
		columnsData[4] = "BUYS COMPUTER";
		
		TableModel tableData = new DefaultTableModel(columnsData, 0);
		table = new JTable(tableData);
		
		scrollTable = new JScrollPane(table);
		scrollTable.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		scrollTable.setLocation(10, 15);
		scrollTable.setSize(950, 633);
		primaryFrame.getContentPane().add(scrollTable);
		
		DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
		centerRend.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRend);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRend);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRend);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRend);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRend);
	}

}
