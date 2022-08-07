package com.cooksysteam1.socialmedia.repository;

import com.cooksysteam1.socialmedia.entity.Hashtag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
	
	boolean existsByLabel(String label);

	Optional<Hashtag> findHashtagByLabelIgnoreCase(String label);
}
