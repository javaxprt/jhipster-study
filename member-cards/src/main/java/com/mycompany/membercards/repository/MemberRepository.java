package com.mycompany.membercards.repository;

import com.mycompany.membercards.domain.Member;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Member entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {}
