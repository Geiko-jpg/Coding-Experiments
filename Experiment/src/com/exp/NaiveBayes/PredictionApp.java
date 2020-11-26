package com.exp.NaiveBayes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PredictionApp implements ActionListener{
	private JFrame primaryFrame;
	private JTable table;
	private JPanel primePanel, secondaryPanel;
	private JScrollPane scrollTable;
	private JTextField ageField, incomeField, creditField; 
	private ButtonGroup studentButtonGroup, buysButtonGroup;
	private JRadioButton isStudent, isNotStudent, buys_yes;
	private JRadioButton buys_no;
	private String status, buys_status;
	private JButton submitButton, resetButton, exitButton;
	private PersonDatabase pdb;
	private static InputStream myStream = null, secondStream = null;
	
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
		submissionPoint();
		
		primaryFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PredictionApp();
	}
	
	public void dataEntryArea() {
		primePanel = new JPanel();
		primePanel.setSize(400, 315);
		primePanel.setLocation(980, 16);
		primePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.GRAY));
		primePanel.setLayout(null);
		
		// - - > ADD NEW FONT 
		try {
			myStream = new BufferedInputStream(new FileInputStream("src\\com\\exp\\NaiveBayes\\AccidentalPresidency.ttf"));
			Font font = Font.createFont(Font.TRUETYPE_FONT, myStream);
			JLabel mastHead = new JLabel("NAIVE BAYES APP");
			mastHead.setFont(font.deriveFont(Font.PLAIN, 48));
			
			mastHead.setLocation(82, 1);
			mastHead.setSize(250, 50);
			
			primePanel.add(mastHead);
		}catch(FontFormatException | IOException ex) {
			ex.printStackTrace();
		}
		
		// - - > INPUT DATA | AGE
		JLabel ageLabel = new JLabel("AGE:");
		ageLabel.setSize(40, 25);
		ageLabel.setLocation(10, 80);
		ageLabel.setFont(new Font("Arial", Font.BOLD, 14));
		primePanel.add(ageLabel);
		
		ageField = new JTextField();
		ageField.setSize(290, 25);
		ageField.setLocation(95, 80);
		primePanel.add(ageField);
		ageField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '\b') {
					ageField.setEditable(true);
					ageLabel.setText("AGE:");
				}else {
					ageField.setEditable(false);
					ageLabel.setText("* DIGITS");
				}
			}
		});
		
		// - - > INCOME CLASSIFICATION
		JLabel incomeLabel = new JLabel("INCOME:");
		incomeLabel.setSize(70, 25);
		incomeLabel.setLocation(10, 130);
		incomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
		primePanel.add(incomeLabel);
		
		incomeField = new JTextField();
		incomeField.setSize(290, 25);
		incomeField.setLocation(95, 130);
		primePanel.add(incomeField);
		
		// - - > STUDENT BUTTON GROUP
		JLabel studentLabel = new JLabel("STUDENT:");
		studentLabel.setSize(70,  25);
		studentLabel.setLocation(10, 180);
		studentLabel.setFont(new Font("Arial", Font.BOLD, 14));
		primePanel.add(studentLabel);
		
		isStudent = new JRadioButton("IS STUDENT");
		isStudent.setMnemonic(KeyEvent.VK_1);
		isStudent.setActionCommand("true");
		isStudent.setSize(100, 25);
		isStudent.setLocation(120, 180);
		primePanel.add(isStudent);
		
		isNotStudent = new JRadioButton("IS NOT STUDENT");
		isNotStudent.setMnemonic(KeyEvent.VK_2);
		isNotStudent.setActionCommand("false");
		isNotStudent.setSize(140, 25);
		isNotStudent.setLocation(230, 180);
		primePanel.add(isNotStudent);
		
		studentButtonGroup = new ButtonGroup();
		studentButtonGroup.add(isStudent); studentButtonGroup.add(isNotStudent);
		
		isStudent.addActionListener(this);
		isNotStudent.addActionListener(this);
		
		// - - > CREDIT RATING
		JLabel credLabel = new JLabel("CREDIT RATING:");
		credLabel.setSize(150, 25);
		credLabel.setLocation(10, 225);
		credLabel.setFont(new Font("Arial", Font.BOLD, 14));
		primePanel.add(credLabel);
		
		creditField = new JTextField();
		creditField.setSize(255, 25);
		creditField.setLocation(130, 225);
		primePanel.add(creditField);
		
		// - - > BUYS COMPUTER
		JLabel buysLabel = new JLabel("BUYS COMPUTER:");
		buysLabel.setSize(150, 25);
		buysLabel.setLocation(10, 270);
		buysLabel.setFont(new Font("Arial", Font.BOLD, 14));
		primePanel.add(buysLabel);
		
		buys_yes = new JRadioButton("YES");
		buys_yes.setMnemonic(KeyEvent.VK_3);
		buys_yes.setActionCommand("true"); 
		buys_yes.setSize(50, 25);
		buys_yes.setLocation(180, 270);
	
		buys_no = new JRadioButton("NO");
		buys_no.setMnemonic(KeyEvent.VK_4);
		buys_no.setActionCommand("false");
		buys_no.setSize(50, 25);
		buys_no.setLocation(270, 270);
		
		primePanel.add(buys_no);
		primePanel.add(buys_yes);
		
		buysButtonGroup = new ButtonGroup();
		buysButtonGroup.add(buys_yes); buysButtonGroup.add(buys_no);
		
		buys_no.addActionListener(this);
		buys_yes.addActionListener(this);
		
		primaryFrame.getContentPane().add(primePanel);
	}
	
	public void submissionPoint() {
		secondaryPanel = new JPanel();
		secondaryPanel.setSize(400, 308);
		secondaryPanel.setLocation(980, 340);
		secondaryPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.GRAY));
		secondaryPanel.setLayout(null);
		
		try {
			secondStream = new BufferedInputStream(new FileInputStream("src\\com\\exp\\NaiveBayes\\AccidentalPresidency.ttf"));
			Font font = Font.createFont(Font.TRUETYPE_FONT, secondStream);
			JLabel secondMastHead = new JLabel("PREDICT | RECORD");
			secondMastHead.setFont(font.deriveFont(Font.PLAIN, 48));
			
			secondMastHead.setSize(250, 50);
			secondMastHead.setLocation(76, 1);
			
			secondaryPanel.add(secondMastHead);
		}catch(FontFormatException | IOException ex) {
			ex.printStackTrace();
		}
		
		ImageIcon submitPic = new ImageIcon("src\\com\\exp\\NaiveBayes\\sent1.png");
		JLabel icon1 = new JLabel(submitPic);
		icon1.setSize(40, 40);
		icon1.setLocation(330, 75);
		secondaryPanel.add(icon1);
		
		submitButton = new JButton("SUBMIT");
		submitButton.setSize(290, 25);
		submitButton.setLocation(20, 85);
		secondaryPanel.add(submitButton);
		submitButton.addActionListener(this);
		
		ImageIcon resetPic = new ImageIcon("src\\com\\exp\\NaiveBayes\\rsz_reset.png");
		JLabel icon2 = new JLabel(resetPic);
		icon2.setSize(40, 40);
		icon2.setLocation(330, 146);
		secondaryPanel.add(icon2);
		
		resetButton = new JButton("RESET");
		resetButton.setSize(290, 25);
		resetButton.setLocation(20, 150);
		secondaryPanel.add(resetButton);
		resetButton.addActionListener(this);
		
		ImageIcon exitPic = new ImageIcon("src\\com\\exp\\NaiveBayes\\rsz_exit.png");
		JLabel icon3 = new JLabel(exitPic);
		icon3.setSize(40, 40);
		icon3.setLocation(330, 214);
		secondaryPanel.add(icon3);
		
		exitButton = new JButton("EXIT PROGRAM");
		exitButton.setSize(290, 25);
		exitButton.setLocation(20, 220);
		secondaryPanel.add(exitButton);
		exitButton.addActionListener(this);
		
		primaryFrame.getContentPane().add(secondaryPanel);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(isStudent)) {
			status = null; 
			status = isStudent.getActionCommand().toString();
		}else if(e.getSource().equals(isNotStudent)) {
			status = null;
			status = isNotStudent.getActionCommand().toString();
		}else if(e.getSource().equals(buys_yes)) {
			buys_status = null;
			buys_status = buys_yes.getActionCommand().toString();
		}else if(e.getSource().equals(buys_no)) {
			buys_status = null;
			buys_status = buys_no.getActionCommand().toString();
		}else if(e.getSource().equals(submitButton)) {
			System.out.println("SAVING CREDENTIALS...");
			
			// - - > FILTER
			int ageFilter = 0;
			try {
				ageFilter = Integer.parseInt(ageField.getText().toString());
			}catch(NumberFormatException nfe) {
				nfe.printStackTrace();
			}
			
			PersonCredentials pCreds = new PersonCredentials.PersonCredentialsBuilder()
					.setAge(ageFilter)
					.setIncome(incomeField.getText().toString())
					.setStudent(status)
					.setCreditRating(creditField.getText().toString())
					.setBuysComputer(buys_status)
					.build();
			
			// - - > TEST
			System.out.println("INPUT: " + pCreds.getAge() + "|" + pCreds.getIncome() + "|" + pCreds.isStudent()
			+ "|" + pCreds.getCredit_rating() + "|" + pCreds.isBuys_computer());
			
			// - - > INSERT TO DATABASE
			pdb = new PersonDatabase(pCreds);
			pdb.WriteToDatabase();
			
			ageField.setText(null); // RESET FIELDS
			incomeField.setText(null);
			creditField.setText(null);
			studentButtonGroup.clearSelection(); status = null;
			buysButtonGroup.clearSelection(); buys_status = null;
		}
	}

}
