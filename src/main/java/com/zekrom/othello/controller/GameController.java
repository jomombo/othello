package com.zekrom.othello.controller;

import com.zekrom.othello.model.Board;
import com.zekrom.othello.model.Game;
import com.zekrom.othello.model.User;
import com.zekrom.othello.service.GameService;
import com.zekrom.othello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class GameController {

    private Game game;

    private Board board;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

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
        board.cryptMatrice();

        Game save = gameService.save(game);

        return new RedirectView("/othello");
    }

    @GetMapping("pass")
    public RedirectView pass(){
        board.pass();

        return new RedirectView("/othello");
    }


    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        String loggedInUsername = principal.getName();
        User loggedInUser = (User) userService.loadUserByUsername(loggedInUsername);

        List<Game> loggedInUserGames = gameService.findAllByUsername(loggedInUsername);

        model.addAttribute("games", loggedInUserGames);

        return "home";
    }


    @GetMapping("new")
    public RedirectView newGame(Principal principal) {
        board = Board.initBasicGame(8,8);
        String loggedInUsername = principal.getName();
        User loggedInUser = (User) userService.loadUserByUsername(loggedInUsername);

        board.cryptMatrice();
        Game game = new Game(loggedInUser.getUsername(), board);
        gameService.save(game);

        return new RedirectView("/home");
    }

    @GetMapping("othello/{id}")
    public RedirectView gamebyId(@PathVariable("id")Long id, Model model) {
        game = gameService.findById(id).get();
        board = game.getBoard();
        board.decryptMatrice();


        model.addAttribute("matrice", board);

        return new RedirectView("/othello");
    }

}
