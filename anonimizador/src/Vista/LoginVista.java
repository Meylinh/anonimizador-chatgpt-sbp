package Vista;

import Controlador.UsuarioDAO;
import javax.swing.*;

public class LoginVista extends JFrame {

    public LoginVista() {
        setTitle("Login - Anonimador SBP");
        setSize(350, 230);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // Centrar ventana

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setBounds(30, 30, 80, 30);
        add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(120, 30, 150, 30);
        add(txtUser);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 80, 80, 30);
        add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(120, 80, 150, 30);
        add(txtPass);

        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(120, 130, 100, 30);
        add(btnLogin);

        // Acción del botón
        btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = String.valueOf(txtPass.getPassword());

            int idUsuario = UsuarioDAO.login(user, pass);

            if (idUsuario != -1) {
                JOptionPane.showMessageDialog(this, "Bienvenido/a!");

                // Abrir menú principal
                new MenuVista(idUsuario).setVisible(true);
                dispose(); // Cerrar ventana de login
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        });
    }
}
