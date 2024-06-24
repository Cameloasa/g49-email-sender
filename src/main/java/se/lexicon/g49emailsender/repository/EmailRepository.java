package se.lexicon.g49emailsender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g49emailsender.domain.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
