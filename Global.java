package edit;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Global
{
    static List<FILE> open_files=new ArrayList<FILE>();
    static public FILE op_file=null;
    static public Clipboard clipBoard=Toolkit.getDefaultToolkit().getSystemClipboard();
    static HashMap<JButton,FILE> hashmap=new HashMap<JButton, FILE>();
    static public Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
    static public JFrame frame = new JFrame("Editor");
    //static public JTabbedPane tab=new JTabbedPane();
}
