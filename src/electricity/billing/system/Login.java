
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton login,cancel,signup;
    JTextField username,password;
    Choice loginin;
    Login()
    {
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblusername=new JLabel("Username");
        lblusername.setBounds(300, 20, 100, 20);
        add(lblusername);
        
        username = new JTextField();
        username.setBounds(400,20,150,20);
        add(username);
        
        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(300, 60, 100, 20);
        add(lblpassword);
        
        password = new JTextField();
        password.setBounds(400,60,150,20);
        add(password);
        
        JLabel logininas=new JLabel("Log in as");
        logininas.setBounds(300, 100, 100, 20);
        add(logininas);
        
        loginin=new Choice();
        loginin.add("Select from below");
        loginin.add("Admin");
        loginin.add("Customer");
        loginin.setBounds(400, 100, 150, 20);
        add(loginin);
         
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login.png")); 
        Image i2=i1.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        login=new JButton("Login",i3);
        login.setBounds(330, 160, 100, 20);
        login.addActionListener(this);
        add(login);
        
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg")); 
        Image i5=i4.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        ImageIcon i6= new ImageIcon(i5);
        cancel=new JButton("Cancel",i6);
        cancel.setBounds(450, 160, 100, 20);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png")); 
        Image i8=i7.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        ImageIcon i9= new ImageIcon(i8);
         signup=new JButton("Signup",i9);
        signup.setBounds(380, 200, 100, 20);
        signup.addActionListener(this);
        add(signup);
        
        
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg")); 
        Image i11=i10.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i12= new ImageIcon(i11);
        JLabel image=new JLabel(i12);
        image.setBounds(0,0,250,250);
        
        add(image);
        
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }
     public void actionPerformed(ActionEvent ae)
     {
        if(ae.getSource()==login)
        {
            String susername=username.getText();
            String spassword=password.getText();
            String user=loginin.getSelectedItem();
            
            try{
                Conn c=new Conn();
                String query = "select * from login where username = '"+susername+"' and password = '"+spassword+"' and user = '"+user+"'";
                
                ResultSet rs = c.s.executeQuery(query);
                
                if(rs.next())
                {
                    String meter=rs.getString("meter_no");
                    setVisible(false);
                    new Project(user,meter);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                    username.setText("");
                    password.setText("");
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==cancel){
            setVisible(false);
        }
        else if(ae.getSource()==signup)
        {
            setVisible(false);
            new Signup();
        }
     }
    public static void main(String [] args)
    {
        new Login();
    }
}
