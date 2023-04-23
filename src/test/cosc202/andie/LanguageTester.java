package test.cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cosc202.andie.*;
import java.util.*;
import javax.swing.*;



public class LanguageTester {

    @Test
    void testDefaultLanguage() {
        SettingsActions sl = new SettingsActions();  
        Assertions.assertEquals(sl.bundle.getBaseBundleName(), "cosc202.andie.MessageBundle_en_NZ");
    }

    @Test
    void testEventListeners() {
        SettingsActions sl = new SettingsActions(); 
        ArrayList<Action> actions = sl.getActions();
        int count = 0;
        String[] bundleNames = {"cosc202.andie.MessageBundle_en_NZ", "cosc202.andie.MessageBundle_fr_FR", "cosc202.andie.MessageBundle_mi_NZ", "cosc202.andie.MessageBundle_sp_SP"};
        for(Action action : actions) {
            sl.activateAction(action);
            Assertions.assertEquals(sl.bundle.getBaseBundleName(), bundleNames[count]);
            count++;
        }
    }

     
     
}
