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
public class SaldoDAO {
    
    public List<Saldo> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Saldo>saldos = new ArrayList <> ();
        try{
          /*  stmt = con.prepareStatement("SELECT A.descricaoproduto,"+
                               " B.codigoproduto, "+
                    " (SELECT Sum(quantidade)"+
                    " FROM   historico"+
                    "WHERE  operacao = \"entrada\" AND codigoproduto=B.codigoProduto)AS entrada,"+
                    "(SELECT Sum(quantidade)"+
                    "FROM   historico"+
                    "WHERE  operacao = \"saida\" AND codigoproduto= B.codigoProduto)  AS saida"+
                    "FROM   produto A,"+
                    "historico B"+
            /*        "WHERE  A.codigoproduto = B.codigoproduto GROUP BY descricaoProduto")*/
          stmt = con.prepareStatement("SELECT A.descricaoproduto,\n" +
"       B.codigoproduto,\n" +
"       (SELECT Sum(quantidade)\n" +
"        FROM   historico\n" +
"        WHERE  operacao = \"entrada\" AND codigoproduto=B.codigoProduto)AS entrada,\n" +
"       (SELECT Sum(quantidade)\n" +
"        FROM   historico\n" +
"        WHERE  operacao = \"saida\" AND codigoproduto= B.codigoProduto)  AS saida\n" +
"FROM   produto A,\n" +
"       historico B\n" +
"WHERE  A.codigoproduto = B.codigoproduto GROUP BY descricaoProduto");
                    rs = stmt.executeQuery();
              while(rs.next()){
                  Saldo saldo = new Saldo();
                  saldo.setDescricaoProduto(rs.getString("descricaoProduto"));
                  saldo.setCodigoProduto(rs.getInt("codigoProduto"));
                  saldo.setEntrada(rs.getInt("entrada"));
                  saldo.setSaida(rs.getInt("saida"));
                  saldos.add(saldo);
                }
        }catch(SQLException ex){
            Logger.getLogger(SaldoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return saldos;
        
    }
    
    
    
    
    
    
    public List<Saldo> readExpec(int valor1){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Saldo>saldos = new ArrayList <> ();
        
        try{
             stmt = con.prepareStatement("SELECT A.descricaoproduto,"+
                     "B.codigoproduto,"+
                     "(SELECT Sum(quantidade)"+
                     " FROM   historico"+
                     " WHERE  operacao = \"entrada\""+
                     " AND codigoproduto = B.codigoproduto) AS entrada,"+
                     "(SELECT Sum(quantidade)"+
                     "FROM   historico"+
                     " WHERE  operacao = \"saida\""+
                     " AND codigoproduto = B.codigoproduto) AS saida"+
                     " FROM   produto A,"+
                     "historico B"+
                     " WHERE  A.codigoproduto = B.codigoproduto"+
                     " AND B.codigoproduto = "+valor1+
                     " GROUP  BY descricaoproduto");
             rs = stmt.executeQuery();
             
            while(rs.next()){
                 Saldo saldo = new Saldo();
                  saldo.setDescricaoProduto(rs.getString("descricaoProduto"));
                  saldo.setCodigoProduto(rs.getInt("codigoProduto"));
                  saldo.setEntrada(rs.getInt("entrada"));
                  saldo.setSaida(rs.getInt("saida"));
                  saldos.add(saldo);
            }
        }catch(SQLException ex){
            Logger.getLogger(SaldoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return saldos;
    }
    
    
    public List<Saldo> readS(int valor1){
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Saldo>saldos = new ArrayList <> ();
        
        try{
             stmt = con.prepareStatement("SELECT A.descricaoproduto,"+
                     " B.codigoproduto,"+
                     "(SELECT Sum(quantidade)"+
                     " FROM   historico"+
                     " WHERE  operacao = \"entrada\""+
                     " AND codigoproduto = B.codigoproduto) AS entrada"+
                     " FROM   produto A,"+
                     "historico B"+
                     " WHERE  A.codigoproduto = B.codigoproduto"+
                     " AND B.codigoproduto = '"+valor1+
                     "' GROUP  BY descricaoproduto ");
             rs = stmt.executeQuery();
             
            while(rs.next()){
                 Saldo saldo = new Saldo();
                  saldo.setDescricaoProduto(rs.getString("descricaoProduto"));
                  saldo.setCodigoProduto(rs.getInt("codigoProduto"));
                  saldo.setEntrada(rs.getInt("entrada"));
                  saldos.add(saldo);
            }
        }catch(SQLException ex){
            Logger.getLogger(SaldoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return saldos;
    }
}
