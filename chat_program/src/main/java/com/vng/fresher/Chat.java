package com.vng.fresher;

import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Chat {

    public JButton btnSend;
    public JFormattedTextField txtMsg;
    public JTextArea txtListMsg;
    public JPanel panelMain;
    private JLabel lblUsername;
    public static String username;

    public RedissonClient InitRC() {
        try {
            RedissonClient client = Redisson.create();
            return client;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Chưa chạy Server !!!");
        }
        return null;
    }
    public Chat() {

        RedissonClient client = InitRC();
        final RTopic<String> topic = client.getTopic("topic");
        final RMapCache<String, String> map = client.getMapCache("message");

        for (Map.Entry m:map.entrySet()) {
            txtListMsg.append(m.getKey() + ": " + m.getValue() + "\n");
        }
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String msg = txtMsg.getText();
                topic.publish(username + ": " + msg);
                map.put(username, msg, 1, TimeUnit.DAYS);
                txtMsg.setText("");
            }
        });

        topic.addListener(new MessageListener<String>() {
            public void onMessage(String s, String s2) {
                txtListMsg.append(s2 + "\n");
            }
        });
    }

    public void Run() {
        JFrame frame = new JFrame("Chat");
        frame.setContentPane(new Chat().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Register dialog = new Register();
        dialog.pack();
        dialog.setVisible(true);
        username = dialog.username;
        frame.setTitle(username);
        if (dialog.isCancel == true) {
            System.exit(0);
        }
    }

}
