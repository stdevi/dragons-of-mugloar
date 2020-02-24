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
        log.warn("\uD83D\uDD25Game over\uD83D\uDD25");
    }

    @AfterReturning(
            value = "execution(* com.stdevi.dragonsofmugloar.controller.AdController.solveOptimalAd(..))",
            returning = "adResult")
    public void printAdResult(AdResult adResult) {
        String result = adResult.isSuccess() ? "\n\uD83D\uDD3A {}" : "\n\uD83D\uDD3B {}";
        log.info(result, adResult.getMessage());
    }

    @After("execution(* com.stdevi.dragonsofmugloar.controller.AdController.solveOptimalAd(..)) && args(game,..)")
    public void printStats(Game game) {
        log.info("\n\uD83D\uDDA4{} \uD83D\uDCB2{} \uD83D\uDC09{} \uD83E\uDD47{}",
                game.getLives(), game.getGold(), game.getLevel(), game.getScore());
    }
}
