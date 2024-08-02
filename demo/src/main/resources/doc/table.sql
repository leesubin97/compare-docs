
-- 비교 문서 테이블
CREATE TABLE doc_compare (
	`doc_index` INT AUTO_INCREMENT PRIMARY KEY,
    `doc_order` INT NOT NULL,
    doc_content TEXT NOT NULL,
    doc_title VARCHAR(800) NOT NULL,
    section VARCHAR(1000) NOT NULL,
    doc_chapter VARCHAR(1000) NOT NULL,
    content_root1 text,
    content_root2 text,
    content_root3 text,
    content_root4 text,
    content_root5 text,
    content_root6 text
)
;

-- 비교 문서 테이블 조회
SELECT * 
FROM doc_compare dc ;

-- 테스트 데이터
INSERT INTO doc_compare (doc_order, doc_content, doc_title, section, doc_chapter, content_root1, content_root2, content_root3, content_root4, content_root5, content_root6)
VALUES
(1, '첫 번째 문서의 내용입니다. 이 내용은 문서의 첫 번째 섹션에 대한 설명입니다. 추가적인 세부 사항은 부제목 아래에 포함됩니다.', '문서 1', '섹션 1', '챕터 1', '제목 1 - 첫 번째 문서의 첫 번째 섹션', '부제목 1 - 첫 번째 문서의 부제목', '부제목 2 - 첫 번째 문서의 두 번째 부제목', '', '', ''),
(2, '두 번째 문서의 내용입니다. 이 내용은 문서의 첫 번째 섹션에 대한 설명입니다. 추가적인 세부 사항은 부제목 아래에 포함됩니다.', '문서 2', '섹션 2', '챕터 2', '제목 1 - 두 번째 문서의 첫 번째 섹션', '부제목 1 - 두 번째 문서의 부제목', '부제목 2 - 두 번째 문서의 두 번째 부제목', '', '', '');
