package ap.finalProject.mapPanel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class JTreetmp extends JPanel {
    public JTreetmp() {
        initializeUI();
    }
    public Creator creator;
    public void setcreator(Creator creator){
        this.creator = creator;
    }


    private void initializeUI() {
        setSize(200, 400);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        DefaultMutableTreeNode resources = new DefaultMutableTreeNode("Resources");
        DefaultMutableTreeNode trees = new DefaultMutableTreeNode("tree");
        DefaultMutableTreeNode deep = new DefaultMutableTreeNode("deep fish");
        DefaultMutableTreeNode shallow = new DefaultMutableTreeNode("shallow fish");
        DefaultMutableTreeNode goldmine = new DefaultMutableTreeNode("goldmine");
        DefaultMutableTreeNode ironquery = new DefaultMutableTreeNode("ironquery");

        resources.add(trees);
        resources.add(deep);
        resources.add(shallow);
        resources.add(goldmine);
        resources.add(ironquery);

        DefaultMutableTreeNode terrains = new DefaultMutableTreeNode("Terrains");

        DefaultMutableTreeNode lawn = new DefaultMutableTreeNode("lawn");
        DefaultMutableTreeNode deept = new DefaultMutableTreeNode("deep water");
        DefaultMutableTreeNode shallowt = new DefaultMutableTreeNode("shallow water");
        DefaultMutableTreeNode beach = new DefaultMutableTreeNode("beach");
        DefaultMutableTreeNode mountain = new DefaultMutableTreeNode("mountain");
        DefaultMutableTreeNode peak = new DefaultMutableTreeNode("peak");

        terrains.add(lawn);
        terrains.add(deept);
        terrains.add(shallowt);
        terrains.add(beach);
        terrains.add(mountain);
        terrains.add(peak);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        root.add(resources);
        root.add(terrains);


        ImageIcon imageIcon = new ImageIcon("icon.png");
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(imageIcon);


        JTree tree = new JTree(root);
        this.add(tree);
        tree.setCellRenderer(renderer);
//        JScrollPane pane = new JScrollPane(tree);
//        pane.setPreferredSize(new Dimension(150, 300));
//        getContentPane().add(pane);

        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                System.out.println(selectedNode.getUserObject().toString());
                //in .tostringe masalan hala age == kaj bood kajo bokon markeret
                if(selectedNode.getParent().toString().equals("Resources"))
                {
                    if(selectedNode.getUserObject().toString().equals("tree"))
                    {
                        creator.select('3');
                    }else if(selectedNode.getUserObject().toString().equals("deep fish")){
                        creator.select('5');
                    }else if(selectedNode.getUserObject().toString().equals("shallow fish")){
                        creator.select('4');
                    }else if(selectedNode.getUserObject().toString().equals("goldmine")){
                        creator.select('1');
                    }else if(selectedNode.getUserObject().toString().equals("ironquery")){
                        creator.select('2');
                    }
                }else if(selectedNode.getParent().toString().equals("Terrains"))
                {
                    if(selectedNode.getUserObject().toString().equals("shallow water"))
                    {
                        creator.select('s');
                    }else if(selectedNode.getUserObject().toString().equals("deep water"))
                    {
                        creator.select('d');
                    }else if(selectedNode.getUserObject().toString().equals("lawn"))
                    {
                        creator.select('l');
                    }else if(selectedNode.getUserObject().toString().equals("beach")){
                        creator.select('b');
                    }else if(selectedNode.getUserObject().toString().equals("mountain")){
                        creator.select('m');
                    }else if(selectedNode.getUserObject().toString().equals("peak")){
                        creator.select('p');
                    }
                }
            }
        });
        tree.setRootVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JTreetmp().setVisible(true));
    }
}
