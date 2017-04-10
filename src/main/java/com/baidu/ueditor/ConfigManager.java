package com.baidu.ueditor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.baidu.ueditor.define.ActionMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 配置管理器
 * 
 * @author hancong03@baidu.com
 *
 */
public final class ConfigManager {

	private String rootPath;
	private String userdir;
	private String contextPath;
	private final String configFileName = "config.json";

	private JsonNode jsonConfig = null;
	// 涂鸦上传filename定义
	private final static String SCRAWL_FILE_NAME = "scrawl";
	// 远程图片抓取filename定义
	private final static String REMOTE_FILE_NAME = "remote";

	/**
	 * 配置管理器构造工厂
	 * 
	 * @param rootPath
	 *            服务器根路径
	 * @param userdir
	 *            服务器所在项目路径
	 * @return 配置管理器实例或者null
	 */
	public ConfigManager(String rootPath,String contexPath, String userdir){
		this.rootPath = rootPath;
		this.userdir = userdir;
		this.contextPath = contexPath;
		
		if (jsonConfig == null)
			this.initEnv();
	}

	private String getConfigfilePath() {
		return this.rootPath+configFileName;
	}

	// 验证配置文件加载是否正确
	public boolean valid() {
		return this.jsonConfig != null;
	}

	public JsonNode getAllConfig() {
		return this.jsonConfig;
	}

	public Map<String, Object> getConfig(int type) {

		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;

		switch (type) {
		case ActionMap.UPLOAD_FILE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.getLong("fileMaxSize"));
			conf.put("allowFiles", this.getArray("fileAllowFiles"));
			conf.put("fieldName", this.getString("fileFieldName"));
			savePath = this.getDirString("filePathFormat");
			break;

		case ActionMap.UPLOAD_IMAGE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.getLong("imageMaxSize"));
			conf.put("allowFiles", this.getArray("imageAllowFiles"));
			conf.put("fieldName", this.getString("imageFieldName"));
			savePath = this.getDirString("imagePathFormat");
			break;

		case ActionMap.UPLOAD_VIDEO:
			conf.put("maxSize", this.getLong("videoMaxSize"));
			conf.put("allowFiles", this.getArray("videoAllowFiles"));
			conf.put("fieldName", this.getString("videoFieldName"));
			savePath = this.getDirString("videoPathFormat");
			break;

		case ActionMap.UPLOAD_SCRAWL:
			conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
			conf.put("maxSize", this.getLong("scrawlMaxSize"));
			conf.put("fieldName", this.getString("scrawlFieldName"));
			conf.put("isBase64", "true");
			savePath = this.getDirString("scrawlPathFormat");
			break;

		case ActionMap.CATCH_IMAGE:
			conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
			conf.put("filter", this.getArray("catcherLocalDomain"));
			conf.put("maxSize", this.getLong("catcherMaxSize"));
			conf.put("allowFiles", this.getArray("catcherAllowFiles"));
			conf.put("fieldName", this.getString("catcherFieldName") + "[]");
			savePath = this.getDirString("catcherPathFormat");
			break;

		case ActionMap.LIST_IMAGE:
			conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
			conf.put("dir", this.getDirString("imageManagerListPath"));
			conf.put("count", this.getLong("imageManagerListSize"));
			break;

		case ActionMap.LIST_FILE:
			conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
			conf.put("dir", this.getDirString("fileManagerListPath"));
			conf.put("count", this.getLong("fileManagerListSize"));
			break;
		}

		conf.put("savePath", savePath);
		conf.put("rootPath", this.rootPath);
		conf.put("contextPath", this.contextPath);

		return conf;
	}
	
	private String getDirString(String key) {
		return this.jsonConfig.get(key).asText().replace("{user}",this.userdir);
	}
	
	private void initEnv(){
		try {
			String configContent = this.readFile(getConfigfilePath());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(configContent);// 这里的JsonNode和XML里面的Node很像
			this.jsonConfig = node;
		} catch (Exception e) {
			this.jsonConfig = null;
		}
	}

	private String getString(String key) {
		return this.jsonConfig.get(key).asText();
	}

	private Long getLong(String key) {
		return this.jsonConfig.get(key).asLong();
	}

	private String[] getArray(String key) {
		JsonNode arrayNode = this.jsonConfig.get(key);
		if (arrayNode.isArray()) {
			String[] result = new String[arrayNode.size()];

			for (int i = 0, len = arrayNode.size(); i < len; i++) {
				result[i] = arrayNode.get(i).asText();
			}

			return result;
		}
		return null;
	}

	private String readFile(String path) throws IOException {

		StringBuilder builder = new StringBuilder();

		try {

			InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();

		} catch (UnsupportedEncodingException e) {
			// 忽略
		}

		return this.filter(builder.toString());

	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter(String input) {
		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
	}

}
