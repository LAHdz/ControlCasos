package control_casos.backend.usuarios;

public class Usuario {
    
    private String dpi;
    private String nombre;
    private int tipo;
    private String nombreUsuarioS;
    private String password;

    public Usuario() {
    }

    public Usuario(String dpi, String nombre, int tipo) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombreUsuarioS() {
        return nombreUsuarioS;
    }

    public void setNombreUsuarioS(String nombreUsuarioS) {
        this.nombreUsuarioS = nombreUsuarioS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
