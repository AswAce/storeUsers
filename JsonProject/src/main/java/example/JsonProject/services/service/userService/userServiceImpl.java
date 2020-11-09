package example.JsonProject.services.service.userService;

import example.JsonProject.models.User;
import example.JsonProject.repositories.UserRepository;
import example.JsonProject.services.seed.UserSeedJSON;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class userServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public userServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insertUser(UserSeedJSON userSeed) {
        User user = this.modelMapper.map(userSeed, User.class);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        long randomId = random.nextInt((int) this.userRepository.count()) + 1;
        return this.userRepository.findById(randomId);
    }

    @Override
    public User getUSerFromId(long id) {
        return this.userRepository.findById(id);
    }
}
