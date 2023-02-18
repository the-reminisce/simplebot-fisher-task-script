package me.remie.ikov.fisher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.remie.ikov.fisher.data.FisherSettings;
import me.remie.ikov.fisher.types.FishingSpot;
import simple.api.ClientContext;
import simple.api.filters.SimpleSkills;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by Reminisce on Feb 15, 2023 at 10:30 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707 <138751815847116800>
 */
public class FisherFrame extends JFrame {

    private final ClientContext ctx;
    private final FisherScript script;

    private JCheckBox powerFishingCheckBox;
    private JCheckBox progressiveFishingCheckBox;
    private JComboBox<FishingSpot> fishingSpotComboBox;

    public FisherFrame(final FisherScript script) {
        this.script = script;
        this.ctx = script.ctx;
        initComponents();
    }

    private void initComponents() {
        this.setTitle(script.getName());
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(ctx.mouse.getComponent());

        JTabbedPane tabbedPane = new JTabbedPane();
        this.add(tabbedPane);

        JPanel generalPanel = new JPanel();
        tabbedPane.addTab("General", generalPanel);

        generalPanel.setBorder(new EmptyBorder(5, 5, 0, 5));

        generalPanel.setLayout(new GridLayout(6, 1));

        JLabel powerFishingLabel = new JLabel("Power Fishing: ");
        powerFishingLabel.setToolTipText("If you want to power fish");
        generalPanel.add(powerFishingLabel);

        powerFishingCheckBox = new JCheckBox();
        generalPanel.add(powerFishingCheckBox);

        JLabel progressiveFishingLabel = new JLabel("Progressive Fishing: ");
        progressiveFishingLabel.setToolTipText("If you want to progressive fish");
        generalPanel.add(progressiveFishingLabel);

        progressiveFishingCheckBox = new JCheckBox();
        generalPanel.add(progressiveFishingCheckBox);

        JLabel fishingSpotLabel = new JLabel("Fishing Spot: ");
        fishingSpotLabel.setToolTipText("The fishing spot you want to fish at");
        generalPanel.add(fishingSpotLabel);

        fishingSpotComboBox = new JComboBox<>(FishingSpot.values());
        generalPanel.add(fishingSpotComboBox);

        //We're adding an action listener to the progressive fishing checkbox
        //If it's selected, we disable the fishing spot combo box
        //If it's not selected, we enable the fishing spot combo box
        progressiveFishingCheckBox.addActionListener(e -> fishingSpotComboBox.setEnabled(!progressiveFishingCheckBox.isSelected()));

        JPanel infoPanel = new JPanel();
        tabbedPane.addTab("Info", infoPanel);
        infoPanel.setLayout(new BorderLayout());

        JScrollPane infoScrollPane = new JScrollPane();
        infoPanel.add(infoScrollPane, BorderLayout.CENTER);

        JTextPane infoTextArea = new JTextPane();
        infoTextArea.setContentType("text/html");
        infoTextArea.setEditable(false);
        infoTextArea.setPreferredSize(new Dimension(500, 200));
        infoTextArea.setText("Catherby fishing script for Ikov.");
        infoTextArea.setCaretPosition(0);
        infoScrollPane.setViewportView(infoTextArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        JPanel buttonSubPanel1 = new JPanel();
        buttonSubPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton saveButton = new JButton("Save settings");
        saveButton.addActionListener(e -> saveSettings());
        buttonSubPanel1.add(saveButton);
        JButton loadButton = new JButton("Load settings");
        loadButton.addActionListener(e -> loadSettings());
        buttonSubPanel1.add(loadButton);
        buttonPanel.add(buttonSubPanel1, BorderLayout.NORTH);

        JPanel buttonSubPanel2 = new JPanel();
        buttonSubPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        JButton startScriptButton = new JButton("Start Script");
        startScriptButton.addActionListener(e -> startScript());
        buttonSubPanel2.add(startScriptButton);
        buttonPanel.add(buttonSubPanel2, BorderLayout.SOUTH);

        this.add(buttonPanel, BorderLayout.SOUTH);

        pack();
        this.setVisible(true);
    }

    /**
     * Starts the script with the current settings
     */
    public void startScript() {
        final boolean progressiveFishing = progressiveFishingCheckBox.isSelected();
        final FishingSpot fishingSpot = (FishingSpot) fishingSpotComboBox.getSelectedItem();

        if (!progressiveFishing) {
            if (fishingSpot.getRequiredLevel() > ctx.skills.getLevel(SimpleSkills.Skill.FISHING)) {
                JOptionPane.showMessageDialog(this, "You need a fishing level of " +
                                fishingSpot.getRequiredLevel() + " to fish for " + fishingSpot.getName() + ".",
                        "Insufficient Fishing Level", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        script.startScript(getSettings());
    }

    /**
     * Creates a new settings object from the GUI settings.
     *
     * @return the settings object
     */
    private FisherSettings getSettings() {
        try {
            final boolean powerFishing = powerFishingCheckBox.isSelected();
            final boolean progressiveFishing = progressiveFishingCheckBox.isSelected();
            final FishingSpot fishingSpot = (FishingSpot) fishingSpotComboBox.getSelectedItem();

            final FisherSettings settings = new FisherSettings(fishingSpot, powerFishing, progressiveFishing);
            return settings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Opens a file dialog to load settings from a json file
     */
    private void saveSettings() {
        try {
            final FisherSettings settings = getSettings();

            final File scriptDirectory = script.getStorageDirectory();

            //Open a save file dialog that only allows json files to be selected
            final JFileChooser fileChooser = new JFileChooser(scriptDirectory);
            fileChooser.setDialogTitle("Save settings");
            fileChooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getName().endsWith(".json")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".json");
                }
                final Gson gson = new GsonBuilder().setPrettyPrinting().create();
                final String json = gson.toJson(settings);

                final FileWriter writer = new FileWriter(selectedFile);
                writer.write(json);
                writer.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads settings from a json file
     */
    private void loadSettings() {
        try {
            final File scriptDirectory = script.getStorageDirectory();

            final JFileChooser fileChooser = new JFileChooser(scriptDirectory);
            fileChooser.setDialogTitle("Load settings");
            fileChooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (!selectedFile.getName().endsWith(".json")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".json");
                }
                final Gson gson = new GsonBuilder().setPrettyPrinting().create();
                final FisherSettings settings = gson.fromJson(new FileReader(selectedFile), FisherSettings.class);


                setupFromSettings(settings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupFromSettings(final FisherSettings settings) {
        powerFishingCheckBox.setSelected(settings.isPowerFishing());
        progressiveFishingCheckBox.setSelected(settings.isProgressiveFishing());
        fishingSpotComboBox.setSelectedItem(settings.getFishingSpot());
    }

    /**
     * Stops the script and disposes the GUI when the window is closed
     */
    @Override
    public void dispose() {
        super.dispose();
        ctx.stopScript();
    }

}
