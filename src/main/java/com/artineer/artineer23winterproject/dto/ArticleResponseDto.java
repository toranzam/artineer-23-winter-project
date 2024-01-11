package com.artineer.artineer23winterproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class ArticleResponseDto {

    private List<Integer> pageNumList;

    private PageDto pageDto;

    private boolean prev, next;

    private int prevPage, nextPage, totalPage;

    /* 객체가 만들어짐과 동시에 초기화 및 paging 알고리즘 적용 */
    @Builder
    public ArticleResponseDto(PageDto pageDto, long total) {
        this.pageDto = pageDto;
        this.totalPage = (int)total;

        /* 보여질 페이징 버튼 갯수 */
        int showPages = 3; // = 10

        /* 현재 보여지는 페이지가 속한 그룹을 계산 */
        int group = (int)Math.ceil((double) pageDto.getPage() / showPages);

        /* 보여줄 페이징 버튼의 시작 숫자*/
        int start = (group - 1) * showPages + 1;

        /* 보여줄 페이징 버튼의 마지막 숫자*/
        int end = group * showPages;

        /* 총페이지의 마지막 페이지 */
        int last = (int)(Math.ceil(total/(double) pageDto.getSize()));

        /*  보여줄 페이징버튼의 마지막이 마지막페이지보다 크다면 마지막페이지를 보여줌 */
        end = end > last ? last : end;

        /* 시작페이지가 1보다 크다면 이전버튼 활성화 */
        this.prev = start > 1;

        /*  */
        this.next = totalPage > end * pageDto.getSize();

        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());



        // 과제
        this.prevPage = prev ? start - 1 : 0;

        this.nextPage = next ? end + 1 : 0;
    }
}
