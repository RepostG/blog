package com.baidu.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;

public class ActionEnter {

	private HttpServletRequest request = null;

	private String actionType = null;

	private ConfigManager configManager = null;

	public ActionEnter(HttpServletRequest request, String rootPath, String contextPath, String userPath) {
		this.request = request;

		this.actionType = request.getParameter("action");
		this.configManager = new ConfigManager(rootPath, contextPath, userPath);
	}

	public ActionEnter(HttpServletRequest request, String rootPath, String contextPath, String userPath,
			String action) {
		this.request = request;

		this.actionType = action;
		this.configManager = new ConfigManager(rootPath, contextPath, userPath);
	}

	public String exec() {
		String callbackName = this.request.getParameter("callback");

		if (callbackName != null) {

			if (!validCallbackName(callbackName)) {
				return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
			}

			return callbackName + "(" + this.invoke() + ");";

		} else {
			return this.invoke();
		}
	}

	public State invoke(int actionCode) {

		Map<String, Object> conf = null;
		State state = null;
		switch (actionCode) {
		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			conf = this.configManager.getConfig(actionCode);
			state = new Uploader(request, conf).doExec();
			break;
		case ActionMap.UPLOAD_MULTI_IMAGE:
			conf = this.configManager.getConfig(ActionMap.UPLOAD_IMAGE);
			state = new Uploader(request, conf).doUploadImgs();
			break;
		case ActionMap.CATCH_IMAGE:
			conf = configManager.getConfig(actionCode);
			String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
			state = new ImageHunter(conf).capture(list);
			break;

		case ActionMap.LIST_IMAGE:
		case ActionMap.LIST_FILE:
			conf = configManager.getConfig(actionCode);
			int start = this.getStartIndex();
			state = new FileManager(conf).listFile(start);
			break;

		case ActionMap.LIST_IMAGE_CUSTOMER:
			conf = configManager.getConfig(ActionMap.LIST_IMAGE);
			start = this.getCusStartIndex();
			state = new FileManager(conf).listCustomerFile(start, 15);
			break;
		case ActionMap.DELETE_IMAGE_CUSTOMER:
			conf = configManager.getConfig(ActionMap.LIST_IMAGE);
			String filename = request.getAttribute("filename").toString();
			state = new FileManager(conf).deleteCustomerFile(filename);
			break;
		}

		return state;
	}

	public String invoke() {

		if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}
		if (this.configManager == null || !this.configManager.valid()) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}

		int actionCode = ActionMap.getType(this.actionType);

		if (actionCode == ActionMap.CONFIG) {
			return this.configManager.getAllConfig().toString();
		}

		State state = invoke(actionCode);

		return state.toJSONString();
	}

	public int getCusStartIndex() {

		Object start = this.request.getAttribute("start");

		try {
			if (null == start)
				return 0;
			else {
				return Integer.parseInt(start.toString());
			}
		} catch (Exception e) {
			return 0;
		}

	}

	public int getStartIndex() {

		String start = this.request.getParameter("start");

		try {
			return Integer.parseInt(start);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * callback参数验证
	 */
	public boolean validCallbackName(String name) {

		if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
			return true;
		}

		return false;

	}

}