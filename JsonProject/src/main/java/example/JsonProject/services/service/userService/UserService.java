package example.JsonProject.services.service.userService;

import example.JsonProject.models.User;
import example.JsonProject.services.seed.UserSeedJSON;

public interface UserService {

    void insertUser(UserSeedJSON userSeed);

    User getRandomUser();

    User getUSerFromId(long id);
}
