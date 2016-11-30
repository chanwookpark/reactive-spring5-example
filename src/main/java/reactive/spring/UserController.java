package reactive.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

/**
 * @author chanwook
 */
@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    @RequestMapping("/user/{userId}/just")
    public Observable<User> getUserByJust(@PathVariable String userId) {
        return Observable.just(new User(userId, "박찬욱"));
    }

    @RequestMapping(value = "/user/{userId}", produces = {"application/json"})
    public Observable<User> getUserByDb(@PathVariable String userId) {
        final Observable<User> user = repository.findOne(userId);
        return user;
    }
}
