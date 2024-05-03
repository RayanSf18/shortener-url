package br.com.rayansf.urlshortener.domain.url;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "urls")
public class Url {

  @Id
  private String id;

  private String fullUrl;

  @Indexed(expireAfterSeconds = 0)
  private LocalDateTime expiresAt;

}
