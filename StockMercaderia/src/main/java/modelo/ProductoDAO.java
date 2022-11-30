/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import static java.lang.System.in;
import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.guiVista;
public class ProductoDAO {
    Conexion conectando = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar()
    {
        List<Producto>datos = new ArrayList<>();
        var sql = "select * from mercaderia";
        try{
            con = conectando.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next())
            {
                var p = new Producto();
                p.setId(rs.getInt("id"));
                p.setProducto(rs.getString("producto"));
                p.setPrecio(rs.getInt("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                
                datos.add(p);
                
            }
            out.println();
            
        }catch(Exception e){}
        
        return datos;
        
    }
    
    
    
    
    public int agregar(Producto p){
        var sql = "insert into mercaderia(producto, precio, cantidad) values(?,?,?)";
        try{
            con = conectando.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getProducto());
            ps.setInt(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.executeUpdate();
                    
        }catch(SQLException e)
        {
            showMessageDialog(null, "no se cargo nada");
        }
       
        return 1;
        
    }
    
    public int actualizar(Producto p)
    {
        var sql = "update mercaderia set producto=?, precio=?, cantidad=? where id=?";
        try{
            con = conectando.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getProducto());
            ps.setInt(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            
            ps.setInt(4, p.getId());
            ps.executeUpdate();
                    
        }catch(SQLException e)
        {
            showMessageDialog(null, "no se actualizo nada");
        }
       
        return 1;
    }
    
    public void eliminarFila(int id)
    {
        var sql = "delete from mercaderia where id="+id;
        try{
            con = conectando.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
        }catch(Exception e){
            
        }
        
    }
    
    public int buscarProducto(Producto p)
    {
        var sql = "update mercaderia set * where producto=?";
        try{
            con = conectando.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, p.getPrecio());
            ps.setInt(2, p.getCantidad());
            
            ps.setString(3, p.getProducto());
            ps.executeUpdate();
                    
        }catch(SQLException e)
        {
            showMessageDialog(null, "no se actualizo nada");
        }
       
        return 1;
    }
    
    public static void main(String[]args)
    {
        var op = 0;
        String menu;
        var producto = new ProductoDAO();
        Producto p; 
        var entrada = new Scanner(in);
        
         
        do
        {
            out.println("*******menu******");
            out.println("1- agregar producto");
            out.println("2- mostrar producto");
           
            op = entrada.nextInt();
            switch(op)
            {
            case 1:
                //int id = entrada.nextInt();
                var prod = entrada.next();
                var precio = entrada.nextInt();
                var cantidad = entrada.nextInt(); 
                p = new Producto(prod, precio, cantidad);
                producto.agregar(p);
                break;
            case 2:
                var muestreo = producto.listar();
                out.println(muestreo);
                
            }
            
            }while(op!=-1);
                
    }
}