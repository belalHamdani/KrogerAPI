import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;

public class Animal implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    public JTextArea tya;
    public JTextArea po;
    private JTextArea ta;
    private int WIDTH=800;
    private int HEIGHT=700;
    int charNum = 0;


    public Animal() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Animal example3 = new Animal();
        example3.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Animal Facts");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());

        tya=new JTextArea("tya");
        po = new JTextArea("po");

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);


        //end menu at top



        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,3)); //set the layout of the pannel

        mainFrame.add(controlPanel);
        //mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Next")) {

                if(charNum<19) {
                    charNum++;
                }
                else {
                    charNum = 0;
                }

                try {
                    pull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Next API");
            }
            if (command.equals("Back")) {

                if(charNum>0) {
                    charNum--;
                }
                try {
                    pull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Back API");



            }


        }
    }
    public  void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
        try {
            //   https://pokeapi.co/api/v2/pokemon/ditto
            URL url = new URL("https://api.api-ninjas.com/v1/animals?name=cheetah");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) parser.parse(totlaJson);
        System.out.println(jsonArray);

        try {

            int f = jsonArray.size();
            // for (int z = 0; z < f; ++z) {
            JSONObject gaga = (JSONObject) jsonArray.get(charNum);


            String name = (String) gaga.get("name");

            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) gaga.get("class");

            int n = msg.size(); //(msg).length();

            for (int i = 0; i < n; ++i) {
                String test = (String) msg.get(i);
                System.out.println("\n"+"My class is " + test);
                tya.append("\n"+"My class is " + test);


                // System.out.println(person.getInt("key"));
            }
            org.json.simple.JSONArray smg = (org.json.simple.JSONArray) gaga.get("locations");

            int a = smg.size(); //(msg).length();

            for (int i = 0; i < a; ++i) {
                String test1 = (String) smg.get(i);
                System.out.println(test1);
                tya.append("\n"+"My locations are " + test1);
            }
            org.json.simple.JSONArray vra = (org.json.simple.JSONArray) gaga.get("prey");

            int l = vra.size(); //(msg).length();

            for (int i = 0; i < l; ++i) {
                String prey = (String) vra.get(i);
                System.out.println(prey);
                tya.append("\n"+"My prey is " + prey);
            }



            String height = (String) gaga.get("height");
            System.out.println("My name is " + name);
            tya.append("\n"+"My name is " + name);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void showEventDemo() {
        JButton NextButton = new JButton("Next");


        JButton backButton = new JButton("back");
        JLabel tickaLabel = new JLabel("ticka");





        backButton.setActionCommand("Back");
        NextButton.setActionCommand("Next");
        NextButton.addActionListener(new ButtonClickListener());
        backButton.addActionListener(new ButtonClickListener());
        controlPanel.add(tya);


        mainFrame.add(NextButton, BorderLayout.EAST);
        controlPanel.add(po);
        mainFrame.add(backButton, BorderLayout.WEST);






        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }


}

