package com.mycompany.gestaodeprojetosv2;

import com.mycompany.gestaodeprojetosv2.ProjetoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProjectDialog extends JFrame {

    private JTextField nomeTextField;
    private JTextArea descricaoTextArea;
    private JButton salvarButton;
    private JButton cancelarButton;

    public ProjectDialog(MainScreen mainScreen) {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Inicialização dos componentes
        nomeTextField = new JTextField();
        descricaoTextArea = new JTextArea();
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        // Configuração do layout
        JPanel panel = new JPanel(null);
        JLabel nomeLabel = new JLabel("Nome do Projeto:");
        JLabel descricaoLabel = new JLabel("Descrição:");

        nomeLabel.setBounds(20, 20, 120, 25);
        nomeTextField.setBounds(150, 20, 200, 25);

        descricaoLabel.setBounds(20, 60, 120, 25);
        descricaoTextArea.setBounds(150, 60, 200, 100);

        salvarButton.setBounds(20, 180, 100, 30);
        cancelarButton.setBounds(150, 180, 100, 30);

        panel.add(nomeLabel);
        panel.add(nomeTextField);
        panel.add(descricaoLabel);
        panel.add(descricaoTextArea);
        panel.add(salvarButton);
        panel.add(cancelarButton);

        add(panel);

        // Configurações da janela
        setTitle("Novo Projeto");
        setSize(400, 250);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Ação do botão Salvar
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para salvar o projeto
                Projeto projeto = new Projeto();
                projeto.setNome(nomeTextField.getText());
                projeto.setDescricao(descricaoTextArea.getText());
                
                ProjetoDAO projetoDAO = new ProjetoDAO();
                projetoDAO.save(projeto);

                JOptionPane.showMessageDialog(null, "Projeto salvo com sucesso!");
                dispose();
            }
        });

        // Ação do botão Cancelar
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
    }
}