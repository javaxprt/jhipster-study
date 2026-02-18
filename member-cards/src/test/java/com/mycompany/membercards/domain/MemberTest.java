package com.mycompany.membercards.domain;

import static com.mycompany.membercards.domain.CardTestSamples.*;
import static com.mycompany.membercards.domain.MemberTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.membercards.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Member.class);
        Member member1 = getMemberSample1();
        Member member2 = new Member();
        assertThat(member1).isNotEqualTo(member2);

        member2.setId(member1.getId());
        assertThat(member1).isEqualTo(member2);

        member2 = getMemberSample2();
        assertThat(member1).isNotEqualTo(member2);
    }

    @Test
    void cardTest() {
        Member member = getMemberRandomSampleGenerator();
        Card cardBack = getCardRandomSampleGenerator();

        member.addCard(cardBack);
        assertThat(member.getCards()).containsOnly(cardBack);
        assertThat(cardBack.getMember()).isEqualTo(member);

        member.removeCard(cardBack);
        assertThat(member.getCards()).doesNotContain(cardBack);
        assertThat(cardBack.getMember()).isNull();

        member.cards(new HashSet<>(Set.of(cardBack)));
        assertThat(member.getCards()).containsOnly(cardBack);
        assertThat(cardBack.getMember()).isEqualTo(member);

        member.setCards(new HashSet<>());
        assertThat(member.getCards()).doesNotContain(cardBack);
        assertThat(cardBack.getMember()).isNull();
    }
}
