package service;

import entities.Tag;
import repository.Imp.TagRepositoryImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TagService {
    Scanner sc = new Scanner(System.in);
    List<Tag> tagList = TagRepositoryImp.all();

    protected List setArticleTags() {
        List<Tag> tags = new ArrayList<>();
        System.out.println("Please enter the tags of the article: \n at the end enter -1");
        for (int i = 0; i < tagList.getIndex(); i++) {
            Tag tag = tagList.getObjects(i);
            System.out.println(tag.getTitle());
        }
        System.out.println("For add a tag enter 1");
        while (true) {
            String tagName = this.sc.nextLine();
            if (tagName.equals("-1")) {
                break;
            }
            if (tagName.equals("1")) {
                System.out.println("Please enter your tag name");
                String newTagName = this.sc.nextLine();
                if (findTagByTitle(newTagName) != null) {
                    System.out.println("Tag already exists");
                } else {
                    Tag newTag = new Tag(newTagName);
                    tagList.add(newTag);
                    System.out.println("New tags are there please choose a tag: \n at the end enter -1");
                    for (int j = 0; j < tagList.getIndex(); j++) {
                        Tag tag = (Tag) tagList.getObjects(j);
                        System.out.println(tag.getTitle());
                    }
                    System.out.println("For add a tag enter 1");
                }

            } else {
                Tag newTag = findTagByTitle(tagName);
                if (newTag != null) {
                    tagList.add(newTag);
                }
            }
        }
        return tagList;
    }

    private Tag findTagByTitle(String title) {
        for (int i = 0; i < tagList.getIndex(); ++i) {
            Tag tempTag = (Tag) tagList.getObjects(i);
            if (tempTag.getTitle().equals(title)) {
                return tempTag;
            }
        }
        System.out.println("That tag does not exist");
        return null;
    }

}
