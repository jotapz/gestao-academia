package Views;
import javax.swing.*;
import java.awt.*;

public class TelaPrincipalView extends JFrame {

        private JButton btnGerenciarAlunos;
        private JButton btnGerenciarInstrutores;

        public TelaPrincipalView() {
            super("Sistema de Gestão de Academia"); // Define o título via construtor do pai
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(600, 400);
            this.setLayout(new BorderLayout());
            this.setLocationRelativeTo(null); // Centraliza

            // chama meetodos para organizar a criação dos componentes
            initComponents();
            initLayout();
            initListeners();
        }

        private void initComponents() {
            btnGerenciarAlunos = new JButton("Gerenciar Alunos");
            btnGerenciarInstrutores = new JButton("Gerenciar Instrutores");
        }

        private void initLayout() {
            // Painel de Título
            JPanel painelTitulo = new JPanel();
            JLabel labelTitulo = new JLabel("Menu Principal");
            labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
            painelTitulo.add(labelTitulo);
            this.add(painelTitulo, BorderLayout.NORTH);

            // Painel de Botões
            JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 10, 10));
            painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            painelBotoes.add(btnGerenciarAlunos);
            painelBotoes.add(btnGerenciarInstrutores);
            this.add(painelBotoes, BorderLayout.CENTER);
        }

        private void initListeners() {
            btnGerenciarAlunos.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Abrindo tela de alunos...");
                 new TelaAlunoView().setVisible(true); // chamando outra tela
            });

            btnGerenciarInstrutores.addActionListener(e -> {
            });
        }
    }

