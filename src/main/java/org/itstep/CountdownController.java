package org.itstep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
public class CountdownController {

    @Autowired
    private CountdownService countdownService;

    @GetMapping(value ="/list")
    public String index(Model model) {
        model.addAttribute("countdowns", countdownService.findAll());
        model.addAttribute("countdown",new Countdown());
        return "list";
    }

    @PostMapping(value="/list")
    public String add(Countdown countdown, Model model, HttpServletResponse response) {
        //Передать id в заголовке ответа
        Countdown newCountdown =countdownService.save(countdown);
        long id = newCountdown.getId();
        response.addHeader("id", String.valueOf(id));
        model.addAttribute("countdowns", countdownService.findAll());
        return "redirect:/list";
    }

    @PostMapping(value="/start")
    public String start(Model model, @RequestParam(name="id") Long id) {
        Countdown countdown = countdownService.findById(id);
        Calendar time = countdown.getTime();
        Calendar now = new GregorianCalendar();
        boolean available = true;
        if (time.compareTo(now)<0) available = false; //Доступен ли ресурс
        model.addAttribute("countdown",countdown); //Синхронизация
        model.addAttribute("available",available);
        return "start";
    }
    @DeleteMapping(value="/delete")
    public String delete(@RequestParam(name="id") Long id) {
        countdownService.deleteById(id);
        return "redirect:/list";
    }
}