/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class HistoricoDAO {
    
    public void create (Historico h){
        try{
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
            stmt = con.prepareStatement("INSERT INTO historico (codigoProduto,quantidade,operacao) VALUES(?,?,?)");
            
            stmt.setInt(1,h.getCodigoProduto());
            stmt.setInt(2,h.getQuantidade());
            stmt.setString(3,h.getOperacao());
            stmt.execute();
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
