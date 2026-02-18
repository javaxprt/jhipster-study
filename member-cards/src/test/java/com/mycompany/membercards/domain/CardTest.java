package com.mycompany.membercards.domain;

import static com.mycompany.membercards.domain.CardTestSamples.*;
import static com.mycompany.membercards.domain.MemberTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.membercards.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Card.class);
        Card card1 = getCardSample1();
        Card card2 = new Card();
        assertThat(card1).isNotEqualTo(card2);

        card2.setId(card1.getId());
        assertThat(card1).isEqualTo(card2);

        card2 = getCardSample2();
        assertThat(card1).isNotEqualTo(card2);
    }

    @Test
    void memberTest() {
        Card card = getCardRandomSampleGenerator();
        Member memberBack = getMemberRandomSampleGenerator();

        card.setMember(memberBack);
        assertThat(card.getMember()).isEqualTo(memberBack);

        card.member(null);
        assertThat(card.getMember()).isNull();
    }
}
