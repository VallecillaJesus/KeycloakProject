package com.digitalmedia.users.repository;


import com.digitalmedia.users.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KeycloakRepository {

    @Autowired
    private Keycloak keycloak;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${keycloak.realm}")
    private String realm;

    public List<UserRepresentation> getAllUsers(){
        RealmResource realm = this.keycloak.realm(this.realm);
        List<GroupRepresentation> foundGroups = new ArrayList<>(realm.groups().groups());
        foundGroups.removeIf( group -> group.getName().equals("admin"));
        List<UserRepresentation> users = new ArrayList<>();
        foundGroups.forEach(
                groupRepresentation -> users.addAll(
                        realm.users().search(groupRepresentation.getName())
                )
        );
        return users;
    }

}
