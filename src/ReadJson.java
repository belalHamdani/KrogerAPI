import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

// Program for print data in JSON format.
public class ReadJson implements ActionListener {
    public int charNum=0;
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JPanel blackPanel;
    public JScrollPane scrollPane;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    public JTextArea tya;
    public JTextArea po;
    private JTextArea ta;
    private int WIDTH=800;
    private int HEIGHT=700;

    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
new ReadJson();
    }

    public ReadJson(){
        prepareGUI();
        showEventDemo();

    }
    private void prepareGUI() {
        mainFrame = new JFrame("Animal Facts");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());

        tya=new JTextArea("Facts:");
        po = new JTextArea("Insert Animal Name");

        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        scrollPane = new JScrollPane(tya);


        //end menu at top



        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        blackPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,3)); //set the layout of the pannel
        blackPanel.setLayout(new GridLayout(1,3));
        mainFrame.add(controlPanel);
        mainFrame.add(blackPanel);
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
        String totlaJson="";
        System.out.println(po.getText());
        String saver = po.getText();


        int start = saver.indexOf("Name") +5;
        saver = saver.substring(start);
        System.out.println(saver);

        try {

            URL url = new URL("https://api.api-ninjas.com/v1/animals?name="+saver);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
          //  conn.setRequestProperty("Accept", "application/json");

            conn.setRequestProperty("X-Api-Key", "on5u/Dp4rqF4mukon+86Fw==6SfQxME0nQYXBS53");

            conn.setDoOutput(true);


            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
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

            org.json.simple.JSONObject msg = (org.json.simple.JSONObject) gaga.get("taxonomy");

            //int n = msg.size(); //(msg).length();

            //for (int i = 0; i < n; ++i) {
                String test = (String) msg.get("class");
                System.out.println("\n"+"My class is " + test);
                tya.append("\n"+"My class is "  + test);


                // System.out.println(person.getInt("key"));
           // }
            org.json.simple.JSONArray smg = (org.json.simple.JSONArray) gaga.get("locations");

            int a = smg.size(); //(msg).length();

            for (int i = 0; i < a; ++i) {
                String test1 = (String) smg.get(i);
                System.out.println(test1);
                tya.append("\n"+"My locations are "  + test1);
               // tya.append("\n"+"My locations are " + test);
            }
            org.json.simple.JSONObject character = (org.json.simple.JSONObject) gaga.get("characteristics");

            //int n = msg.size(); //(msg).length();

            //for (int i = 0; i < n; ++i) {
            String prey = (String) character.get("prey");
            System.out.println("My prey is " + prey);
            tya.append("\n"+"My prey is "  + prey);

            String diet = (String) character.get("diet");
            System.out.println("My diet is " + diet);
            tya.append("\n"+"My diet is "  + diet);

            String lifespan = (String) character.get("lifespan");
            System.out.println("My lifespan is " + lifespan);
            tya.append("\n"+"My lifespan is "  + lifespan);

            String height = (String) character.get("height");
            System.out.println("My height is " + height);
            tya.append("\n"+"My height is "  + height);

            String weight = (String) character.get("weight");
            System.out.println("My weight is " + weight);
            tya.append("\n"+"My weight is "  + weight);


            System.out.println("I am a " + name);
            tya.append("\n"+"I am a "  + name);
        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
    private void showEventDemo() {
        JButton NextButton = new JButton("Next");
        JButton ResetButton = new JButton("Reset");


        JButton backButton = new JButton("back");
        JLabel tickaLabel = new JLabel("ticka");





        backButton.setActionCommand("Back");
        NextButton.setActionCommand("Next");
        NextButton.addActionListener(new ReadJson.ButtonClickListener());
        backButton.addActionListener(new ReadJson.ButtonClickListener());
        ResetButton.setActionCommand("Reset");
        ResetButton.addActionListener(new ButtonClickListener());



        mainFrame.add(NextButton, BorderLayout.EAST);
        mainFrame.add(ResetButton, BorderLayout.WEST);

        mainFrame.add(po, BorderLayout.WEST);
        mainFrame.add(ResetButton, BorderLayout.WEST);

        blackPanel.add(scrollPane);





        mainFrame.setVisible(true);
    }
    if (command.equals("Reset"))
    {
        ta.setText(null);
        tya.setText(null);
        po.setText(null);
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





