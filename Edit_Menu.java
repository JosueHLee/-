package edit;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Edit_Menu
{
    public static JMenu initial()
    {
    	JMenu edit_menu=new JMenu("编辑");
    	JMenuItem cut=initial_cut();
    	add_key(cut,KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
    	JMenuItem copy=initial_copy();
    	add_key(copy,KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
    	JMenuItem paste=initial_paste();
    	add_key(paste,KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
    	JMenuItem delete=initial_delete();
    	add_key(delete,KeyStroke.getKeyStroke(KeyEvent.VK_B,KeyEvent.CTRL_DOWN_MASK));
    	JMenuItem selectall=initial_selectall();
    	add_key(delete,KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));

    	edit_menu.add(cut);
    	edit_menu.add(copy);
    	edit_menu.add(paste);
    	edit_menu.add(delete);
    	edit_menu.add(selectall);
    	
    	return edit_menu;
    }

    static JMenuItem initial_cut()
    {
    	JMenuItem cut=new JMenuItem("剪切");
    	cut.addActionListener(e->
    	{
    		try
        	{
        		Global.op_file.get_field().requestFocus();
        		String text=Global.op_file.get_field().getSelectedText();
        		StringSelection selection=new StringSelection(text);
        		Global.clipBoard.setContents(selection,null);
        		Global.op_file.get_field().replaceRange("",Global.op_file.get_field().getSelectionStart(),Global.op_file.get_field().getSelectionEnd());
        	}catch(NullPointerException e1){	
        	}
    	}	
    	);
    	return cut;
    }
    static JMenuItem initial_copy()
    {
    	JMenuItem copy=new JMenuItem("复制");
    	copy.addActionListener(e->
    	{
    		try
        	{
        		Global.op_file.get_field().requestFocus();
        		String text=Global.op_file.get_field().getSelectedText();
        		StringSelection selection=new StringSelection(text);
        		Global.clipBoard.setContents(selection,null);
        	}catch(NullPointerException e1) {
        	}
    	});
    	return copy;
    }
    static JMenuItem initial_paste()
    {
    	JMenuItem paste=new JMenuItem("粘贴");
    	paste.addActionListener(e->
    	{
    		try
        	{
        		Global.op_file.get_field().requestFocus();
        		Transferable contents=Global.clipBoard.getContents(null);
        		if(contents==null)
        			return;
        		String text="";
        		try
        		{
        			text=(String)contents.getTransferData(DataFlavor.stringFlavor);
        		}catch(Exception e1) {
        		}
        		Global.op_file.get_field().replaceRange(text,Global.op_file.get_field().getSelectionStart(),Global.op_file.get_field().getSelectionEnd());
        	}catch(NullPointerException e1) {
        	}	
        });
    	
    	return paste;
    }
    static JMenuItem initial_delete()
    {
    	JMenuItem delete=new JMenuItem("删除");
    	delete.addActionListener(e->
    	{
    		try
    	{
    		Global.op_file.get_field().requestFocus();
        	Global.op_file.get_field().replaceRange("",Global.op_file.get_field().getSelectionStart(),Global.op_file.get_field().getSelectionEnd());
    	}catch (NullPointerException e1){
    	}
    	});
    	
    	return delete;
    }
    static JMenuItem initial_selectall()
    {
    	JMenuItem selectall=new JMenuItem("全选");
    	selectall.addActionListener(e->
    	{
    		try
    	{
    		Global.op_file.get_field().selectAll();
    	}catch(NullPointerException e1){
    	}
    	});
    	
    	return selectall;
    }
    static void add_key(JMenuItem cpn,KeyStroke key)
    {
    	cpn.setAccelerator(key);
    }
}
