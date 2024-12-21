-- 아이템 희귀도 'RARE'인 아이템들의 모든 다음 업그레이드 아이템의 아이템ID, 아이템 명, 아이템 희귀도 출력
-- 아이템ID 내림차순

-- 아이템 희귀도가 RARE인 아이템들을 우선 분류
-- 각 아이템 별로 다음 업그레이드 아이템이 무엇인지 조건 체크

SELECT I.ITEM_ID, I.ITEM_NAME, I.RARITY
FROM ITEM_INFO I
    JOIN ITEM_TREE T
    ON I.ITEM_ID = T.ITEM_ID
WHERE T.PARENT_ITEM_ID IN (
    SELECT ITEM_ID
    FROM ITEM_INFO
    WHERE RARITY = 'RARE'
)
ORDER BY T.ITEM_ID DESC
;