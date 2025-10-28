import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;


public class RestaurantService {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);

        server.createContext("/api/menu", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if ("GET".equals(exchange.getRequestMethod())) {
                    String response = "[" +
                        "{\"id\": 1, \"name\": \"Pizza\", \"price\": 12.99, \"category\": \"Main Course\"}," +
                        "{\"id\": 2, \"name\": \"Pasta\", \"price\": 9.99, \"category\": \"Main Course\"}," +
                        "{\"id\": 3, \"name\": \"Salad\", \"price\": 7.99, \"category\": \"Appetizer\"}" +
                    "]";
                    sendJsonResponse(exchange, response, 200);
                    System.out.println(" GET /api/menu - Returned menu items");
                }
            }
        });

       
        server.createContext("/api/menu/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if ("GET".equals(exchange.getRequestMethod())) {
                    String path = exchange.getRequestURI().getPath();
                    String id = path.substring(path.lastIndexOf("/") + 1);
                    
                    String response = "{\"id\": " + id + ", \"name\": \"Item " + id + "\", \"price\": 10.99, \"category\": \"Food\"}";
                    sendJsonResponse(exchange, response, 200);
                    System.out.println(" GET /api/menu/" + id + " - Returned menu item");
                }
            }
        });

        
        server.createContext("/api/orders", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if ("POST".equals(exchange.getRequestMethod())) {
                    String requestBody = readRequestBody(exchange);
                    String response = "{\"status\": \"success\", \"message\": \"Order created\", \"orderId\": 1001, \"data\": " + requestBody + "}";
                    sendJsonResponse(exchange, response, 201);
                    System.out.println(" POST /api/orders - Created order: " + requestBody);
                }
            }
        });

        server.start();
        
        System.out.println("=================================================");
        System.out.println("  REST Endpoints Available:");
        System.out.println("   GET  http://localhost:8090/api/menu");
        System.out.println("   GET  http://localhost:8090/api/menu/{id}");
        System.out.println("   POST http://localhost:8090/api/orders");
        System.out.println("=================================================");
    }
    
    private static void sendJsonResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
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