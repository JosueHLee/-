package edit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class File_Menu
{
    public static JMenu initial()
    {
        JMenu file_menu=new JMenu("文件");
        JMenuItem create=initial_create();
        add_key(create,KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        JMenuItem open=initial_open();
        add_key(open,KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        JMenuItem save=initial_save();
        add_key(save,KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        JMenuItem save_a=initial_save_another();
        add_key(save_a,KeyStroke.getKeyStroke(KeyEvent.VK_M,KeyEvent.CTRL_DOWN_MASK));
        JMenuItem close=initial_close();
        add_key(close,KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));

        //
        file_menu.add(create);
        file_menu.add(open);
        file_menu.add(save);
        file_menu.add(save_a);
        file_menu.add(close);
        //

        return file_menu;
    }
    static JMenuItem initial_create()
    {
        JMenuItem create=new JMenuItem("创建");
        create.addActionListener(e->{
            JTextArea editArea1=new JTextArea();//添加一个文本编辑界面
            editArea1.setLineWrap(true);
            editArea1.setWrapStyleWord(true);
            JScrollPane scrollPane=new JScrollPane(editArea1);//添加一个滚动条
            Global.frame.revalidate();
            Global.frame.repaint();
            Global.op_file=new FILE();
            Global.op_file.change_name("Undifined");
            Global.op_file.field=scrollPane;

            Global.open_files.add(Global.op_file);
            GUI.add_page();
        });
        return create;
    }
    static JMenuItem initial_save()
    {
        JMenuItem save=new JMenuItem("保存");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                if(Global.op_file.get_file()==null)
                {
                    FileDialog savedia=new FileDialog(Global.frame,"保存",FileDialog.SAVE);
                    //savedia.setDirectory("D:\\");
                    //savedia.setFile("text.txt");
                    savedia.setVisible(true);
                    Global.op_file.change_path(savedia.getDirectory());
                    Global.op_file.change_name(savedia.getFile());
                    Global.op_file.change_file(new File(savedia.getDirectory(),savedia.getFile()));
                }
                    BufferedWriter bfw=new BufferedWriter(new FileWriter(Global.op_file.get_file_name()));
                    String Text=Global.op_file.get_field().getText();
                    System.out.println(Text);
                    bfw.write(Text);
                    bfw.close();
                    JOptionPane.showMessageDialog(null,"保存成功！");
                }
                catch(NullPointerException e1)
                {
                    JOptionPane.showMessageDialog(null,"请选择或创建一个文件进行操作!","Hint",JOptionPane.WARNING_MESSAGE);
                }
                catch(IOException e2)
                {
                    JOptionPane.showMessageDialog(null,"保存文件失败","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        return save;
    }
    static JMenuItem initial_open()
    {
        JMenuItem open =new JMenuItem("打开");
        open.addActionListener(e->{
            FileDialog opendia=new FileDialog(Global.frame,"打开",FileDialog.LOAD);
            //opendia.setDirectory("D:\\");
            opendia.setVisible(true);
            try
            {
                Global.op_file=new FILE();
                Global.op_file.change_file(new File(opendia.getDirectory(),opendia.getFile()));
                Global.op_file.change_name(opendia.getFile());
                Global.op_file.change_path(opendia.getDirectory());
                //
                JTextArea editArea1=new JTextArea();//添加一个文本编辑界面


                editArea1.setLineWrap(true);
                editArea1.setWrapStyleWord(true);
                //添加一个滚动条
                Global.op_file.field= new JScrollPane(editArea1);
                //
                BufferedReader bfr=new BufferedReader(new FileReader(Global.op_file.get_file()));
                String s;
                while((s= bfr.readLine())!=null)
                {
                    Global.op_file.get_field().append(s);
                }
                bfr.close();
                Global.frame.revalidate();
                Global.frame.repaint();
                //JOptionPane.showMessageDialog(null,"打开成功");
                Global.open_files.add(Global.op_file);
                GUI.add_page();
            }
            catch(NullPointerException e1)
            {
                JOptionPane.showMessageDialog(null,"请选择一个文件！","Hint",JOptionPane.WARNING_MESSAGE);
            }
            catch(FileNotFoundException e1)
            {
                JOptionPane.showMessageDialog(null,"未找到文件！","Error",JOptionPane.ERROR_MESSAGE);
            }
            catch(IOException e1)
            {
                JOptionPane.showMessageDialog(null,"写入文件失败！","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        return open;
    }
    static JMenuItem initial_save_another()
    {
        JMenuItem save_a=new JMenuItem("另存为");
        save_a.addActionListener(e->{
            try
            {
                FileDialog savedia=new FileDialog(Global.frame,"另存为",FileDialog.SAVE);
                savedia.setFile(Global.op_file.get_file_name());
                savedia.setDirectory(Global.op_file.get_file_path());
                savedia.setVisible(true);
                //
                BufferedWriter bfw=new BufferedWriter(new FileWriter(savedia.getFile()));
                String Text=Global.op_file.get_field().getText();
                bfw.write(Text);
                bfw.close();
                Global.op_file.change_file(new File(savedia.getDirectory(),savedia.getFile()));
                Global.op_file.change_path(savedia.getDirectory());
                Global.op_file.change_name(savedia.getFile());
                JOptionPane.showMessageDialog(null,"保存成功！");
            }
            catch(NullPointerException e1)
            {
                JOptionPane.showMessageDialog(null,"未打开文件！","Error",JOptionPane.ERROR_MESSAGE);
            }
            catch(IOException e1)
            {
                JOptionPane.showMessageDialog(null,"保存文件失败","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        return save_a;
    }
    static JMenuItem initial_close()
    {
        JMenuItem close=new JMenuItem("关闭");
        close.addActionListener(e->
        {
            try
            {
                Global.open_files.remove(Global.op_file);
                GUI.tab.remove(Global.op_file.field);
                if(Global.open_files.isEmpty())
                {
                    Global.op_file=null;
                }
                else
                    Global.op_file=Global.open_files.getLast();
                if(Global.op_file!=null)
                    GUI.exchange_page();
            }
            catch(NullPointerException e1)
            {
                System.out.println(e1.toString());
                JOptionPane.showMessageDialog(null,"当前无可关闭的文件","Error",JOptionPane.ERROR_MESSAGE);
            }

        });
        return close;
    }
    static  void add_key(JMenuItem cpn,KeyStroke key)
    {
        cpn.setAccelerator(key);
    }
}
