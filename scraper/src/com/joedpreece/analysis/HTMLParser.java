package com.joedpreece.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.joedpreece.objects.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

    public static Conversation parseHTMLFile(String filepath) throws IOException {
        Set<Contribution> contributions = new HashSet<>();
        File htmlFile = new File(filepath);
        if (htmlFile.exists()) {
            System.out.println(filepath + " opened successfully.");
        } else {
            throw new FileNotFoundException();
        }
        Document document = Jsoup.parse(htmlFile, null);
        Elements contributionElements = document.getElementsByClass("clearfix _42ef");
        for (Element contributionElement : contributionElements) {
            Elements timestampElements = contributionElement.getElementsByClass("_35 timestamp");
            Elements userElements = contributionElement.getElementsByClass("_36");
            Elements messageElements = contributionElement.getElementsByClass("_53");
            Elements imageElements = contributionElement.getElementsByTag("img");
            Date timestamp = new Date(Long.valueOf(timestampElements.attr("data-utime")));
            User user = new User(userElements.select("a[href]").text(), userElements.select("a[href]").attr("href"));
            ArrayList<Message> messages = new ArrayList<>();
            for (Element messageElement : messageElements) {
                Elements individualMessageElements = messageElement.getElementsByClass("pclass");
                for (Element individualMessageElement : individualMessageElements) {
                    messages.add(new StringMessage(individualMessageElement));
                }
            }
            if (!imageElements.isEmpty()) {
                messages.add(new ImageMessage(messageElements.select("src").html()));
            }
            contributions.add(new Contribution(timestamp, user, messages));
        }
        System.out.println(filepath + " parsed successfully.\n");
        return new Conversation(contributions);
    }

}
