package com.devOpsTool.jamadomain.stagingresources;

import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamaServices.JamaDomainObject;
import com.devOpsTool.jamaServices.JamaInstance;
import com.devOpsTool.jamadomain.lazyresources.JamaItem;
import com.devOpsTool.jamadomain.lazyresources.JamaRelationship;
import com.devOpsTool.jamadomain.lazyresources.JamaRelationshipType;

public class StagingRelationship extends JamaRelationship implements StagingResource {
    final private JamaRelationship originatingRelationship;
    private boolean invalid = false;


    @Override
    public JamaItem getToItem() {
        return toItem;
    }

    @Override
    public JamaItem getFromItem() {
        return fromItem;
    }

    @Override
    protected String getEditUrl() throws RestClientException {
        return "relationships/" + getId();
    }

    @Override
    protected String getCreateUrl() throws RestClientException {
        return "relationships/";
    }

    @Override
    protected String getDeleteUrl() throws RestClientException {
        return "relationships/" + getId();
    }

    private void testValidity() throws RestClientException {
        if(invalid) {
            throw new RestClientException("Staging relationship may not be accessed after commit is called.");
        }
    }

    protected StagingRelationship(){
        originatingRelationship = null;
    }

    protected StagingRelationship(JamaRelationship relationship) throws RestClientException {
        super(relationship);
        if(relationship.getId() != null)
            super.associate(relationship.getId(), relationship.getJamaInstance());
        else
            super.associate(relationship.getJamaInstance());
        originatingRelationship = relationship;
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        super.copyContentFrom(jamaDomainObject);
    }


    public StagingRelationship setRelationshipType(JamaRelationshipType relationshipType) throws RestClientException {
        testValidity();
        this.relationshipType = relationshipType;
        return this;
    }

    public StagingRelationship setFromItem(JamaItem item) throws RestClientException {
        testValidity();
        this.fromItem = item;
        return this;
    }

    public StagingRelationship setToItem(JamaItem item) throws RestClientException {
        testValidity();
        this.toItem = item;
        return this;
    }


    @Override
    public void associate(int id, JamaInstance jamaInstance) throws RestClientException {
        testValidity();
        throw new RestClientException("You cannot associate a relationship while it is in edit mode.");
    }

}
