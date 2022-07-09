/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.dal;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonatha
 */
public class ModuloConexao {
    
    public static Connection Conector() {
		Connection conexao;

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/os?characterEncoding=utf-8";
		String user = "admin";
		String password = "ZRPGNwaxIF)JtdUL";
                
		
		
		try {
			
                    
                        Class.forName(driver);
                    	conexao = DriverManager.getConnection(url, user, password);
			
			return conexao;
			
		} catch (Exception erro) {
                   
                    JOptionPane.showMessageDialog(null, erro);
		   return null;
		}
		
    }	
	
}