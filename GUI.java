package edit;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLUE;

public class GUI
{
    static public JTabbedPane tab=new JTabbedPane();
    public static void create_GUI()
    {
        initial_GUI();
        initial_Menu();
        initial_label();
        Global.frame.revalidate();
        Global.frame.repaint();
    }
    static void initial_GUI()
    {
        int w=(int)Global.screensize.getWidth(),h=(int)Global.screensize.getHeight();
        Global.frame.setSize(w,h);
        Global.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Global.frame.setLayout(new BorderLayout());
        Global.frame.setBackground(BLUE);
        Global.frame.setVisible(true);

    }
    static void initial_Menu()
    {
        JMenuBar Menu=new JMenuBar();
        Menu.add(File_Menu.initial());
        Menu.add(Edit_Menu.initial());
        //添加各个菜单项
        Global.frame.setJMenuBar(Menu);
    }
    static void initial_label()
    {
        //Global.tab.setLayout(new BorderLayout());
        Global.frame.add(tab,BorderLayout.CENTER);
    }
    static void exchange_page()
    {
        tab.setSelectedComponent(Global.op_file.field);
    }
    static void add_page()
    {

        tab.add(Global.op_file.field,Global.op_file.get_file_name());
        JPanel panel=new JPanel();
        JLabel name=new JLabel(Global.op_file.get_file_name());
        JButton close=new JButton("X");
        Global.hashmap.put(close,Global.op_file);
        //close.setName(Global.op_file.get_file_name());
        close.addActionListener(e->
    {
        try
        {
            FILE temp=Global.hashmap.get(close);
            Global.open_files.remove(temp);
            tab.remove(temp.field);
            Global.hashmap.remove(close);
            if(Global.open_files.isEmpty())
            {
                Global.op_file=null;
            }
            else
            {
                Global.op_file=Global.open_files.getLast();
            }
            if(Global.op_file!=null)
                GUI.exchange_page();
        }
        catch(NullPointerException e1)
        {
            System.out.println(e1.toString());
            JOptionPane.showMessageDialog(null,"当前无可关闭的文件","Error",JOptionPane.ERROR_MESSAGE);
        }
    });
        panel.add(name);
        panel.add(close);
        try {
            tab.addChangeListener(e ->
            {
                if(tab.getTabCount()!=0)
                    Global.op_file = Global.open_files.get(tab.getSelectedIndex());
                else
                    Global.op_file=null;
            });
        }
        catch(IndexOutOfBoundsException e1)
        {
            System.out.println(e1.toString());
        }
        tab.setTabComponentAt(tab.indexOfComponent(Global.op_file.field),panel);
        tab.setSelectedComponent(Global.op_file.field);
    }
}
