package Views;

import dao.AlunoDAO;
import entity.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class TelaAlunoView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private AlunoDAO alunoDAO = new AlunoDAO();

    public TelaAlunoView(){
        super("Tela Aluno");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 400);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        initComponents();
        loadAlunos();
    }

    private void initComponents(){
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Data Ingresso"}, 0) {
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
                Aluno a = showAlunoDialog(null);
                if (a != null) {
                    alunoDAO.salvar(a);
                    loadAlunos();
                }
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sel = table.getSelectedRow();
                if (sel >= 0) {
                    int id = (int) tableModel.getValueAt(sel, 0);
                    Aluno existente = alunoDAO.buscarPorId(id);
                    Aluno atualizado = showAlunoDialog(existente);
                    if (atualizado != null) {
                        alunoDAO.atualizar(atualizado);
                        loadAlunos();
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaAlunoView.this, "Selecione um aluno para editar.");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sel = table.getSelectedRow();
                if (sel >= 0) {
                    int id = (int) tableModel.getValueAt(sel, 0);
                    int option = JOptionPane.showConfirmDialog(TelaAlunoView.this, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        alunoDAO.deletar(id);
                        loadAlunos();
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaAlunoView.this, "Selecione um aluno para deletar.");
                }
            }
        });
    }

    private void loadAlunos(){
        tableModel.setRowCount(0);
        List<Aluno> alunos;
        try {
            alunos = alunoDAO.listarTodos();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível carregar alunos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Aluno a : alunos) {
            Object[] row = new Object[]{a.getId(), a.getNome(), a.getCpf(), a.getDataIngresso() != null ? df.format(a.getDataIngresso()) : ""};
            tableModel.addRow(row);
        }
    }

    private Aluno showAlunoDialog(Aluno existente){
        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtData = new JTextField();

        if (existente != null) {
            txtNome.setText(existente.getNome());
            txtCpf.setText(existente.getCpf());
            if (existente.getDataIngresso() != null) txtData.setText(new SimpleDateFormat("yyyy-MM-dd").format(existente.getDataIngresso()));
        }

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Nome:")); panel.add(txtNome);
        panel.add(new JLabel("CPF:")); panel.add(txtCpf);
        panel.add(new JLabel("Data Ingresso (yyyy-MM-dd):")); panel.add(txtData);

        int result = JOptionPane.showConfirmDialog(this, panel, existente == null ? "Adicionar Aluno" : "Editar Aluno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Aluno a = existente != null ? existente : new Aluno();
                a.setNome(txtNome.getText());
                a.setCpf(txtCpf.getText());
                if (!txtData.getText().trim().isEmpty()) {
                    java.util.Date parsed = new SimpleDateFormat("yyyy-MM-dd").parse(txtData.getText().trim());
                    a.setDataIngresso(parsed);
                }
                return a;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao ler dados: " + ex.getMessage());
            }
        }
        return null;
    }
}
