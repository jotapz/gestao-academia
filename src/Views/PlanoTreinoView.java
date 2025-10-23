package Views;

import controller.PlanoTreinoController;
import controller.AlunoController;
import controller.InstrutorController;
import entity.PlanoTreino;
import entity.Aluno;
import entity.Instrutor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PlanoTreinoView extends JFrame {
    private PlanoTreinoController controller = new PlanoTreinoController();
    private AlunoController alunoCtrl = new AlunoController();
    private InstrutorController instrutorCtrl = new InstrutorController();

    private JTextField txtDesc = new JTextField(20);
    private JTextField txtDuracao = new JTextField(5);
    private JComboBox<Aluno> cbAluno = new JComboBox<>();
    private JComboBox<Instrutor> cbInstrutor = new JComboBox<>();
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnListar = new JButton("Listar");
    private JTextArea txtResultado = new JTextArea(10, 40);

    public PlanoTreinoView() {
        super("Cadastro de Planos de Treino");
        setLayout(new FlowLayout());
        carregarCombos();

        add(new JLabel("Descrição:"));
        add(txtDesc);
        add(new JLabel("Duração (semanas):"));
        add(txtDuracao);
        add(new JLabel("Aluno:"));
        add(cbAluno);
        add(new JLabel("Instrutor:"));
        add(cbInstrutor);
        add(btnSalvar);
        add(btnListar);
        add(new JScrollPane(txtResultado));

        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void carregarCombos() {
        cbAluno.removeAllItems();
        cbInstrutor.removeAllItems();
        for (Aluno a : alunoCtrl.listar()) cbAluno.addItem(a);
        for (Instrutor i : instrutorCtrl.listar()) cbInstrutor.addItem(i);
    }

    private void salvar(ActionEvent e) {
        PlanoTreino p = new PlanoTreino();
        p.setDescricao(txtDesc.getText());
        p.setDuracaoSemanas(Integer.parseInt(txtDuracao.getText()));
        p.setAluno((Aluno) cbAluno.getSelectedItem());
        p.setInstrutor((Instrutor) cbInstrutor.getSelectedItem());
        controller.cadastrar(p);
        JOptionPane.showMessageDialog(this, "Plano de treino cadastrado!");
    }

    private void listar(ActionEvent e) {
        List<PlanoTreino> lista = controller.listar();
        txtResultado.setText("");
        for (PlanoTreino p : lista) {
            txtResultado.append("ID: " + p.getId() + " | Desc: " + p.getDescricao() +
                    " | Aluno: " + p.getAluno().getNome() + " | Instrutor: " + p.getInstrutor().getNome() + "\n");
        }
    }
}
