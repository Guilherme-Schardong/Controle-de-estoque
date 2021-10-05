/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO {
    
    public void create (Produto p){
      try{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        stmt = con.prepareStatement("INSERT INTO produto (codigoProduto,descricaoProduto,precoUnitProduto,idCor,idMarca) VALUES(?,?,?,?,?)");
        
        stmt.setInt(1,p.getCodigoProduto());
        stmt.setString(2,p.getDescricaoProduto());
        stmt.setDouble(3,p.getPrecoUnitProduto());
        stmt.setInt(4,p.getIdCor());
        stmt.setInt(5,p.getIdMarca());
        stmt.execute();
        }catch(SQLException ex){
        Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public List<Produto> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList <>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setCodigoProduto(rs.getInt("codigoProduto"));
                produto.setDescricaoProduto(rs.getString("descricaoProduto"));
                produtos.add(produto);
            }
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
       return produtos; 
    }
    
}

    
    

