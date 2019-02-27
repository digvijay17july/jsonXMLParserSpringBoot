package com.devOpsTool.jamadomain.lazyresources;

import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamaServices.JamaDomainObject;
import com.devOpsTool.jamaServices.JamaInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemTypeList extends LazyCollection {
    private List<JamaItemType> itemTypeList;

    public ItemTypeList(JamaInstance jamaInstance) throws RestClientException {
        // this has an id of -1 because it doesn't have an Id associated with it in Jama
        associate(-1, jamaInstance);
    }

    @Override
    public String getCollectionUrl() {
        return "itemtypes";
    }

    @Override
    public void copyContentFrom(List<JamaDomainObject> objectList) {
        List<JamaItemType> itemTypes = new ArrayList<>();
        for(JamaDomainObject o : objectList) {
            itemTypes.add((JamaItemType) o);
        }

        this.itemTypeList = itemTypes;
    }

    public List<JamaItemType> getItemTypes() {
        fetch();
        return Collections.unmodifiableList(itemTypeList);
    }
}
