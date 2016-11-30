package reactive.spring;

import com.github.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rx.Observable;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author chanwook
 */
@Repository
public class UserRepository {

    private Database database;

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void init() {
        database = Database.fromDataSource(dataSource);
    }

    public Observable<User> findOne(String id) {
        final Observable<User> user = database.select("SELECT USER_ID, USER_NAME FROM USER WHERE USER_ID = ?")
                .parameter(id)
                .get(rs -> new User(rs.getString(1), rs.getString(2)));
        return user;
    }
}
