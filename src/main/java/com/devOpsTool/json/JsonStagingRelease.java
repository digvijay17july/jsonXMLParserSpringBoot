package com.devOpsTool.json;

import com.devOpsTool.jamaServices.JamaDomainObject;
import com.devOpsTool.jamadomain.stagingresources.StagingRelease;

public class JsonStagingRelease extends StagingRelease {

    JsonStagingRelease() {}

    @Override
    public void writeContentTo(JamaDomainObject jamaDomainObject) {
        super.writeContentTo(jamaDomainObject);
    }

    public JsonStagingRelease setItemCount(int itemCount){
        this.itemCount = itemCount;
        return this;
    }

}
