package ca1java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ForumApp {

    private JFrame frame;
    private DefaultListModel<String> threadListModel;
    private JList<String> threadList;
    private JTextArea postArea;
    private JTextField threadField, authorField, postField;
    private JButton addThreadButton, addPostButton;
    private List<ForumThread> threads;

    public ForumApp() {
        threads = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Forum");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        threadListModel = new DefaultListModel<>();
        threadList = new JList<>(threadListModel);
        frame.add(new JScrollPane(threadList), BorderLayout.WEST);

        postArea = new JTextArea();
        postArea.setEditable(false);
        frame.add(new JScrollPane(postArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(0, 1));
        threadField = new JTextField();
        authorField = new JTextField();
        postField = new JTextField();
        addThreadButton = new JButton("Add Thread");
        addPostButton = new JButton("Add Post");

        bottomPanel.add(new JLabel("Thread:"));
        bottomPanel.add(threadField);
        bottomPanel.add(addThreadButton);
        bottomPanel.add(new JLabel("Author:"));
        bottomPanel.add(authorField);
        bottomPanel.add(new JLabel("Post:"));
        bottomPanel.add(postField);
        bottomPanel.add(addPostButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        addThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String threadName = threadField.getText();
                if (!threadName.isEmpty()) {
                    ForumThread thread = new ForumThread(threadName);
                    threads.add(thread);
                    threadListModel.addElement(threadName);
                    threadField.setText("");
                }
            }
        });

        addPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedThreadName = threadList.getSelectedValue();
                String author = authorField.getText();
                String content = postField.getText();
                if (selectedThreadName != null && !author.isEmpty() && !content.isEmpty()) {
                    
                    //???
                    ForumThread thread = threads.stream()
                        .filter(t -> t.getTitle().equals(selectedThreadName))
                        .findFirst()
                        .orElse(null);

                    if (thread != null) {
                        thread.addPost(new ForumPost(author, content));
                        updatePostArea(thread);
                        authorField.setText("");
                        postField.setText("");
                    }
                }
            }
        });

        threadList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedThreadName = threadList.getSelectedValue();
                if (selectedThreadName != null) {
                    ForumThread thread = threads.stream()
                        .filter(t -> t.getTitle().equals(selectedThreadName))
                        .findFirst()
                        .orElse(null);
                    
                    if (thread != null) {
                        updatePostArea(thread);
                    }
                }
            }
        });

        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updatePostArea(ForumThread thread) {
        StringBuilder postsText = new StringBuilder();
        for (ForumPost post : thread.getPosts()) {
            postsText.append(post.getAuthor()).append(": ").append(post.getContent()).append("\n");
        }
        postArea.setText(postsText.toString());
    }

    private static class ForumThread {
        private String title;
        private List<ForumPost> posts;

        public ForumThread(String title) {
            this.title = title;
            this.posts = new ArrayList<>();
        }

        public void addPost(ForumPost post) {
            posts.add(post);
        }

        public String getTitle() {
            return title;
        }

        public List<ForumPost> getPosts() {
            return posts;
        }
    }

    private static class ForumPost {
        private String author;
        private String content;

        public ForumPost(String author, String content) {
            this.author = author;
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ForumApp();
            }
        });
    }
}
