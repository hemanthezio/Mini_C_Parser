package c_parser;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.DocFlavor.URL;
import javax.swing.*;




public class C_Parser extends JFrame implements ActionListener{
	JTextArea a1,a2;
	File log = new File("ip.txt");
	JButton parse = new JButton("PARSE--->");
	JButton load = new JButton("LOAD FILE");
	JLabel ip,op,title;
	String output1;
	
	C_Parser(){
		super();
		this.getContentPane().setBackground(Color.yellow);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("MINI C PARSER");
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1250, 700);           
		//this.setVisible(true);
		a1 =new JTextArea(5, 20);
		a1.setBounds(10, 50, 500, 600);
		
		//JTextPane textPane = new JTextPane();
		//JScrollPane scrollPane = new JScrollPane(textPane);
		
		
		
		
		JScrollPane scrollPane1 = new JScrollPane(a1);
		TextLineNumber tln = new TextLineNumber(a1);
		tln.setOpaque(false);
		scrollPane1.setRowHeaderView( tln );
        scrollPane1.setViewportView(a1);
        scrollPane1.setBounds(10, 100, 600, 600);
        scrollPane1.setOpaque(false);
        a1.setFont(new Font ("TimesRoman", Font.PLAIN, 18));
        a1.setForeground(Color.WHITE);
        a1.setOpaque(false);
        a1.setCaretColor(Color.WHITE);
        scrollPane1.getViewport().setOpaque(false);
        scrollPane1.setOpaque(false);
        
		a2 =new JTextArea(5, 20);
		a2.setBounds(590, 100, 400, 600);
		a2.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(a2);
        scrollPane2.setViewportView(a2);
        scrollPane2.setBounds(820, 100, 400, 600);
        a2.setFont(new Font ("TimesRoman", Font.ITALIC, 20));
        a2.setForeground(Color.WHITE);
        a2.setOpaque(false);
        a2.setCaretColor(Color.WHITE);
        scrollPane2.getViewport().setOpaque(false);
        scrollPane2.setOpaque(false);
		
        
        parse.setContentAreaFilled(false);
        parse.setFocusPainted(false);
        parse.setForeground(Color.WHITE);
        parse.setFont(new Font ("TimesRoman", Font.ITALIC, 20));
        parse.setBounds(635, 400, 150, 30);
        parse.addActionListener(this);
        
        load.setContentAreaFilled(false);
        load.setFocusPainted(false);
        load.setForeground(Color.WHITE);
        load.setFont(new Font ("TimesRoman", Font.ITALIC, 20));
        load.setBounds(635, 300, 150, 30);
        load.addActionListener(this);
        
        
        JLabel bg=new JLabel(new ImageIcon("/home/hemanthkumar/eclipse-workspace/MINI_C_PARSER/parser.jpg"));
        bg.setBounds(0, 0, 1250, 700);
		bg.setLayout(null);
		this.add(bg);
		
		ip =  new JLabel("INPUT");
		ip.setForeground(Color.WHITE);
		ip.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 25));
		ip.setBounds(200, 50, 100, 50);
		op =  new JLabel("OUTPUT");
		op.setForeground(Color.WHITE);
		op.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 25));
		op.setBounds(800, 50, 150, 50);
		title =  new JLabel("MINI C PARSER");
		title.setForeground(Color.WHITE);
		title.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 25));
		title.setBounds(450, 0, 300, 50);
		
		
		//this.add(a1);
		//this.add(a2);
		bg.add(scrollPane1);
		bg.add(scrollPane2);
		bg.add(parse);
		bg.add(ip);
		bg.add(op);
		bg.add(title);
		bg.add(load);
		
		
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==parse) {
			a2.setText(null);
			try{
                PrintWriter w  = new PrintWriter(new BufferedWriter(new FileWriter("ip.txt", false)));
                String[] lines = a1.getText().split("\n");
                //w.print("ip.txt");
                for(int i = 0 ; i< lines.length; i++) {                          
                    w.print( lines[i]);
                    w.print("\n");
                }
                //w.print("\n");
                w.close();
            }catch (IOException E) {} 
			output1 = executeCommandd("./a.out ip.txt");
			//System.out.println(output1);
			a2.append(output1);
        }
		
		if(e.getSource()==load) {
			String file = JOptionPane.showInputDialog(this, "Enter the file name:");
			if(file==null) return;
			a1.setText(null);    
            try{
                BufferedReader reader = null;
                reader = new BufferedReader( new FileReader(file));
                String line;
                while( ( line = reader.readLine())!=null){
                    String temp[] =line.split("\n");
                    for(int i=0;i<temp.length;i++){
                    	a1.append(temp[i]+"\n");
                    }
                }
                reader.close();
            }catch (IOException x) {}
		}
		
		
		
	}
	public void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public  String executeCommandd(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
				//a2.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	

	}
	
	public static void main(String args[]){
		C_Parser window =new C_Parser();
        window.centreWindow(window);
        window.setVisible(true);
        window.getContentPane().setBackground(Color.yellow);
        
       
	}

}
