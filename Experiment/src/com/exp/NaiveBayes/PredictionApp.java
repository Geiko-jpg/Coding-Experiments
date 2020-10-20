package com.exp.NaiveBayes;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PredictionApp {
	private JFrame primaryFrame;
	private JTable table;
	private JPanel primePanel;
	private JScrollPane scrollTable;
	
	public PredictionApp(){
		primaryFrame = new JFrame();
		primaryFrame.setSize(1200, 700);
		primaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		primaryFrame.setTitle("Naive Bayes App");
		primaryFrame.getContentPane().setLayout(null);
		primaryFrame.setResizable(false);
		
		// - - > OPEN TABLE VIEW
		dataTable();
		
		primaryFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PredictionApp();
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
