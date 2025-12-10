package Vista;

import javax.swing.*;
import Controlador.UsuarioDAO;

public class LoginVista extends JFrame {

    public LoginVista() {

        setTitle("Login");
        setSize(350, 230);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ====== ETIQUETAS ======
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setBounds(30, 40, 80, 25);
        add(lblUser);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 80, 80, 25);
        add(lblPass);

        // ====== CAMPOS ======
        JTextField user = new JTextField();
        user.setBounds(110, 40, 180, 25);
        add(user);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(110, 80, 180, 25);
        add(pass);

        // ====== BOTÓN ======
        JButton btn = new JButton("Entrar");
        btn.setBounds(120, 120, 100, 30);
        add(btn);

        // ====== TEXTO DE AYUDA (OPCIONAL, PERO ÚTIL) ======
        JLabel hint = new JLabel("Usuario inicial: admin | Contraseña: 1234");
        hint.setBounds(30, 160, 280, 20);
        hint.setFont(hint.getFont().deriveFont(10f));
        add(hint);

        // ====== EVENTO LOGIN ======
        btn.addActionListener(e -> {
            int id = UsuarioDAO.login(
                    user.getText(),
                    new String(pass.getPassword())
            );

            if (id > 0) {
                new MenuVista(id).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Usuario o Contraseña incorrectos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
