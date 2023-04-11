import javax.swing.*;

public class EditGood {
    private JFrame editFrame;
    private JPanel editPanel;
    private JLabel editName;
    private JLabel editDescription;
    private JLabel editProducer;
    private JLabel editPrice;
    private JLabel editAmount;

    public EditGood() {
        editFrame = new JFrame();
        editFrame.setBounds(MainMenu.screenDimension.width/3,MainMenu.screenDimension.height/4,600,900);
        editFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        editFrame.setVisible(true);
    }

    public void setEditMenu(){

    }
}
