import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopWatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel("00:00:00.00");
    long startTime = 0;
    long elapsedTime = 0;
    boolean started = false;
    Timer timer;

    StopWatch() {
        timeLabel.setBounds(60, 50, 200, 100);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(60, 160, 100, 50);
        startButton.setFont(new Font("Verdana", Font.BOLD, 17));
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(160, 160, 100, 50);
        resetButton.setFont(new Font("Verdana", Font.BOLD, 17));
        resetButton.setBackground(Color.ORANGE);
        resetButton.setForeground(Color.BLACK);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("StopWatch App, by Dobro");
        frame.setSize(340, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime = System.currentTimeMillis() - startTime;
                updateLabel();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!started) {
                started = true;
                startButton.setText("STOP");
                startButton.setBackground(Color.RED);
                start();
            } else {
                started = false;
                startButton.setText("START");
                startButton.setBackground(Color.GREEN);
                stop();
            }
        }
        if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("START");
            startButton.setBackground(Color.GREEN);
            reset();
        }
    }

    void start() {
        startTime = System.currentTimeMillis() - elapsedTime;
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        elapsedTime = 0;
        updateLabel();
    }

    void updateLabel() {
        long hours = (elapsedTime / 3600000) % 24;
        long minutes = (elapsedTime / 60000) % 60;
        long seconds = (elapsedTime / 1000) % 60;
        long milliseconds = (elapsedTime / 10) % 100;
        String timeString = String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, milliseconds);
        timeLabel.setText(timeString);
    }


}
