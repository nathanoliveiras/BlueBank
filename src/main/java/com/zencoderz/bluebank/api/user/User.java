package com.zencoderz.bluebank.api.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.zencoderz.bluebank.api.account.Account;
import com.zencoderz.bluebank.api.user.attributes.Authority;
import com.zencoderz.bluebank.api.user.attributes.IdentifierType;

import javax.persistence.*;

import java.util.UUID;

@Entity
@Table(name="user", indexes = {@Index(name = "identifier_identifier_type_index", columnList = "identifier, identifierType", unique = true)})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    private Authority authority = Authority.APPUSER;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private IdentifierType identifierType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "user")
    private Account account;

    public User() {}

    public User(UUID id, String name, String username, String password, Authority authority, String identifier, IdentifierType identifierType, Account account) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.identifier = identifier;
        this.identifierType = identifierType;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public IdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(IdentifierType identifierType) {
        this.identifierType = identifierType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
