package com.mycompany.membercards.web.rest;

import com.mycompany.membercards.domain.Card;
import com.mycompany.membercards.repository.CardRepository;
import com.mycompany.membercards.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.membercards.domain.Card}.
 */
@RestController
@RequestMapping("/api/cards")
@Transactional
public class CardResource {

    private static final Logger LOG = LoggerFactory.getLogger(CardResource.class);

    private static final String ENTITY_NAME = "card";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CardRepository cardRepository;

    public CardResource(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * {@code POST  /cards} : Create a new card.
     *
     * @param card the card to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new card, or with status {@code 400 (Bad Request)} if the card has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Card> createCard(@Valid @RequestBody Card card) throws URISyntaxException {
        LOG.debug("REST request to save Card : {}", card);
        if (card.getId() != null) {
            throw new BadRequestAlertException("A new card cannot already have an ID", ENTITY_NAME, "idexists");
        }
        card = cardRepository.save(card);
        return ResponseEntity.created(new URI("/api/cards/" + card.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, card.getId().toString()))
            .body(card);
    }

    /**
     * {@code PUT  /cards/:id} : Updates an existing card.
     *
     * @param id the id of the card to save.
     * @param card the card to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated card,
     * or with status {@code 400 (Bad Request)} if the card is not valid,
     * or with status {@code 500 (Internal Server Error)} if the card couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Card card)
        throws URISyntaxException {
        LOG.debug("REST request to update Card : {}, {}", id, card);
        if (card.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, card.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        card = cardRepository.save(card);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, card.getId().toString()))
            .body(card);
    }

    /**
     * {@code PATCH  /cards/:id} : Partial updates given fields of an existing card, field will ignore if it is null
     *
     * @param id the id of the card to save.
     * @param card the card to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated card,
     * or with status {@code 400 (Bad Request)} if the card is not valid,
     * or with status {@code 404 (Not Found)} if the card is not found,
     * or with status {@code 500 (Internal Server Error)} if the card couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Card> partialUpdateCard(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Card card
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Card partially : {}, {}", id, card);
        if (card.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, card.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Card> result = cardRepository
            .findById(card.getId())
            .map(existingCard -> {
                if (card.getCardNumber() != null) {
                    existingCard.setCardNumber(card.getCardNumber());
                }
                if (card.getExpirationDate() != null) {
                    existingCard.setExpirationDate(card.getExpirationDate());
                }
                if (card.getCvv() != null) {
                    existingCard.setCvv(card.getCvv());
                }

                return existingCard;
            })
            .map(cardRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, card.getId().toString())
        );
    }

    /**
     * {@code GET  /cards} : get all the cards.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cards in body.
     */
    @GetMapping("")
    public List<Card> getAllCards() {
        LOG.debug("REST request to get all Cards");
        return cardRepository.findAll();
    }

    /**
     * {@code GET  /cards/:id} : get the "id" card.
     *
     * @param id the id of the card to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the card, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCard(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Card : {}", id);
        Optional<Card> card = cardRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(card);
    }

    /**
     * {@code DELETE  /cards/:id} : delete the "id" card.
     *
     * @param id the id of the card to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Card : {}", id);
        cardRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
