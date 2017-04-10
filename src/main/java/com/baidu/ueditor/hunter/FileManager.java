package com.baidu.ueditor.hunter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;

public class FileManager {

	private String dir = null;
	private String rootPath = null;
	private String contextPath = null;
	private String[] allowFiles = null;
	private int count = 0;
	
	public FileManager (Map<String, Object> conf ) {
		this.rootPath = (String)conf.get( "rootPath" );
		this.dir = Paths.get(this.rootPath,conf.get("dir").toString()).toString();
		this.allowFiles = this.getAllowFiles( conf.get("allowFiles") );
		this.count = ((Long)conf.get( "count" )).intValue();
		this.contextPath = (String)conf.get("contextPath");
	}
	
	public State listFile (int index) {
		File dir = new File(this.dir);
		State state = null;

		if ( !dir.exists() ) {
			return new BaseState( false, AppInfo.NOT_EXIST );
		}
		
		if ( !dir.isDirectory() ) {
			return new BaseState( false, AppInfo.NOT_DIRECTORY );
		}
		
		Collection<File> list = FileUtils.listFiles( dir, this.allowFiles, true );
		
		if ( index < 0 || index > list.size() ) {
			state = new MultiState( true );
		} else {
			Object[] fileList = Arrays.copyOfRange( list.toArray(), index, index + this.count );
			state = this.getState( fileList );
		}
		
		state.putInfo( "start", index );
		state.putInfo( "total", list.size() );
		
		return state;
	}
	
	public State listCustomerFile(int index,int size) {
		MultiState state = new MultiState(true);
		
		File dir = new File( this.dir );

		if (dir.exists() && dir.isDirectory() ) {
			Collection<File> list = FileUtils.listFiles(dir, this.allowFiles,true);
			
			if(list != null && list.size()>0){
				if (index*size < list.size() ) {
					File[] files = new File[list.size()];
					list.toArray(files);
					
					Arrays.sort(files, new Comparator<File>(){ 
					    public int compare(File f1, File f2) 
					    { 
					        return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified()); 
					    }
					});
					
					state.putInfo("total", files.length);
					
					int to = files.length - index*size;
					if(to > size) to = (index+1)*size;else {
						to = index*size+to;
					}
					
					files = Arrays.copyOfRange(files, index*size,to);
					
					for (File file2 : files) {
						state.addData(getPath(file2));
					}
				}
			}
		}else{
			return new MultiState( false, AppInfo.IO_ERROR);
		}

		return state;
	}
	
	private State getState ( Object[] files ) {
		MultiState state = new MultiState(true);
		
		BaseState fileState = null;
		
		File file = null;
		
		for ( Object obj : files ) {
			if ( obj == null ) {
				break;
			}
			file = (File)obj;
			fileState = new BaseState( true );
			fileState.putInfo( "url", PathFormat.format( this.getPath( file ) ) );
			state.addState( fileState );
		}
		
		return state;
	}
	
	private String getPath ( File file ) {
		
		String path = file.getAbsolutePath();
		
		return PathFormat.format(Paths.get(contextPath,path.replace( this.rootPath, "/" )).toString());
		
	}
	
	private String[] getAllowFiles ( Object fileExt ) {
		
		String[] exts = null;
		String ext = null;
		
		if ( fileExt == null ) {
			return new String[ 0 ];
		}
		
		exts = (String[])fileExt;
		
		for ( int i = 0, len = exts.length; i < len; i++ ) {
			
			ext = exts[ i ];
			exts[ i ] = ext.replace( ".", "" );
			
		}
		
		return exts;
		
	}

	public State deleteCustomerFile(String filename) {
		BaseState state = null;
		try {
			System.out.println(MessageFormat.format("dir :{0}, filename:{1}", dir, filename));

			state = new BaseState(Files.deleteIfExists(Paths.get(dir, filename)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return state;
	}
	
}
