package com.timo.lingoapplication.session.presentation;


import com.timo.lingoapplication.session.application.SessionServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    private SessionServiceInterface sessionServiceInterface;

    public SessionController(SessionServiceInterface sessionServiceInterface) {
        this.sessionServiceInterface = sessionServiceInterface;
    }

}
