package com.company;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static com.company.Interface_login.signin;
import static com.company.Main_interface.generate_gui;

public class Interface_login {
    public static String position;
    public static Connection conn = null;
    private static JFrame loginfrm;
    private static JButton log_in;
    private static JLabel jlab1;
    private static JLabel jlab2;
    private static JTextField jtf1;
    private static JPasswordField jtf2;

    public static void createGUI() {
        get_connect();
        JFrame.setDefaultLookAndFeelDecorated(true);
        loginfrm = new JFrame("Вход в систему \"Катины мебли\"");
        loginfrm.getContentPane().setLayout(new FlowLayout());
        loginfrm.setSize(290, 190);
        loginfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jlab1 = new JLabel("Логин");
        jlab1.setFont(jlab1.getFont().deriveFont(17f));
        jlab2 = new JLabel("Пароль");
        jlab2.setFont(jlab2.getFont().deriveFont(17f));

        jlab1.setDisplayedMnemonic('л');
        jlab2.setDisplayedMnemonic('п');

        log_in = new JButton("Вход");
        log_in.setPreferredSize(new Dimension(100, 28));
        log_in.setFont(log_in.getFont().deriveFont(17f));
        log_in.addActionListener(new ListenerActionShowForm());

        jtf1 = new JTextField(20);
        jtf1.setPreferredSize(new Dimension(55, 26));
        jtf1.setFont(jtf1.getFont().deriveFont(17f));
        jtf2 = new JPasswordField(20);
        jtf2.setPreferredSize(new Dimension(55, 26));
        jtf2.setFont(jtf2.getFont().deriveFont(17f));
        jtf2.setEchoChar('*');

        jlab1.setLabelFor(jtf1);
        jlab2.setLabelFor(jtf2);

        jtf2.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    try {
                        signin();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        });

        loginfrm.getContentPane().add(jlab1);
        loginfrm.getContentPane().add(jtf1);
        loginfrm.getContentPane().add(jlab2);
        loginfrm.getContentPane().add(jtf2);
        loginfrm.getContentPane().add(log_in);
        loginfrm.setLocationRelativeTo(null);
        loginfrm.setVisible(true);
    }

    public static void get_num() throws SQLException {
        Statement stmt3 = conn.createStatement();
        ResultSet rs = stmt3.executeQuery("SELECT Статус from Пользователи where Логин = " + "\"" + jtf1.getText() + "\" and Пароль = " + "\"" + jtf2.getText() + "\"" + ";");
        rs.next();
        position = rs.getString(1);
    }

    public static void get_connect()
    {
        try {
            conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Владимир/Desktop/Университет/База данных 2 часть/Курсовая/Курсач.accdb");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static void signin() throws SQLException {

        Statement stmt3 = conn.createStatement();
        ResultSet rs = stmt3.executeQuery("SELECT Логин, Пароль from Пользователи where Логин = " + "\"" + jtf1.getText() + "\" and Пароль = " + "\"" + jtf2.getText() + "\"" + ";");
        if (rs.next()) {
            loginfrm.dispose();
            get_num();
            generate_gui();
        } else {
            JOptionPane.showMessageDialog(loginfrm,"Неправильный пароль или логин!", "Помилка", JOptionPane.DEFAULT_OPTION );
            //PreparedStatement st = conn.prepareStatement("INSERT INTO Клиенты (Логин, Пароль) VALUES (" + "\'" + jtf1.getText() + "\'" + "," + "\'" + jtf2.getText() + "\')");
            //st.executeUpdate();
        }
    }
}

class ListenerActionShowForm extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        try {
            signin();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
