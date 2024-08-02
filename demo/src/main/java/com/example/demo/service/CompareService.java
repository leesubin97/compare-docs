package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;

import com.example.demo.aop.TimeCheck;
import com.example.demo.toc.TOCNode;

public class CompareService {
    
	/**
	 * 두 문서 전체 비교
	 */
	private static int[][] levenshteinDistance(List<TOCNode> a, List<TOCNode> b) {
		int[][] dp = new int[a.size() + 1][b.size() + 1];
		boolean[][] isSameTable = new boolean[a.size()][b.size()];

		// 쓰레드 수 지정
		ExecutorService executor = Executors.newFixedThreadPool(16);
		List<Future<?>> futures = new ArrayList<>();

		// isSame 계산을 위한 작업을 생성하고 실행
		for (int i = 0; i < a.size(); i++) {
			int finalI = i;
			for (int j = 0; j < b.size(); j++) {
				int finalJ = j;
				futures.add(executor.submit(() -> {
					isSameTable[finalI][finalJ] = isSame(a.get(finalI).getTopContent(), b.get(finalJ).getTopContent());
				}));
			}
		}

		// 모든 작업이 완료될 때까지 기다림
		for (Future<?> future : futures) {
			try {
				future.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 테이블 초기화 및 최소 편집 거리 계산
		for (int i = 0; i <= a.size(); i++) {
			for (int j = 0; j <= b.size(); j++) {
				if (i == 0) {
					dp[i][j] = j; // 첫 번째 행 초기화
				} else if (j == 0) {
					dp[i][j] = i; // 첫 번째 열 초기화
				} else {
					if (isSameTable[i - 1][j - 1]) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
					}
				}
			}
		}

		executor.shutdown(); // ExecutorService 종료

		return dp;
	}

	/**
	 * 단일 문자 열 비교 - 비교 결과 차이점에서 한번 더 추출
	 */
	@TimeCheck
	private static int[][] levenshteinDistanceString(String[] a, String[] b) {
		int[][] dp = new int[a.length + 1][b.length + 1];

		for (int i = 0; i <= a.length; i++) {
			for (int j = 0; j <= b.length; j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {
					boolean isSame = a[i - 1].equals(b[j - 1]);
					if (isSame) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
					}
				}
			}
		}
		return dp;
	}

	/**
	 * 두문서간 전체 정확도 퍼센트
	 */
	public static int calculateDocumentSimilarity(StringBuilder originBuilder, StringBuilder targetBuilder) {
		String textOrigin = originBuilder.toString();
		String textTarget = targetBuilder.toString();

		int levenshteinDistance = StringUtils.getLevenshteinDistance(textOrigin, textTarget);
		int maxLength = Math.max(textOrigin.length(), textTarget.length());

		// 비어있을 경우 처리
		if (maxLength == 0)
			return 100;

		// 유사도 계산
		double similarity = (1 - ((double) levenshteinDistance / maxLength)) * 100;
		return (int) Math.round(similarity);
	}

	public static boolean isSame(String original, String target) {
		boolean isResult = false;
		if (original == null || target == null) {
			return false;
		}

		if (StringUtils.getLevenshteinDistance(original, target) * 4 < Math.max(original.length(),
				target.length()) == true) {
			isResult = true;
		}

		// boolean isResult = StringUtils.getLevenshteinDistance(original, target) * 4 <
		// Math.max(original.length(), target.length());
		return isResult;
	}
}
