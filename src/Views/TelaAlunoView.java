package Views;
import javax.swing.*;
import java.awt.*;

public class TelaAlunoView extends JFrame {

    public TelaAlunoView(){
        super("Tela Aluno");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
    }
}
