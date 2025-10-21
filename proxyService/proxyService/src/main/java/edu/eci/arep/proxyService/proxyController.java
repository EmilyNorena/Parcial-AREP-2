package edu.eci.arep.proxyService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class proxyController {
    private AtomicInteger server = new AtomicInteger(1);

    private String roundRobin(){
        String GET_URL;
        if(server.get() == 1){
            server.set(2);
            GET_URL = "http://44.223.17.228:8080";
        }else{
            server.set(1);
            GET_URL = "http://54.87.13.156:8080";
        }
        return GET_URL;
    }

    private String httpConnection(String GET_URL) throws Exception{
        String USER_AGENT = "Mozilla/5.0";
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        }
        return "Error";

    }

    @GetMapping("/linearSearch")
    public ResponseEntity<?> linearSearch(@RequestParam String list, @RequestParam String value){
        String GET_URL =  roundRobin()+"/linearSearch?list="+ list +"&value="+ value;
        try {
            String index = httpConnection(GET_URL);
            return ResponseEntity.ok(index);
        } catch (Exception ex) {
            return ResponseEntity.status(505).body("ERROR");
        }
    }

    @GetMapping("/binarySearch")
    public ResponseEntity<?> binarySearch(@RequestParam String list, @RequestParam String value){
        String GET_URL =  roundRobin()+"/binarySearch?list="+ list +"&value="+ value;
        try {
            String index = httpConnection(GET_URL);
            return ResponseEntity.ok(index);
        } catch (Exception ex) {
            return ResponseEntity.status(505).body("ERROR");
        }
    }
}