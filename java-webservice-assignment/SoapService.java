import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;


public class SoapService {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9999), 0);

        
        server.createContext("/soap", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if ("POST".equals(exchange.getRequestMethod())) {
                    String soapAction = exchange.getRequestHeaders().getFirst("SOAPAction");
                    String requestBody = readRequestBody(exchange);

                    System.out.println(" SOAP Request Received:");
                    System.out.println("SOAPAction: " + soapAction);
                    System.out.println("Body: " + requestBody);

                    String response = processSoapRequest(soapAction, requestBody);

                    exchange.getResponseHeaders().set("Content-Type", "text/xml");
                    exchange.sendResponseHeaders(200, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                    System.out.println(" SOAP Response Sent");
                }
            }
        });

        server.start();
        System.out.println("  SOAP Endpoint Available:");
        System.out.println("   POST http://localhost:9999/soap");
        System.out.println("  SOAP Methods Available:");
        System.out.println("   - getMenu");
        System.out.println("   - placeOrder");
        System.out.println("   - calculateTotal");
        System.out.println("   - checkOrderStatus");
        System.out.println("=================================================");
    }

    private static String processSoapRequest(String soapAction, String requestBody) {
        if (soapAction == null) return createSoapFault("Missing SOAPAction");

        switch (soapAction) {
            case "getMenu":
                return createSoapResponse("<menu><item>Pizza - $12.99</item><item>Pasta - $9.99</item><item>Salad - $7.99</item></menu>");

            case "placeOrder":
                return createSoapResponse("<order><status>confirmed</status><orderId>1001</orderId><message>Order placed successfully</message></order>");

            case "calculateTotal":
                return createSoapResponse("<calculation><total>33.97</total><currency>USD</currency></calculation>");

            case "checkOrderStatus":
                return createSoapResponse("<status><orderId>1001</orderId><status>preparing</status><estimatedTime>20 minutes</estimatedTime></status>");

            default:
                return createSoapFault("Unknown SOAPAction: " + soapAction);
        }
    }

    private static String createSoapResponse(String content) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
               "  <soap:Body>\n" +
               "    " + content + "\n" +
               "  </soap:Body>\n" +
               "</soap:Envelope>";
    }

    private static String createSoapFault(String fault) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
               "  <soap:Body>\n" +
               "    <soap:Fault>\n" +
               "      <faultstring>" + fault + "</faultstring>\n" +
               "    </soap:Fault>\n" +
               "  </soap:Body>\n" +
               "</soap:Envelope>";
    }

    private static String readRequestBody(HttpExchange exchange) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        return body.toString();
    }
}
