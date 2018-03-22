package io.github.josephmtinangi.urlshortener.repositories;

import io.github.josephmtinangi.urlshortener.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    public Url findFirstByOriginalUrl(String url);
}
