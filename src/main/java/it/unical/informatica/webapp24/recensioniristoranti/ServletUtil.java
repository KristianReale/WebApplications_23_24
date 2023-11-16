package it.unical.informatica.webapp24.recensioniristoranti;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ServletUtil {

    @RequestMapping(value = "/views/**", method = {RequestMethod.GET, RequestMethod.POST})
    public String templateHandler(HttpServletRequest request) {
        String resource = request.getRequestURI().substring("/views/".length());
        System.out.println(resource);
        resource = resource.substring(0, resource.indexOf(".html"));
        return resource;
    }

}