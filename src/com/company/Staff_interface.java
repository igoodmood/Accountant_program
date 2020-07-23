package com.company;

import javafx.event.ActionEvent;
import net.proteanit.sql.DbUtils;
import org.jdesktop.xswingx.PromptSupport;
//import org.jdesktop.xswingx.PromptSupport;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Staff_interface {

    private static JFrame frame;
    private static JPanel jpnl;
    private static JLabel status;
    private static JTable table;
    private static JScrollPane pane;
    private static JTextField search;
    private static Connection conner;

    public static void staff_gui() {
        Interface_login login = new Interface_login();
        conner = login.conn;
        search = new JTextField();
        PromptSupport.setPrompt("Введите сюда код работника для поиска...", search);
        table = new JTable() {
            public boolean getScrollableTracksViewportHeight() {
                if (getParent() instanceof JViewport)
                    return (((JViewport) getParent()).getHeight() > getPreferredSize().height);

                return super.getScrollableTracksViewportHeight();
            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getRowCount() == 0) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.BLACK);
                    g2d.drawString("Здесь пока нет информации для отображения", 185, 65);
                }
            }
        };
        pane = new JScrollPane(table);
        get_staff("Select * from Сборщики");
        status = new JLabel("Работники");
        status.setFont(status.getFont().deriveFont(24f));
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Работники");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jpnl = new JPanel();
        jpnl.setLayout(new FlowLayout(FlowLayout.LEFT));

        search.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if (search.getText().length() != 0){
                    get_staff("Select * from Сборщики WHERE Code_sbor = " + search.getText());
                }
                else get_staff("Select * from Сборщики");
            }
        });

        search.setBounds(10, 60, 645, 25);
        status.setBounds(250, 5, 250, 50);
        pane.setBounds(10, 100, 645, 150);

        frame.getContentPane().add(search);
        frame.getContentPane().add(status);
        frame.getContentPane().add(pane);
        frame.getContentPane().add(jpnl);

        frame.setPreferredSize(new Dimension(675, 300));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void get_staff(String sql)
    {
        try {
            PreparedStatement pst = conner.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
