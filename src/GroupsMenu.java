import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupsMenu {
    private static JTree productTree;
    private static DefaultMutableTreeNode root;
    private static JFrame groupsFrame;
    private static JButton editButton, deleteButton, backToMenu;
    private static DefaultMutableTreeNode selectedNode;

    public static void setGroupsMenu(Rectangle bounds) {
         groupsFrame = new JFrame("Групи товарів");
        groupsFrame.addWindowListener(new ShopWindowListener());
        groupsFrame.setBounds(bounds);
        groupsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        groupsFrame.setLayout(new BorderLayout());

        ProductGroupTree tree = new ProductGroupTree();

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.ORANGE);
        JLabel logo = new JLabel("Вибір Груп Товарів");
        logo.setForeground(Color.BLUE);
        logo.setFont(new Font("Arial Black", Font.BOLD, 32));
        logoPanel.add(logo);

        JPanel buttons = new JPanel(new GridLayout(1,3));
        buttons.setPreferredSize(new Dimension(groupsFrame.getWidth(),groupsFrame.getHeight()/8));
        editButton = new JButton("Редагувати");
        editButton.addActionListener(e -> {
            if (selectedNode != null && selectedNode.getUserObject().getClass() == Group.class) {
                editGroup((Group)selectedNode.getUserObject());
                productTree.updateUI();
            }
        });

        deleteButton = new JButton("Видалити");
        deleteButton.addActionListener(e -> {
            if (selectedNode != null && selectedNode.getUserObject().getClass() == Group.class) {
                root.remove(selectedNode);
                Group g = (Group) selectedNode.getUserObject();
                Shop.goodsArray.removeIf(good -> good.groupName.equals(g.name));
                Shop.groupArray.remove(g);
                productTree.updateUI();
            }
        });
        backToMenu = new JButton("Повернутися");
        backToMenu.addActionListener(e -> {
                groupsFrame.setVisible(false);
                MainMenu.mainFrame.setBounds(groupsFrame.getBounds());
                MainMenu.mainFrame.setVisible(true);
        });
        buttons.add(editButton);
        buttons.add(deleteButton);
        buttons.add(backToMenu);

        groupsFrame.add(logoPanel, BorderLayout.NORTH);
        groupsFrame.add(new JScrollPane(tree), BorderLayout.CENTER);
        groupsFrame.add(buttons, BorderLayout.SOUTH);
        groupsFrame.setVisible(true);
    }
    private static void editGroup(Group g){
        JFrame editFrame = new JFrame("Редагування " + g.name);
        editFrame.setLayout(new BorderLayout());
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setBounds(groupsFrame.getX() + groupsFrame.getWidth()/4, groupsFrame.getY() + groupsFrame.getHeight()/4,groupsFrame.getWidth()/2, groupsFrame.getHeight()/2);

        JPanel inputs = new JPanel(new GridLayout(2,2));
        inputs.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        inputs.add(new JLabel("НАЗВА "));
        JTextField name = new JTextField(g.name);
        inputs.add(name);
        inputs.add(new JLabel("ОПИС "));
        JTextArea description = new JTextArea(g.description);
        inputs.add(new JScrollPane(description));

        JButton approve = new JButton("Підтвердити");
        approve.addActionListener(e -> {
            for (Good good : Shop.goodsArray) if (good.groupName.equals(g.name)) good.groupName = name.getText();
            g.name = name.getText();
            g.description = description.getText();
            editFrame.setVisible(false);
        });

        editFrame.add(inputs,BorderLayout.CENTER);
        editFrame.add(approve, BorderLayout.SOUTH);
        editFrame.setVisible(true);
    }
    private static class ProductGroupTree extends JPanel {

        public ProductGroupTree() {
            root = new DefaultMutableTreeNode("Product Groups");
            setNode();
            productTree = new JTree(root);
            productTree.setFont(new Font("Arial Black", Font.PLAIN, 20));
            productTree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    selectedNode = (DefaultMutableTreeNode) productTree.getLastSelectedPathComponent();
                }
            });

            setLayout(new BorderLayout());
            add(new JScrollPane(productTree), BorderLayout.CENTER);
        }

        void setNode(){
            for(Group group : Shop.groupArray){
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(group);
                for (Good good : Shop.goodsArray){
                    if (good.groupName.equals(group.name))node.add(new DefaultMutableTreeNode(good));
                }
                root.add(node);
            }
        }
    }
}