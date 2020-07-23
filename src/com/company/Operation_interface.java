package com.company;

import net.proteanit.sql.DbUtils;
import org.jdesktop.xswingx.PromptSupport;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.company.Interface_login.conn;
import static com.company.Operation_interface.combo;
import static com.company.Operation_interface.key;
import static com.company.Operation_interface.loaddata;

public class Operation_interface {

    private static JFrame frame;
    private static JPanel jpnl;
    private static JLabel status;
    private static JTable table;
    protected static String key;
    protected static int rovcount = 0;
    private static JScrollPane pane;
    private static JTextField search;
    protected static JComboBox combo;
    private static JButton delete;
    public static JLabel labels[] = new JLabel[9];
    public static JTextField textfields[] = new JTextField[9];
    private static JButton insert;
    private static JButton update;
    private static Connection conner;

    public static void operation_gui() {
        Interface_login login = new Interface_login();
        conner = conn;
        search = new JTextField();
        update = new JButton("Обновить");
        update.addActionListener(new ListenerActionUpdate());
        insert = new JButton("Вставить");
        insert.addActionListener(new ListenerActionInsert());
        delete = new JButton("Удалить");
        delete.addActionListener(new ListenerActionDelete());
        PromptSupport.setPrompt("Введите сюда код для поиска...", search);
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
                    g2d.drawString("Здесь пока нет информации для отображения", 200, 80);
                }
            }
        };
        pane = new JScrollPane(table);
        combo = new JComboBox(new Object[]{"Материалы", "Поставщики", "Закупки", "Модели", "Сборка", "Сборщики"});
        //get_staff("Select * from Сборщики");
        status = new JLabel("Операции над данными");
        status.setFont(status.getFont().deriveFont(24f));
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Операции над данными");

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jpnl = new JPanel();
        jpnl.setLayout(new FlowLayout(FlowLayout.LEFT));

        search.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                searcher();
            }
            public void removeUpdate(DocumentEvent e) {
                searcher();
            }
            public void insertUpdate(DocumentEvent e) {
                searcher();
            }

            public void searcher() {
                String name_combo = combo.getSelectedItem().toString();
                switch (name_combo) {
                    case "Материалы": {
                        if (search.getText().length() != 0)
                            get_information("SELECT * FROM Материалы WHERE Code_material = " + search.getText());
                        else get_information("SELECT * FROM Материалы");
                        break;
                    }
                    case "Поставщики": {
                        if (search.getText().length() != 0)
                            get_information("SELECT * FROM Поставщики WHERE Code_provider = " + search.getText());
                        else get_information("SELECT * FROM Поставщики");
                        break;
                    }
                    case "Закупки": {
                        if (search.getText().length() != 0)
                            get_information("SELECT * FROM Закупки WHERE Code_material = " + search.getText());
                        else get_information("SELECT * FROM Закупки");
                        break;
                    }
                    case "Модели": {
                        if (search.getText().length() != 0)
                            get_information("SELECT * FROM Модели WHERE Code_model = " + search.getText());
                        else get_information("SELECT * FROM Модели");
                        break;
                    }
                    case "Сборка": {
                        if (search.getText().length() != 0)
                            get_information("SELECT * FROM Сборка WHERE Code_sbor = " + search.getText());
                        else get_information("SELECT * FROM Сборка");
                        break;
                    }
                    case "Сборщики": {
                        if (search.getText().length() != 0)
                            get_information("SELECT * FROM Сборщики WHERE Code_sbor = " + search.getText());
                        else get_information("SELECT * FROM Сборщики");
                        break;
                    }
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {

                for (int i = 0; i < labels.length; i++)
                {
                    textfields[i].setText("");
                }

                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    rovcount = table.getSelectedRow();
                    int count = 0;
                    for (int i = 0; i < labels.length; i++) {
                        if (labels[i].isVisible()) count++;
                    }
                    for (int j = 0; j < count; j++) {
                        textfields[j].setText(table.getModel().getValueAt(rovcount, j).toString());
                    }
                    count = 0;
                    key = table.getModel().getValueAt(rovcount, table.getColumnCount() - 1).toString();
                }
            }
        });

        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Data_operation data_operation = new Data_operation();
                String str = combo.getSelectedItem().toString();
                loaddata(str);
                switch (str) {
                    case "Материалы": {
                        visible(data_operation.materials);
                        break;
                    }
                    case "Поставщики": {
                        visible(data_operation.providers);
                        break;
                    }
                    case "Закупки": {
                        visible(data_operation.procurement);
                        break;
                    }
                    case "Модели": {
                        visible(data_operation.models);
                        break;
                    }
                    case "Сборка": {
                        visible(data_operation.assembly);
                        break;
                    }
                    case "Сборщики": {
                        visible(data_operation.assemblers);
                        break;
                    }
                }
                search.setText("");
            }
        });

        search.setBounds(710, 60, 205, 25);
        combo.setBounds(710, 92, 205, 25);
        insert.setBounds(767, 125, 100, 25);
        delete.setBounds(767, 160, 100, 25);
        update.setBounds(757, 195, 120, 25);
        status.setBounds(300, 5, 350, 50);
        pane.setBounds(10, 60, 690, 161);

        int x = 6, y = 230, width = 100, height = 25;
        for (int i = 0; i < labels.length; i++) {
            if (i == 4) {
                y = y + 30;
                x = 6;
            }
            labels[i] = new JLabel();
            labels[i].setBounds(x, y, width - 30, height);
            textfields[i] = new JTextField();
            labels[i].setVisible(false);
            textfields[i].setVisible(false);
            x = x + 85;
            textfields[i].setBounds(x, y, width + 30, height);
            frame.getContentPane().add(labels[i]);
            frame.getContentPane().add(textfields[i]);
            x = x + 145;
        }

        frame.getContentPane().add(search);
        frame.getContentPane().add(status);
        frame.getContentPane().add(insert);
        frame.getContentPane().add(update);
        frame.getContentPane().add(delete);
        frame.getContentPane().add(combo);
        frame.getContentPane().add(pane);
        frame.getContentPane().add(jpnl);

        frame.setPreferredSize(new Dimension(935, 330));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void visible(String[] a)
    {
        for (int j = 0; j < labels.length; j++)
        {
            labels[j].setVisible(false);
            textfields[j].setVisible(false);
        }

        for (int i = 0; i < a.length; i++)
        {
            labels[i].setText(a[i].toString() + ":");
            labels[i].setVisible(true);
            textfields[i].setVisible(true);
        }
    }

    public static void loaddata(String name_table)
    {
        try {
            String sql = "Select * from " + name_table;
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void get_information(String sql)
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

class ListenerActionUpdate extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String type = combo.getSelectedItem().toString();
        Data_operation data_operation = new Data_operation();
        data_operation.UpdateRow(type, key);
        loaddata(combo.getSelectedItem().toString());
    }
}

class ListenerActionInsert extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String type = combo.getSelectedItem().toString();
        Data_operation data_operation = new Data_operation();
        data_operation.InsertRow(type, key);
        loaddata(combo.getSelectedItem().toString());
    }
}

class ListenerActionDelete extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String type = combo.getSelectedItem().toString();
        Data_operation data_operation = new Data_operation();
        data_operation.DeleteRow(type, key);
        loaddata(combo.getSelectedItem().toString());
    }
}
