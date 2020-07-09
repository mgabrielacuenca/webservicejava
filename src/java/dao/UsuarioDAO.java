/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controle.Conexao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author Gabriela
 */
public class UsuarioDAO {
    
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<Usuario> getUsuarios() throws ClassNotFoundException, SQLException{
        
       List<Usuario> usuarios = new ArrayList<>(); 
       Usuario u = null;
       sql = "SELECT * FROM usuario";
       con = Conexao.cb();
       pst = con.prepareStatement(sql);
       rs = pst.executeQuery();
       while(rs.next()){
           u = new Usuario();
           u.setId(rs.getInt("id"));
           u.setNome(rs.getString("nome"));
           u.setSenha(rs.getString("senha"));
           
           usuarios.add(u);
       }
       Conexao.db();
       return usuarios;
    }
    
    public boolean inserirUsuario(Usuario u){
        try {
            sql = "INSERT INTO usuario (nome,senha) VALUES (?,?)";
            con = Conexao.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getSenha());
            
            pst.execute();
            
            Conexao.db();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
       }
    
    public boolean deletarUsuario(int id){
        try {
            sql = "DELETE FROM usuario WHERE id = ?";
            con = Conexao.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            
            pst.execute();
            
            Conexao.db();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
       public boolean atualizarUsuario(Usuario u){
        try {
            sql = "UPDATE usuario SET nome = ?, senha = ? WHERE id = ?;";
            con = Conexao.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getSenha());
            pst.setInt(3, u.getId());
            
            pst.executeUpdate();
            
            Conexao.db();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
       }
}
