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

// Program for print data in JSON format.
public class ReadJson {
    public int charNum=0;
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
        try {
            pull();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    public  void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://api.api-ninjas.com/v1/animals?name=Zebra");
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
            //    tya.append("\n"+"My class is " + test);


                // System.out.println(person.getInt("key"));
           // }
            org.json.simple.JSONArray smg = (org.json.simple.JSONArray) gaga.get("locations");

            int a = smg.size(); //(msg).length();

            for (int i = 0; i < a; ++i) {
                String test1 = (String) smg.get(i);
                System.out.println(test1);
               // tya.append("\n"+"My locations are " + test);
            }
            org.json.simple.JSONObject character = (org.json.simple.JSONObject) gaga.get("characteristics");

            //int n = msg.size(); //(msg).length();

            //for (int i = 0; i < n; ++i) {
            String prey = (String) character.get("prey");
            System.out.println("My prey is " + prey);

            String diet = (String) character.get("diet");
            System.out.println("My diet is " + diet);

            String lifespan = (String) character.get("lifespan");
            System.out.println("My lifespan is " + lifespan);

            String height = (String) character.get("height");
            System.out.println("My height is " + height);

            String weight = (String) character.get("weight");
            System.out.println("My weight is " + weight);


            System.out.println("I am a " + name);
        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
}


