import javax.swing.*;

public class GUI
{
    //static public JFrame frame = new JFrame("Editor");
    public static void create_GUI()
    {
        initial_GUI();
        initial_Menu();
    }
    static void initial_GUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);

        Global.frame.setSize(800,500);
        Global.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Global.frame.setVisible(true);
    }
    static void initial_Menu()
    {
        JMenuBar Menu=new JMenuBar();
        //李要实现的：
        Menu.add(File_Menu.initial());
        
        //Menu.add(JMenu object);
        //袁要实现的：
        Menu.add(Edit_Menu.initial());

        //马要实现的：
        Menu.add(Settings_Menu.initial());
    }
}
