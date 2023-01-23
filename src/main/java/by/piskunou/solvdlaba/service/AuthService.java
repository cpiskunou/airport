package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.AuthEntity;
import by.piskunou.solvdlaba.domain.User;

public interface AuthService {

    AuthEntity register(User user);

    AuthEntity authenticate(User user);

    AuthEntity refresh(AuthEntity authEntity);

    AuthEntity getNewAccessToken(AuthEntity authEntity);

}
