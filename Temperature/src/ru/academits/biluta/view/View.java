package ru.academits.biluta.view;

import com.apple.eawt.Application;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class View extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private DefaultListModel<String> unitsListModel;

    private JList<String> unitsSource;
    private JList<String> unitsResult;

    private JTextField inputTextField;
    private JTextField resultTextField;

    private JButton swapUnitsButton;
    private JButton convertButton;

    public View(String header) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // set FRAME
        frame = new JFrame(header);
        frame.setIconImage(new ImageIcon("Temperature/src/ru/academits/biluta/icons/icon.png").getImage());
        Application.getApplication().setDockIconImage(
                new ImageIcon("Temperature/src/ru/academits/biluta/icons/icon.png").getImage());
        frame.setSize(330, 210);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initiate components
        panel = new JPanel();
        label = new JLabel("Check units and enter a value:");

        unitsListModel = new DefaultListModel<>();
        unitsSource = new JList<>(unitsListModel);
        unitsResult = new JList<>(unitsListModel);

        swapUnitsButton = new JButton("<-swap->");
        convertButton = new JButton("Convert->");

        inputTextField = new JTextField(7);
        resultTextField = new JTextField(7);

        // set PANEL
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        frame.add(panel);

        GridBagConstraints c = new GridBagConstraints();

        // set LABEL
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.insets = new Insets(5, 2, 5, 2);
        panel.add(label, c);

        unitsSource.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        unitsSource.setSelectedIndex(0);
        unitsSource.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 2, 5, 2);
        panel.add(unitsSource, c);

        // set LIST units result
        unitsResult.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        unitsResult.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(5, 2, 5, 2);

        panel.add(unitsResult, c);
        unitsResult.setSelectedIndex(0);

        // set BUTTON switch units

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 10;
        panel.add(swapUnitsButton, c);

        // set INPUT TEXT FIELD
        inputTextField.setText("0.0");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 2, 10, 2);
        panel.add(inputTextField, c);

        // set CONVERTING BUTTON

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 10;
        c.weightx = 0.5;
        panel.add(convertButton, c);

        // set OUTPUT TEXT FIELD
        resultTextField.setEditable(false);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(10, 2, 10, 2);
        panel.add(resultTextField, c);

        panel.setVisible(true);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public DefaultListModel<String> getUnitsListModel() {
        return unitsListModel;
    }

    public void setUnitsListModel(DefaultListModel<String> unitsListModel) {
        this.unitsListModel = unitsListModel;
    }

    public JList<String> getUnitsSource() {
        return unitsSource;
    }

    public void setUnitsSource(JList<String> unitsSource) {
        this.unitsSource = unitsSource;
    }

    public JList<String> getUnitsResult() {
        return unitsResult;
    }

    public void setUnitsResult(JList<String> unitsResult) {
        this.unitsResult = unitsResult;
    }

    public JTextField getInputField() {
        return inputTextField;
    }

    public void setInputField(JTextField inputTextField) {
        this.inputTextField = inputTextField;
    }

    public JTextField getResultField() {
        return resultTextField;
    }

    public void setResultField(JTextField resultTextField) {
        this.resultTextField = resultTextField;
    }

    public JButton getSwapUnitsButton() {
        return swapUnitsButton;
    }

    public void setSwapUnitsButton(JButton swapUnitsButton) {
        this.swapUnitsButton = swapUnitsButton;
    }

    public JButton getConvertButton() {
        return convertButton;
    }

    public void setConvertButton(JButton convertButton) {
        this.convertButton = convertButton;
    }
}