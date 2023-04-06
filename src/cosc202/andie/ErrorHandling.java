package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ErrorHandling {
    
    public static void FileError(){
        JOptionPane.showMessageDialog(null,
        SettingsActions.bundle.getString("UnsupportedFileMessage"),
        SettingsActions.bundle.getString("UnsupportedFileTitle"),
        JOptionPane.ERROR_MESSAGE); 
    }

    public static void NoFileOpenError(){
        JOptionPane.showMessageDialog(null,
        SettingsActions.bundle.getString("NoCurrentImageMessage"),
        SettingsActions.bundle.getString("PleaseOpenImageTitle"),
        JOptionPane.ERROR_MESSAGE);
    }

    public static void NoUndoError(){
        JOptionPane.showMessageDialog(null,
        SettingsActions.bundle.getString("NoUndoMessage"),
        SettingsActions.bundle.getString("OpsStackEmptyTitle"),
        JOptionPane.ERROR_MESSAGE);
    }

    public static void NoRedoError(){
        JOptionPane.showMessageDialog(null,
        SettingsActions.bundle.getString("NoRedoMessage"),
        SettingsActions.bundle.getString("OpsStackEmptyTitle"),
        JOptionPane.ERROR_MESSAGE);
    }

    public static void PleaseSaveBeforeExit(){
        //Custom button text
        Object[] options = {"Yes",
        "No",
        "Cancel"};
        int n = JOptionPane.showOptionDialog(null,
        SettingsActions.bundle.getString("SaveBeforeExitMessage"),
        SettingsActions.bundle.getString("SaveBeforeExitTitle"),
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[2]);
    }
}
