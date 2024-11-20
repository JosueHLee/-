import GUI.GUI;
public class Main {
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
           public void run()
           {
               GUI.createAndShowGUI();
           }
        });
        //System.out.println("Hello world!");
    }
}