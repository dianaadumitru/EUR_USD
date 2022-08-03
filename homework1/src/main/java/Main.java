import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);

        String url_str = "https://api.exchangerate.host/latest";

        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        int responseCode = request.getResponseCode();



        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            StringBuilder dataFromUrl = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                dataFromUrl.append(scanner.nextLine());
            }

            scanner.close();
            System.out.println(dataFromUrl);

            int pos = dataFromUrl.indexOf("USD");
            System.out.println(pos);
            String value = dataFromUrl.substring(pos+5,pos+13);
            System.out.println(value);
            logger.trace(value);
            System.out.println("EUR_USD: " + value);
        }
    }
}
