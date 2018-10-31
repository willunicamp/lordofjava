/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.control.Alert;

/**
 *
 * @author will
 */
public class Utils {
    public static void print(String s){
        System.out.println(s);
    }
    
    public static void alert(String s){
        alert(s,Alert.AlertType.INFORMATION);
    }
    
    public static void alert(String s, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Lord of Java");
        alert.setHeaderText("Informativo");
        alert.setContentText(s);
        alert.showAndWait();
    }
}
