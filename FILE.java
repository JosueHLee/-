package edit;

import java.io.File;
import javax.swing.*;

public class FILE
{
    private File file;
    private String file_name;
    private String file_path;
    private boolean saved=false;
    public JScrollPane field;

    public File get_file( )
    {
        return file;
    }
    public String get_file_name()
    {
        return file_name;
    }
    public String get_file_path()
    {
        return file_path;
    }
    public boolean get_saved(){ return saved;}

    public void change_file(File f)
    {
        this.file=f;
    }
    public void change_path(String s)
    {
        this.file_path=s;
    }
    public void change_name(String s)
    {
        this.file_name=s;
    }
    public void  save()
    {
        saved=true;
    }
    public JTextArea get_field()
    {
        return (JTextArea)this.field.getViewport().getComponent(0);
    }
}
