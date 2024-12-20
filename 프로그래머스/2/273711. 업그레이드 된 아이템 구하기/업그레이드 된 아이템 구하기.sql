-- 아이템 희귀도 'RARE'인 아이템들의 모든 다음 업그레이드 아이템의 아이템ID, 아이템 명, 아이템 희귀도 출력
-- 아이템ID 내림차순

-- 아이템 희귀도가 RARE인 아이템들을 우선 분류
-- 각 아이템 별로 다음 업그레이드 아이템이 무엇인지 조건 체크

# SELECT B.ITEM_ID,
#        (SELECT C.RARITY 
#         FROM ITEM_INFO AS C
#         WHERE B.ITEM_ID = C.ITEM_ID) AS RARITY
# FROM (SELECT ITEM_ID
#       FROM ITEM_INFO
#       WHERE RARITY = 'RARE') AS A
#     JOIN ITEM_TREE AS B
#     ON A.ITEM_ID = B.PARENT_ITEM_ID
# ORDER BY B.ITEM_ID DESC
# ;

SELECT A.ITEM_ID, C.ITEM_NAME, C.RARITY
FROM ITEM_TREE AS A
    JOIN ITEM_INFO AS B
    ON A.PARENT_ITEM_ID = B.ITEM_ID AND B.RARITY = 'RARE'
        JOIN ITEM_INFO AS C
        ON A.ITEM_ID = C.ITEM_ID
ORDER BY A.ITEM_ID DESC
;