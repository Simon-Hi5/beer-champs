package at.ac.uibk.beerchamps.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ErrorControllerImpl implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object errorCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int code = Integer.parseInt(errorCode.toString());
        Calendar calendar = Calendar.getInstance(request.getLocale());
        Date timestamp = calendar.getTime();

        model.addAttribute("status", errorCode);
        model.addAttribute("error", HttpStatus.valueOf(code));
        model.addAttribute("timestamp", timestamp);
        model.addAttribute("message", request.getAttribute("javax.servlet.error.exception"));
        model.addAttribute("path", request.getAttribute("javax.servlet.forward.request_uri"));

        return "error";
    }
}
