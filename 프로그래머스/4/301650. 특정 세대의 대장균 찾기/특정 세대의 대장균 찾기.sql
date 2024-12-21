SELECT D.ID
FROM ECOLI_DATA D
    JOIN (SELECT B.ID
          FROM ECOLI_DATA B
              JOIN (SELECT ID
                    FROM ECOLI_DATA
                    WHERE PARENT_ID IS NULL) A
              ON A.ID = B.PARENT_ID) C
    ON C.ID = D.PARENT_ID
ORDER BY D.ID
;