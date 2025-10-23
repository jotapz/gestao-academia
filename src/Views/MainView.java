package Views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        super("Sistema de Gerenciamento de Academia");
        setLayout(new FlowLayout());

        JButton btnAluno = new JButton("Gerenciar Alunos");
        JButton btnInstrutor = new JButton("Gerenciar Instrutores");
        JButton btnPlano = new JButton("Gerenciar Planos de Treino");
        JButton btnFreq = new JButton("Gerenciar Frequências");

        add(btnAluno);
        add(btnInstrutor);
        add(btnPlano);
        add(btnFreq);

        btnAluno.addActionListener(e -> new TelaAlunoView());
        btnInstrutor.addActionListener(e -> new TelaInstrutorView());
        btnPlano.addActionListener(e -> new PlanoTreinoView());
        btnFreq.addActionListener(e -> new FrequenciaView());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
