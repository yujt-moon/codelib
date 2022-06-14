package com.moon.jvm;

/**
 * VM: -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
 *
 * LD_LIBRARY_PATH=/data/home/yujt/study/tool 该 tool 下面有 hsdis-amd64.so
 */
public class T {
	public static volatile int i = 0;

	public static void main(String[] args) {
		n();
	}

	public static synchronized void m() {
	}

	public static void n() {
		i = 1;
	}
}
