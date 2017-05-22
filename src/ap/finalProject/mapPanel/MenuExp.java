package ap.finalProject.mapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuExp {
    private PlacingArrangement placer;
    private JMenuBar menuBar;
    public MenuExp() {



        menuBar = new JMenuBar();
        menuBar.setFont(new Font("Arial",Font.PLAIN,100));


        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);


        JMenuItem newAction = new JMenuItem("New");
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem exitAction = new JMenuItem("Exit");


        JMenuItem undoAction = new JMenuItem("Undo");
        JMenuItem redoAction = new JMenuItem("Redo");



        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);

        editMenu.add(undoAction);
        editMenu.add(redoAction);

        newAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //new
            }
        });

        openAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //open
            }
        });

        exitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //exit
            }
        });

        undoAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                placer.undo();
            }
        });

        redoAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                placer.redo();
            }
        });
    }

    public JMenuBar getMenubar()
    {
        return menuBar;
    }

    public void setplacer(PlacingArrangement placer){
        this.placer = placer;
    }
    public static void main(String[] args) {
        MenuExp me = new MenuExp();
////        me.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        me.setVisible(true);
    }
}
