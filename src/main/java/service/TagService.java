package service;

import database.DataBase;
import entities.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TagService {
    Scanner sc = new Scanner(System.in);

    private List setArticleTags() {
        List<Tag> tags = new ArrayList<>();
        System.out.println("Please enter the tags of the article: \n at the end enter -1");
        for (int i = 0; i < DataBase.tagList.getIndex(); i++) {
            Tag tag = (Tag) DataBase.tagList.getObjects(i);
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
                    DataBase.tagList.add(newTag);
                    System.out.println("New tags are there please choose a tag: \n at the end enter -1");
                    for (int j = 0; j < DataBase.tagList.getIndex(); j++) {
                        Tag tag = (Tag) DataBase.tagList.getObjects(j);
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
        for (int i = 0; i < DataBase.tagList.getIndex(); ++i) {
            Tag tempTag = (Tag) DataBase.tagList.getObjects(i);
            if (tempTag.getTitle().equals(title)) {
                return tempTag;
            }
        }
        System.out.println("That tag does not exist");
        return null;
    }

}
