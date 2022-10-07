package laur.spilca.tuto.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
@Builder
@ToString
public class Otp {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username, otp;
}
