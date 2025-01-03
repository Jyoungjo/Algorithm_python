-- FISH_INFO ID, FISH_TYPE, LENGTH, TIME / 길이 10CM 이하 = NULL
-- FISH_NAME_INFO FISH_TYPE, FISH_NAME

-- BASS와 SNAPPER의 수를 FISH_COUNT로 출력

SELECT COUNT(*) AS FISH_COUNT
FROM FISH_INFO I
    JOIN FISH_NAME_INFO N
    ON I.FISH_TYPE = N.FISH_TYPE
WHERE N.FISH_NAME IN ('BASS', 'SNAPPER')
;