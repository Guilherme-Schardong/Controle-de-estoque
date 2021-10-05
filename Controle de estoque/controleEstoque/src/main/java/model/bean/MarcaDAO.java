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
public class MarcaDAO {
    
    public List<Marca> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List <Marca> marcas = new ArrayList<> ();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM marca");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Marca marca = new Marca();
                
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setDescricaoMarca(rs.getNString("descricaoMarca"));
                marcas.add(marca);
            }
        }catch(SQLException ex){
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return marcas;
    }
}
