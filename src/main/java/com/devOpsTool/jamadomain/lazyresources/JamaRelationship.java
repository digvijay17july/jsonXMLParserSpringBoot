package com.devOpsTool.jamadomain.lazyresources;

import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamaServices.JamaDomainObject;
import com.devOpsTool.jamadomain.core.LazyResource;
import com.devOpsTool.jamadomain.stagingresources.StagingRelationship;

public class JamaRelationship extends LazyResource {
    protected JamaItem toItem;
    protected JamaItem fromItem;
    protected JamaRelationshipType relationshipType;
    protected boolean suspect;

    @Override
    protected String getResourceUrl() {
        return "relationships/" + getId();
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(JamaRelationship.class, jamaDomainObject);

        JamaRelationship jamaRelationship = (JamaRelationship)jamaDomainObject;
        toItem = jamaRelationship.toItem;
        fromItem = jamaRelationship.fromItem;
        relationshipType = jamaRelationship.relationshipType;
        suspect = jamaRelationship.suspect;
    }

    public JamaRelationship(JamaRelationship relationship) {
        copyContentFrom(relationship);
    }

    public JamaRelationship(){}

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(JamaRelationship.class, jamaDomainObject);
        ((JamaRelationship)jamaDomainObject).copyContentFrom(this);
    }

    public JamaItem getToItem() {
        fetch();
        return toItem;
    }

    public JamaItem getFromItem() {
        fetch();
        return fromItem;
    }

    public JamaRelationshipType getRelationshipType() {
        fetch();
        return relationshipType;
    }

    public boolean isSuspect() {
        fetch();
        return suspect;
    }

    public StagingRelationship edit() throws RestClientException {
        return jamaInstance.editRelationship(this);
    }

}
