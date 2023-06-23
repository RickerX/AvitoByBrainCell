package com.example.avitobybraincell.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.avitobybraincell.model.Advert;

@Repository

public interface AdvertRepository extends JpaRepository<Advert, Integer> {
}
