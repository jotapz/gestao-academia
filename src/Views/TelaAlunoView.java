package Views;

import controller.AlunoController;
import entity.Aluno;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

public class TelaAlunoView extends JFrame {
    private AlunoController controller = new AlunoController();
    private JTextField txtNome = new JTextField(20);
    private JTextField txtCpf = new JTextField(15);
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnListar = new JButton("Listar");
    private JTextArea txtResultado = new JTextArea(10, 40);

    public TelaAlunoView() {
        super("Cadastro de Alunos");
        setLayout(new FlowLayout());
        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("CPF:"));
        add(txtCpf);
        add(btnSalvar);
        add(btnListar);
        add(new JScrollPane(txtResultado));

        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void salvar(ActionEvent e) {
        Aluno aluno = new Aluno();
        aluno.setNome(txtNome.getText());
        aluno.setCpf(txtCpf.getText());
        aluno.setDataIngresso(new Date());
        controller.cadastrar(aluno);
        JOptionPane.showMessageDialog(this, "Aluno cadastrado!");
    }

    private void listar(ActionEvent e) {
        List<Aluno> lista = controller.listar();
        txtResultado.setText("");
        for (Aluno a : lista) {
            txtResultado.append("ID: " + a.getId() + " | Nome: " + a.getNome() + " | CPF: " + a.getCpf() + "\n");
        }
    }
}
