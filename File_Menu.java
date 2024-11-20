import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class File_Menu
{
    public static JMenu initial()
    {
        JMenu file_menu=new JMenu("文件");
        file_menu.add(initial_create());
        return file_menu;
    }
    static JMenuItem initial_create()
    {
        JMenuItem create=new JMenuItem("创建");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField get_name=new JTextField("请输入文件名：");
                File_op.current_file_name=get_name.getText();
                Global.frame.add(get_name, BorderLayout.CENTER);
                Global.frame.revalidate();
                Global.frame.repaint();
                //文本编辑界面
                Global.frame.getContentPane().removeAll();
                JTextArea editArea1=new JTextArea();//添加一个文本编辑界面
                File_op.current_area=editArea1;
                editArea1.setLineWrap(true);
                editArea1.setWrapStyleWord(true);
                JScrollPane scrollPane=new JScrollPane(editArea1);//添加一个滚动条
                Global.frame.add(scrollPane,BorderLayout.CENTER);
                Global.frame.revalidate();
                Global.frame.repaint();
            }
        });
        return create;
    }
}
