-- 코드를 입력하세요
-- 식당 ID, 식당 이름, 음식 종류, 즐겨찾기 수, 주소, 리뷰 평균 점수 조회
-- 서울에 위치해야함 / 리뷰 평균 점수는 소수점 셋째자리 반올림
-- 결과 평균 점수 내림차순 / 같으면 즐겨찾기수 내림차순
SELECT A.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, SCORE
FROM REST_INFO AS A
    JOIN (SELECT REST_ID, ROUND(AVG(REVIEW_SCORE), 2) AS SCORE
          FROM REST_REVIEW
          GROUP BY REST_ID
         ) AS B
    ON B.REST_ID = A.REST_ID
WHERE ADDRESS LIKE '서울%'
ORDER BY SCORE DESC, FAVORITES DESC
;