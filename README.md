paginator-tag
=============

A JSP custom tag that displays a customizable search results paginator

Inspired by: [http://www.gotoquiz.com/web-coding/programming/java-programming/simple-pagination-taglib-for-jsp/]

Usage
------
##### Required attributes:
* pageUrl (String) Url to use for page links
* maxLinks (int) Max number of numbered page links to display in the paginator
* totalResults (int) Total number of results for the search
* itemsPerPage (int) Number of items to display per page
* currentPage (int) The current page number

##### Optional attributes:
* labelFirst (String) Label to display for "First" (Go to first page) link. Default: "First" 
* labelPrev (String) Label to display for "Previous" (Go back one page) link. Default: "Previous" 
* labelNext (String) Label to display for "Next" (Go forward one page) link. Default: "Next"
* labelLast (String) Label to display for "Last" (Go to last page) link. Default: "Last"
* showFirst (boolean) Show "First" page link. Default: true
* showLast (boolean) Show "Last" page link. Default: true

```<c:url var="pageUrl" value="/search?q=keyword" />
<paginator:display pageUrl="${pageUrl}" maxLinks="8" totalResults=${search.totalResults} itemsPerPage="25" 
currentPage="${currentPage}" labelFirst="&laquo; First" labelPrev="&lt; Previous" labelNext="Next &gt;" labelLast="Last &raquo;" showFirst="false" showLast="false" />
```