package Views;

import controller.FrequenciaController;
import controller.AlunoController;
import model.Aluno;
import model.Frequencia;
import model.PlanoTreino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FrequenciaView extends JFrame {
    private FrequenciaController controller = new FrequenciaController();
    private AlunoController alunoCtrl = new AlunoController();

    private JComboBox<Aluno> cbAluno = new JComboBox<>();
    private JCheckBox chkPresente = new JCheckBox("Presente");
    private JButton btnSalvar = new JButton("Registrar");
    private JButton btnListar = new JButton("Listar");
    private JButton btnDeletar = new JButton("Deletar");
    private JTextArea txtResultado = new JTextArea(10, 40);

    public FrequenciaView() {
        super("Registro de Frequência");
        setLayout(new BorderLayout(5,5));
        carregarAlunos();


        JPanel painelBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBox.add(new JLabel("Aluno:"));
        painelBox.add(cbAluno);
        painelBox.add(chkPresente);
        painelBox.add(btnSalvar);
        painelBox.add(btnListar);

        add(painelBox, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnSalvar.addActionListener(this::registrar);
        btnListar.addActionListener(this::listar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void carregarAlunos() {
        cbAluno.removeAllItems();
        for (Aluno a : alunoCtrl.listar()) cbAluno.addItem(a);
    }

    private void registrar(ActionEvent e) {
        controller.registrar((Aluno)cbAluno.getSelectedItem(), chkPresente.isSelected());
        JOptionPane.showMessageDialog(this, "Frequência registrada!");
        listarautomatico();
    }

    private void listar(ActionEvent e) {
        Aluno aluno = (Aluno) cbAluno.getSelectedItem();
        txtResultado.setText("");
        for (Frequencia f : controller.listarPorAluno(aluno.getId())) {
            txtResultado.append("Data: " + f.getData() + " | Presença: " + (f.isPresenca() ? "Sim" : "Não") + "\n");
        }
    }

    public void listarautomatico() {
        Aluno aluno = (Aluno) cbAluno.getSelectedItem();
        List<Frequencia> lista = controller.listarPorAluno(aluno.getId());
        txtResultado.setText("");
        for (Frequencia f : lista) {
            txtResultado.append("Data: " + f.getData() + " | Presença: " + (f.isPresenca() ? "Sim" : "Não") + "\n");
        }
    }
}
