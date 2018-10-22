package br.com.leogsouza.awesome.repository;

import br.com.leogsouza.awesome.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);
}
