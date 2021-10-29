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
        String keyStorePath = "src/main/resources/KeyStore.jks";
        String pass = "123456";

        String trustStorePath = "src/main/resources/truststore.jks";

        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(new FileInputStream(keyStorePath), pass.toCharArray());

        secure(keyStorePath, pass, trustStorePath, pass);

        staticFiles.location("web");
        get("/hello", (req, res) -> "Hello World");

        SimpleServer socketServer = new SimpleServer(5555);
        socketServer.start();
    }
}
