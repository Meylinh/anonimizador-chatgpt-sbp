package Modelo;

public class Usuario {
    private int id;
    private String usuario;
    private String password;

    public Usuario(int id, String usuario, String password) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
    }

    public int getId() { return id; }
    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }
}
