package com.mycompany.gestaodeprojetosv2;

public class Main {

    public static void main(String[] args) {
        // Usa uma thread para garantir que a interface seja iniciada de forma segura
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }
}