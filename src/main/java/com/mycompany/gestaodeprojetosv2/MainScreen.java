package com.mycompany.gestaodeprojetosv2;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends javax.swing.JFrame {

    private JTabbedPane jTabbedPane;
    private JPanel jPanelProjects;
    private JPanel jPanelTasks;

    public MainScreen() {
        initComponents();
        this.setLocationRelativeTo(null); // Centraliza a janela
    }

    private void initComponents() {
        jTabbedPane = new JTabbedPane();
        jPanelProjects = new JPanel(new BorderLayout());
        jPanelTasks = new JPanel(new BorderLayout());

        // Configuração da janela principal
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Projetos");

        // Painel para botões na aba de projetos
        JPanel projectButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addProjectButton = new JButton("+");
        projectButtonsPanel.add(addProjectButton);
        jPanelProjects.add(projectButtonsPanel, BorderLayout.NORTH);

        // Adiciona JTable e JScrollPane na aba de projetos
        JTable projectsTable = new JTable();
        JScrollPane projectsScrollPane = new JScrollPane(projectsTable);
        jPanelProjects.add(projectsScrollPane, BorderLayout.CENTER);

        // Adiciona JTable e JScrollPane na aba de tarefas
        JTable tasksTable = new JTable();
        JScrollPane tasksScrollPane = new JScrollPane(tasksTable);
        jPanelTasks.add(tasksScrollPane, BorderLayout.CENTER);

        jTabbedPane.addTab("Projetos", jPanelProjects);
        jTabbedPane.addTab("Tarefas", jPanelTasks);

        // Ação do botão para abrir a janela de adicionar projeto
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProjectDialog(MainScreen.this).setVisible(true);
            }
        });

        // Define o layout da janela principal
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }
}