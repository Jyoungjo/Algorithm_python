-- 우유와 요거트 동시에 구입한 장바구니 조회 / 장바구니 아이디 순
SELECT CART_ID
FROM CART_PRODUCTS
GROUP BY CART_ID
HAVING SUM (
    DISTINCT CASE WHEN NAME = 'Milk' THEN 1
    WHEN NAME = 'Yogurt' THEN 2
    ELSE 0 END
) > 2
ORDER BY 1