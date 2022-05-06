package com.example.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class KnowledgeAndOutlineTree {
    private String title;
    private String value;
    private List<TreeNode> children;
}
