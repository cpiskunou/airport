package by.piskunou.solvdlaba.security.expression;

import by.piskunou.solvdlaba.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    @Setter
    private TicketService ticketService;

    private HttpServletRequest request;

    @Getter
    @Setter
    private Object filterObject;

    @Setter
    @Getter
    private Object returnObject;

    private Object target;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean hasOwner(long userId, long ticketId) {
        return ticketService.isOwner(ticketId, userId);
    }

    @Override
    public Object getThis() {
        return target;
    }

}
