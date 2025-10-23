package Views;

import controller.InstrutorController;
import entity.Instrutor;
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
	private JTextArea txtResultado = new JTextArea(10, 40);

	public TelaInstrutorView() {
		super("Cadastro de Instrutores");
		setLayout(new FlowLayout());
		add(new JLabel("Nome:"));
		add(txtNome);
		add(new JLabel("Especialidade:"));
		add(txtEsp);
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
		Instrutor i = new Instrutor();
		i.setNome(txtNome.getText());
		i.setEspecialidade(txtEsp.getText());
		controller.cadastrar(i);
		JOptionPane.showMessageDialog(this, "Instrutor cadastrado!");
	}

	private void listar(ActionEvent e) {
		List<Instrutor> lista = controller.listar();
		txtResultado.setText("");
		for (Instrutor i : lista) {
			txtResultado.append("ID: " + i.getId() + " | Nome: " + i.getNome() + " | Esp: " + i.getEspecialidade() + "\n");
		}
	}
}
