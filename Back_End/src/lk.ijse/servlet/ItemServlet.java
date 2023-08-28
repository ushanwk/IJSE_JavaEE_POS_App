package lk.ijse.servlet;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = {"/pages/item"})
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaeePosApp ", "root", "ushan1234");

            PreparedStatement pstm = connection.prepareStatement("select * from item");
            ResultSet rst = pstm.executeQuery();
            PrintWriter writer = resp.getWriter();

            resp.addHeader("Content-Type","application/json");
            resp.addHeader("Access-Control-Allow-Origin","*");

            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            while (rst.next()) {
                String code = rst.getString(1);
                String item = rst.getString(2);
                String qty = rst.getString(3);
                String price = rst.getString(4);

                JsonObjectBuilder customer = Json.createObjectBuilder();

                customer.add("customerId", code);
                customer.add("customerName", item);
                customer.add("customerAddress", qty);
                customer.add("customerSalary", price);

                allCustomers.add(customer.build());

            }

            writer.print(allCustomers.build());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type","application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        PrintWriter writer = resp.getWriter();

        JsonReader reader = Json.createReader(req.getReader());

        JsonObject jsonObject = reader.readObject();


        String itemCode = jsonObject.getString("code");
        String itemName = jsonObject.getString("item");
        String itemQty = jsonObject.getString("qty");
        String itemPrice = jsonObject.getString("price");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaeePosApp", "root", "ushan1234");


            PreparedStatement pstm = connection.prepareStatement("insert into item values(?,?,?,?)");
            pstm.setObject(1, itemCode);
            pstm.setObject(2, itemName);
            pstm.setObject(3, Integer.parseInt(itemQty));
            pstm.setObject(4, Integer.parseInt(itemPrice));

            if (pstm.executeUpdate() > 0) {

                resp.addHeader("Content-Type","application/json");

                JsonObjectBuilder m = Json.createObjectBuilder();
                m.add("state","OK");
                m.add("message","Succesfuly Added");
                m.add("data","Succesfuly Added");
                resp.setStatus(200);
                writer.print(m.build());

            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {

            resp.addHeader("Content-Type","application/json");

            JsonObjectBuilder m = Json.createObjectBuilder();
            m.add("state","NO");
            m.add("message",e.getMessage());
            m.add("data","Not Added");
            writer.print(m.build());
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type","application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaeePosApp", "root", "ushan1234");
            PrintWriter writer = resp.getWriter();

            JsonReader reader = Json.createReader(req.getReader());

            JsonObject jsonObject = reader.readObject();


            String itemCode = jsonObject.getString("code");
            String itemName = jsonObject.getString("item");
            String itemQty = jsonObject.getString("qty");
            String itemPrice = jsonObject.getString("price");


            PreparedStatement pstm3 = connection.prepareStatement("update item set itemName=?,itemQty=?,itemPrice=? where itemCode=?");
            pstm3.setObject(4, itemCode);
            pstm3.setObject(1, itemName);
            pstm3.setObject(2, itemQty);
            pstm3.setObject(3, itemPrice);
            if (pstm3.executeUpdate() > 0) {
                resp.addHeader("Content-Type","application/json");

                JsonObjectBuilder m = Json.createObjectBuilder();
                m.add("state","OK");
                m.add("message","Succesfuly Update");
                m.add("data","Succesfuly Update");
                writer.print(m.build());
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type","application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaeePosApp", "root", "ushan1234");
            PrintWriter writer = resp.getWriter();

            JsonReader reader = Json.createReader(req.getReader());

            JsonObject jsonObject = reader.readObject();

            String code = jsonObject.getString("itemCode");

            System.out.println(code);


            PreparedStatement pstm2 = connection.prepareStatement("delete from item where itemCode=?");
            pstm2.setObject(1, code);
            if (pstm2.executeUpdate() > 0) {
                resp.addHeader("Content-Type","application/json");

                JsonObjectBuilder m = Json.createObjectBuilder();
                m.add("state","OK");
                m.add("message","Succesfuly Delete");
                m.add("data","Succesfuly Delete");
                writer.print(m.build());
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","PUT");
        resp.addHeader("Access-Control-Allow-Methods","DELETE");
        resp.addHeader("Access-Control-Allow-Headers","content-type");
    }

}