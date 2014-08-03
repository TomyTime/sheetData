package com.canteen.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PageUtils {

	public static final int EXPORT_SIZE = 500;

	public static final int PAGE_SHOW_NUM = 5;

	/**
	 * 获取request中的时间 主要考虑的是主流的YYYY-MM-DD此格式的数据 如果没有则默认为当日数据
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	// public static Long getRequestTime(HttpServletRequest request,
	// String paramName) {
	// String param = request.getParameter(paramName);
	// String now = DateUtil.format(new Date(), "yyyyMMdd");
	// if (StringUtils.isBlank(param)) {
	// return new Long(now);
	// }
	// param = param.replaceAll("-", "");
	// if (NumberUtils.isDigits(param)) {
	// return new Long(param);
	// } else {
	// return new Long(now);
	// }
	// }
	/**
	 * 从map 中获取值
	 * 
	 * @param map
	 * @param key
	 * @param num
	 *            默认值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Long getFromMap(Map map, String key, Long num) {
		if (map == null)
			return num;
		if (map.get(key) == null) {
			return num;
		}
		if (map.get(key) instanceof Integer) {
			num = ((Integer) map.get(key)).longValue();
		} else if (map.get(key) instanceof Long) {
			num = (Long) map.get(key);
		} else {
			num = ((BigDecimal) map.get(key)).longValue();
		}
		return num;
	}

	/**
	 * 切割Url
	 * 
	 * @param url
	 * @param len
	 * @return
	 */
	public static String subUrl(String url, int len) {
		if (StringUtils.isBlank(url))
			return "";
		return StringUtils.substring(url, 0, len);
	}

	/**
	 * 获取COOKIE值
	 * 
	 * @param cookieName
	 * @param request
	 * @return
	 */
	public static Cookie findCookie(String cookieName,
			HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookieName.equals(cookies[i].getName())) {
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 添加cookie值
	 *
	 * @param maxAge
	 * @return
	 */
	public static Cookie createCookie(String key, String value, int maxAge) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		return cookie;
	}

	@SuppressWarnings("unchecked")
	public static List subList(List list, int size) {
		if (list.size() <= size) {
			return list;
		}
		return list.subList(0, size);
	}

	/**
	 * 从request中获取分页信息，设置页面分页信息，并返回当前页数
	 * 
	 * @param request
	 * @return 返回当前页码
	 */
	public static int populatePageInfoFromRequest(HttpServletRequest request) {

		int pageNo = 1;
		String page_no = request.getParameter("pageno");
		if (StringUtils.isNotBlank(page_no)) {
			request.setAttribute("pageno", 1);
		}
		String s_pageNo = StringUtils.trimToEmpty(request
				.getParameter("pageno"));

		if (StringUtils.isNotBlank(s_pageNo)) {
			pageNo = Integer.parseInt(s_pageNo);
		} else {
			s_pageNo = StringUtils.trimToEmpty(request
					.getParameter("currentPageNo"));

			if (StringUtils.isNotBlank(s_pageNo)) {
				pageNo = Integer.parseInt(s_pageNo);
			}
		}
        String url=PageUtils.getRequestURL(request,
				new String[] { "pageno", "currentPageNo" });
        if (url.indexOf("?")==-1) {
			url+="?cur=1";
		}
		// 根据原请求字符串拼装分页url字符串，为了防止pageno重复，应将原先的pageno参数去除
		request.setAttribute("sourceURL", url);

		return pageNo;
	}

	@SuppressWarnings("unchecked")
	public static String getRequestURL(HttpServletRequest request,
			String[] noParams) {
		String contextUrl = request.getRequestURL().toString();
		List list = (List) Collections.list(request.getParameterNames());

		Collections.reverse(list);
		if (list != null) {
			if (list.size() > 0)
				contextUrl = contextUrl + "?";
			String connectStr = "";
			int j = 0;
			for (int i = 0; i < list.size(); ++i) {
				String para = (String) list.get(i);
				if (ArrayUtils.contains(noParams, para))
					continue;
				if (j++ > 0)
					connectStr = "&";
				contextUrl = contextUrl + connectStr + para + "="
						+PageUtils.encodeStrToUTF8(request.getParameter(para));
				//+request.getParameter(para);
			}
		}

		return contextUrl;
	}

	public static String encodeStrToUTF8(String inputStr) {
		if ((inputStr == null) || (inputStr.length() <= 0))
			return "";
		try {
			return URLEncoder.encode(inputStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 每页显示数量的提取 1:用户选择的每页数量 2:用户设置在cookie里面的 3:默认的每页数量 优先级:查询条件 > cookie > 默认的
	 * 
	 * @param request
	 * @return 返回每页数量
	 */
	public static int populatePageSizeFromRequest(HttpServletRequest request) {

		// 默认的
		int pageSize = Constants.DEFAULT_PAGE_SIZE;
		String size = request.getParameter("pageSize");
		if (StringUtils.isNotBlank(size)) {
			pageSize = new Integer(size);
		}
		return pageSize;
	}

	/**
	 * face='webdings' 在linux下
	 * 
	 * @param totalPageNo
	 *            分页总数
	 * @param currentPageNo
	 *            当前页
	 * @param sourceURL
	 *            源url
	 * @return
	 */
	public static String ShowPage(int totalCount, int totalPageNo,
			int currentPageNo, String sourceURL, String basePath) {
		sourceURL = sourceURL + "&pageno";
		int k = 0;
//		String page_tmp = "每页" + Constants.DEFAULT_PAGE_SIZE + "条，共有("
//				+ totalCount + ")条记录 " + currentPageNo + " / " + totalPageNo
//				+ " 页 ";
		String page_tmp = "<div class='d1'><span class='d1s1'>"+totalCount+"</span><span class='d1s2'>记录总数</span></div>";
		page_tmp += "<div class='d2'>";
		if (currentPageNo > 1) {
			page_tmp = page_tmp + "<a class='goFirst' href='" + sourceURL
					+ "=1'   title='第一页'><img src='"+basePath+"images/page_first.png' alt='第一页' border=0 /></a>&nbsp;<a   href='" + sourceURL
					+ "=" + (currentPageNo - 1) + "' class='goPrev' title='上页'><img src='"+basePath+"images/prev.png' alt='上一页' border=0 /></a>&nbsp;";
		} else {
			page_tmp = page_tmp + "<a class='goFirst' href='#' title='第一页'><img src='"+basePath+"images/page_first.png' alt='第一页' border=0 /></a>&nbsp;";
			page_tmp = page_tmp + "<a class='goPrev' href='#'><img src='"+basePath+"images/prev.png' alt='上一页' border=0 /></a>&nbsp;";
		}

		if (totalPageNo == 1) {
			page_tmp = page_tmp + "<span class='current'>1</span>&nbsp;";
		}
		if (totalPageNo <= 10 && totalPageNo > 1) {
			for (k = 1; k <= totalPageNo; k++) {
				if (k != currentPageNo)
					page_tmp = page_tmp + "<a class='box'  href='" + sourceURL
							+ "=" + k + "'>" + k + "</a>&nbsp;";
				else
					page_tmp = page_tmp + "<span title='第"+k+"页' class='current'>" + k
							+ "</span>&nbsp;";
			}
		}
		if (totalPageNo > 10) {
			if (currentPageNo <= 5) {
				for (k = 1; k <= 10; k++) {
					if (k != currentPageNo)
						page_tmp = page_tmp + "<a title='第"+k+"页' class='box'  href='"
								+ sourceURL + "=" + k + "'>" + k + "</a>&nbsp;";
					else
						page_tmp = page_tmp + "<span title='第"+k+"页' class='current'>" + k
								+ "</span>&nbsp;";
				}
				page_tmp = page_tmp + "...&nbsp;";
			} else if (currentPageNo >= totalPageNo - 5) {
				page_tmp = page_tmp + "...&nbsp;";
				for (k = totalPageNo - 10 + 1; k <= totalPageNo; k++) {
					if (k != currentPageNo)
						page_tmp = page_tmp + "<a title='第"+k+"页' class='box'  href='"
								+ sourceURL + "=" + k + "'>" + k + "</a>&nbsp;";
					else
						page_tmp = page_tmp + "<span title='第"+k+"页' class='current'>" + k
								+ "</span>&nbsp;";
				}
			} else {
				page_tmp = page_tmp + "...&nbsp;";
				for (k = currentPageNo - 5 + 1; k <= currentPageNo + 5; k++) {
					if (k != currentPageNo)
						page_tmp = page_tmp + "<a title='第"+k+"页' class='box'  href='"
								+ sourceURL + "=" + k + "'>" + k + "</a>&nbsp;";
					else
						page_tmp = page_tmp + "<span title='第"+k+"页' class='current'>" + k
								+ "</span>&nbsp;";
				}
				page_tmp = page_tmp + "...&nbsp;";
			}
		}
		if (currentPageNo < totalPageNo) {
			page_tmp = page_tmp + "<a   href='" + sourceURL + "="
					+ (currentPageNo + 1) + "' class='goNext' title= '下一页'><img src='"+basePath+"images/next.png' alt='下一页' border=0 /></a>";
			page_tmp = page_tmp + "&nbsp;<a href='" + sourceURL + "="
					+ totalPageNo + "' class='goLast' title= '最后一页 '><img src='"+basePath+"images/goLast.png' alt='最后一页' border=0 /></a>&nbsp;";
		} else {
			page_tmp = page_tmp + "<a class='goNext' href='#'><img src='"+basePath+"images/next.png' alt='下一页' border=0 /></a>&nbsp;";
			page_tmp = page_tmp + "<a class='goLast' title= '最后一页' href='#'><img src='"+basePath+"images/goLast.png' alt='最后一页' border=0 /></a>&nbsp;";
		}
		page_tmp += "</div>";
		return page_tmp;
	}

	/**
	 * face='webdings' 在linux下
	 * 
	 * @param totalPageNo
	 *            分页总数
	 * @param currentPageNo
	 *            当前页
	 * @param sourceURL
	 *            源url
	 * @return
	 */
	public static String ShowPage2(int totalCount, int totalPageNo,
			int currentPageNo, String sourceURL, int showNum) {

		int cNum = showNum / 2;
		// sourceURL = sourceURL + "&pageno";

		if (sourceURL.lastIndexOf("&") > 0) {
			sourceURL = sourceURL + "&pageno";
		} else {
			if (sourceURL.lastIndexOf("?") > 0) {
				// sourceURL = sourceURL.substring(0,
				// sourceURL.lastIndexOf("?"));
				sourceURL = sourceURL + "&pageno";
			} else
				sourceURL = sourceURL + "?pageno";
		}

		int k = 0;
		StringBuffer sb = new StringBuffer("");

		if (totalPageNo <= 1) {
			return sb.toString(); // 就一页则不显示分页
		}

		// 第一段
		if (currentPageNo > 1) {
			sb.append("<span class='btn'><a href='").append(sourceURL).append(
					"=1' title='首页'>首页</a></span>");
			sb.append("<span class='btn'><a href='").append(sourceURL).append(
					"=").append(currentPageNo - 1).append(
					"' title='上一页'>上一页</a></span>");

		} else {
			// sb.append("<a href='#'>上一页</a>");
			sb.append("<span class='btn'><a href='").append(sourceURL).append(
					"=1' title='首页'>首页</a></span>");
		}

		// 第二段
		if (totalPageNo <= showNum) {
			for (k = 1; k <= totalPageNo; k++) {
				if (k != currentPageNo) {
					sb.append("<span class='number'><a href='").append(
							sourceURL).append("=").append(k).append("'>")
							.append(k).append("</a></span>");
				} else {
					sb.append("<span class='target'>").append(k).append(
							"</span>");
				}
			}
		} else {
			if (totalPageNo > showNum) {
				if (currentPageNo <= (showNum - cNum)) {//
					for (k = 1; k <= showNum; k++) {
						if (k != currentPageNo) {
							sb.append("<span class='number'><a href='").append(
									sourceURL).append("=").append(k).append(
									"'>").append(k).append("</a></span>");
						} else {
							sb.append("<span class='target'>").append(k)
									.append("</span>");
						}
					}
					// sb.append("...");

				} else if (currentPageNo >= (totalPageNo - (showNum - cNum - 1))) {
					// sb.append("...");

					for (k = totalPageNo - showNum + 1; k <= totalPageNo; k++) {
						if (k != currentPageNo) {
							sb.append("<span class='number'><a href='").append(
									sourceURL).append("=").append(k).append(
									"'>").append(k).append("</a></span>");
						} else {
							sb.append("<span class='target'>").append(k)
									.append("</span>");
						}
					}
				} else {
					// sb.append("...");
					for (k = currentPageNo - 2; k <= currentPageNo + showNum
							- 3; k++) {

						if (k != currentPageNo) {
							sb.append("<span class='number'><a href='").append(
									sourceURL).append("=").append(k).append(
									"'>").append(k).append("</a></span>");
						} else {
							sb.append("<span class='target'>").append(k)
									.append("</span>");
						}

					}
					// sb.append("...");
				}
			}
		}

		// 第三段
		if (currentPageNo < totalPageNo) {
			sb.append("<span class='btn'><a href='").append(sourceURL).append(
					"=").append(currentPageNo + 1).append(
					"' title= '下一页'>下一页</a></span>");
			sb.append("<span class='btn'><a href='").append(sourceURL).append(
					"=").append(totalPageNo).append(
					"' title='尾页'>尾页</a></span>");

		} else {
			// sb.append("<a href='#'>下一页</a>");
			sb.append("<span class='btn'><a href='").append(sourceURL).append(
					"=").append(totalPageNo).append(
					"' title='尾页'>尾页</a></span>");
		}

		sb.append("<span class='shumu'>共").append(totalPageNo).append(
				"页</span>");
		return sb.toString();

	}

	public static String ShowPageIndex(int totalCount, int totalPageNo,
			int currentPageNo, String sourceURL, int showNum) {

		int cNum = showNum / 2;
		if (sourceURL.lastIndexOf("&") > 0) {
			sourceURL = sourceURL + "&pageno";
		} else {
			if (sourceURL.lastIndexOf("?") > 0) {
				// sourceURL = sourceURL.substring(0,
				// sourceURL.lastIndexOf("?"));
				sourceURL = sourceURL + "&pageno";
			} else
				sourceURL = sourceURL + "?pageno";
		}

		int k = 0;
		StringBuffer sb = new StringBuffer("");

		if (totalPageNo <= 1) {
			return sb.toString(); // 就一页则不显示分页
		}
		sb.append("<ul class='hidden'>");
		// 第一段
		if (currentPageNo > 1) {
			sb.append("<li><a class='wd57' href='").append(sourceURL).append(
					"=1' title='首页'>首页</a></li>");
			sb.append("<li><a class='wd57' href='").append(sourceURL).append(
					"=").append(currentPageNo - 1).append(
					"' title='上一页'>上一页</a></li>");

		} else {
			// sb.append("<a href='#'>上一页</a>");
			sb.append("<li><a class='wd57' href='").append(sourceURL).append(
					"=1' title='首页'>首页</a></li>");
		}

		// 第二段
		if (totalPageNo <= showNum) {

			for (k = 1; k <= totalPageNo; k++) {
				if (k != currentPageNo) {
					sb.append("<li ><a class='current' href='").append(
							sourceURL).append("=").append(k).append("'>")
							.append(k).append("</a></li>");
				} else {
					sb.append("<li>").append(k).append("</li>");
				}
			}
		} else {
			if (totalPageNo > showNum) {
				if (currentPageNo <= (showNum - cNum)) {//
					for (k = 1; k <= showNum; k++) {
						if (k != currentPageNo) {
							sb.append("<li><a href='").append(sourceURL)
									.append("=").append(k).append("'>").append(
											k).append("</a></li>");
						} else {
							sb.append("<li>").append(k).append("</li>");
						}
					}
				} else if (currentPageNo >= (totalPageNo - (showNum - cNum - 1))) {
					for (k = totalPageNo - showNum + 1; k <= totalPageNo; k++) {
						if (k != currentPageNo) {
							sb.append("<li><a href='").append(sourceURL)
									.append("=").append(k).append("'>").append(
											k).append("</a></li>");
						} else {
							sb.append("<li>").append(k).append("</li>");
						}
					}
				} else {
					// sb.append("...");
					for (k = currentPageNo - 2; k <= currentPageNo + showNum
							- 3; k++) {
						if (k != currentPageNo) {
							sb.append("<li><a href='").append(sourceURL)
									.append("=").append(k).append("'>").append(
											k).append("</a></li>");
						} else {
							sb.append("<li>").append(k).append("</li>");
						}

					}
					// sb.append("...");
				}
			}
		}

		// 第三段
		if (currentPageNo < totalPageNo) {
			sb.append("<li><a class='wd57' href='").append(sourceURL).append(
					"=").append(currentPageNo + 1).append(
					"' title= '下一页'>下一页</a></li>");
			sb.append("<li><a  class='wd57' href='").append(sourceURL).append(
					"=").append(totalPageNo).append("' title='尾页'>尾页</a></li>");

		} else {
			sb.append("<li><a class='wd57' href='").append(sourceURL).append(
					"=").append(totalPageNo).append("' title='尾页'>尾页</a></li>");
		}

		sb.append("<li style='width: 57px;''>共").append(totalPageNo).append(
				"页</li>");

		return sb.toString();

	}

	/**
	 * 只有首页 上一页 [页码] 下一页 现在使用在图片检索中
	 */
	public static String ShowPage4(int totalCount, int totalPageNo,
			int currentPageNo, String sourceURL, int showNum) {

		// String page_tmp1 = "<span class='cur'>1</span><a href='#'>2</a><a
		// href='#'>3</a>"+
		// "<a href='#'>4</a><a href='#'>5</a><span class='break'>...</span> <a
		// class=page-next href='#'><span>下一页</span></a><span>"+
		// "共"+totalPageNo+"页 到第 <input name='' type='text' size='3'/> 页
		// &nbsp;<button type='submit'>确&nbsp;定</button></span>";
		int cNum = showNum / 2;
		sourceURL = sourceURL + "&pageno";
		int k = 0;
		StringBuffer sb = new StringBuffer("");

		if (totalPageNo <= 1) {
			return sb.toString(); // 就一页则不显示分页
		}

		// 第一段
		if (currentPageNo > 1) {
			sb.append("<a href='").append(sourceURL).append("=").append(
					currentPageNo - 1).append("' title='上一页'>上一页</a>&nbsp;");
			// sb.append("<a href='").append(sourceURL).append(
			// "=1' title='首页'>首页</a>&nbsp;");
			//			

		} else {
			// sb.append("<a href='#'>上一页</a>&nbsp;");
			// sb.append("<a href='").append(sourceURL).append(
			// "=1' title='首页'>首页</a>&nbsp;");
		}

		// 第二段
		if (totalPageNo <= showNum) {
			for (k = 1; k <= totalPageNo; k++) {
				if (k != currentPageNo) {
					sb.append("<a class='box' href='").append(sourceURL)
							.append("=").append(k).append("'>").append(k)
							.append("</a>&nbsp;");
				} else {
					sb.append("<span class='cur'>").append(k).append(
							"</span>&nbsp;");
				}
			}
		} else {
			if (totalPageNo > showNum) {
				if (currentPageNo <= (showNum - cNum)) {// 1-2
					System.out.println("1111");
					for (k = 1; k <= showNum; k++) {
						if (k != currentPageNo) {
							sb.append("<a class='box' href='")
									.append(sourceURL).append("=").append(k)
									.append("'>").append(k)
									.append("</a>&nbsp;");
						} else {
							sb.append("<span class='cur'>").append(k).append(
									"</span>&nbsp;");
						}
					}
					sb.append("...&nbsp;");

				} else if (currentPageNo >= (totalPageNo - (showNum - cNum - 1))) {
					sb.append("<a class='box' href='").append(sourceURL)
							.append("=").append(1).append("'>").append(1)
							.append("</a>&nbsp;");// 第一页
					sb.append("...&nbsp;");

					for (k = totalPageNo - showNum + 1; k <= totalPageNo; k++) {
						if (k != currentPageNo) {
							sb.append("<a class='box' href='")
									.append(sourceURL).append("=").append(k)
									.append("'>").append(k)
									.append("</a>&nbsp;");
						} else {
							sb.append("<span class='cur'>").append(k).append(
									"</span>&nbsp;");
						}
					}
				} else { // 在4- (末尾-3)
					sb.append("<a class='box' href='").append(sourceURL)
							.append("=").append(1).append("'>").append(1)
							.append("</a>&nbsp;");
					sb.append("...&nbsp;");
					for (k = currentPageNo - 2; k <= currentPageNo + showNum
							- 3; k++) {

						if (k != currentPageNo) {
							sb.append("<a class='box' href='")
									.append(sourceURL).append("=").append(k)
									.append("'>").append(k)
									.append("</a>&nbsp;");
						} else {
							sb.append("<span class='cur'>").append(k).append(
									"</span>&nbsp;");
						}

					}
					sb.append("...&nbsp;");
				}
			}
		}

		// 第三段
		if (currentPageNo < totalPageNo) {
			sb.append("<a href='").append(sourceURL).append("=").append(
					currentPageNo + 1).append("' title= '下一页'>下一页</a>&nbsp;");
		} else {
			// sb.append("<a href='").append(sourceURL).append("=").append(
			// currentPageNo).append("#' title= '下一页'>下一页</a>&nbsp;");
		}

		return sb.toString();

	}

	/**
	 * 只有上一页 /下一页
	 * 
	 * @param totalCount
	 * @param totalPageNo
	 * @param currentPageNo
	 * @param sourceURL
	 * @param showNum
	 * @return
	 */
	public static String ShowPage3(int totalCount, int totalPageNo,
			int currentPageNo, String sourceURL, int showNum) {

		sourceURL = sourceURL + "&pageno";
		int k = 0;
		StringBuffer sb = new StringBuffer("");

		if (totalPageNo <= 1) {
			return sb.toString(); // 就一页则不显示分页
		}

		// 第一段
		if (currentPageNo > 1) {
			sb.append("<a href='").append(sourceURL).append("=").append(
					currentPageNo - 1).append("' title='上一页'>上一页</a>&nbsp;");

		} else {
		}

		// System.out.println(sb.toString());

		// 第三段
		if (currentPageNo < totalPageNo) {
			sb.append("<a href='").append(sourceURL).append("=").append(
					currentPageNo + 1).append("' title= '下一页'>下一页</a>");

		} else {
		}

		return sb.toString();

	}

	public static void main(String[] args) {
		// String a = "11aaaaaaaaaaaaaaaabbbbbbbbb";
		// System.out.println(PageUtils.subUrl(a, 1000));

	}

}
