import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TemplateHandler {

    private final ServletContext context;

    public TemplateHandler(ServletContext context) {
        this.context = context;
    }


    public void hanlde(String templateName,
                       Map<String, String> params,
                       Writer writer) {
        try (InputStream is = context.getResourceAsStream("/WEB-INF/classes/templates/" + templateName)) {

            String template = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            for (Map.Entry<String, String> entry : params.entrySet()) {
                String param = "${" + entry.getKey() + "}";
                template = template.replace(param, entry.getValue());
            }

            writer.write(template);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// 1. Найти файл по имени templateName
// 2. Прочитать файл в строку
// 3. Найти в файле ${param_name} и заменить на значения параметра
// 4. Передать строку во writer
