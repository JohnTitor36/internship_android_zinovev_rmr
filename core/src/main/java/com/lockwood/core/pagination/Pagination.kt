package com.lockwood.core.pagination

interface Pagination {

    /** Последняя доступная страница для пагинации */
    var pageCount: Int

    /** Последний посещенная страница для пагинации */
    var currentPage: Int

    /** Лимит для пагинации */
    var perPage: Int

    val isLastPage
        get() = currentPage == pageCount

    val nextPage
        get() = currentPage + 1

}