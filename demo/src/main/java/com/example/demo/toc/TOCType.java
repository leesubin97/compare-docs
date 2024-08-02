package com.example.demo.toc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 목차를 표현하는 타입<br/>
 * 필드 순서 변경하면 다르게 판단될 수 있음
 */
@AllArgsConstructor
@Getter
public enum TOCType {
	/**
	 * 섹션 정보<br/>
	 * SECTION4.- ???, SECTION 4 - ???
	 */
	SPEC("(Spec\\.)"),

	/**
	 * 섹션 정보<br/>
	 * SECTION4.- ???, SECTION 4 - ???
	 */
	SECTION("SECTION\\s*\\d{1}.*"),
	
	/**
	 *	APPENDICES 정보
	 */
	APPENDICES("APPENDICES.*"),
	
	/**
	 *	APPENDIX
	 */
	APPENDIX("APPENDIX.*"),
	
	KWATER_DEPTH1("제(\\d+)장.*"),
	KWATER_DEPTH2("제(\\d+)조.*"),
	

	/**
	 * 챕터 정보<br/>
	 * Chapter.- ???, Chapter 1 - ???
	 */
	CHAPTER("(?i)CHAPTER.*"),
	
	/**
	 * 파트 정보<br/>
	 * PART.- ???, PART 5 - ???
	 */
	PART("PART.*"),
	
	ADDITIONAL("\\[\\d+년 추록\\]"),
	
	SNC_1DEPTH("SNC[1-9]{1}000"),
	SNC_2DEPTH("SNC[1-9]{2}00"),
	SNC_3DEPTH("SNC[1-9]{3}0"),
	SNC_4DEPTH("SNC[1-9]{4}"),

	/**
	 * 숫자만 존재하는 타입<br/>
	 * 1, 12, 123 ...
	 */
	ONLY_NUMBER("\\.?\\d+\\.?"),

	/**
	 * 마침표로 구분짓는 타입<br/>
	 * 1.2.3.4
	 */
	NUMBER_WITH_DOT_3("\\.?\\d+(?:\\.\\d+){3}\\.?"),

	/**
	 * 마침표로 구분짓는 타입<br/>
	 * 1.2.3
	 */
	NUMBER_WITH_DOT_2("\\.?\\d+(?:\\.\\d+){2}\\.?"),
	
	/**
	 * A.1.2
	 */
	ALPA_NUMBER_WITH_DOT_1("[A-Z]\\.\\d+(?:\\.\\d+){1}"),
	
	ALPA_NUMBER_WITH_DOT_2("[A-Z]\\.\\d+(?:\\.\\d+){2}"),
	
	ALPA_NUMBER_WITH_DOT_3("[A-Z]\\.\\d+(?:\\.\\d+){3}"),

	/**
	 * 마침표로 구분짓는 타입<br/>
	 * 1.2
	 */
	NUMBER_WITH_DOT_1("\\.?\\d+(?:\\.\\d+){1}\\.?"),
	
	/**
	 * 마침표로 구분짓는 타입<br/>
	 * 1.2
	 */
	NUMBER_WITH_DOT_HYPHEN("\\w+\\.\\d+(?:\\.\\d+)?-[^\\d].*"),

	/**
	 * -로 구분짓는 경우<br/>
	 * 1-2-3-4
	 */
	NUMBER_WITH_DASH_3("\\.?\\d+(?:-\\d+){3}\\.?"),

	/**
	 * -로 구분짓는 경우<br/>
	 * 1-2-3
	 */
	NUMBER_WITH_DASH_2("\\.?\\d+(?:-\\d+){2}\\.?"),

	/**
	 * -로 구분짓는 경우<br/>
	 * 1-2
	 */
	NUMBER_WITH_DASH_1("\\.?\\d+(?:-\\d+){1}\\.?"),
	
	/**
	 * A.1-
	 */
	ALPA_NUMBER_WITH_DASH_1("[A-Z]\\.?\\d+(?:-\\d+){1}"),
	
	ALPA_NUMBER_WITH_DASH_2("[A-Z]\\.?\\d+(?:-\\d+){2}"),
	
	ALPA_NUMBER_WITH_DASH_3("[A-Z]\\.?\\d+(?:-\\d+){3}"),
	
	ALPA_NUMBER_WITH_DASH_ALL("[A-Z]\\.\\d+(?:\\.\\d+)*.*"),

	/**
	 * 영어대문자만 존재하는 타입<br/>
	 * A, AB ...
	 */
	ONLY_UPPER("\\.?[A-Z]{1,2}\\.?"),

	/**
	 * 영어소문자만 존재하는 타입<br/>
	 * a, ab ...
	 */
	ONLY_LOWER("\\.?[a-z]{1}\\.?"),

	/*
	 * 영어소문자 + 숫자만 존재하는 2depth 타입<br/>
	 * a1.1, b12.3
	 */
	LOWER_WITH_NUMBER_2("\\.?[a-z]+\\d{1,2}\\.{1}\\d{1,2}"),

	/**
	 * 영어소문자 + 숫자만 존재하는 타입<br/>
	 * a1 ...
	 */
	LOWER_WITH_NUMBER_1("\\.?[a-z]+\\d{1,2}\\.?"),

	/**
	 * 영어대문자 + 숫자만 존재하는 타입<br/>
	 * A1, B12, 123 ...
	 */
	UPPER_WITH_NUMBER_1("\\.?[A-Z]+\\d{1,2}\\.?"),

	/**
	 * 영어대문자 + 숫자만 존재하는 타입<br/>
	 * A1.1, B12.3 ...
	 */
	UPPER_WITH_NUMBER_2("\\.?[A-Z]+\\d{1,2}\\.{1}\\d{1,2}"),
	
	/**
	 * 영어소문자 + 괄호가 존재하는 경우
	 * a) , (a), (b)
	 */
	LOWER_WITH_BOTH_BRACKET("\\([a-z]\\)"),
	LOWER_WITH_CLOSE_BRACKET("[a-z]\\)"),
	HAN_WITH_BOTH_BRACKET("\\([가-힣]\\)"),
	HAN_WITH_CLOSE_BRACKET("\\[가-힣]\\)"),
	NUMEBR_WITH_CLOSE_BRACKET("[\\d]\\)"),
	
	NUMBER_WITH_BRACKET("\\([\\d]+\\)"),
	NUMBER_WITH_LARGE_BRACKET("\\[[\\d]+\\]"),
	NUMBER_WITH_TRI_BOTH_BRACKET("<[\\d]+>"),
	NUMBER_WITH_TRI_CLOSE_BRACKET("[\\d]+>"),
	HAN_WITH_TRI_BOTH_BRACKET("<[가-힣]+>"),
	HAN_WITH_TRI_CLOSE_BRACKET("[가-힣]+>"),
	
	/**
	 * 한글 + 괄호가 존재하는 경우
	 * (가), (나)
	 */
	KOR_WITH_BRACKET("\\(\\s*[가-힣]+\\)"),
	
	
	/**
	 * standard 코드 형식인 경우
	 * C493-92 Standard
	 */
	STANDARD_CODE("([A-Z]+\\d+-\\d+[a-z]?)\\s+(Standard .+)"),
	
	/**
	 * standard 코드 형식인 경우
	 * ([A-Z][0-9]+-[0-9]+[a-z]?)
	 */
	STANDARD_CODE2("([A-Z][0-9]+-[0-9]+[a-z]?)"),
	
	/**
	 * 루트는 독자적인 타입을 주기 위한 임시조치
	 */
	ROOT("^(ROOT){100}&");

	private final String value; // 체크를 위한 정규식
}
