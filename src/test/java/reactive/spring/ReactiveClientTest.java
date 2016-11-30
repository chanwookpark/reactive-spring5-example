package reactive.spring;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.BodyExtractors;
import org.springframework.web.client.reactive.ClientRequest;
import org.springframework.web.client.reactive.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author chanwook
 */
public class ReactiveClientTest {

    @Test
    public void getUser() throws Exception {
        WebClient webClient = WebClient.create(new ReactorClientHttpConnector());
        final ClientRequest<Void> request =
                ClientRequest
                        .GET("http://localhost:8000/user/chanwook")
                        .accept(MediaType.APPLICATION_JSON) //너로 해줘야 하는거자나.. 안되네..
                        .header(HttpHeaders.CONTENT_TYPE, "application/json") // 혹시나 했는데 당연히 너는 아니지..
                        .build();

        // FIXME 테스트 실패 이유 기록
        // BodyExtractors.readWithMessageReaders(..)를 들어가보면 응답 헤더에서 Content-type을 열어보는데 이게 없다
        // ReactorClientHttpResponse.response.headers 에는 제대로 있으나 ReactorClientHttpResponse.response.responseState.headers 에는 없다..
        // 잘못된 건지 내가 모르는건지 좀 더 봐야알듯..@@

        final Mono<User> mono = webClient.exchange(request)
                .then(response -> response.body(BodyExtractors.toMono(User.class)));
        final User user = mono.block(); //이렇게 해야 하는 건가??

        assert mono != null;
        assert "chanwook".equals(user.getId());
        assert "박찬욱".equals(user.getName());
    }
}
