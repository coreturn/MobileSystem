package com.example.momentweb.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class CommentResponseBody {
    private ArrayList<String> comments;

    private int code;
}