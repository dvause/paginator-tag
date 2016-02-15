package com.dvause.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;

public class PaginationTag extends SimpleTagSupport {
	private String pageUrl;
	private int maxLinks;
	private int totalResults;
	private int itemsPerPage;
	private int currentPage;
	private String labelFirst = "First";
	private String labelPrev = "Previous";
	private String labelNext = "Next";
	private String labelLast = "Last";
	private boolean showFirst = true;
	private boolean showLast = true;


	public void doTag() throws JspException {
		Writer out = getJspContext().getOut();

		int totalPages = (int) Math.ceil((double)totalResults / itemsPerPage);
		int pageStart = Math.max(currentPage - maxLinks / 2 ,1);
		int pageEnd = pageStart + maxLinks;

		if (pageEnd > totalPages + 1) {
			int diff = pageEnd - totalPages;
			pageStart -= diff - 1;
			if (pageStart < 1) {
				pageStart = 1;
			}
			pageEnd = totalPages + 1;
		}

		try {
			out.write("<ul class=\"pagination\">");
			if (currentPage > 1) {
				if (showFirst) {
					out.write(buildPageItem(1, labelFirst, null));
				}
				out.write(buildPageItem(currentPage - 1, labelPrev, null));
			}
			if (maxLinks > 0) {
				for (int i = pageStart; i < pageEnd; i++) {
					if (i == currentPage) {
						out.write("<li class=\"active\"><span>" + currentPage + "</span></li>");
					} else {
						out.write(buildPageItem(i));
					}
				}
			}

			boolean lastPage = currentPage == totalPages;
			if (!lastPage) {
				out.write(buildPageItem((currentPage + 1), labelNext, null));
				if (showLast) {
					out.write(buildPageItem(totalPages, labelLast, null));
				}
			}
			out.write("</ul>");

		} catch (IOException e) {
			throw new JspException("Error in WavePaginationTag", e);
		}
	}
	private String buildPageItem(int page) {
		return buildPageItem(page, String.valueOf(page), null);
	}

	private String buildPageItem(int page, String label, String className) {
		StringBuilder link = new StringBuilder("<li");
	        if (className != null) {
	            link.append( "class=\"").append(className).append("\"");
	        }
	        link.append(">")
	                .append("<a href=\"")
	                .append(pageUrl);
	                if (pageUrl.contains("?")) {
	                    link.append("&page=");
	                } else {
	                    link.append("?page=");
	                }
	                link.append(page)
	                .append("&limit=").append(itemsPerPage)
	                .append("\">")
	                .append(label)
	                .append("</a></li>");
	        return link.toString();
	 }

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public void setMaxLinks(int maxLinks) {
		this.maxLinks = maxLinks;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setLabelFirst(String labelFirst) {
		this.labelFirst = labelFirst;
	}

	public void setLabelPrev(String labelPrev) {
		this.labelPrev = labelPrev;
	}

	public void setLabelNext(String labelNext) {
		this.labelNext = labelNext;
	}

	public void setLabelLast(String labelLast) {
		this.labelLast = labelLast;
	}

	public void setShowFirst(boolean showFirst) {
		this.showFirst = showFirst;
	}

	public void setShowLast(boolean showLast) {
		this.showLast = showLast;
	}
}
