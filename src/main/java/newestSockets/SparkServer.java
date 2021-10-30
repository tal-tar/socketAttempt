package newestSockets;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import static spark.Spark.*;


public class SparkServer {
    public static void main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        String keyStorePath = "/usr/local/lib/KeyStore.jks";
        String pass = "123456";

        String trustStorePath = "/usr/local/lib/truststore.jks";

        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(new FileInputStream(keyStorePath), pass.toCharArray());

        System.out.println(SparkServer.class.getResource(keyStorePath));

        secure(keyStorePath, pass, trustStorePath, pass);
//        port(getOCPort());
        webSocket("/socket", SparkSocketServer.class);
        staticFiles.location("web");
        get("/hello", (req, res) -> "Hello World");

//        SimpleServer socketServer = new SimpleServer(5555);
//        socketServer.start();
    }


    static int getOCPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
