package project.first.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {


    @Pointcut("within(project.first.post.PostController)")
    public void postControllerMethods() {}

    @Pointcut("within(project.first.board.BoardController)")
    public void boardControllerMethods() {}

    @Pointcut("within(project.first.comment.CommentController)")
    public void commentControllerMethods() {}

    @Pointcut("within(project.first.FirstController)")
    public void firstControllerMethods() {}

    // 메서드 호출 전 로깅
    @Before("postControllerMethods() || boardControllerMethods() || firstControllerMethods() || commentControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[메서드 호출 전] 호출 메서드: {}", joinPoint.getSignature().toShortString());
    }

    // 메서드 호출 후 로깅
    @After("postControllerMethods() || boardControllerMethods() || firstControllerMethods() || commentControllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("[메서드 호출 후] 호출 메서드: {}", joinPoint.getSignature().toShortString());
    }

}
