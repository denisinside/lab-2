import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GroupsMenu {
    private static JTree productTree;
    private static DefaultMutableTreeNode root;
    private static JFrame groupsFrame;
    private static JButton editButton, deleteButton, backToMenuButton, addButton;
    private static DefaultMutableTreeNode selectedNode;
    static ImageIcon groupIcon;
    static ImageIcon goodIcon;

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
                editGroup((Group)selectedNode.getUserObject(),"Редагування");
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
        backToMenuButton = new JButton("Повернутися");
        backToMenuButton.addActionListener(e -> {
            groupsFrame.setVisible(false);
            MainMenu.mainFrame.setBounds(groupsFrame.getBounds());
            MainMenu.mainFrame.setVisible(true);
        });
        addButton = new JButton("Додати групу");
        addButton.addActionListener(e -> {
            editGroup(new Group("",""), "Додавання нової групи товарів");
            System.out.println("adding");

        });
        buttons.add(addButton);
        buttons.add(editButton);
        buttons.add(deleteButton);
        buttons.add(backToMenuButton);

        groupsFrame.add(logoPanel, BorderLayout.NORTH);
        groupsFrame.add(new JScrollPane(tree), BorderLayout.CENTER);
        groupsFrame.add(buttons, BorderLayout.SOUTH);
        groupsFrame.setVisible(true);
    }

    private static void editGroup(Group g,String operation){
        JFrame editFrame = new JFrame(operation + " " + g.name);
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
            if (!operation.equals("Редагування")) {
                if (!g.name.equals("")) {
                    root.add(new DefaultMutableTreeNode(g));
                    Shop.groupArray.add(g);
                    System.out.println("Group added to tree and group array: " + g);
                }
            }
            editFrame.setVisible(false);
            productTree.updateUI();
        });

        editFrame.add(inputs,BorderLayout.CENTER);
        editFrame.add(approve, BorderLayout.SOUTH);
        editFrame.setVisible(true);
    }

    static void setIcons(){
        try {
            groupIcon = new ImageIcon(MainMenu.tk.createImage(new URL("https://cdn-icons-png.flaticon.com/512/3712/3712174.png")).getScaledInstance(59, 50, Image.SCALE_SMOOTH));
            goodIcon = new ImageIcon(MainMenu.tk.createImage(new URL("https://cdn-icons-png.flaticon.com/512/962/962863.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        }catch (MalformedURLException ignored){}
    }
    private static class ProductGroupTree extends JPanel {

        public ProductGroupTree()  {
            root = new DefaultMutableTreeNode("АТБ");
            setNode();

            productTree = new JTree(root);
            productTree.setBackground(new Color(252, 233, 174));
            productTree.setForeground(Color.ORANGE);
            productTree.setCellRenderer(new CustomTreeCellRenderer());
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
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(group, true);
                for (Good good : Shop.goodsArray){
                    if (good.groupName.equals(group.name)){
                        DefaultMutableTreeNode goodNode = new DefaultMutableTreeNode(good,false);
                        node.add(goodNode);
                    }
                }
                root.add(node);
            }
        }

        static class CustomTreeCellRenderer extends DefaultTreeCellRenderer {


            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);

                if (value instanceof DefaultMutableTreeNode) {
                    Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
                    if (userObject instanceof Group) {
                        setIcon(groupIcon);
                    } else if (userObject instanceof Good) {
                        setIcon(goodIcon);
                    }
                }

                return this;
            }
        }
    }
}