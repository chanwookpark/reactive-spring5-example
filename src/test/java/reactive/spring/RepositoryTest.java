package reactive.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void contextLoads() {
        assert repository != null;
    }

    @Test
    @Sql("/shcema.sql")
    public void querySingleByAsync() throws Exception {
        final Observable<User> observable = repository.findOne("chanwook");

        final User user = observable.toBlocking().single();

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("박찬욱");
    }
}
