package laur.spilca.tuto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laur.spilca.tuto.entities.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer>{

	Optional<Otp> findOtpByUsername(String username);
}
