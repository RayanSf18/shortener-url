package br.com.rayansf.urlshortener.controller;

import br.com.rayansf.urlshortener.UrlRepository;
import br.com.rayansf.urlshortener.domain.url.ShortenUrlRequest;
import br.com.rayansf.urlshortener.domain.url.ShortenUrlResponse;
import br.com.rayansf.urlshortener.domain.url.Url;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class UrlController {

  private final UrlRepository urlRepository;

  @PostMapping(path = "/shorten-url")
  public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest urlRequest, HttpServletRequest servletRequest) {
    String id;
    do {
      id = RandomStringUtils.randomAlphanumeric(5, 10);
    } while (urlRepository.existsById(id));
    var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);
    this.urlRepository.save(new Url(id, urlRequest.url(), LocalDateTime.now().plusMinutes(1)));
    return ResponseEntity.status(HttpStatus.OK).body(new ShortenUrlResponse(redirectUrl));
  }

  @GetMapping("{id}")
  public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
    var url = this.urlRepository.findById(id);
    if (url.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(URI.create(url.get().getFullUrl()));
    return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).build();
  }

}
