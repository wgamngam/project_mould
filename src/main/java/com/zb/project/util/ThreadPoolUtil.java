/**
 * 
 */
package com.zb.project.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池工具类
 * 
 */
public class ThreadPoolUtil {
	private static final ExecutorService executorService = Executors
			.newCachedThreadPool();

	public static void execute(Runnable runnable) {
		executorService.execute(runnable);
	}


}
