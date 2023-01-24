package by.piskunou.solvdlaba.web.security.expression;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@AllArgsConstructor
public class MethodSecurityConfig { //extends GlobalMethodSecurityConfiguration {

//    private ApplicationContext applicationContext;
//
//    @Bean
//    public AuthenticationTrustResolver trustResolver() {
//        return new AuthenticationTrustResolverImpl();
//    }
//
//    @Override
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        var expressionHandler = new CustomMethodSecurityExpressionHandler();
//        expressionHandler.setApplicationContext(applicationContext);
//        expressionHandler.setTrustResolver(trustResolver());
//        return expressionHandler;
//    }
//
//    @Override
//    protected AccessDecisionManager accessDecisionManager() {
//
//        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
//
//        var expressionAdvice= new ExpressionBasedPreInvocationAdvice();
//        expressionAdvice.setExpressionHandler(getExpressionHandler());
//
//        decisionVoters.add(new PreInvocationAuthorizationAdviceVoter(expressionAdvice));
//        decisionVoters.add(new AuthenticatedVoter());
//
//        return new AffirmativeBased(decisionVoters);
//    }

}
