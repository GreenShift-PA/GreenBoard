package com.greenshift.greenboard.services;

import com.google.gson.reflect.TypeToken;
import com.greenshift.greenboard.models.entities.Tag;

import java.util.ArrayList;
import java.util.Arrays;

public class TagService extends BaseCrudService<Tag> {

    public TagService() {
        super("http://localhost:3000/api/v1/tags");
    }

    public static void main(String[] args) {
        TagService tagService = new TagService();
        String tagId = "0892bff6-be0e-4157-a620-9fab2f38a976";

        Tag tag = tagService.getById(tagId);
        System.out.println("Tag: " + tag);

        tag.setName("Manga");
        tag.setColor("#000000");
        Tag updatedTag = tagService.update(tag);
        System.out.println("Updated Tag: " + updatedTag);

        tag.setName("New Tag");
        tag.setId(null);
        Tag newTag = tagService.create(tag);
        System.out.println("New Tag: " + newTag);

        Tag[] tags = tagService.getAll();
        System.out.println("All Tags: " + Arrays.toString(tags));

        Tag deletedTag = tagService.delete(newTag.getId());
        System.out.println("Deleted Tag: " + deletedTag);
    }

    @Override
    protected String getEntityId(Tag entity) {
        return entity.getId();
    }

    @Override
    protected TypeToken<Tag> getTypeToken() {
        return new TypeToken<>() {
        };
    }

    @Override
    protected TypeToken<Tag[]> getArrayTypeToken() {
        return new TypeToken<>() {
        };
    }
}
