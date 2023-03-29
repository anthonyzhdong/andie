package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ErrorHandling {
    
    public static void OpenError(){
        JOptionPane.showMessageDialog(null,
        "File Unsupported by ANDIE, please open image files only",
        "Unsupported File Format",
        JOptionPane.ERROR_MESSAGE); 
    }
}
