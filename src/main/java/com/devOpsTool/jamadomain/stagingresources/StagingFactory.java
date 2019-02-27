package com.devOpsTool.jamadomain.stagingresources;

import com.devOpsTool.JamaParent;
import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamadomain.core.JamaInstance;
import com.devOpsTool.jamadomain.lazyresources.JamaItem;
import com.devOpsTool.jamadomain.lazyresources.JamaItemType;
import com.devOpsTool.jamadomain.lazyresources.JamaRelationship;

public class StagingFactory {

    protected StagingItem createStagingItem() { return new StagingItem(); }

    protected StagingItem createStagingItem(JamaItem jamaItem) throws RestClientException {
        return new StagingItem(jamaItem);
    }

    protected StagingItem createStagingItem(JamaInstance jamaInstance, String name, JamaParent parent, JamaItemType itemType) throws RestClientException {
        return new StagingItem(jamaInstance, name, parent, itemType);
    }

    protected StagingRelationship createStagingRelationship() { return new StagingRelationship(); }

    protected StagingRelationship createStagingRelationship(JamaRelationship jamaRelationship) throws RestClientException {
        return new StagingRelationship(jamaRelationship);
    }
}
