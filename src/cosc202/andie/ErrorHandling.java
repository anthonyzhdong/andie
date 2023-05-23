package cosc202.andie;

import javax.swing.*;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class ErrorHandling {
    
    /** Creates error message for: Unsupported File Types */
    public static void FileError(){multiErrorHandling (0);}
    /** Creates error message for: No Current Image Being Open */
    public static void NoFileOpenError(){multiErrorHandling (1);}
    /** Creates error message for: Undo Stack Being Empty */
    public static void NoUndoError(){multiErrorHandling (2);}
    /** Creates error message for: Redo Stack Being Empty */
    public static void NoRedoError(){multiErrorHandling (3);}


    /**
     * <p>
     * Handles dealing with multiple errors simplfying the proccess of making a new error. 
     * (Reduces repeating code by looking at a txt file instead)
     * </p>
     * @param type
     */
    private static void multiErrorHandling (int type) {
        try {
            File file = new File("cosc202/andie/errorHandling.txt");
            Scanner sc = new Scanner(file);
            String info = "";
            
            
            for (int x = 0; x <= type; x++) {
                info = sc.nextLine();
            }
            String[] data = info.replaceAll(" ", "").split(",");

            
            JOptionPane.showMessageDialog(null,
            SettingsActions.bundle.getString(data[0]),
            SettingsActions.bundle.getString(data[1]),
            JOptionPane.ERROR_MESSAGE); 

            sc.close();
          } catch (FileNotFoundException e) {
            System.out.println("An unknown error occurred.");
            e.printStackTrace();
          }
    }


    /**
     * <p>
     * Creates a pop up window asking the user if they want to save before exiting.
     * </p>
     */
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
