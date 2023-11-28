package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;

public class FormacaoDeLoucura extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    private Point initialClick;
    private JLabel silabaA;
    private JLabel silabaB;
    private JLabel silabaC;

    public FormacaoDeLoucura() {
        initComponents();
        initSilabasArrastaveis();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonProximo = new javax.swing.JButton("Próximo");
        jButtonProximo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
        jButtonProximo.setBackground(Color.YELLOW);
        jButtonProximo.setForeground(Color.WHITE);
        
        // Define as coordenadas do botão "Próximo" no lado direito
        jButtonProximo.setBounds(100, 150, 200, 60);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 24));
        jLabel5.setText("Organize");

        jSeparator1.setForeground(Color.BLACK);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(396, 396, 396)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(528, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButtonProximo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(97, 97, 97)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160)
                .addComponent(jButtonProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonProximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botão 'Próximo' clicado!");
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void initSilabasArrastaveis() {
        // Crie labels para as sílabas e defina suas propriedades
        silabaA = createSilabaLabel("A", 34, 100);
        silabaB = createSilabaLabel("B", 120, 100);
        silabaC = createSilabaLabel("C", 230, 100);

        // Adicione ouvintes de mouse às sílabas
        addMouseListenersToSilaba(silabaA);
        addMouseListenersToSilaba(silabaB);
        addMouseListenersToSilaba(silabaC);

        jPanel1.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int separatorX = jSeparator1.getX();
                int separatorWidth = jSeparator1.getWidth();
                checkProximity(silabaA, separatorX, separatorWidth);
                checkProximity(silabaB, separatorX, separatorWidth);
                checkProximity(silabaC, separatorX, separatorWidth);
            }
        });
    }

    private JLabel createSilabaLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 50, 50);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setHorizontalAlignment(JLabel.CENTER);
        jPanel1.add(label);
        return label;
    }

    private void addMouseListenersToSilaba(JLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        label.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = label.getLocation().x + e.getX() - initialClick.x;
                int thisY = label.getLocation().y + e.getY() - initialClick.y;
                label.setLocation(thisX, thisY);
            }
        });
    }

    private void checkProximity(JLabel label, int separatorX, int separatorWidth) {
        int labelX = label.getX();
        int labelWidth = label.getWidth();
        int labelCenterX = labelX + labelWidth / 2;
        int proximityDistance = 10;

        if (Math.abs(labelCenterX - (separatorX + separatorWidth / 2)) <= proximityDistance) {
            int newLabelX = separatorX + separatorWidth / 2 - labelWidth / 2;
            label.setLocation(newLabelX, label.getY());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FormacaoDeLoucura().setVisible(true);
        });
    }

    private javax.swing.JButton jButtonProximo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
}