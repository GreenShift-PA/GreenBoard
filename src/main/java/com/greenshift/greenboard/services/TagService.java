package com.greenshift.greenboard.services;

import com.greenshift.greenboard.models.entities.Tag;

import java.util.Arrays;

public class TagService extends BaseCrudService<Tag> {

    public TagService(String baseUrl) {
        super(baseUrl);
    }

    public static void main(String[] args) {
        TagService tagService = new TagService("http://localhost:3000/api/v1/tags");
        String tagId = "0892bff6-be0e-4157-a620-9fab2f38a976";

        Tag tag = tagService.getById(tagId, Tag.class);
        System.out.println("Tag: " + tag);

        tag.setName("Manga");
        tag.setColor("#000000");
        Tag updatedTag = tagService.update(tag, Tag.class);
        System.out.println("Updated Tag: " + updatedTag);

        tag.setName("New Tag");
        tag.setId(null);
        Tag newTag = tagService.create(tag, Tag.class);
        System.out.println("New Tag: " + newTag);

        Tag[] tags = tagService.getAll(Tag[].class);
        System.out.println("All Tags: " + Arrays.toString(tags));

        Tag deletedTag = tagService.delete(newTag.getId(), Tag.class);
        System.out.println("Deleted Tag: " + deletedTag);
    }

    @Override
    protected String getEntityId(Tag entity) {
        return entity.getId();
    }
}
