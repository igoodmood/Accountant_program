package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.company.Operation_interface.textfields;

public class Data_operation {

    protected static String[] procurement = new String[] {
            "Date_buy",
            "Material",
            "Code_material",
            "Code_provider",
            "Price",
            "Number",
            "Amount",
            "Provider"
    };
    protected static String[] materials = new String[] {
            "Cipher",
            "Nomination",
            "Size",
            "Provider",
            "Code_material",
            "Code_provider",
            "Price",
            "Type_material"
    };
    protected static String[] models = new String[] {
            "Cipher",
            "Nomination",
            "Price",
            "Code_model",
            "Type_sofa"
    };
    protected static String[] providers = new String[] {
            "Organization",
            "Adress",
            "Master",
            "Passport",
            "Nalog_number",
            "Phone",
            "Code_provider"
    };
    protected static String[] assembly = new String[] {
            "Type_material",
            "Expense",
            "Method_assembly",
            "Code_model",
            "Code_material",
            "Code_sbor",
            "Name_Model",
            "Date_assembly"
    };
    protected static String[] assemblers = new String[] {
            "FIO",
            "Pasport",
            "Place_work",
            "Specialization",
            "Stagh",
            "Phone",
            "Adress",
            "Code_sbor"
    };

    public static void DeleteRow(String select, String main_field)
    {
        Interface_login login = new Interface_login();
        Connection connery = login.conn;
        try
        {
            PreparedStatement st = connery.prepareStatement("DELETE FROM " + select + " WHERE Coder " + "=" + "\"" + main_field + "\"");
            st.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void InsertRow(String select, String main_field)
    {
        Interface_login login = new Interface_login();
        Connection connery = login.conn;

        try
        {
            switch (select) {
                case "Материалы" :
                {
                    PreparedStatement st = connery.prepareStatement("INSERT INTO " + select + " " +
                            "(" + materials[0] + "," +
                            materials[1] + "," +
                            materials[2] + "," +
                            materials[3] + "," +
                            materials[4] + "," +
                            materials[5] + "," +
                            materials[6] + "," +
                            materials[7] + ")" +
                            " VALUES ("
                            + "\'" + textfields[0].getText().toString() + "\'" + ","
                            + "\'" + textfields[1].getText().toString() + "\'" + ","
                            + "\'" + textfields[2].getText().toString() + "\'" + ","
                            + "\'" + textfields[3].getText().toString() + "\'" + ","
                            + "\'" + textfields[4].getText().toString() + "\'" + ","
                            + "\'" + textfields[5].getText().toString() + "\'" + ","
                            + "\'" + textfields[6].getText().toString() + "\'" + ","
                            + "\'" + textfields[7].getText().toString() + "\')");
                    st.executeUpdate();
                    break;
                }
                case "Поставщики" :
                {
                    PreparedStatement st = connery.prepareStatement("INSERT INTO " + select + " " +
                            "(" + providers[0] + "," +
                            providers[1] + "," +
                            providers[2] + "," +
                            providers[3] + "," +
                            providers[4] + "," +
                            providers[5] + "," +
                            providers[6] + ")" +
                            " VALUES ("
                            + "\'" + textfields[0].getText().toString() + "\'" + ","
                            + "\'" + textfields[1].getText().toString() + "\'" + ","
                            + "\'" + textfields[2].getText().toString() + "\'" + ","
                            + "\'" + textfields[3].getText().toString() + "\'" + ","
                            + "\'" + textfields[4].getText().toString() + "\'" + ","
                            + "\'" + textfields[5].getText().toString() + "\'" + ","
                            + "\'" + textfields[6].getText().toString() + "\')");
                    st.executeUpdate();
                    break;
                }
                case "Закупки" :
                {
                    PreparedStatement st = connery.prepareStatement("INSERT INTO " + select + " " +
                            "(" + procurement[0] + "," +
                            procurement[1] + "," +
                            procurement[2] + "," +
                            procurement[3] + "," +
                            procurement[4] + "," +
                            procurement[5] + "," +
                            procurement[6] + "," +
                            procurement[7] + ")" +
                            " VALUES ("
                            + "\'" + textfields[0].getText().toString() + "\'" + ","
                            + "\'" + textfields[1].getText().toString() + "\'" + ","
                            + "\'" + textfields[2].getText().toString() + "\'" + ","
                            + "\'" + textfields[3].getText().toString() + "\'" + ","
                            + "\'" + textfields[4].getText().toString() + "\'" + ","
                            + "\'" + textfields[5].getText().toString() + "\'" + ","
                            + "\'" + textfields[6].getText().toString() + "\'" + ","
                            + "\'" + textfields[7].getText().toString() + "\')");
                    st.executeUpdate();
                    break;
                }
                case "Модели" :
                {
                    PreparedStatement st = connery.prepareStatement("INSERT INTO " + select + " " +
                            "(" + models[0] + "," +
                            models[1] + "," +
                            models[2] + "," +
                            models[3] + "," +
                            models[4] + ")" +
                            " VALUES ("
                            + "\'" + textfields[0].getText().toString() + "\'" + ","
                            + "\'" + textfields[1].getText().toString() + "\'" + ","
                            + "\'" + textfields[2].getText().toString() + "\'" + ","
                            + "\'" + textfields[3].getText().toString() + "\'" + ","
                            + "\'" + textfields[4].getText().toString() + "\')");
                    st.executeUpdate();
                    break;
                }
                case "Сборка" :
                {
                    PreparedStatement st = connery.prepareStatement("INSERT INTO " + select + " " +
                            "(" + assembly[0] + "," +
                            assembly[1] + "," +
                            assembly[2] + "," +
                            assembly[3] + "," +
                            assembly[4] + "," +
                            assembly[5] + "," +
                            assembly[6] + "," +
                            assembly[7] + ")" +
                            " VALUES ("
                            + "\'" + textfields[0].getText().toString() + "\'" + ","
                            + "\'" + textfields[1].getText().toString() + "\'" + ","
                            + "\'" + textfields[2].getText().toString() + "\'" + ","
                            + "\'" + textfields[3].getText().toString() + "\'" + ","
                            + "\'" + textfields[4].getText().toString() + "\'" + ","
                            + "\'" + textfields[5].getText().toString() + "\'" + ","
                            + "\'" + textfields[6].getText().toString() + "\'" + ","
                            + "\'" + textfields[7].getText().toString() + "\')");
                    st.executeUpdate();
                    break;
                }
                case "Сборщики" :
                {
                    PreparedStatement st = connery.prepareStatement("INSERT INTO " + select + " " +
                            "(" + assemblers[0] + "," +
                            assemblers[1] + "," +
                            assemblers[2] + "," +
                            assemblers[3] + "," +
                            assemblers[4] + "," +
                            assemblers[5] + "," +
                            assemblers[6] + "," +
                            assemblers[7] + ")" +
                            " VALUES ("
                            + "\'" + textfields[0].getText().toString() + "\'" + ","
                            + "\'" + textfields[1].getText().toString() + "\'" + ","
                            + "\'" + textfields[2].getText().toString() + "\'" + ","
                            + "\'" + textfields[3].getText().toString() + "\'" + ","
                            + "\'" + textfields[4].getText().toString() + "\'" + ","
                            + "\'" + textfields[5].getText().toString() + "\'" + ","
                            + "\'" + textfields[6].getText().toString() + "\'" + ","
                            + "\'" + textfields[7].getText().toString() + "\')");
                    st.executeUpdate();
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void UpdateRow(String select, String main_field)
    {
        Interface_login login = new Interface_login();
        Connection connery = login.conn;
        try
        {
            switch (select) {
                case "Материалы" :
                {
                    PreparedStatement st = connery.prepareStatement("UPDATE " + select + " SET " +
                            materials[0] + "=" + "\"" + textfields[0].getText() + "\"" + "," +
                            materials[1] + "=" + "\"" + textfields[1].getText() + "\"" + "," +
                            materials[2] + "=" + "\"" + textfields[2].getText() + "\"" + "," +
                            materials[3] + "=" + "\"" + textfields[3].getText() + "\"" + "," +
                            materials[4] + "=" + "\"" + textfields[4].getText() + "\"" + "," +
                            materials[5] + "=" + "\"" + textfields[5].getText() + "\"" + "," +
                            materials[6] + "=" + "\"" + textfields[6].getText() + "\"" + "," +
                            materials[7] + "=" + "\"" + textfields[7].getText() + "\"" +
                            " WHERE " + "Coder = " + main_field + ";");
                    st.executeUpdate();
                    break;
                }
                case "Поставщики" :
                {
                    PreparedStatement st = connery.prepareStatement("UPDATE " + select + " SET " +
                            providers[0] + "=" + "\"" + textfields[0].getText() + "\"" + "," +
                            providers[1] + "=" + "\"" + textfields[1].getText() + "\"" + "," +
                            providers[2] + "=" + "\"" + textfields[2].getText() + "\"" + "," +
                            providers[3] + "=" + "\"" + textfields[3].getText() + "\"" + "," +
                            providers[4] + "=" + "\"" + textfields[4].getText() + "\"" + "," +
                            providers[5] + "=" + "\"" + textfields[5].getText() + "\"" + "," +
                            providers[6] + "=" + "\"" + textfields[6].getText() + "\"" +
                            " WHERE " + "Coder = " + main_field + ";");
                    st.executeUpdate();
                    break;
                }
                case "Закупки" :
                {
                    PreparedStatement st = connery.prepareStatement("UPDATE " + select + " SET " +
                            procurement[0] + "=" + "\"" + textfields[0].getText() + "\"" + "," +
                            procurement[1] + "=" + "\"" + textfields[1].getText() + "\"" + "," +
                            procurement[2] + "=" + "\"" + textfields[2].getText() + "\"" + "," +
                            procurement[3] + "=" + "\"" + textfields[3].getText() + "\"" + "," +
                            procurement[4] + "=" + "\"" + textfields[4].getText() + "\"" + "," +
                            procurement[5] + "=" + "\"" + textfields[5].getText() + "\"" + "," +
                            procurement[6] + "=" + "\"" + textfields[6].getText() + "\"" + "," +
                            procurement[7] + "=" + "\"" + textfields[7].getText() + "\"" +
                            " WHERE " + "Coder = " + main_field + ";");
                    st.executeUpdate();
                    break;
                }
                case "Модели" :
                {
                    PreparedStatement st = connery.prepareStatement("UPDATE " + select + " SET " +
                            models[0] + "=" + "\"" + textfields[0].getText() + "\"" + "," +
                            models[1] + "=" + "\"" + textfields[1].getText() + "\"" + "," +
                            models[2] + "=" + "\"" + textfields[2].getText() + "\"" + "," +
                            models[3] + "=" + "\"" + textfields[3].getText() + "\"" + "," +
                            models[4] + "=" + "\"" + textfields[4].getText() + "\"" +
                            " WHERE " + "Coder = " + main_field + ";");
                    st.executeUpdate();
                    break;
                }
                case "Сборка" :
                {
                    PreparedStatement st = connery.prepareStatement("UPDATE " + select + " SET " +
                            assembly[0] + "=" + "\"" + textfields[0].getText() + "\"" + "," +
                            assembly[1] + "=" + "\"" + textfields[1].getText() + "\"" + "," +
                            assembly[2] + "=" + "\"" + textfields[2].getText() + "\"" + "," +
                            assembly[3] + "=" + "\"" + textfields[3].getText() + "\"" + "," +
                            assembly[4] + "=" + "\"" + textfields[4].getText() + "\"" + "," +
                            assembly[5] + "=" + "\"" + textfields[5].getText() + "\"" + "," +
                            assembly[6] + "=" + "\"" + textfields[6].getText() + "\"" + "," +
                            assembly[7] + "=" + "\"" + textfields[7].getText() + "\"" +
                            " WHERE " + "Coder = " + main_field + ";");
                    st.executeUpdate();
                    break;
                }
                case "Сборщики" :
                {
                    PreparedStatement st = connery.prepareStatement("UPDATE " + select + " SET " +
                            assemblers[0] + "=" + "\"" + textfields[0].getText() + "\"" + "," +
                            assemblers[1] + "=" + "\"" + textfields[1].getText() + "\"" + "," +
                            assemblers[2] + "=" + "\"" + textfields[2].getText() + "\"" + "," +
                            assemblers[3] + "=" + "\"" + textfields[3].getText() + "\"" + "," +
                            assemblers[4] + "=" + "\"" + textfields[4].getText() + "\"" + "," +
                            assemblers[5] + "=" + "\"" + textfields[5].getText() + "\"" + "," +
                            assemblers[6] + "=" + "\"" + textfields[6].getText() + "\"" + "," +
                            assemblers[7] + "=" + "\"" + textfields[7].getText() + "\"" +
                            " WHERE " + "Coder = " + main_field + ";");
                    st.executeUpdate();
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
