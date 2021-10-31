package newestSockets;

import org.java_websocket.server.DefaultSSLWebSocketServerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import static spark.Spark.*;


public class SparkServer {
    public static void main(String[] args) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
//        String keyStorePath = "src/main/resources/KeyStore.jks";
//        String pass = "123456";
//
//        String trustStorePath = "src/main/resources/truststore.jks";
//
//        KeyStore keystore = KeyStore.getInstance("JKS");
//        keystore.load(new FileInputStream(keyStorePath), pass.toCharArray());
//
//        secure(keyStorePath, pass, trustStorePath, pass);
//
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
//        kmf.init(keystore, pass.toCharArray());
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
//        tmf.init(keystore);
//
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        SimpleServer socketServer = new SimpleServer(5555);
//        socketServer.setWebSocketFactory(new DefaultSSLWebSocketServerFactory(sslContext));
        socketServer.start();
//        webSocket("/socket", SparkSocketServer.class);
        staticFiles.location("web");
//        get("/hello", (req, res) -> "Hello World");
//        get("/socket", (req, res) -> halt());
        get("/*", (req, res) -> "src/main/resources/web");
//        unmap("/socket");

    }


    static int getOCPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
