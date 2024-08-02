package com.example.demo.toc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 변경 여부를 표현하는 타입
 */
@AllArgsConstructor
@Getter
public enum TOCDiff {
	SAME(""),
	INSERT("bg-green-50 border-green-200 border-2"),
	UPDATE("bg-yellow-50 border-yellow-200 border-2"),
	DELETE("bg-red-50 border-red-200 border-2");

	private final String value; // 체크를 위한 정규식
}
