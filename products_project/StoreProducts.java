import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
public class StoreProducts extends HttpServlet{
        public void service(HttpServletRequest req,HttpServletResponse res){
            try{

                String prodid=req.getParameter("pid");
                String prodname=req.getParameter("pname");
                String prodcat=req.getParameter("cat");
                int prodqty=Integer.parseInt(req.getParameter("qty"));
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "Codechamp@2022");
                PreparedStatement stmt= con.prepareStatement("insert into products values (?,?,?,?)");
                stmt.setString(1, prodid);
                stmt.setString(2, prodname);
                stmt.setString(3, prodcat);
                stmt.setInt(4,prodqty);

                stmt.executeUpdate();
                con.close();

                PrintWriter out=res.getWriter();
                out.println("Product id: "+prodid);
                out.println("Product name: "+prodname);
                out.println("Product cat: "+prodcat);
                out.println("Product qty: "+prodqty);


            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        }
}