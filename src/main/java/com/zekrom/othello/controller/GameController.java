package com.zekrom.othello.controller;

import com.zekrom.othello.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GameController {

    private Board board;

    @GetMapping("othello")
    public String game(Model model) {

        model.addAttribute("matrice", board);

        return "othello";
    }

    @PostMapping("plays")
    @ModelAttribute(value = "choice")
    public RedirectView plays(Integer choice) {
        if (board.cannot(choice)) {
            return new RedirectView("/othello");
        }
        board.apply(choice);

        return new RedirectView("/othello");
    }

    @GetMapping("pass")
    public RedirectView pass(){
        board.pass();

        return new RedirectView("/othello");
    }

    @GetMapping("new")
    public RedirectView newGame(Model model) {
        board = Board.initBasicGame(8,8);

        model.addAttribute("matrice", board);

        return new RedirectView("/othello");
    }
}
