package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static com.company.Interface_login.signin;

public class Main_interface {

    private static JFrame frame;
    private static JLabel main_img = new JLabel();
    private static JLabel title;
    private static JPanel jpnl;
    private static JButton staff;
    private static JButton report;
    private static JButton operation;
    private static JButton stock;
    private static JLabel status;

    public static void generate_gui()
    {
        Interface_login stat = new Interface_login();
        String pos = stat.position;
        status = new JLabel("Ваш статус в системе : " + pos);
        status.setFont(status.getFont().deriveFont(15f));
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("\"Катина мебель\"");
        jpnl = new JPanel();
        jpnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        title = new JLabel("Отдел мебельной фабрики \"Катина мебель\"");
        ImageIcon imgThisImg = new ImageIcon("bookshelf.png");
        title.setFont(title.getFont().deriveFont(25f));
        main_img.setIcon(imgThisImg);

        staff = new JButton("Работники", new ImageIcon("staff.png"));
        staff.setFont(staff.getFont().deriveFont(18f));
        staff.addActionListener(new ListenerActionStaff());
        report = new JButton("Отчёты", new ImageIcon("report.png"));
        report.setFont(report.getFont().deriveFont(18f));
        report.addActionListener(new ListenerActionReport());
        operation = new JButton("Операции", new ImageIcon("function.png"));
        operation.setFont(operation.getFont().deriveFont(18f));
        operation.addActionListener(new ListenerActionOperation());
        if (!pos.equals("Начальник")) operation.setEnabled(false);
        stock = new JButton("Склад", new ImageIcon("stock.png"));
        stock.setFont(stock.getFont().deriveFont(18f));
        stock.addActionListener(new ListenerActionStock());
        status.setBounds(215, 55, 250, 50);
        title.setBounds(80, -60, 600, 200);
        main_img.setBounds(10, 10, 64,64);
        staff.setBounds(100, 110, 180,50);
        report.setBounds(370, 110, 180, 50);
        operation.setBounds(100, 200, 180, 50);
        stock.setBounds(370, 200, 180, 50);

        frame.getContentPane().add(status);
        frame.getContentPane().add(operation);
        frame.getContentPane().add(report);
        frame.getContentPane().add(staff);
        frame.getContentPane().add(stock);
        frame.getContentPane().add(title);
        frame.getContentPane().add(main_img);
        frame.getContentPane().add(jpnl);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(655, 300));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class ListenerActionStaff extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Staff_interface staff = new Staff_interface();
        staff.staff_gui();
    }
}

class ListenerActionStock extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Stock_interface stock = new Stock_interface();
        stock.stock_gui();
    }
}

class ListenerActionReport extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Reports_interface report = new Reports_interface();
        report.report_gui();
    }
}

class ListenerActionOperation extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Operation_interface operation = new Operation_interface();
        operation.operation_gui();
    }
}