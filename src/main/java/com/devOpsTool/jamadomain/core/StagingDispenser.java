package com.devOpsTool.jamadomain.core;

import com.devOpsTool.JamaParent;
import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamadomain.lazyresources.JamaItem;
import com.devOpsTool.jamadomain.lazyresources.JamaItemType;
import com.devOpsTool.jamadomain.lazyresources.JamaRelationship;
import com.devOpsTool.jamadomain.stagingresources.StagingFactory;
import com.devOpsTool.jamadomain.stagingresources.StagingItem;
import com.devOpsTool.jamadomain.stagingresources.StagingRelationship;

public class StagingDispenser extends StagingFactory {

    StagingDispenser() {}

    @Override
    protected StagingItem createStagingItem() {
        return super.createStagingItem();
    }

    @Override
    protected StagingItem createStagingItem(JamaItem jamaItem) throws RestClientException {
        return super.createStagingItem(jamaItem);
    }

    @Override
    protected StagingItem createStagingItem(JamaInstance jamaInstance, String name, JamaParent parent, JamaItemType itemType) throws RestClientException {
        return super.createStagingItem(jamaInstance, name, parent, itemType);
    }


    @Override
    protected StagingRelationship createStagingRelationship() {
        return super.createStagingRelationship();
    }

    @Override
    protected StagingRelationship createStagingRelationship(JamaRelationship jamaRelationship) throws RestClientException {
        return super.createStagingRelationship(jamaRelationship);
    }
}
