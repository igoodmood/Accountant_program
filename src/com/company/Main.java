package com.company;

import static com.company.Interface_login.createGUI;
import static com.company.Main_interface.generate_gui;
import static com.company.Operation_interface.operation_gui;
import static com.company.Reports_interface.report_gui;
import static com.company.Staff_interface.staff_gui;
import static com.company.Stock_interface.stock_gui;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}
