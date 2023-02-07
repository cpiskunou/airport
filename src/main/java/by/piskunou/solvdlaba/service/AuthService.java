package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.AuthEntity;
import by.piskunou.solvdlaba.domain.User;

public interface AuthService {

    AuthEntity signUp(User user);

    AuthEntity login(User user);

    AuthEntity refresh(AuthEntity authEntity);

}
