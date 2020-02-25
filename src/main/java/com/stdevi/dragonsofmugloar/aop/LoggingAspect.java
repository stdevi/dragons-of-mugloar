package com.stdevi.dragonsofmugloar.aop;

import com.stdevi.dragonsofmugloar.model.ad.AdResult;
import com.stdevi.dragonsofmugloar.model.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.stdevi.dragonsofmugloar.controller.GameController.completeGame(..))")
    public void printWelcomeMessage() {
        log.warn("Let's begin new Dragons of Mugloar game!");
    }

    @After(value = "execution(* com.stdevi.dragonsofmugloar.controller.GameController.completeGame(..))")
    public void printGameOver() {
        log.warn("Game over :(");
    }

    @AfterReturning(
            value = "execution(* com.stdevi.dragonsofmugloar.controller.AdController.solveOptimalAd(..))",
            returning = "adResult")
    public void printAdResult(AdResult adResult) {
        log.info("{}", adResult.getMessage());
    }

    @After("execution(* com.stdevi.dragonsofmugloar.controller.AdController.solveOptimalAd(..)) && args(game,..)")
    public void printStats(Game game) {
        log.info("Lives:{} Gold:{} Level:{} Score:{}",
                game.getLives(), game.getGold(), game.getLevel(), game.getScore());
    }
}
