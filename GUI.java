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
        //JMenu file_menu=File_Menu.initial();
        Menu.add(File_Menu.initial());
    }
}
