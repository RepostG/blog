package com.yc.education.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.MultiState;
import com.yc.education.util.AjaxMessage;
import com.yc.education.util.Pager;

@Controller
@RequestMapping("/admin/image/")
public class ImageController {

	@RequestMapping(value={"/","index.html"})
	@ResponseBody
	public void index(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/xml;charset=utf-8");

			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String contextPath = request.getSession().getServletContext().getContextPath();

			PrintWriter out = response.getWriter();
			out.print(new ActionEnter(request, rootPath, contextPath,"admin").exec());

			out.flush();
			out.close();
			return;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage<Object> deleteFile(HttpServletRequest request, String fileName) throws IOException {
		com.yc.education.util.AjaxMessage<Object> ajax = new AjaxMessage<>(true, "删除失败", null);

		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String contextPath = request.getSession().getServletContext().getContextPath();

		Path filePath = Paths.get(rootPath, fileName.substring(contextPath.length()));
		ajax.setIs((Files.deleteIfExists(filePath)));

		return ajax;
	}

	@RequestMapping("/imgbox")
	@ResponseBody
	public ModelAndView imgbox(HttpServletRequest request, Integer start) {
		// String rootPath =
		// request.getRequestURL().delete(request.getRequestURL().length()-request.getServletPath().length()+1,
		// request.getRequestURL().length()).toString();

		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String contextPath = request.getSession().getServletContext().getContextPath();
		if (start == null) {
			start = 0;
		} else if (start > 0) {
			start = start - 1;
		}
		request.setAttribute("start", start);
		MultiState state = (MultiState) new ActionEnter(request, rootPath, contextPath, "admin").invoke(ActionMap.LIST_IMAGE_CUSTOMER);

		Pager<String> pager = new Pager<String>(state.getInfo("total").intValue(), start + 1, 20, state.getDataList());
		ModelAndView modelAndView = new ModelAndView("/common/imgbox");
		modelAndView.addObject("model", pager);

		return modelAndView;
	}
}
