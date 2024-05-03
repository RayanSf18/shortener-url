package br.com.rayansf.urlshortener;

import br.com.rayansf.urlshortener.domain.url.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {
}
