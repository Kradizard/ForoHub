package Alura.ForoHub.dominio.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
    boolean existsByTitle(String title);

    boolean existsByMessage(String message);

    @Query("SELECT t FROM Topic t WHERE t.status != 'ELIMINADO'")
    Page<Topico> findAll(Pageable page);

    @Query("SELECT t FROM Topic t WHERE t.status != 'ELIMINADO' AND t.id=:id")
    Optional<Topico> findId(Long id);
}
