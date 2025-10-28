package Views;

import controller.InstrutorController;
import model.Instrutor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaInstrutorView extends JFrame {
	private InstrutorController controller = new InstrutorController();
	private JTextField txtNome = new JTextField(20);
	private JTextField txtEsp = new JTextField(15);
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnListar = new JButton("Listar");
	private JButton btnDeletar = new JButton("Deletar");
	private JButton btnAtualizar = new JButton("Atualizar");
	private JTextArea txtResultado = new JTextArea(10, 40);

	public TelaInstrutorView() {
		super("Cadastro de Instrutores");

		setLayout(new BorderLayout(5,5));

		JPanel painelCampos = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painelCampos.add(new JLabel("Nome:"));
		painelCampos.add(txtNome);
		painelCampos.add(new JLabel("Especialidade:"));
		painelCampos.add(txtEsp);

		JPanel painelBotoes = new JPanel();
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnListar);
		painelBotoes.add(btnDeletar);
		painelBotoes.add(btnAtualizar);

		add(painelCampos, BorderLayout.NORTH);
		add(painelBotoes, BorderLayout.SOUTH);
		add(new JScrollPane(txtResultado), BorderLayout.CENTER);

		btnSalvar.addActionListener(this::salvar);
		btnListar.addActionListener(this::listar);
		btnDeletar.addActionListener(this::deletar);
		btnAtualizar.addActionListener(this::atualizar);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void salvar(ActionEvent e) {
		if (txtNome.getText().isBlank() && txtEsp.getText().isBlank()) {
			JOptionPane.showMessageDialog(this,
					"Campo nome e especialidade necessitam ser preenchidos",
					"Erro de validação",
					JOptionPane.ERROR_MESSAGE);
		}
		controller.cadastrar(txtNome.getText(), txtEsp.getText());
		JOptionPane.showMessageDialog(this, "Instrutor cadastrado!");
		listarautomatico();
	}

	private void listar(ActionEvent e) {
		txtResultado.setText("");
		for (Instrutor instrutor : controller.listar()) {
			txtResultado.append("ID: " + instrutor.getId() + " | Nome: " + instrutor.getNome() + " | Esp: " + instrutor.getEspecialidade() + "\n");
		}
		JOptionPane.showMessageDialog(this, "Instrutores listados!");
	}

	public void listarautomatico() {
		List<Instrutor> lista = controller.listar();
		txtResultado.setText("");
		for (Instrutor instrutor : lista) {
			txtResultado.append("ID: " + instrutor.getId() + " | Nome: " + instrutor.getNome() + " | Esp: " + instrutor.getEspecialidade() + "\n");
		}
	}

	private void atualizar(ActionEvent e){
		String idStr = JOptionPane.showInputDialog(this,
				"Digite o ID do Instrutor que deseja Atualizar",
				"Atulizar Instrutor",
				JOptionPane.QUESTION_MESSAGE);

		if (idStr.isBlank()) {
			JOptionPane.showMessageDialog(this,
					"É necessário preencher o campo com o ID do instrutor",
					"Erro de validação",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		controller.atualizar(txtNome.getText(), txtEsp.getText(), Integer.parseInt(idStr));
		listarautomatico();
	}


	private void deletar (ActionEvent e) {
		String idStr = JOptionPane.showInputDialog(this,
				"Digite o ID do Instrutor que você deseja deletar",
				"Deletar Instruutor",
				JOptionPane.QUESTION_MESSAGE);
		if (idStr.isBlank()) {
			JOptionPane.showMessageDialog(this, "Para deletar é necessário o ID");
			return;
		}
		int id = Integer.parseInt(idStr);
		int confirm = JOptionPane.showConfirmDialog(this,
				"certeza que quer deletar o id " + id + "?",
				"Confirmação",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_NO_OPTION) {
			controller.deletar(id);
			JOptionPane.showMessageDialog(this,"Instrutor deletado com suuceesso!");
			listarautomatico();
		}
	}
}
