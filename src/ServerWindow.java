import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {

    private static final int POSITION_X = 500;
    private static final int POSITION_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton buttonStart = new JButton("Start");
    private final JButton buttonStop = new JButton("Stop");
    private final JPanel buttonPanel = new JPanel(new BorderLayout());
    private final JTextArea log = new JTextArea();


    private boolean isServerWorking;

    public ServerWindow(){

        isServerWorking = false;
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                buttonStart.setText("Started");
                buttonStart.setEnabled(false);
                buttonStop.setText("Stop");
                buttonStop.setEnabled(true);
                setTitle("Chat Server Started");
                log.append("Server start " + isServerWorking + "\n");
                System.out.println("Server start " + isServerWorking);
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                buttonStop.setText("Stoped");
                buttonStop.setEnabled(false);
                buttonStart.setText("Start");
                buttonStart.setEnabled(true);
                setTitle("Chat Server Stoped");
                log.append("Server start " + isServerWorking + "\n");
                System.out.println("Server start " + isServerWorking);

            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POSITION_X, POSITION_Y, WIDTH, HEIGHT);
        setResizable(true);
        setTitle("Chat Server");
        setVisible(true);
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        add(buttonPanel, BorderLayout.SOUTH);
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

    }

    public JTextArea getLog() {
        return log;
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }
}
