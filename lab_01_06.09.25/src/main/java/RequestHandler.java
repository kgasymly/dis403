import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.TreeMap;


public class RequestHandler {

    final static Logger logger = LogManager.getLogger(RequestHandler.class);

    public void handle(Socket clientSocket) {
        try {
            // Поток для чтения данных от клиента
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            // Читаем пакет от клиента
            String lineOne = reader.readLine();
            System.out.println(lineOne);
            logger.debug(lineOne);
            String[] components = lineOne.split(" ");
            //TODO реализовать определение метода (GET, POST,...) для передачи как параметра в сервис
            // http://localhost:8080/resource/part?name=tat&region=16
            // URI /resource/part
            //
            // При наличии извлечь параметры и поместить в Map
            String method = components[0];
            String resource = components[1];

            // Проверка есть ли параметр
            String[] params = null;
            if (resource.contains("?")) {
                String[] parts = resource.split("\\?");
                params = parts[1].split("&");
                resource = parts[0];
            }
            TreeMap<String, String> paramsMap = new TreeMap<>();
            if (params != null) {
                logger.debug(Arrays.toString(params));
                for (String param : params) {
                    String[] parts = param.split("=", 2);
                    String key = parts[0];
                    String value = parts.length > 1 ? parts[1] : ""; // проверяется, есть ли вторая часть,
                                                                     //если нет - пустая строка.
                    paramsMap.put(key, value);
                }
            }

            if (resource.equals("/shutdown")) {
                logger.info("server stopped by client");
                //break;
            }
            while (true) {
                // Читаем пакет от клиента
                String message = reader.readLine();
                System.out.println(message);
                logger.debug(message);

                if (message.isEmpty()) {
                    logger.debug("end of request header");
                    OutputStream os = clientSocket.getOutputStream();
                    logger.debug("outputStream" + os);
                    IResourceService resourceService = Application.resourceMap.get(resource);
                    if (resourceService != null) {
                        // TODO передавать метод, передавать Map с параметрами в функцию service
                        resourceService.service(method, null, os);
                    } else {
                        new NotFoundService().service(method, null, os);
                    }
                    os.flush();
                    logger.debug("outputStream" + os);
                    break;
                }

                //clientSocket.close();
            }
        } catch (IOException e) {
            logger.atError().withThrowable(e);
        }

    }

}
