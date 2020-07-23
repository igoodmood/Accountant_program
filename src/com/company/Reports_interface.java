package com.company;

import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.company.Interface_login.signin;
import static com.company.Reports_interface.*;

public class Reports_interface {

    protected static JDateChooser chooser_start, chooser_end;
    protected static String date_start, date_end;
    private static JFrame frame;
    protected static JTextField type_sofa;
    private static JPanel jpnl;
    private static JScrollPane pane;
    private static JTable table;
    private static JButton view;
    private static JLabel start;
    protected static JTextField material;
    private static JLabel end;
    private static JButton go;
    private static JLabel command;
    private static JLabel status;
    protected static JComboBox combo;
    private static Connection conner;
    private static Connection conn;


    public static void selectdata(String load, String sqler) throws SQLException, JRException {
        try {
            conner = DriverManager.getConnection("jdbc:ucanaccess://Курсач.accdb");
            //String reportpath = "temper.jrxml";
            JasperDesign jd = JRXmlLoader.load(load);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sqler);
            jd.setQuery(newQuery);
            //JRDataSource datasrc = new JREmptyDataSource();
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conner);
            JasperViewer.viewReport(jp, false);
            conner.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static void get_report(String sql)
    {
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void report_gui() {
        Interface_login login = new Interface_login();
        conn = login.conn;
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
                    g2d.drawString("Здесь пока нет информации для отображения", 160, 80);
                }
            }
        };
        view = new JButton("Пред. просмотр");
        view.addActionListener(new ListenerActionViewReports());
        view.setFont(view.getFont().deriveFont(17f));
        pane = new JScrollPane(table);
        material = new JTextField();
        material.setVisible(false);
        go = new JButton("Создать");
        go.addActionListener(new ListenerActionMakeReports());
        go.setFont(go.getFont().deriveFont(17f));
        command = new JLabel("Выберите желаемый отчёт : ");
        command.setFont(command.getFont().deriveFont(17f));
        //PromptSupport.setPrompt("Введите сюда код для поиска...", search);
        combo = new JComboBox(new Object[]{"Сборка мебели за период", "Модели по типам", "Материалы", "Закупки за период", "Поставщики по типу материала"});
        combo.setFont(combo.getFont().deriveFont(17f));
        status = new JLabel("Отчёты");
        status.setFont(status.getFont().deriveFont(24f));
        type_sofa = new JTextField();
        type_sofa.setVisible(false);
        JFrame.setDefaultLookAndFeelDecorated(true);
        start = new JLabel("Начальная дата : ");
        start.setFont(start.getFont().deriveFont(17f));
        end = new JLabel("Конечная дата : ");
        end.setFont(end.getFont().deriveFont(17f));
        frame = new JFrame("Отчёты");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jpnl = new JPanel();
        jpnl.setLayout(new FlowLayout(FlowLayout.LEFT));

        chooser_start = new JDateChooser();
        Locale loc = new Locale("ru", "RU");
        chooser_start.setLocale(loc);
        chooser_end = new JDateChooser();
        chooser_end.setLocale(loc);

        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = combo.getSelectedItem().toString();
                switch (type)
                {
                    case "Сборка мебели за период" :
                    {
                        material.setVisible(false);
                        start.setText("Начальная дата : ");
                        end.setVisible(true);
                        chooser_start.setVisible(true);
                        chooser_end.setVisible(true);
                        type_sofa.setVisible(false);
                        break;
                    }
                    case "Модели по типам" :
                    {
                        material.setVisible(false);
                        start.setText("Тип мебели : ");
                        end.setVisible(false);
                        type_sofa.setVisible(true);
                        chooser_start.setVisible(false);
                        chooser_end.setVisible(false);
                        break;
                    }
                    case "Материалы" :
                    {
                        start.setText("Код материала : ");
                        material.setVisible(true);
                        end.setVisible(false);
                        type_sofa.setVisible(false);
                        chooser_start.setVisible(false);
                        chooser_end.setVisible(false);
                        break;
                    }
                    case "Закупки за период" :
                    {
                        material.setVisible(false);
                        start.setText("Начальная дата : ");
                        end.setVisible(true);
                        chooser_start.setVisible(true);
                        chooser_end.setVisible(true);
                        type_sofa.setVisible(false);
                        break;
                    }
                    case "Поставщики по типу материала" :
                    {
                        material.setVisible(false);
                        start.setText("Тип материала : ");
                        end.setVisible(false);
                        type_sofa.setVisible(true);
                        chooser_start.setVisible(false);
                        chooser_end.setVisible(false);
                        break;
                    }
                }
            }
        });

        pane.setBounds(10, 100, 600, 150);
        view.setBounds(385, 259, 200, 30);
        status.setBounds(260, 5, 250, 50);
        combo.setBounds(300, 62, 270, 24);
        type_sofa.setBounds(170, 264, 150, 25);
        material.setBounds(180, 264, 150, 25);
        command.setBounds(50, 60, 250, 25);
        go.setBounds(420, 300, 130, 30);
        chooser_start.setBounds(200, 262, 100, 25);
        chooser_end.setBounds(200, 302, 100, 25);
        start.setBounds(30, 262, 150, 25);
        end.setBounds(30, 302, 150, 25);

        frame.getContentPane().add(start);
        frame.getContentPane().add(pane);
        frame.getContentPane().add(go);
        frame.getContentPane().add(view);
        frame.getContentPane().add(end);
        frame.getContentPane().add(material);
        frame.getContentPane().add(type_sofa);
        frame.getContentPane().add(chooser_start);
        frame.getContentPane().add(chooser_end);
        frame.getContentPane().add(combo);
        frame.getContentPane().add(status);
        frame.getContentPane().add(command);
        frame.getContentPane().add(jpnl);

        frame.setPreferredSize(new Dimension(630, 380));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class ListenerActionMakeReports extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String type_reports = combo.getSelectedItem().toString();
        switch (type_reports)
        {
            case "Сборка мебели за период" :
            {
                DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
                date_start = df1.format(chooser_start.getDate());
                DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                date_end = df2.format(chooser_end.getDate());
                try {
                    selectdata("Kursach(1).jrxml", "SELECT * FROM Сборка WHERE (((Сборка.Date_assembly) Between #" + date_start + "# And #" + date_end + "#));\n");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (JRException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "Модели по типам" :
            {
                try {
                    selectdata("Kursach(2).jrxml", "SELECT * FROM Модели WHERE Модели.Type_sofa = \'" + type_sofa.getText() + "\';");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (JRException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "Материалы" :
            {
                try {
                    if (material.getText().length() == 0) selectdata("Kursach(3).jrxml", "SELECT * FROM Материалы");
                    else selectdata("Kursach(3).jrxml", "SELECT * FROM Материалы WHERE Материалы.Code_material = \'" + material.getText() + "\';");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (JRException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "Закупки за период" :
            {
                DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
                date_start = df1.format(chooser_start.getDate());
                DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                date_end = df2.format(chooser_end.getDate());
                try {
                    selectdata("Kursach(4).jrxml", "SELECT * FROM Закупки WHERE (((Закупки.Date_buy) Between #" + date_start + "# And #" + date_end + "#));\n");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (JRException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "Поставщики по типу материала" :
            {
                try {
                    selectdata("Kursach(5).jrxml", "SELECT Поставщики.Code_provider, Поставщики.Organization, Поставщики.Adress, Поставщики.Master, Поставщики.Phone, Материалы.Code_material, Материалы.Nomination, Материалы.Price, Материалы.Type_material\n" +
                            "FROM Поставщики INNER JOIN Материалы ON Поставщики.Code_provider = Материалы.Code_provider\n" +
                            "WHERE (((Материалы.Type_material)=\"" + type_sofa.getText() + "\"));\n");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (JRException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }

    }
}

class ListenerActionViewReports extends Component implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String type_reports = combo.getSelectedItem().toString();
        switch (type_reports)
        {
            case "Сборка мебели за период" :
            {
                DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
                date_start = df1.format(chooser_start.getDate());
                DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                date_end = df2.format(chooser_end.getDate());
                get_report("SELECT * FROM Сборка WHERE (((Сборка.Date_assembly) Between #" + date_start + "# And #" + date_end + "#));\n");
                break;
            }
            case "Модели по типам" :
            {
                get_report("SELECT * FROM Модели WHERE Модели.Type_sofa = \'" + type_sofa.getText() + "\';");
                break;
            }
            case "Материалы" :
            {
                if (material.getText().length() == 0) get_report("SELECT * FROM Материалы");
                else get_report("SELECT * FROM Материалы WHERE Материалы.Code_material = \'" + material.getText() + "\';");
                break;
            }
            case "Закупки за период" :
            {
                DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
                date_start = df1.format(chooser_start.getDate());
                DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                date_end = df2.format(chooser_end.getDate());
                get_report("SELECT * FROM Закупки WHERE (((Закупки.Date_buy) Between #" + date_start + "# And #" + date_end + "#));\n");
                break;
            }
            case "Поставщики по типу материала" :
            {
                get_report(("SELECT Поставщики.Code_provider, Поставщики.Organization, Поставщики.Adress, Поставщики.Master, Поставщики.Phone, Материалы.Code_material, Материалы.Nomination, Материалы.Price, Материалы.Type_material\n" +
                        "FROM Поставщики INNER JOIN Материалы ON Поставщики.Code_provider = Материалы.Code_provider\n" +
                        "WHERE (((Материалы.Type_material)=\"" + type_sofa.getText() + "\"));\n"));
                break;
            }
        }

    }
}
