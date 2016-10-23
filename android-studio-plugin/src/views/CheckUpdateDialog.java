package views;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckUpdateDialog extends JDialog {

    public static final int MAX_WIDTH = 600;
    public static final int MAX_HEIGHT = 450;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel container;
    private MyLabel serverVersionTx = new MyLabel();
    private MyLabel serverUpdateTimeTx = new MyLabel();
    private MyLabel localVersionTx = new MyLabel();

    public CheckUpdateDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Freeline Gradle Plugin Update");
        container.setLayout(null);
        ImageJPanel panel = new ImageJPanel();
        panel.setBounds(0, 0, MAX_WIDTH, 120);
        panel.setImagePath("/icons/bg_update.png");
        container.add(panel);
        JPanel updateContent = new JPanel();
        updateContent.setBounds(20, 130, MAX_WIDTH, 500);
        updateContent.setLayout(new BoxLayout(updateContent, BoxLayout.PAGE_AXIS));
        container.add(updateContent);
        updateContent.add(new JTitle("Jcenter Version"));
        updateContent.add(serverVersionTx);
        updateContent.add(serverUpdateTimeTx);
        updateContent.add(new JTitle("Local Version"));
        updateContent.add(localVersionTx);
        updateContent.add(new JTitle("Official Website"));
        MyLabel website = new MyLabel("<a href='#'>github.com/alibaba/freeline</a>", true);
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.openUrl("https://github.com/alibaba/freeline");
            }
        });
        updateContent.add(website);
        updateContent.add(new MyLabel("注:点击更新后会自动gradle sync并下载最新freeline.zip工具包"));
        updateContent.add(new MyLabel("NOTE: Click update automatically gradle sync and download the latest freeline.zip Kit"));
        setResizable(false);
        setLocationCenter();
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void setLocationCenter() {
        int windowWidth = MAX_WIDTH;
        int windowHeight = MAX_HEIGHT;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public void showDialog() {
        pack();
        setVisible(true);
    }

    public void setServerVersion(String version) {
        serverVersionTx.setText(version);
    }

    public void setServerUpdateTime(String time) {
        serverUpdateTimeTx.setText(time);
    }

    public void setLocalVersion (String localVersion) {
        localVersionTx.setHtml(localVersion);
    }

    public static void main(String[] args) {
        CheckUpdateDialog dialog = new CheckUpdateDialog();
        dialog.showDialog();
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public void addActionListener(ActionListener listener) {
        buttonOK.addActionListener(listener);
    }

}
