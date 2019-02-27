package com.devOpsTool.jamadomain.core;

import com.devOpsTool.config.JamaConfig;
import com.devOpsTool.exception.RestClientException;
import com.devOpsTool.jamaclient.JamaClient;
import com.devOpsTool.jamadomain.lazyresources.*;
import com.devOpsTool.jamadomain.stagingresources.StagingItem;
import com.devOpsTool.jamadomain.stagingresources.StagingRelationship;
import com.devOpsTool.util.CompareUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JamaInstance implements JamaDomainObject {
    private JamaClient jamaClient;

    @Autowired
    private JamaConfig jamaConfig;
    private Integer resourceTimeOut;
    private JamaUser currentUser;
    private ItemTypeList itemTypeList;
    private RelationshipTypeList relationshipTypeList;

    private Map<String, WeakReference<JamaDomainObject>> resourcePool = new HashMap<>();

    public JamaInstance(JamaConfig jamaConfig) {
        this.jamaConfig = jamaConfig;
        this.resourceTimeOut = jamaConfig.getResourceTimeOut();
        this.jamaClient = new JamaClient(
                jamaConfig.getParseDao(),
                jamaConfig.getJson(),
                jamaConfig.getBaseUrl(),
                jamaConfig.getUsername(),
                jamaConfig.getPassword());
    }

    private JamaDomainObject getPoolOrNull(String key) {
        WeakReference<JamaDomainObject> wr = resourcePool.get(key);
        return wr == null ? null : wr.get();
    }

    public JamaDomainObject checkPool(Class clazz, int id) {
        return getPoolOrNull(clazz.getName() + id);
    }

    public void addToPool(Class clazz, int id, JamaDomainObject jamaDomainObject) {
        resourcePool.put(clazz.getName() + id, new WeakReference<>(jamaDomainObject));
    }

    private JamaDomainObject checkPool(JamaDomainObject fresh) {
        if (fresh instanceof LazyResource) {
            String key = fresh.getClass().getName() + ((LazyResource) fresh).getId();
            LazyResource existingResource = (LazyResource) getPoolOrNull(key);
            if (existingResource != null) {
                existingResource.copyContentFrom(fresh);
                return existingResource;
            }
            resourcePool.put(key, new WeakReference<>(fresh));
        }
        return fresh;
    }

    public JamaDomainObject getResource(String resource) throws RestClientException {
        JamaDomainObject retrieved = jamaClient.getResource(resource, this);
        return checkPool(retrieved);
    }

    public List<JamaDomainObject> getResourceCollection(String resource) throws RestClientException {
        return getAll(resource);
    }

    public List<JamaDomainObject> getAll(String resource) throws RestClientException {
        List<JamaDomainObject> objects = jamaClient.getAll(jamaConfig.getBaseUrl() + resource, this);
        for (int i = 0; i < objects.size(); ++i) {
            objects.set(i, checkPool(objects.get(i)));
        }
        return objects;
    }


    public JamaProject getProject(int id) throws RestClientException {
        String key = JamaProject.class.getName() + id;
        JamaProject project = (JamaProject) getPoolOrNull(key);
        if (project != null) {
            project.fetch();
        } else {
            project = new JamaProject();
            project.associate(id, this);
            resourcePool.put(key, new WeakReference<>((JamaDomainObject) project));
        }
        return project;
    }

    public List<JamaProject> getProjects() throws RestClientException {
        List<JamaProject> projects = new ArrayList<>();
        List<JamaDomainObject> jamaDomainObjects = getAll("projects");
        for (JamaDomainObject jamaDomainObject : jamaDomainObjects) {
            JamaProject project = (JamaProject) jamaDomainObject;
            projects.add(project);
        }
        return projects;
    }

    public List<JamaItemType> getItemTypes() throws RestClientException {
        if (itemTypeList == null) {
            itemTypeList = new ItemTypeList(this);
        }
        return itemTypeList.getItemTypes();
    }

    public JamaItemType getItemType(int id) throws RestClientException {
        JamaItemType itemType = new JamaItemType();
        itemType.associate(id, this);
        return itemType;
    }

    public JamaItemType getItemType(String name) throws RestClientException {
        List<JamaItemType> itemTypes = getItemTypes();
        JamaItemType found = null;
        for (JamaItemType itemType : itemTypes) {
            if (CompareUtil.closeEnough(name, itemType.getDisplay())) {
                if (found != null) {
                    throw new RestClientException("Multiple ItemTypes with the display: " + name);
                }
                found = itemType;
            }
        }
        return found;
    }

    public JamaItem getItem(int id) throws RestClientException {
        String key = JamaItem.class.getName() + id;
        JamaItem item = (JamaItem) getPoolOrNull(key);
        if (item != null) {
            item.fetch();
        } else {
            item = new JamaItem();
            item.associate(id, this);
            resourcePool.put(key, new WeakReference<>((JamaDomainObject) item));
        }
        return item;
    }

    public JamaRelationship getRelationship(int id) throws RestClientException {
        String key = JamaRelationship.class.getName() + id;
        JamaRelationship relationship = (JamaRelationship) getPoolOrNull(key);
        if (relationship != null) {
            relationship.fetch();
        } else {
            relationship = new JamaRelationship();
            relationship.associate(id, this);
            resourcePool.put(key, new WeakReference<>((JamaDomainObject) relationship));
        }
        return relationship;
    }
    public void ping() throws RestClientException {
        jamaClient.ping();
    }

    public Integer getResourceTimeOut() {
        return resourceTimeOut;
    }

    public JamaUser getCurrentUser() throws RestClientException {
        if (currentUser == null) {
            currentUser = (JamaUser) getResource("users/current");
        }
        return currentUser;
    }


    public String getOpenUrl(JamaItem item) {
        return this.jamaConfig.getOpenUrlBase() + item.getId() + "?project=" + item.getProject().getId();
    }

    public void setBaseOpenUrl(String baseOpenUrl) {
        this.jamaConfig.setOpenUrlBase(baseOpenUrl);
    }


    public StagingItem editItem(JamaItem jamaItem) throws RestClientException {
        jamaItem.fetch();
        return (new StagingDispenser()).createStagingItem(jamaItem);
    }

    public StagingRelationship editRelationship(JamaRelationship jamaRelationship) throws RestClientException {
        jamaRelationship.fetch();
        return (new StagingDispenser()).createStagingRelationship(jamaRelationship);
    }


    public List<JamaRelationshipType> getRelationshipTypes() throws RestClientException {
        if (relationshipTypeList == null) {
            relationshipTypeList = new RelationshipTypeList(this);
        }
        return relationshipTypeList.getRelationshipTypes();
    }
}
