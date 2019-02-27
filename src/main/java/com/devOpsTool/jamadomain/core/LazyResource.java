package com.devOpsTool.jamadomain.core;

import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamadomain.lazyresources.LazyBase;

public abstract class LazyResource extends LazyBase implements JamaDomainObject {
    public void forceFetch() throws RestClientException {
        markFetch();
        copyContentFrom(jamaInstance.getResource(getResourceUrl()));
    }

    protected abstract String getResourceUrl();

    protected abstract void copyContentFrom(JamaDomainObject jamaDomainObject);

    protected abstract void writeContentTo(JamaDomainObject jamaDomainObject);

    public boolean isAssociated() {
        return getId() != null && jamaInstance != null;
    }

    protected String getEditUrl() throws RestClientException {
        throw new RestClientException("Unable to edit " + this.getClass() + " for item " + this);
    }

    protected String getCreateUrl() throws RestClientException {
        throw new RestClientException("Unable to create a new " + this.getClass());
    }

    protected String getDeleteUrl() throws RestClientException {
        throw new RestClientException("Unable to delete " + this.getClass());
    };
}
