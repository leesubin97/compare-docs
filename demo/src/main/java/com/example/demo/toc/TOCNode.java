package com.example.demo.toc;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Data
@Builder
public class TOCNode {
    private TOCType type;
    @Builder.Default private TOCDiff diff = TOCDiff.SAME;

    private int depth;

    private int index;

    private int targetIndex;

    private String head;

    private String topContent;

    private String division;

    private String chapter;

    @Builder.Default private String name = "";

    @Builder.Default private String title = "";

    @Builder.Default private String contents = "";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Builder.Default private List<TOCNode> childs = new ArrayList<>();

    @JsonIgnore public TOCNode getLastNode() {
        if (childs.size() == 0) 
            return this;
        return childs
            .get(childs.size() - 1)
            .getLastNode();
    }

    public List<TOCNode> getAllChilds(List<TOCNode> list) {
        list.add(
            TOCNode.builder().depth(depth).index(index).head(head).topContent(topContent).chapter(chapter).name(
                name + title + contents
            ).contents(contents).build()
        );
        for (TOCNode child : childs) 
            child.getAllChilds(list);
        return list;
    }

    public TOCNode findParent(TOCType type) {
        if (childs.stream().anyMatch(node ->node.type == type)) 
            return this;
        if (childs.size() == 0) 
            return this;
        return childs
            .get(childs.size() - 1)
            .findParent(type);
    }

    public void addLine(String line) {
        if (line.isBlank() || line.isEmpty()) 
            return;
        if (title.isBlank() || title.isEmpty()) 
            title = line;
        else 
            contents += line + "  ";
        }
    
    public void print() {
        StringBuilder sb = new StringBuilder();
        makeString(sb);
        System
            .out
            .println(sb);
    }

    public void saveToText(String path) {
        StringBuilder sb = new StringBuilder();
        makeString(sb);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public StringBuilder makeString(StringBuilder sb) {
        sb
            .append("\t".repeat(depth))
            .append(name)
            .append(" ")
            .append(title)
            .append("\n");
        if (!contents.isEmpty()) {
            sb
                .append("\t".repeat(depth + 1))
                .append("- " + contents)
                .append("\n");
        }
        for (TOCNode node : childs) {
            node.makeString(sb);
        }
        return sb;
    }

    public StringBuilder makeJSONString(StringBuilder sb) {
        sb
            .append("{\"name\":\"")
            .append(name.replace('"', '\'').replace("\n", ""))
            .append(" ")
            .append(title.replace('"', '\'').replace("\n", "\\n"))
            .append("\"");
        if (!contents.isEmpty()) {
            sb
                .append(",")
                .append("\"description\":\"")
                .append(contents.replace('"', '\'').replace("\n", "\\n"))
                .append("\"");
        }

        if (childs.size() > 0) {
            sb
                .append(",")
                .append("\"childs\": [");
            for (TOCNode node : childs) {
                node.makeJSONString(sb);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
        }
        sb.append("}");
        return sb;
    }

    private int getMaxDepth() {
        if (childs.size() == 0) 
            return this.depth;
        return childs
            .stream()
            .map(x ->x.getMaxDepth())
            .reduce((a, b) -> Math.max(a, b))
            .get();
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class NodeJSONL {
    int depth;
    int index;
    String id;
    String plant;
    String spec_no;
    String title;
    String text;
    TOCType type;
}