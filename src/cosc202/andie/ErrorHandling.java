package cosc202.andie;

import javax.swing.*;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class ErrorHandling {
    
    public static void FileError(){multiErrorHandling (0);}
    public static void NoFileOpenError(){multiErrorHandling (1);}
    public static void NoUndoError(){multiErrorHandling (2);}
    public static void NoRedoError(){multiErrorHandling (3);}


    /**
     * Handles dealing with multiple errors simplfying the proccess of making a new error. 
     * (Reduces repeating code by looking at a txt file instead)
     * 
     * @param type
     */
    private static void multiErrorHandling (int type) {
        try {
            File file = new File("src/cosc202/andie/errorHandling.txt");
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
