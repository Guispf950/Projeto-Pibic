package controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

public class JogoSilabasDragAndDrop {
    private JFrame frame;
    private JPanel palavraPanel;
    private JPanel respostaPanel;
    private List<JLabel> silabasLabels;
    private List<String> silabasCorretas;
    private List<JLabel> respostaLabels;

    public JogoSilabasDragAndDrop() {
        frame = new JFrame("Jogo de Organização Silábica");
        frame.setLayout(new GridLayout(3, 1));

        silabasLabels = new ArrayList<>();
        silabasCorretas = new ArrayList<>();
        respostaLabels = new ArrayList<>();

        String palavraCorreta = "banana";
        String[] silabas = palavraCorreta.split("");
        for (String silaba : silabas) {
            JLabel silabaLabel = createSilabaLabel(silaba);
            silabasLabels.add(silabaLabel);
            silabasCorretas.add(silaba);
        }
        Collections.shuffle(silabasLabels);

        palavraPanel = new JPanel(new GridLayout(1, silabasLabels.size()));
        respostaPanel = new JPanel(new GridLayout(1, silabasCorretas.size()));

        for (JLabel label : silabasLabels) {
            palavraPanel.add(label);
        }

        for (String silaba : silabasCorretas) {
            JLabel respostaLabel = createRespostaLabel(silaba);
            respostaPanel.add(respostaLabel);
        }

        frame.add(palavraPanel);
        frame.add(respostaPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }

    private JLabel createSilabaLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setTransferHandler(new TransferHandler("text"));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent comp = (JComponent) e.getSource();
                TransferHandler th = comp.getTransferHandler();
                th.exportAsDrag(comp, e, TransferHandler.COPY);
            }
        });
        return label;
    }

    private JLabel createRespostaLabel(String text) {
        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setTransferHandler(new RespostaTransferHandler(text));
        respostaLabels.add(label);
        return label;
    }

    private class RespostaTransferHandler extends TransferHandler {
        private String silaba;

        public RespostaTransferHandler(String silaba) {
            this.silaba = silaba;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            if (!support.isDrop()) {
                return false;
            }

            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            try {
                Transferable transferable = support.getTransferable();
                String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                JLabel respostaLabel = (JLabel) support.getComponent();
                if (silaba.equals(data)) {
                    respostaLabel.setText(silaba);
                    respostaLabel.setForeground(Color.GREEN);
                } else {
                    respostaLabel.setText("");
                }
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JogoSilabasDragAndDrop();
        });
    }
}
