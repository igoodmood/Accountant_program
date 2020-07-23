package com.company;

import net.proteanit.sql.DbUtils;
import org.jdesktop.xswingx.PromptSupport;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stock_interface {

    private static JFrame frame;
    private static JPanel jpnl;
    private static JLabel status;
    private static JTable table;
    private static JScrollPane pane;
    private static JTextField search;
    private static JComboBox combo;
    private static Connection conner;

    public static void stock_gui() {
        Interface_login login = new Interface_login();
        conner = login.conn;
        search = new JTextField();
        PromptSupport.setPrompt("Введите сюда код для поиска...", search);
        combo = new JComboBox(new Object[]{"Материалы", "Модели"});
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
                    g2d.drawString("Здесь пока нет информации для отображения", 180, 80);
                }
            }
        };
        pane = new JScrollPane(table);
        status = new JLabel("Склад");
        status.setFont(status.getFont().deriveFont(24f));
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Склад");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jpnl = new JPanel();
        jpnl.setLayout(new FlowLayout(FlowLayout.LEFT));

        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = combo.getSelectedItem().toString();
                switch (type)
                {
                    case "Модели" :
                    {
                        get_staff("Select * from Модели");
                        break;
                    }
                    case "Материалы" :
                    {
                        get_staff("Select * from Материалы");
                        break;
                    }
                }
                search.setText("");
            }
        });

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
                String type = combo.getSelectedItem().toString();
                switch (type)
                {
                    case "Модели" :
                    {
                        if (search.getText().length() != 0){
                            get_staff("Select * from Модели WHERE Code_model = " + search.getText());
                        }
                        else get_staff("Select * from Модели");
                        break;
                    }
                    case "Материалы" :
                    {
                        if (search.getText().length() != 0){
                            get_staff("Select * from Материалы WHERE Code_material = " + search.getText());
                        }
                        else get_staff("Select * from Материалы");
                        break;
                    }
                }
            }
        });

        search.setBounds(10, 60, 530, 25);
        status.setBounds(280, 5, 250, 50);
        pane.setBounds(10, 100, 645, 150);
        combo.setBounds(550, 60, 104, 24);

        frame.getContentPane().add(combo);
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
