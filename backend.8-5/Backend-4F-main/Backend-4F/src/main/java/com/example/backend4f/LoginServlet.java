package com.example.backend4f;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class LoginServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String Utente = "";
        int flag = 0;
        String user = "";
        String password = "";
        String username = (request.getParameter("username"));
        String pass = (request.getParameter("pass"));
        BufferedReader br = new BufferedReader(new FileReader("login.txt"));
        String riga = br.readLine();
        boolean errore = false;
        while(riga != null){
            flag = 0;
            user = "";
            password = "";

            for(int i=0; i< riga.length(); i++){
                if( riga.charAt(i) != ' '){
                    if (flag == 0){
                        user = user + riga.charAt(i);
                    }else{
                        password = password + riga.charAt(i);
                    }
                }else{
                    flag = 1;
                }
            }

            if(user.equals(username) && (password.equals(pass))){
                errore = false;
                utente = username;
            }
        }

        if (errore == false){
            HttpSession session = request.getSession(true);
            session.setAttribute("messaggio",utente);
            response.sendRedirect("/voti");
        }else{
            HttpSession session = request.getSession(true);
            String messaggio = "Username o Password non corrette";
            session.setAttribute("error",messaggio);
            response.sendRedirect("/Errore");
        }
    }

    public void destroy() {
    }
}