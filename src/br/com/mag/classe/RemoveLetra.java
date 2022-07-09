/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.classe;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Administrador
 */
public class RemoveLetra extends PlainDocument{
    
     private int tamanhoMax = 12;
         
    public RemoveLetra(int tamanhoMax){
        this.tamanhoMax = tamanhoMax;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
         if (str == null) return;  
                    
             String stringAntiga = getText (0, getLength() );  
             int tamanhoNovo = stringAntiga.length() + str.length(); 
                        
             if (tamanhoNovo <= tamanhoMax) {  
                 super.insertString(offset, str.replaceAll("[^0-9]",""),  attr);  
             } else {    
                 super.insertString(offset, "", attr); 
             }  
        
        
        
        
        
        
         
    }
    
    
}
