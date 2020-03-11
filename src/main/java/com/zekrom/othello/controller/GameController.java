package com.zekrom.othello.controller;

import com.zekrom.othello.model.Board;
import com.zekrom.othello.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GameController {

    private Board board;

    @GetMapping("othello")
    public String game(Model model) {

        model.addAttribute("matrice", board);

        return "othello";
    }

    @GetMapping("new")
    public RedirectView newGame(Model model) {
        board = Board.initBasicGame(4,4);

        model.addAttribute("matrice", board);

        return new RedirectView("/othello");
    }
}
