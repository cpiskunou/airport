package by.piskunou.solvdlaba.web.security.expression;

public class CustomMethodSecurityExpressionRoot { //extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

//    @Setter
//    private TicketService ticketService;
//
//    @Setter
//    private UserService userService;
//
//    private HttpServletRequest request;
//
//    @Getter
//    @Setter
//    private Object filterObject;
//
//    @Setter
//    @Getter
//    private Object returnObject;
//
//    private Object target;
//
//    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
//        super(authentication);
//    }
//
//    public boolean hasOwner(long userId, long ticketId) {
//        return ticketService.isOwner(ticketId, userId);
//    }
//
//    public boolean hasUser(long userId) {
//        User user = userService.findById(userId);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication.getName().equals(user.getUsername());
//    }
//
//
//    @Override
//    public Object getThis() {
//        return target;
//    }

}
