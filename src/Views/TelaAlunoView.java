package Views;

import controller.AlunoController;
import model.Aluno;
import model.Instrutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaAlunoView extends JFrame {
    //instanciando o controller
    private AlunoController controller = new AlunoController();
    private JTextField txtNome = new JTextField(20);
    private JTextField txtCpf = new JTextField(15);
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnListar = new JButton("Listar");
    private JButton btnAtualizar = new JButton("Atualizar");
    private JButton btnDeletar = new JButton("Deletar");
    private JTextArea txtResultado = new JTextArea(10, 40);

    public TelaAlunoView() {
        super("Cadastro de Alunos");
        //tirei o flow layout pq queria deixar como blocos e pra isso é melhoor o borderlayout
        setLayout(new BorderLayout(5,5));

        // aqui eu to criando tipo uma div para colocar os campos de texto nome e cpf
        JPanel painelCampos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelCampos.add(new JLabel("Nome: "));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Cpf: "));
        painelCampos.add(txtCpf);

        // outra div com os botoes
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDeletar);

        //aquui euu to posicionando as "divs"
        add(painelCampos, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER); //area onde vai listar os dados
        add(painelBotoes, BorderLayout.SOUTH);

        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);
        btnAtualizar.addActionListener(this::atualizar);
        btnDeletar.addActionListener(this::deletar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void salvar(ActionEvent e) {
        //se os campos estiverem vazios ele vai mostrar um aviso
        if (txtNome.getText() == null && txtCpf.getText() == null|| txtNome.getText().isBlank() && txtCpf.getText().isBlank()){
            JOptionPane.showMessageDialog(this,
                    "Campo nome e cpf precisa ser preenchido",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
        }
        //caso nao esteja vazio ele vai chamar o metodo cadastrar do AlunoController e passar como parametro o txtnome e txtcpf coomo string.
        controller.cadastrar(txtNome.getText(), txtCpf.getText());
        JOptionPane.showMessageDialog(this, "Aluno cadastrado!");
        listarautomatico(e);
    }

    private void listar(ActionEvent e) {
        List<Aluno> lista = controller.listar();
        txtResultado.setText("");
        for (Aluno aluno : lista) {
            txtResultado.append("ID: " + aluno.getId() + " | Nome: " + aluno.getNome() + " | CPF: " + aluno.getCpf() + "\n");
        }
        JOptionPane.showMessageDialog(this, "Alunos listados!");
    }

    //update atualizar na view
    private void atualizar(ActionEvent e) {
        //cria uma tela que tem uum input e voce vai adicionar o id ele vai ser uuma string
        String idStr = JOptionPane.showInputDialog(this,
                "Digite o ID do aluno que deseja ATUALIZAR:",
                "Atualizar Aluno",
                JOptionPane.QUESTION_MESSAGE);
        // see o campo tiver vazio ele avisa e retorna
        if (idStr.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Precisa ter o ID",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        //chama o metodo atualizar do alunocontroller passando no parâmetro o get do txtnome e txtcpf para atualizar o aluno coom aquele id
        controller.atualizar(txtNome.getText(), txtCpf.getText(), Integer.parseInt(idStr));
        listarautomatico(e);
    }


    // aqui fiz o deletar na view
    private void deletar(ActionEvent e) {
        // aqui ele cria um campo onde voce vai mandar um numero em string
        String idStr = JOptionPane.showInputDialog(this,
                "Digite o ID do aluno que deseja deletar",
                "Deletar Aluno",
                JOptionPane.QUESTION_MESSAGE);
        // se o tiver vazio vai mostrar que é necessario o id
        if (idStr == null || idStr.isBlank()) {
            JOptionPane.showMessageDialog(this, "Para deletar é necessário o ID");
            return;
        }
        // aqui ele declara que o int id vai ter o valor digitado no campo string mas ele manda em int
        int id = Integer.parseInt(idStr);
        //tela de confirmar se realment deseja deletar o id do aluno
        int confirm = JOptionPane.showConfirmDialog(this,
                "certeza que quer deletar o id " + id + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);
        //se confirmou ele vai chamar o metodoo deletar do Alunocontroller!!
        if (confirm == JOptionPane.YES_NO_OPTION) {
            controller.deletar(id);
            JOptionPane.showMessageDialog(this,"Aluno deletado com sucesso!");
        }
        listarautomatico(e);
    }

    public void listarautomatico(ActionEvent e) {
        List<Aluno> lista = controller.listar();
        txtResultado.setText("");
        for (Aluno aluno : lista) {
            txtResultado.append("ID: " + aluno.getId() + " | Nome: " + aluno.getNome() + " | Esp: " + aluno.getCpf() + "\n");
        }
    }
}
