package control_casos.backend.datos;

import control_casos.backend.usuarios.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorUsuarios {
    ConectorBaseDatos CBD;

    public ControladorUsuarios() {
        CBD = new ConectorBaseDatos();
    }
    
    public boolean create(Usuario u) {
        try {
            String sql = "INSERT INTO USUARIO(DPI, Nombre, Tipo, Nombre_Usuario, Password) VALUES(?,?,?,?,?)";
            PreparedStatement ps = CBD.conectar().prepareStatement(sql);
            ps.setString(1, u.getDpi());
            ps.setString(2, u.getNombre());
            ps.setInt(3, u.getTipo());
            ps.setString(4, u.getNombreUsuarioS());
            ps.setString(5, u.getPassword());
            ps.execute();
            ps.close();
            ps = null;
            CBD.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("No se inserto registro");
            ex.printStackTrace();
            return false;
        }

    }
    
    public ArrayList<Usuario> leerUsuario(){
        ArrayList<Usuario> lista = new ArrayList();
        try {
            String sql = "SELECT * FROM USUARIO";
            PreparedStatement ps = CBD.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setDpi(rs.getString("DPI"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setNombreUsuarioS(rs.getString("Nombre_Usuario"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setTipo(rs.getInt("Tipo"));
                lista.add(usuario);
            }
            ps.close();
            ps = null;
            CBD.desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo metodo read");
        }
        return lista;
    }
    
    public boolean delete(String dpi) {
        try {
            String sql = "DELETE FROM USUARIO WHERE DPI = ?";
            PreparedStatement ps = CBD.conectar().prepareStatement(sql);
            ps.setString(1, dpi);
            ps.execute();
            ps.close();
            ps = null;
            CBD.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("No se elimino registro");
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean actualizar(Usuario u) {
        try {
            String sql = "UPDATE USUARIO SET Nombre = ?, Tipo = ?, Nombre_Usuario = ?, Password = ? WHERE DPI = ?";
            PreparedStatement ps = CBD.conectar().prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setInt(2, u.getTipo());
            ps.setString(3, u.getNombreUsuarioS());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getDpi());
            ps.execute();
            ps.close();
            ps=null;
            CBD.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }        
    }
    
     public Usuario leerUsuarioFila(String dpi) {
       Usuario u = new Usuario();
        try {
            String sql = "SELECT * FROM USUARIO WHERE DPI = ?";
            PreparedStatement ps = CBD.conectar().prepareStatement(sql);
            ps.setString(1, dpi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u.setDpi(rs.getString("DPI"));
                u.setNombre(rs.getString("Nombre"));
                u.setTipo(rs.getInt("Tipo"));
                u.setNombreUsuarioS(rs.getString("Nombre_Usuario"));
                u.setPassword(rs.getString("Password"));
            }
            ps.close();
            ps = null;
            CBD.desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo metodo leerusuario");
        }
        return u;
    }
    
    public int verificarUsuario(ArrayList<Usuario> users, String nombre, String password){
        int accesoTipo = 0;
        for (Usuario u : users) {
            if (nombre.equalsIgnoreCase(u.getNombreUsuarioS()) && password.equals(u.getPassword())) {
                accesoTipo = u.getTipo();               
            }
        }
        
        return accesoTipo;
    }
}
