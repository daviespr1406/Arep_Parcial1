import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "<title>Parcial AREP</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>Prueba GET</h1>\n"
                    +  "<h1>Form with GET</h1>\n"
                    +  "<form action=\"/key\"\n>"
                    +  " <label for=\"key\">key:</label><br>\n"
                    +  "<input type=\"text\" id=\"key\" key=\"key\" value=\"123\"><br><br>\n"
                    +  "<input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                    +  "</form>\n"
                    +  "<h1>Form with SET</h1>\n"
                    +  "<form action=\"/key\"\n>"
                    +  " <label for=\"key\">key:</label><br>\n"
                    +  "<input type=\"text\" id=\"key\" name=\"key\" value=\"123\"><br><br>\n"
                    +  "<input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                    +  "<br>\n"
                    +  "<input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n"
                    +  "<input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                    +  "</form>\n"
                    +  "<div id=\"getrespmsg\"></div>\n"
                    +  "<script>\n"
                    + "    function loadGetMsg() {\n"
                    + "        let nameVar = this.getElementById(\"key\").value;\n"
                    + "        const xhttp = new XMLHttpRequest();\n"
                    + "        xhttp.onload = function () {\n"
                    + "            document.getElementById(\"getrespmsg\").innerHTML =\n"
                    + "                this.responseText;\n"
                    + "        }\n"
                    + "        xhttp.open(\"GET\", \"/hello?name=\" + nameVar);\n"
                    + "        xhttp.send();\n"
                    + "    }\n"
                    + "</script>\n"
                    + "</body>\n"
                    + "</html>\n";
            out.println(outputLine);
        }
//        out.close();
//        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    public void invoke(){

    }
    public String getElementbyId(String id) {
        return id;
    }
}
