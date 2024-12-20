-- USED_GOODS_BOARD -> 게시글 ID, 작성자 ID, 게시글 제목, 게시글 내용, 가격, 작성일, 거래상태, 조회수
-- USED_GOODS_REPLY -> 댓글 ID, 게시글 ID, 작성자 ID, 댓글 내용, 작성일
-- 2022년 10월에 작성된 게시글 제목, 게시글 ID, 댓글 ID, 댓글 작성자 ID, 댓글 내용, 댓글 작성일
-- 댓글 작성일 오름차순 / 게시글 제목 오름차순
SELECT A.TITLE,
       A.BOARD_ID,
       B.REPLY_ID,
       B.WRITER_ID,
       B.CONTENTS,
       DATE_FORMAT(B.CREATED_DATE, '%Y-%m-%d') AS CREATED_DATE
FROM USED_GOODS_BOARD AS A
    JOIN USED_GOODS_REPLY AS B
    ON A.BOARD_ID = B.BOARD_ID
WHERE A.CREATED_DATE LIKE '2022-10%'
ORDER BY B.CREATED_DATE, A.TITLE
;