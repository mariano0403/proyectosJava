package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.ProductoDAO;
import vista.guiVista;

public class Controlar implements ActionListener {
    
    ProductoDAO dao = new ProductoDAO();
    Producto p = new Producto();
    guiVista vista = new guiVista();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlar(guiVista v)
    {
        this.vista=v;
        this.vista.botonListar.addActionListener(this);
        this.vista.botonGuargar.addActionListener(this);
        this.vista.botonEditar.addActionListener(this);
        this.vista.botonActualizar.addActionListener(this);
        this.vista.botonEliminar.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean muestro;
        if (e.getSource() == vista.botonListar)
        {
           limpiarTabla();
           listar(vista.tablaDatos);
        }
        if (e.getSource() == vista.botonGuargar)
        {
            agregar();
            limpiarTabla();
            listar(vista.tablaDatos);
        }
        
        if (e.getSource() == vista.botonEditar){
            var fila = vista.tablaDatos.getSelectedRow();
            if (fila == -1){
                showMessageDialog(vista, "debe seleccionar fila");
            }
            else{
                var id = parseInt((String)vista.tablaDatos.getValueAt(fila,0).toString());
                var prod = (String)vista.tablaDatos.getValueAt(fila,1);
                var precio = parseInt((String)vista.tablaDatos.getValueAt(fila,2).toString());
                var cantidad = parseInt((String)vista.tablaDatos.getValueAt(fila, 3).toString());
                vista.txtId.setText(""+id);
                vista.txtProducto.setText(prod);
                vista.txtPrecio.setText(""+precio);
                vista.txtCantidad.setText(""+cantidad);
                        
            }
        }
        if (e.getSource() == vista.botonActualizar)
        {
            actualizar();
            limpiarTabla();
            listar(vista.tablaDatos);
        }
        
        if (e.getSource() == vista.botonEliminar)
        {
            eliminar();
            limpiarTabla();
            listar(vista.tablaDatos);
            
        }
        
        
        
    }
    public void actualizar()
    {
        var id = parseInt(vista.txtId.getText());
        var producto = vista.txtProducto.getText();
        var precio = parseInt(vista.txtPrecio.getText());
        var cantidad = parseInt(vista.txtCantidad.getText());
        p.setProducto(producto);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        
        p.setId(id);
        
        var r = dao.actualizar(p);
        if ( r == 1){
            showMessageDialog(null,"producto actualizado");
     
        }else{
            showMessageDialog(null,"no se actualizó nada");
        }
    }
    
    public void buscarProducto()
    {
        
        var producto = vista.txtProducto.getText();
        var precio = parseInt(vista.txtPrecio.getText());
        var cantidad = parseInt(vista.txtCantidad.getText());
        
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        
        p.setProducto(producto);
        
        var r = dao.actualizar(p);
        if ( r == 1){
            showMessageDialog(null,"producto actualizado");
     
        }else{
            showMessageDialog(null,"no se actualizó nada");
        }
    }
    
    public void eliminar()
    {
        var fila = vista.tablaDatos.getSelectedRow();
            //int id = Integer.parseInt((String)vista.tablaDatos.getValueAt(fila, 0));
            
            if (fila==-1){
                showMessageDialog(vista, "debe seleccionar producto");
            }else{
                var id = parseInt((String)vista.tablaDatos.getValueAt(fila, 0).toString());
                dao.eliminarFila(id);
            }
    }
    public void agregar()
    {
        var producto = vista.txtProducto.getText();
        var precio = parseInt(vista.txtPrecio.getText());
        var cantidad = parseInt(vista.txtCantidad.getText());
        
        p.setProducto(producto);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        
        
        var r = dao.agregar(p);
        if ( r == 1){
            showMessageDialog(null,"producto agregado");
     
        }else{
            showMessageDialog(null,"no se agrego nada");
        }
    }
            
        
    
    public void listar(JTable tabla)
    {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Producto> lista = dao.listar();
        var object = new Object[4];
        for (var i= 0; i < lista.size(); i++ )
        {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getProducto();
            object[2] = lista.get(i).getPrecio();
            object[3] = lista.get(i).getCantidad();
            
            
             
            modelo.addRow(object);
            
        }
        
        vista.tablaDatos.setModel(modelo);
        
    }
    
    void limpiarTabla()
    {
        for (var i=0; i<vista.tablaDatos.getRowCount(); i++){
            modelo.removeRow(i);
            i = i-1;
        }
    }

    
    
}