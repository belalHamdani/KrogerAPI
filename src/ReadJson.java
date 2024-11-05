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
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        pull();

    }

    public static void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://api.kroger.com/v1/connect/oauth2/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            conn.setRequestProperty("Authorization", "Basic Zm9vZHByb2R1Y3RzZm9ybXljc2NsYXNzMjAyNC0yNDMyNjEyNDMwMzQyNDRhNTU3NzU0NzI1NzZkMzUzMzQxNGY1NTQ0NTY3NDM5NjU2Yjc3NmE1YTc1Mzg3OTRmNmYzODMzMzQzMTc3NzEzNDc1NGY2Yjc5MzQ2YjUyNTgzMDY1NDU2ZTM3NzQ2YTM5NDc1MzZlNGYyMTY5NzE2NzgyODcyMDM0MTQx:OVJta3VYbm1jTTF0UUd1Wm1ScEtUT19MN21jUEVzWVVxVWFoUUtfRAo=)");

            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write("grant_type=client_credentials&scope=product.full.read");
            writer.flush();
            writer.close();
            conn.getOutputStream().close();

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
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            String name = (String)jsonObject.get("name");

            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("films");
            int n =   msg.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                String test =(String) msg.get(i);
                System.out.println(test);
                // System.out.println(person.getInt("key"));
            }
            //. String name= (String)jsonObject.get("height");
            System.out.println(name);
        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
}


