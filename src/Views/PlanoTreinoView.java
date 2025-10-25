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
    private JButton btnAtualizar = new JButton("Atualizar");
    private JButton btnDeletar = new JButton("Deletar");
    private JTextArea txtResultado = new JTextArea(10, 40);

    public PlanoTreinoView() {
        super("Cadastro de Planos de Treino");
        setLayout(new BorderLayout(5,5));


        carregarCombos();

        JPanel painelCampos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelCampos.add(new JLabel("Descrição:"));
        painelCampos.add(txtDesc);
        painelCampos.add(new JLabel("Duração (semanas):"));
        painelCampos.add(txtDuracao);


        JPanel painelComboBox = new JPanel();
        painelComboBox.add(new JLabel("Aluno:"));
        painelComboBox.add(cbAluno);
        painelComboBox.add(new JLabel("Instrutor:"));
        painelComboBox.add(cbInstrutor);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDeletar);

        JPanel painelteste = new JPanel();
        painelteste.add(painelCampos);
        painelteste.add(painelComboBox);

        add(painelteste, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(new JScrollPane(txtResultado), BorderLayout.SOUTH);

        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);
        btnAtualizar.addActionListener(this::atualizar);
        btnDeletar.addActionListener(this::deletar);

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

        controller.cadastrar(txtDesc.getText(), Integer.parseInt(txtDuracao.getText()), ((Aluno)cbAluno.getSelectedItem()), ((Instrutor)cbInstrutor.getSelectedItem()));
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

    private void atualizar(ActionEvent e){
        String idStr = JOptionPane.showInputDialog(this,
                "Digite o ID do plano que deseja atualizar",
                "Atualizar plano treino",
                JOptionPane.QUESTION_MESSAGE);
        if (idStr.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "O campo precisa ser preenchido!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
        }
        controller.atualizar(txtDesc.getText(), Integer.parseInt(txtDuracao.getText()), ((Aluno)cbAluno.getSelectedItem()), ((Instrutor)cbInstrutor.getSelectedItem()), Integer.parseInt(idStr));
    }

    private void deletar(ActionEvent e){
        String idStr = JOptionPane.showInputDialog(this,
                "Preencha com o ID do plano que deseja deletar",
                "Deletar plano treino",
                JOptionPane.QUESTION_MESSAGE);

        if (idStr.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "O campo precisa ser preenchido!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
        }
        controller.deletar(Integer.parseInt(idStr));
    }
}
