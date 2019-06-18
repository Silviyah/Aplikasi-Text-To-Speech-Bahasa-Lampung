/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author USER
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LampungText {
     // Menyiapkan paramter JDBC untuk koneksi ke datbase
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/text_to_speech";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    private String text = "";
   // private String diphone;
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        try {
        // register driver
        Class.forName(JDBC_DRIVER);

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        
        // ambil input dari user
        String kata = text;
        
        // proses text to diphone
       IntStream diphone = kata.chars();
        
        // query simpan
        for (int i = 0; i < kata.length(); i++) {
            if(i==0){
             //    System.out.print(Stream.of(kata) .map(w -> w.split("")) .flatMap(Arrays::stream) .distinct() .collect(Collectors.toList()));
             System.out.print("_/"+kata.charAt(i)+"//"+kata.charAt(i)+"/"+kata.charAt(i+1)+"//"); 
             
            }else if(i==kata.length()-1){
                System.out.print(kata.charAt(i)+"/_");
            }else{
                System.out.print(kata.charAt(i)+"/"+kata.charAt(i+1)+"//");
                break;
            }
        } 
        String sql = "INSERT INTO text (kata, diphone) VALUE('%s', '%s')";
        sql = String.format(sql, kata, kata); 
     
        
        // simpan kata
        stmt.execute(sql);
        
        
    } catch (Exception e) {
        e.printStackTrace();
        
    }  
    }
  /*static void make_diphone(String line){
        for (int i = 0; i < line.length(); i++) {
            if(i==0){
                System.out.print("_/"+line.charAt(i)+"//"+line.charAt(i)+"/"+line.charAt(i+1)+"//");
            }else if(i==line.length()-1){
                System.out.print(line.charAt(i)+"/_");
            }else{
                System.out.print(line.charAt(i)+"/"+line.charAt(i+1)+"//");
            }
        }
    }*/

    
    public void inputKata() {
     try {
        // register driver
        Class.forName(JDBC_DRIVER);

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        
        // ambil input dari user
         //System.out.print("kata: ");
        //String kata = input.readLine().trim();
        LampungText x = new LampungText();
        String kata = x.text;
        //String kata = "coba";
        //System.out.print("diphone: ");
        IntStream diphone = kata.chars();
              
        // query simpan
        for (int i = 0; i < kata.length(); i++) {
            if(i==0){
             //    System.out.print(Stream.of(kata) .map(w -> w.split("")) .flatMap(Arrays::stream) .distinct() .collect(Collectors.toList()));
             System.out.print("_/"+kata.charAt(i)+"//"+kata.charAt(i)+"/"+kata.charAt(i+1)+"//"); 
             
            }else if(i==kata.length()-1){
                System.out.print(kata.charAt(i)+"/_");
            }else{
                System.out.print(kata.charAt(i)+"/"+kata.charAt(i+1)+"//");
                break;
            }
        }
        String sql = "INSERT INTO text (kata, diphone) VALUE('%s', '%s')";
        sql = String.format(sql, kata, diphone);
     
        // simpan kata
        stmt.execute(sql);
        
        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }   
   }
}
  