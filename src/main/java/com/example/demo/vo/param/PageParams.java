package com.example.demo.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageParams {
    private int page = 1;
    private int pageSize = 10;
}
