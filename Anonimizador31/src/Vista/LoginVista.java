package Vista;

import javax.swing.*;
import Controlador.UsuarioDAO;

public class LoginVista extends JFrame {

    public LoginVista() {
        setTitle("Login");
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Usuario:");
        l1.setBounds(20,20,80,25);
        add(l1);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(100,20,150,25);
        add(txtUser);

        JLabel l2 = new JLabel("Clave:");
        l2.setBounds(20,60,80,25);
        add(l2);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(100,60,150,25);
        add(txtPass);

        JButton btn = new JButton("Entrar");
        btn.setBounds(100,110,100,30);
        add(btn);

        btn.addActionListener(e -> {
            int id = UsuarioDAO.login(txtUser.getText(), new String(txtPass.getPassword()));

            if (id > 0) {
                new MenuVista(id).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
        });
    }
}
