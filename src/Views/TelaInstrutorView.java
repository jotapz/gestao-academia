package Views;

import dao.InstrutorDAO;
import entity.Instrutor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaInstrutorView extends JFrame {

	private JTable table;
	private DefaultTableModel tableModel;
	private InstrutorDAO instrutorDAO = new InstrutorDAO();

	public TelaInstrutorView() {
		super("Tela Instrutor");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);

		initComponents();
		loadInstrutores();
	}

	private void initComponents() {
		tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Especialidade"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel pnlButtons = new JPanel();
		JButton btnAdd = new JButton("Adicionar");
		JButton btnEdit = new JButton("Editar");
		JButton btnDelete = new JButton("Deletar");

		pnlButtons.add(btnAdd);
		pnlButtons.add(btnEdit);
		pnlButtons.add(btnDelete);
		this.add(pnlButtons, BorderLayout.SOUTH);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Instrutor i = showInstrutorDialog(null);
				if (i != null) {
					instrutorDAO.salvar(i);
					loadInstrutores();
				}
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sel = table.getSelectedRow();
				if (sel >= 0) {
					int id = (int) tableModel.getValueAt(sel, 0);
					Instrutor existente = instrutorDAO.buscarPorId(id);
					Instrutor atualizado = showInstrutorDialog(existente);
					if (atualizado != null) {
						instrutorDAO.atualizar(atualizado);
						loadInstrutores();
					}
				} else {
					JOptionPane.showMessageDialog(TelaInstrutorView.this, "Selecione um instrutor para editar.");
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sel = table.getSelectedRow();
				if (sel >= 0) {
					int id = (int) tableModel.getValueAt(sel, 0);
					int option = JOptionPane.showConfirmDialog(TelaInstrutorView.this, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						instrutorDAO.deletar(id);
						loadInstrutores();
					}
				} else {
					JOptionPane.showMessageDialog(TelaInstrutorView.this, "Selecione um instrutor para deletar.");
				}
			}
		});
	}

	private void loadInstrutores() {
		tableModel.setRowCount(0);
		List<Instrutor> instrutores;
		try {
			instrutores = instrutorDAO.listarTodos();
		} catch (RuntimeException ex) {
			JOptionPane.showMessageDialog(this, "Não foi possível carregar instrutores: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (Instrutor i : instrutores) {
			Object[] row = new Object[]{i.getId(), i.getNome(), i.getEspecialidade()};
			tableModel.addRow(row);
		}
	}

	private Instrutor showInstrutorDialog(Instrutor existente) {
		JTextField txtNome = new JTextField();
		JTextField txtEspecialidade = new JTextField();

		if (existente != null) {
			txtNome.setText(existente.getNome());
			txtEspecialidade.setText(existente.getEspecialidade());
		}

		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.add(new JLabel("Nome:")); panel.add(txtNome);
		panel.add(new JLabel("Especialidade:")); panel.add(txtEspecialidade);

		int result = JOptionPane.showConfirmDialog(this, panel, existente == null ? "Adicionar Instrutor" : "Editar Instrutor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			Instrutor i = existente != null ? existente : new Instrutor();
			i.setNome(txtNome.getText());
			i.setEspecialidade(txtEspecialidade.getText());
			return i;
		}
		return null;
	}
}
